package vn.khanhduy.controller.interceptor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.khanhduy.services.ICategoryService;

@Controller
public class AdminController {

	@Autowired
	ICategoryService categoryService;

	@GetMapping("/admin/home")
	public String adminHome() {
		return "admin/admin-home";
	}

	@GetMapping("/admin/category")
	public ModelAndView manageCategory(ModelMap model) {
		//return "admin/category/list";
		return new ModelAndView("redirect:/admin/category/searchpaginated?page=1&size=3", model);
	}

	@GetMapping("/admin/product")
	public ModelAndView manageProduct(ModelMap model) {
		//return "admin/product";
		return new ModelAndView("redirect:/admin/product/searchpaginated?page=1&size=3", model);
	}

}
