package jwt.springboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/test")
public class TestController {

    @GetMapping("/admin")
    public String getTest() {
        return "Test Success";
    }

    @GetMapping("/super")
    @PreAuthorize(value = "hasAnyRole('ROLE_SUPER', 'ROLE_ADMIN')")
    public String getSuper() {
        return "Test super";
    }
}
