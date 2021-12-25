package com.project.neo.BabyRepository;

import com.project.neo.Baby.Baby;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//This is the data layer that interfaces directly with the local database

@Repository
public interface Babyrepository extends MongoRepository<Baby, Integer> {
    @Query(value = "{'id':?0}")
    Optional<Baby> getBabyById(Integer id);

    long count();

}
