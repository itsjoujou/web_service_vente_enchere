package app.service;

import app.model.User;
import app.repository.UserRepository;
import app.util.CustomError;
import app.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object authenticate(User user) throws Exception {
        userRepository.generateToken(user.getUsername(), user.getPassword());
        Optional<User> optionalUser = userRepository.authenticateUserWithUsernameAndHashedPassword(user.getUsername(), user.getPassword(), 2);
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found!");
        }

        return new Data(optionalUser.get());
    }

    public Object signUp(User newUser) throws Exception {
        userRepository.insertNewUser(newUser.getUsername(), newUser.getPassword());

        return authenticate(newUser);
    }
}
