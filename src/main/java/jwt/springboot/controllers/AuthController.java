package jwt.springboot.controllers;

import jwt.springboot.models.User;
import jwt.springboot.services.AuthService;
import jwt.springboot.services.JwtUserDetailsService;
import jwt.springboot.utils.JwtUser;
import jwt.springboot.utils.JwtUtil;
import jwt.springboot.utils.Token;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return authService.signUp(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtUser jwtUser) {

        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtUser.getUsername(), jwtUser.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        final String token = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new Token(token), HttpStatus.OK);
    }
}
