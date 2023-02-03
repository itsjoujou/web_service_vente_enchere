package app.web;

import app.model.User;
import app.service.UserService;
import app.util.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/authenticate")
@RestController
public class AuthenticateController {
    @Autowired
    private final UserService service;

    public  AuthenticateController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public Object AuthenticateAsUser(@RequestBody User authenticateUser) {
        try {
            return service.authenticate(authenticateUser);
        } catch (Exception e) {
            return new CustomError(e.getMessage());
        }
    }
}
