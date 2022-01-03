package com.project.neo.Security;

import com.project.neo.Role.RoleRepository;
import com.project.neo.User.UserRepository;
import com.project.neo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        String usernameOfUser = user.getUsername();
        String passwordOfUser = user.getPassword();
        return new org.springframework.security.core.userdetails.User(usernameOfUser, passwordOfUser, new ArrayList<>());
    }
}
