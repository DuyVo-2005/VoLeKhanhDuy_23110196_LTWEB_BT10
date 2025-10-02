package vn.khanhduy.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.khanhduy.entities.User;
import vn.khanhduy.services.IUserService;

@Controller
public class LoginController {

	@Autowired
	IUserService userService;

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		Optional<User> user = userService.findByEmail(email);
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			session.setAttribute("email", email);
			session.setAttribute("role", user.get().getRole().getName());
			
			// điều hướng theo role
            if ("ADMIN".equals(user.get().getRole().getName())) {
                return "redirect:/admin/home";
            } else if ("USER".equals(user.get().getRole().getName())) {
                return "redirect:/user/home";
            } else {
                return "redirect:/login";
            }
		}
		return "redirect:/login?error"; //sai username/password
	}

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
