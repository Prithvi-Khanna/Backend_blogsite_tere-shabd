package Back.Blog.controller;


import Back.Blog.repository.BlogRepository;
import Back.Blog.repository.DetailsRepository;
import Back.Blog.repository.UsersRepository;
import Back.Blog.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

