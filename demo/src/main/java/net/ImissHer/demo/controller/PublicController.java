package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.repo.UserEntryRepository;
import net.ImissHer.demo.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

@Autowired
    private UserEntryService userService ;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

}
