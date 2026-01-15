package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.service.JournalEntryService;
import net.ImissHer.demo.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JornalEntryControllerv2 {

    private Map<Long , journalEntry> entries = new HashMap<>() ;


    @Autowired
    private JournalEntryService journalEntryService ;

    @Autowired
    private UserEntryService userEntryService ;


     // read
     @GetMapping("/all")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName = authentication.getName() ;

         User user = userEntryService.findByUserName(userName);
        List<journalEntry> all =  user.getJournalEntries();

        if(all!=null || !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping
    public ResponseEntity<journalEntry> createEntry( @RequestBody journalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;

         try{
             journalEntryService.saveEntry(entry , userName);

             return new ResponseEntity<>(entry , HttpStatus.CREATED) ;
         } catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }

     }

       // get by id
    @GetMapping("/{id}")
    public ResponseEntity<journalEntry> getbyId(@PathVariable ObjectId id){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;

         User user = userEntryService.findByUserName(userName);

          List<journalEntry> collect =  user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

          if(!collect.isEmpty()){
              Optional<journalEntry> entry =  journalEntryService.findbyId(id);

              if(entry.isPresent()){
                  return new ResponseEntity<>(entry.get() , HttpStatus.OK);
              }
          }
          return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable ObjectId id ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;

         journalEntryService.deletebyId(id , userName);
        return true ;

    }


    @PutMapping("id/{id}")
    public ResponseEntity<?> updateUsers( @RequestBody journalEntry newEntry , @PathVariable ObjectId id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName() ;

        User user = userEntryService.findByUserName(userName);

        List<journalEntry> collect =  user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());


        if(!collect.isEmpty()){
            Optional<journalEntry> entry =  journalEntryService.findbyId(id);
            if(entry.isPresent()){

                journalEntry old = entry.get();

                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());

                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
