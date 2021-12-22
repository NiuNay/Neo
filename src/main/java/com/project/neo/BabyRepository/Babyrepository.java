package com.project.neo.BabyRepository;

import com.project.neo.Baby.Baby;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Babyrepository extends MongoRepository<Baby, String> {


}
