package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JornalEntryController {

    private Map<Long , journalEntry> entries = new HashMap<>() ;


     // read
     @GetMapping("/users")
    public ArrayList<journalEntry> getall(){
      return new ArrayList<>(entries.values()) ;
    }

    //  create
//    @PostMapping("/create")
//    public boolean create( @RequestBody journalEntry user){
//        entries.put(user.getId() ,user);
//        return true ;
//     }

      // get by id read
    @GetMapping("/user/{id}")
    public journalEntry getbyId(@PathVariable String id){
         return entries.get(id) ;
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
         entries.remove(id) ;
         return true ;

    }


    @PutMapping("/update/{id}")
    public boolean updateUsers(@PathVariable Long id , @RequestBody journalEntry user){
        entries.put(id , user) ;
        return true ;
    }

}
