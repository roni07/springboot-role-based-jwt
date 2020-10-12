package jwt.springboot.controllers;

import jwt.springboot.models.Role;
import jwt.springboot.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jwt/role")
@AllArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @PostMapping("/create")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @GetMapping("/list")
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }
}
