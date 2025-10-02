package vn.khanhduy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String home() {
        return "admin-home"; // admin-home.html
    }

    @GetMapping("/categories")
    public String categories() {
        return "admin-categories";
    }

    @GetMapping("/products")
    public String products() {
        return "admin-products";
    }
}
