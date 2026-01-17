package net.ImissHer.demo.controller;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {

        List<User> all = userEntryService.getAll();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/creating-admin-user")
    public void addUsers(@RequestBody User user) {
        try {
            userEntryService.saveAdmin(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
