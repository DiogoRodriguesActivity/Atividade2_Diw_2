package com.example.controller;

import com.example.application.User;
import com.example.application.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
        return "redirect:/login?success";
    }

    @GetMapping("/recoverpassword")
    public String recoverPasswordPage() {
        return "recoverPassword";
    }

    @PostMapping("/recoverpassword")
    public String processRecoverPassword(String email, Model model) {
        // Lógica simulada de envio de email de recuperação
        model.addAttribute("message", "Se o email existir, instruções foram enviadas.");
        return "recoverPassword";
    }
    
    @GetMapping("/home")
    public String homePage() {
        return "home"; // Página protegida após o login
    }
}