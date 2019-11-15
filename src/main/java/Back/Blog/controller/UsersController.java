package Back.Blog.controller;

import Back.Blog.model.Users;
import Back.Blog.repository.UsersRepository;
import Back.Blog.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping( value = "/users")
public class UsersController {

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    UsersRepository UsersRepo;

    @GetMapping("/get1")
    public List<Users> getAllNotes() {
        return UsersRepo.findAll();
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public Users validateLogin() {
        return new Users("User successfully authenticated");
    }

    @GetMapping("/get_user/{username}")
    public Optional<Users> getUser(@PathVariable( value = "username") String name)
    {
        return UsersRepo.findByUsername(name);
    }

    @PostMapping("/post1")
    public Users createNote(@Valid @RequestBody Users user1) {
        user1.setRole("User");
        user1.setActive(1);
        return UsersRepo.save(user1);
    }

    @GetMapping("/get1/{id}")
    public Optional<Users> getNoteById(@PathVariable(value = "id") Long noteId) {
        return UsersRepo.findById(noteId);

        //To HAndle Error        .orElseThrow(() -> new NotFound("Note", "id", noteId));
    }


    @PutMapping("/put_user")
    public Users putting(@Valid @RequestBody Users user , Principal principal)
    {
        Users user1 = currentUserService.CurrentUser(principal).get();
        //user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        user1.setPhone(user.getPhone());
        //user1.setPassword(user.getPassword());
        user1.setActive(1);
        return UsersRepo.save(user1);
    }

    @GetMapping("/get_role/{username}")
    public String getrole(@PathVariable( value = "username") String name,Principal principal)
    {
        return currentUserService.getuserrole(principal);
    }

    @GetMapping("/searchUser/{search1}")
    public List<Users> search1(@PathVariable ( value = "search1") String name)
    {
        List <Users> list1 = UsersRepo.findAllByUsernameContaining(name);
        if(list1.size() == 0)
        {
            Users u1 = new Users();
            u1.setUsername("null");
            list1.add(u1);
        }
        return list1;
    }
}
