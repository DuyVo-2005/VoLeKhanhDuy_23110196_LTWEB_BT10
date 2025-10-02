package vn.khanhduy.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.khanhduy.entities.Category;
import vn.khanhduy.repository.CategoryRepository;
import vn.khanhduy.services.ICategoryService;

@Controller
public class CategoryController {

	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@QueryMapping(name = "categories")//tranh trung
	public List<Category> findAllCategories(){
		return categoryService.findAll();
	}
	
	//lay id edit tren html
	@QueryMapping
	public Category category(@Argument Integer id) {
	    return categoryRepository.findById(id)
	             .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	
	@MutationMapping
	public Category createCategory(@Argument String images, @Argument String name) {
		Category category = new Category();
		category.setName(name);
		category.setImages(images);
		return categoryService.save(category);
	}
	
	@MutationMapping
	public Category updateCategory(@Argument Integer id, @Argument String images, @Argument String name) {
		Optional<Category> category = categoryService.findById(id);
		if(category.isPresent()) {
			if(name != null) {
				category.get().setName(name);
			}
			if(images != null) {
				category.get().setImages(images);
			}
			return categoryService.save(category.get());
		}
		return null;
	}
	
	@MutationMapping
	public Boolean deleteCategory(@Argument Integer id) {
		categoryService.deleteById(id);
		return true;
	}
}
