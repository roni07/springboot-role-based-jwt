package jwt.springboot.services;

import jwt.springboot.models.User;
import jwt.springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }
}
