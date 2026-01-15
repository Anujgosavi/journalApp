package net.ImissHer.demo.service;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.repo.JournalEntryRepository;
import net.ImissHer.demo.repo.UserEntryRepository;
import org.bson.types.ObjectId;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository ; // dependency injection

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowire the bean

    public void saveEntry(User user){
        // Only encode if password is not already encoded
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.startsWith("$2a$")) { // BCrypt hash starts with $2a$
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        user.setRoles(Arrays.asList("USER"));
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

    public void saveAdmin(User user) {
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        // Store both roles as they should appear in database
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userEntryRepository.save(user);
    }
}


//  controller --> service -->> repository