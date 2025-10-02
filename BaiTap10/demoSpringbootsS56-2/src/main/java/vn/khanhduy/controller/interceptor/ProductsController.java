package vn.khanhduy.controller.interceptor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.khanhduy.entities.CategoryEntity;
import vn.khanhduy.entities.ProductEntity;
import vn.khanhduy.entities.UserEntity;
import vn.khanhduy.model.ProductModel;
import vn.khanhduy.services.ICategoryService;
import vn.khanhduy.services.IProductService;
import vn.khanhduy.services.IUserService;

@Controller
@RequestMapping("admin/product")
public class ProductsController {

	@Autowired
	IProductService productService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("add")
	public String add(ModelMap model) {
		ProductModel productModel = new ProductModel();
		productModel.setEdit(false);

		// chuyen data tu model vao bien de data len view
		model.addAttribute("product", productModel);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("users", userService.findAll());
		return "admin/product/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(@Valid ModelMap model, @Valid @ModelAttribute("product") ProductModel productModel,
			BindingResult result) {
		if (result.hasErrors()) {
			//cần nạp lại danh sách users, categories khi có lỗi để render lại select box
	        model.addAttribute("users", userService.findAll());
	        model.addAttribute("categories", categoryService.findAll());
	        
	        //đồng thời add lại chính productModel vào model để giữ dữ liệu nhập
	        model.addAttribute("product", productModel);
			return new ModelAndView("admin/product/addOrEdit", model);
		}
		ProductEntity entity = new ProductEntity();
		// copy tu model sang entity
		BeanUtils.copyProperties(productModel, entity);
		
		if (productModel.getUserId() != null) {
	        UserEntity user = userService.findById(productModel.getUserId())
	                                     .orElse(null);
	        entity.setUser(user);
	    }
		
		if (productModel.getCategoryId() != null) {
	        CategoryEntity category = categoryService.findById(productModel.getCategoryId())
	                                     .orElse(null);
	        entity.setCategory(category);
	    }
		
		// goi ham save trong service
		productService.save(entity);
		// dua thong bao ve cho bien message
		String message = "";
		if (productModel.isEdit()) {
			message = "Product is Edited!!!!!";
		} else {
			message = "Product is Saved!!!!!!";
		}
		model.addAttribute("message", message);
		// redirect về url controller
		return new ModelAndView("redirect:/admin/product/searchpaginated?page=1&size=3", model);
	}

	@RequestMapping("list")
	public String list(ModelMap model) {

		// gọi hàm file all trong service
		List<ProductEntity> list = productService.findAll();

		// chuyển dữ liệu từ list lên biến products
		model.addAttribute("products", list);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/product/list";
	}

	@GetMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<ProductEntity> opt = productService.findById(productId);
		ProductModel productModel = new ProductModel();
		// kiểm tra sự tồn tại của category
		if (opt.isPresent()) {
			ProductEntity entity = opt.get();
			// copy từ entity sang model
			BeanUtils.copyProperties(entity, productModel);
			productModel.setEdit(true);
			// đẩy dữ liệu ra view
			model.addAttribute("product", productModel);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("users", userService.findAll());
			return new ModelAndView("admin/product/addOrEdit", model);
		}

		model.addAttribute("message", "Product is not existed!!!");
		return new ModelAndView("forward:/admin/product", model);
	}

	@GetMapping("delete/{productId}")
	public ModelAndView delete(ModelMap model, @PathVariable("productId") Long productId) {
		productService.deleteById(productId);
		model.addAttribute("message", "Product is Deleted");
		return new ModelAndView("forward:/admin/product/searchpaginated", model);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<ProductEntity> list = null;
		// có nội dung truyền về hoặc không (name là tùy chọn)
		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
		} else {
			list = productService.findAll();
		}
		model.addAttribute("products", list);
		return "admin/product/search";
	}

	@RequestMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int count = (int) productService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<ProductEntity> resultPage = null;
		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = productService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			/*
			 * if (totalPages > 5) { if (end == totalPages) { start = end - 4; } else if
			 * (start == 1) { end = start + 4; } }
			 */
			if (totalPages > count) {
				if (end == totalPages) {
					start = end - count;
				} else if (start == 1) {
					end = start + count;
				}
			}
			/*
			 * if (totalPages > count) {
			 * 
			 * if (end == totalPages) start = end - count; else if (start == 1) end = start
			 * + count;
			 * 
			 * 
			 * // hiển thị tối đa 5 số trang if (totalPages > 5) { if (end == totalPages)
			 * start = end - 4; else if (start == 1) end = start + 4; } }
			 * 
			 */
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);

		}
		model.addAttribute("productPage", resultPage);
		return "admin/product/searchpaginated";
	}

	@GetMapping("view/{productId}")
	public ModelAndView view(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<ProductEntity> opt = productService.findById(productId);

		if (opt.isPresent()) {
			model.addAttribute("product", opt.get());
			return new ModelAndView("admin/product/view", model);
		}

		model.addAttribute("message", "Product not found!");
		return new ModelAndView("redirect:/admin/product/searchpaginated?page=1&size=3");
	}
}
