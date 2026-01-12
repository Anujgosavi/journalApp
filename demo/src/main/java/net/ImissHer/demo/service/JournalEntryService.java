package net.ImissHer.demo.service;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    @Autowired
    private UserEntryService userEntryService ;// dependency injection

    public void saveEntry(journalEntry  entry, String userName){
        entry.setDate(LocalDateTime.now());
        User user = userEntryService.findByUserName(userName);
        journalEntry saved = journalEntryRepository.save(entry);
        user.getJournalEntries().add(saved);
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

    public void deletebyId(ObjectId id, String userName){
        User user = userEntryService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userEntryService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}


//  controller --> service -->> repository