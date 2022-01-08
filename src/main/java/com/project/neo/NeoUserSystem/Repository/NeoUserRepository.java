package com.project.neo.NeoUserSystem.Repository;

import com.project.neo.NeoUserSystem.NeoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeoUserRepository extends MongoRepository<NeoUser, Long> {
    NeoUser findByUsername(String username);
}
