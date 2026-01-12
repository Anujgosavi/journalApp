package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.UserEntryRepository;
import net.ImissHer.demo.service.JournalEntryService;
import net.ImissHer.demo.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {
     @Autowired
     UserEntryService userService ;

     @GetMapping("/all")
     public List<User> getAllUser(){
        return  userService.getAll() ;

     }

     @PostMapping ("/create")
    public void createUser(@RequestBody User user){
         userService.saveEntry(user);
     }

    @PutMapping("/{userName}")
   ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);

        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

