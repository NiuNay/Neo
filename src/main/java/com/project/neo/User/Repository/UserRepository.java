package com.project.neo.User.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.neo.User.Model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{'id':?0}")
    Optional<User> getUserById(Integer id);

    boolean existsById(Integer s);

    void deleteById(Integer s);

    long count();
}
