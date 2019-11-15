package Back.Blog.controller;


import Back.Blog.model.Blog;
import Back.Blog.model.Followers;
import Back.Blog.model.Users;
import Back.Blog.repository.BlogRepository;
import Back.Blog.repository.FollowersRepo;
import Back.Blog.repository.UsersRepository;
import Back.Blog.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping( value = "/follow")
public class FollowersController {

    @Autowired
    FollowersRepo FollowRepo;
     @Autowired
    UsersRepository UsersRepo;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    BlogRepository BlogRepo;

     @GetMapping("/add_follower/{username}")
    public Followers add(@PathVariable ( value = "username") String name , Principal principal)
     {
         Followers f = new Followers();
         Users following = UsersRepo.findByUsername(name).get();
         Users follower = currentUserService.CurrentUser(principal).get();
         f.setFollower(follower);
         f.setFser1(following);
         following.setFollowers(following.getFollowers() + 1);
         follower.setFollowing(follower.getFollowing() + 1);
         return FollowRepo.save(f);
     }

     @GetMapping("/check_follower")
     public List<Followers> check(Principal principal)
     {
         Users follower = currentUserService.CurrentUser(principal).get();
         return FollowRepo.findAllByFollower(follower);


     }

    @GetMapping("/remove_follower/{username}")
    public void remove(@PathVariable ( value = "username") String name , Principal principal)
    {
        Users following = UsersRepo.findByUsername(name).get();
        Users follower = currentUserService.CurrentUser(principal).get();
        follower.setFollowing(follower.getFollowing() - 1);
        following.setFollowers(following.getFollowers() - 1);
        Followers f1 = FollowRepo.findByFollowerAndFser1(follower , following);
        FollowRepo.delete(f1);
    }

    @GetMapping("/get_followers")
    public Set<Blog> getFollowers(Principal principal)
    {
        Users follower = currentUserService.CurrentUser(principal).get();
        List<Followers> f1 = FollowRepo.findAllByFollower(follower);
        Set<Blog> b1 = new HashSet<Blog>();
        for( int i=0 ; i<f1.size() ;i++)
        {
            b1.addAll(BlogRepo.findAllByUserOrderByDate1Desc(f1.get(i).getFser1()));
        }
        return b1;
    }
}
