package com.project.neo.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.neo.User.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsById(Integer s);

    User findByUsername(String username);

    void deleteById(Integer s);

    long count();
}
