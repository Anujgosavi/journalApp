package net.ImissHer.demo.service;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    @Autowired
    private UserEntryService userEntryService ;// dependency injection


     @Transactional //  meaning all the operations succeeds or nothing happens
    public void saveEntry(journalEntry  entry, String userName){
        entry.setDate(LocalDateTime.now());
        User user = userEntryService.findByUserName(userName);
        journalEntry saved = journalEntryRepository.save(entry);

        //  good now suppose the gets saved in journalEntry and something bad happens just after that
        //  means it will not get stored in userEntries
        //  thats why this is not consistent
        //  ex of this is
        user.getJournalEntries().add(saved);
//        user.setUserName(null);

        //  now the user is null so it will not store that entry with res to that user but it will store the
        //  entry in entries db
        userEntryService.saveEntry(user);
    }

    //  method overloading
    public void saveEntry(journalEntry  entry){
        journalEntryRepository.save(entry);
    }


    public List<journalEntry> getAll(){
        return journalEntryRepository.findAll() ;
    }

    public Optional<journalEntry> findbyId(ObjectId myid){
        return journalEntryRepository.findById(myid) ;
    }


    @Transactional
    public void deletebyId(ObjectId id, String userName) {
         try {


             User user = userEntryService.findByUserName(userName);
             boolean flag = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
             if (flag) {
                 userEntryService.saveEntry(user);
                 journalEntryRepository.deleteById(id);
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

//    public List<journalEntry> findByUserName(String userName){
//
//    }

}


//  controller --> service -->> repository