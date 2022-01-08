package com.project.neo.NeoUserSystem;

import java.util.List;

public interface NeoUserService {
    NeoUser saveUser(NeoUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    NeoUser getUser(String username);
    List<NeoUser> getUsers();
}
