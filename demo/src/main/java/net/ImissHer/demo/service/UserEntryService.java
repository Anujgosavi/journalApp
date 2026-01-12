package net.ImissHer.demo.service;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.JournalEntryRepository;
import net.ImissHer.demo.repo.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository ; // dependency injection

    public void saveEntry(User user){
        userEntryRepository.save(user);
    }
    public List<User> getAll(){
        return userEntryRepository.findAll() ;
    }

    public Optional<User> findbyId(ObjectId myid){
        return userEntryRepository.findById(myid) ;
    }

    public void deletebyId(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findByUserName(String username){
      return userEntryRepository.findByUserName(username) ;
    }

}


//  controller --> service -->> repository