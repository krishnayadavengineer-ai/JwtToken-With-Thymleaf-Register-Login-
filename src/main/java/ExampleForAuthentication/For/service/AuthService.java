package ExampleForAuthentication.For.service;

import ExampleForAuthentication.For.DTO.LoginRequest;
import ExampleForAuthentication.For.Repo.UserRepo;
import ExampleForAuthentication.For.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public void register(LoginRequest req) {

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));

        repo.save(user);
    }


}