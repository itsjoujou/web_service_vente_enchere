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

    public Object authenticate(User user) {
        userRepository.generateToken(user.getUsername(), user.getPassword());
        Optional<User> optionalUser = userRepository.authenticateUserWithUsernameAndHashedPassword(user.getUsername(), user.getPassword(), 2);

        return optionalUser.isPresent() ? new Data(optionalUser.get()) : new CustomError("No user found!");
    }

    public void signUp(User newUser) {
        userRepository.insertNewUser(newUser.getUsername(), newUser.getPassword());
    }
}
