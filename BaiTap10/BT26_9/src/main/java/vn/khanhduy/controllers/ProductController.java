package vn.khanhduy.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.khanhduy.entities.Product;
import vn.khanhduy.services.IProductService;

@Controller
public class ProductController {

	@Autowired
	IProductService productService;

	@QueryMapping(name = "products")
	public List<Product> products() {
		return productService.findAll();
	}

	@QueryMapping
	public Optional<Product> productById(@Argument Integer id) {
		return productService.findById(id);
	}

	@QueryMapping
	public List<Product> findAllByOrderByPriceAsc() {
		return productService.findAllByOrderByPriceAsc();
	}

	@QueryMapping
	public List<Product> findProductsByCategoryId(@Argument Integer categoryId) {
		return productService.findProductsByCategoryId(categoryId);
	}

	@MutationMapping
	public Product createProduct(@Argument String description, @Argument BigDecimal price, @Argument Integer quantity,
			@Argument String title) {
		Product product = new Product();
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setTitle(title);
		return productService.save(product);
	}

	@MutationMapping
	public Product updateProduct(@Argument Integer id, @Argument String description, @Argument BigDecimal price,
			@Argument Integer quantity, @Argument String title) {
		Optional<Product> product = productService.findById(id);
		if (product.isPresent()) {
			if (description != null) {
				product.get().setDescription(description);
			}
			if (price != null) {
				product.get().setPrice(price);
			}
			if (quantity != null) {
				product.get().setQuantity(quantity);
			}
			if (title != null) {
				product.get().setTitle(title);
			}
			return productService.save(product.get());
		}
		return null;
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Integer id) {// phai co annotation
		productService.deleteById(id);
		return true;
	}
}
