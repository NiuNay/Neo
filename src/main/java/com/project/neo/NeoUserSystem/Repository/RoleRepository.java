package com.project.neo.NeoUserSystem.Repository;

import com.project.neo.NeoUserSystem.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Role findByName(String name);
}
