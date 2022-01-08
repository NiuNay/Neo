package com.project.neo.NeoUserSystem;

import com.project.neo.NeoUserSystem.Repository.NeoUserRepository;
import com.project.neo.NeoUserSystem.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j // Slf4j - for logging
public class NeoUserServiceImplementation implements NeoUserService, UserDetailsService {
    private final NeoUserRepository neoUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NeoUser user = neoUserRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public NeoUser saveUser(NeoUser user) {
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return neoUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        NeoUser user = neoUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public NeoUser getUser(String username) {
        log.info("Fetching user {}", username);
        return neoUserRepository.findByUsername(username);
    }

    @Override
    public List<NeoUser> getUsers() {
        log.info("Fetching all users");
        return neoUserRepository.findAll();
    }

}
