package app.web;

import app.model.User;
import app.service.UserService;
import app.util.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new_account")
    public Object signUp(@RequestBody User newUser) {
        try {
            System.out.println(newUser.getUsername());
            return userService.signUp(newUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new CustomError(e.getMessage());
        }
    }
}
