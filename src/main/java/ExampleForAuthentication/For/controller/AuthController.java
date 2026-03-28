package ExampleForAuthentication.For.controller;

import ExampleForAuthentication.For.DTO.LoginRequest;
import ExampleForAuthentication.For.DTO.RegisterRequest;
import ExampleForAuthentication.For.security.JwtUtils;
import ExampleForAuthentication.For.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginRequest request,
                        HttpServletResponse response) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtils.generateToken(request.getUsername());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

//    @PostMapping("/register")
//    public String register(RegisterRequest request) {
//        authService.register(request);
//        return "redirect:/login";
//    }
}