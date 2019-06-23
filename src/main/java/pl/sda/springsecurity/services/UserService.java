package pl.sda.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.springsecurity.model.User;
import pl.sda.springsecurity.model.UserRole;
import pl.sda.springsecurity.repositories.UserRepository;
import pl.sda.springsecurity.repositories.UserRoleRepository;

@Service
public class UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUserWithDefaultRole(User user){
        UserRole roleUser = userRoleRepository.findByRole(ROLE_USER);
        user.getRoles().add(roleUser);

        String passwordHash = passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordHash);
        userRepository.save(user);
    }
}
