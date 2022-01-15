package com.project.neo.NeoUserSystem;

import java.util.List;

/* Reference 1 - taken from https://www.youtube.com/watch?v=VVn9OG9nfH0 */
public interface NeoUserService {
    NeoUser saveUser(NeoUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    NeoUser getUser(String username);
    List<NeoUser> getUsers();
}
/* end of Reference 1 */

