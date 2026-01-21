package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.repo.UserEntryRepository;
import net.ImissHer.demo.repo.UserRepositoryImplementation;
import net.ImissHer.demo.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

@Autowired
    private UserEntryService userService ;

@Autowired
private UserRepositoryImplementation userRepositoryImplementation ;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @GetMapping("getUserwithSA")
    public List<User> getUserswithSa(){
        return userRepositoryImplementation.getUserBySA() ;
    }

}
