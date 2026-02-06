package net.ImissHer.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.ImissHer.demo.Utils.JwtUtil;
import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.repo.UserEntryRepository;
import net.ImissHer.demo.repo.UserRepositoryImplementation;
import net.ImissHer.demo.service.UserDetailServiceImp;
import net.ImissHer.demo.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
@Tag(name = "PUBLIC APIs" , description = "For login and sign up")
public class PublicController {

@Autowired
    private UserEntryService userService ;

@Autowired
private UserRepositoryImplementation userRepositoryImplementation ;

@Autowired
private AuthenticationManager authenticationManager ;
@Autowired
private UserDetailServiceImp userDetailServiceImp ;

@Autowired
private JwtUtil jwtUtil ;
    @PostMapping("/signup")
    public void SignUp(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){

        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername()) ;

            return  new ResponseEntity<>( jwt , HttpStatus.OK) ;
        }
        catch (Exception e) {
            log.error("Not found");
            return new ResponseEntity<>("UserName not Found", HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("getUserwithSA")
    public List<User> getUserswithSa(){
        return userRepositoryImplementation.getUserBySA() ;
    }

}
