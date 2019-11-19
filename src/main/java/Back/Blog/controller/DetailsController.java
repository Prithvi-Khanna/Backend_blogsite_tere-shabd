package Back.Blog.controller;


import Back.Blog.model.Blog;
import Back.Blog.model.Details;
import Back.Blog.model.Users;
import Back.Blog.repository.BlogRepository;
import Back.Blog.repository.DetailsRepository;
import Back.Blog.repository.UsersRepository;
import Back.Blog.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping( value = "/details")
public class DetailsController {

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    UsersRepository UsersRepo;

    @Autowired
    DetailsRepository DetailsRepo;

    @Autowired
    BlogRepository BlogRepo;

    @GetMapping("/get_likes/{id}")
    public Details getlikes(@PathVariable( value = "id") Long id ,Principal principal)
    {
       Users u1 = currentUserService.CurrentUser(principal).get();
       Blog b1 = BlogRepo.findById(id).get();
       if( DetailsRepo.findByBlogAndUsers(b1, u1).isPresent() )
       {
           return DetailsRepo.findByBlogAndUsers(b1, u1).get();
       }
       else {
           Details d1 = new Details();
           d1.setLikes(false);
           d1.setDislikes(false);
           d1.setComment("");

           return d1;
       }
    }

    @PostMapping("/like")
    public Details like(@Valid @RequestBody Details d1 , Principal principal)
    {
        Users user = currentUserService.CurrentUser(principal).get();
        Blog blog = BlogRepo.findById(d1.getBlog().getBlogId()).get();
        if(DetailsRepo.findByBlogAndUsers(blog, user).isPresent())
        {
            Details det = DetailsRepo.findByBlogAndUsers(blog, user).get();
            if(det.getDislikes() == d1.getDislikes())
            {

            }
            else
            {
                if(d1.getDislikes() == true)
                {
                    blog.setDislikes(blog.getDislikes() + 1);
                }
                else
                {
                    blog.setDislikes(blog.getDislikes() - 1);
                }
            }

            if( det.getLikes() == d1.getLikes())
            {

            }
            else
            {
                if(d1.getLikes() == true)
                {
                    blog.setLikes(blog.getLikes() + 1);
                }
                else
                {
                    blog.setLikes(blog.getLikes() -1);
                }
            }
            BlogRepo.save(blog);
            det.setLikes(d1.getLikes());
            det.setDislikes(d1.getDislikes());

          return DetailsRepo.save(det);
        }
        else
        {
            if(d1.getLikes() == true)
            {
                blog.setLikes(1);
            }
            else
            {
                blog.setDislikes(1);
            }
            BlogRepo.save(blog);
            Details det = new Details();
            det.setDislikes(d1.getDislikes());
            det.setLikes(d1.getLikes());
            det.setBlog(d1.getBlog());
            det.setComment("");
            det.setUsers(user);
            DetailsRepo.save(det);

            return det;
        }

    }

    @PostMapping("/comment")
    public String comment(@Valid @RequestBody Details d1 , Principal principal)
    {
        Users user = currentUserService.CurrentUser(principal).get();
        Blog blog = BlogRepo.findById(d1.getBlog().getBlogId()).get();
        if(DetailsRepo.findByBlogAndUsers(blog, user).isPresent())
        {
            blog.setComments( blog.getComments() + 1);
            BlogRepo.save(blog);
            Details det = DetailsRepo.findByBlogAndUsers(blog, user).get();
            det.setComment(d1.getComment());
            DetailsRepo.save(det);
            return "\"UPDATED\"";
        }
        else
        {
            blog.setComments( blog.getComments() + 1);
            BlogRepo.save(blog);
            Details det = new Details();
            det.setDislikes(false);
            det.setLikes(false);
            det.setBlog(d1.getBlog());
            det.setComment(d1.getComment());
            det.setUsers(user);
            DetailsRepo.save(det);

            return "\"SAVED\"";
        }

    }
}

