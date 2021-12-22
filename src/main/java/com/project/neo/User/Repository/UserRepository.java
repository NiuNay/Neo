package com.project.neo.User.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.neo.User.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
