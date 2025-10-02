package vn.khanhduy.controller.interceptor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vn.khanhduy.entities.UserEntity;
import vn.khanhduy.services.IUserService;

@Controller
public class LoginController {

	@Autowired
	IUserService userService;
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		Optional<UserEntity> user = userService.findByUsername(username);
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			session.setAttribute("username", username);
			session.setAttribute("role", user.get().getRole().getName());
			
            if ("ADMIN".equals(user.get().getRole().getName())) {
                return "redirect:/admin/home";
            } else if ("USER".equals(user.get().getRole().getName())) {
                return "redirect:/user/home";
            } else {
                return "redirect:/login";
            }
		}
		return "redirect:/login?error"; 
	}

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
