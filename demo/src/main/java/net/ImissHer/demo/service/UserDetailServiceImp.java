package net.ImissHer.demo.service;

import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.repo.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailServiceImp implements UserDetailsService {


    @Autowired
    private  UserEntryRepository userEntryRepository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntryRepository.findByUserName(username);

        if(user!=null){
            UserDetails userDetails =  org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])) // will craete a new Array
                    .build();

            return userDetails;

        }
       throw new UsernameNotFoundException("User Not found with username " +  username) ;
    }
}
