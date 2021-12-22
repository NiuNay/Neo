package com.project.neo.User.Service;

import com.project.neo.User.Repository.UserRepository;
import com.project.neo.User.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository user_repository;

    @Autowired
    public UserService(UserRepository user_repository) {
        this.user_repository = user_repository;
    }

    public List<User> returnUser() {
        return user_repository.findAll();
    }
}
