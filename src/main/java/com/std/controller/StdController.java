package com.std.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.std.model.Std;
import com.std.service.StdService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StdController {

    @Autowired
    private StdService service;

    // Login page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    // Registration page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("student", new Std());
        return "register";
    }

    // Save registration
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Std student) {
        service.register(student);
        return "redirect:/";
    }

    // Login logic
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Std student = service.login(email, password);

        if (student != null) {
            session.setAttribute("student", student);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Std student = (Std) session.getAttribute("student");

        if (student == null) {
            return "redirect:/";
        }

        model.addAttribute("student", student);
        return "dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
