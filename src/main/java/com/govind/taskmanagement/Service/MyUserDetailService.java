package com.govind.taskmanagement.Service;

import com.govind.taskmanagement.Model.UserPrincipal;
import com.govind.taskmanagement.Model.Users;
import com.govind.taskmanagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if(users == null){
            System.out.println("user not found!!");
            throw new UsernameNotFoundException("user not found!!");
        }

        return new UserPrincipal(users);
    }

    public Boolean addUser(Users users) {
        String hashPass = passwordEncoder.encode(users.getPassword());
        users.setPassword(hashPass);
        userRepo.save(users);
        return userRepo.existsById(users.getId());
    }
}
