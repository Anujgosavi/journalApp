package net.ImissHer.demo.controller;

import net.ImissHer.demo.WeatherResponse.WeatherResp;
import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.UserEntryRepository;
import net.ImissHer.demo.service.JournalEntryService;
import net.ImissHer.demo.service.UserEntryService;
import net.ImissHer.demo.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

     @Autowired
    WeatherService weatherService ;

     @Autowired
     private  UserEntryRepository userEntryRepository ;

     @Autowired
     private WeatherResp weatherResp ;

     @GetMapping("")
     public List<User> getAllUser(){
        return  userService.getAll() ;

     }



    @PutMapping("")
   ResponseEntity<?> updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        String userName = authentication.getName() ;
        User userInDb = userService.findByUserName(userName);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        System.out.println("Deleting user: " + username);


        userEntryRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/w")
    ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        WeatherResp res = weatherService.getWeather("Pune") ;
        return new ResponseEntity("Hii " + authentication.getName() + "feels like " +  res.getWeatherDescription()   , HttpStatus.OK) ;
    }
}

