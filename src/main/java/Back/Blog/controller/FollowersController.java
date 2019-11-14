package Back.Blog.controller;


import Back.Blog.repository.FollowersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping( value = "/follow")
public class FollowersController {

    @Autowired
    FollowersRepo FollowRepo;

}
