package com.project.neo.BabyRepository;

import com.project.neo.Baby.Baby;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//This is the data layer that interfaces directly with the local database

@Repository
public interface Babyrepository extends MongoRepository<Baby, Integer> {

}
