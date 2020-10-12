package jwt.springboot;

import jwt.springboot.models.Role;
import jwt.springboot.models.User;
import jwt.springboot.repositories.RoleRepository;
import jwt.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class InitialSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");

        User user = new User();
        user.setUsername("user");
        user.setPassword("admin");

        Role superUser = new Role();
        superUser.setRoleId(1);
        superUser.setRoleName("SUPER");

        Role adminRole = new Role();
        adminRole.setRoleId(2);
        adminRole.setRoleName("ADMIN");

        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            log.info("Initial user already exist.");
        } else {
            List<Role> roleList = roleRepository.saveAll(Arrays.asList(superUser, adminRole));

            admin.setRoleList(roleList);

            user.setRoleList(Collections.singletonList(roleList.get(1)));

            userRepository.saveAll(Arrays.asList(admin, user));
            log.info("Initial user saved.");
        }
    }
}
