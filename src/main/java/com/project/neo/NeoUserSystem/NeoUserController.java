package com.project.neo.NeoUserSystem;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/* Reference 1 - taken from https://www.youtube.com/watch?v=VVn9OG9nfH0 */
@RestController
@RequestMapping("/user-api")
@RequiredArgsConstructor
public class NeoUserController {
    private final NeoUserService neoUserService;

    @GetMapping("/users")
    public ResponseEntity<List<NeoUser>>getUsers() {
        return ResponseEntity.ok().body(neoUserService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<NeoUser>saveUser(@RequestBody NeoUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(neoUserService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(neoUserService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form) {
        neoUserService.addRoleToUser(form.getUsername(), form.getUsername());
        return ResponseEntity.ok().build();
    }

    @Data
    static
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
/* end of Reference 1 */
