package Back.Blog.controller;

import Back.Blog.model.Blog;
import Back.Blog.model.Users;
import Back.Blog.repository.BlogRepository;
import Back.Blog.repository.UsersRepository;
import Back.Blog.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping( value = "/blog" , method = { RequestMethod.GET , RequestMethod.POST})
public class BlogController {

    @Autowired
    BlogRepository BlogRepo;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    UsersRepository UsersRepo;

    @GetMapping("/get1")
    public List<Blog> getAllNotes() {
        return BlogRepo.findAll();
    }


    @GetMapping("/get_userblog")
    public List<Blog> userblog(Principal principal)
    {
        Users user = currentUserService.CurrentUser(principal).get();
        return BlogRepo.findAllByUser(user);
    }

    @GetMapping("/get_viewblog/{BlogId}")
    public Blog myblog(@PathVariable ( value = "BlogId") Long id)
    {
        return BlogRepo.findById(id).get();
    }

    @GetMapping("/searchblog/{description}")
    public Set<Blog> search(@PathVariable ( value = "description") String description1 , Principal principal)
    {
        Users user1 = currentUserService.CurrentUser(principal).get();
        List<Blog> list1 = BlogRepo.findAllByDescriptionContainingOrderByDate1Desc(description1);
        list1.addAll(BlogRepo.findAllByTitleContainingOrderByDate1Desc(description1));
        Set<Blog> hSet = new HashSet<Blog>();
        for (Blog x : list1)
            hSet.add(x);

        return hSet;
    }

    @GetMapping("/view_user/{username}")
    public List<Blog> viewUser(@PathVariable (value = "username") String name)
    {
        Users user = UsersRepo.findByUsername(name).get();
        List<Blog> list1 = BlogRepo.findAllByUserAndPrivOrderByDate1Desc(user , false);
        return list1;
    }

    @PostMapping("/post1")
    public Blog createNote(@Valid @RequestBody Blog user1 , Principal principal) {

        user1.setdate1();
        user1.setUser(currentUserService.CurrentUser(principal).get());
        return BlogRepo.save(user1);
    }

    @PutMapping("/put_blog")
    public Blog putting(@Valid @RequestBody Blog blog , Principal principal)
    {
        Blog blog1 = BlogRepo.findById(blog.getBlogId()).get();
        blog1.setDescription(blog.getDescription());
        blog1.setTitle(blog.getTitle());
        blog1.setPriv(blog.getPriv());
        blog1.setBlogId(blog.getBlogId());
        return BlogRepo.save(blog1);
    }

}
