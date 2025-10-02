package vn.khanhduy.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.khanhduy.controllers.CategoryController;
import vn.khanhduy.entities.Product;
import vn.khanhduy.repository.ProductRepository;
import vn.khanhduy.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	//private final CategoryController categoryController;

	// private final CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	ProductServiceImpl(ProductRepository productRepository, CategoryController categoryController) {
		this.productRepository = productRepository;
		//this.categoryController = categoryController;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> findAllByOrderByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	@Override
	public List<Product> findProductsByCategoryId(Integer categoryId) {
		return productRepository.findProductsByCategoryId(categoryId);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	/*
	 * @Override public Product updateProduct(Integer id, Product product) { return
	 * productRepository.findById(id).map(existingProduct -> {
	 * existingProduct.setDescription(product.getDescription()); if
	 * (existingProduct.getPrice() != null) {
	 * existingProduct.setPrice(product.getPrice()); } else {
	 * existingProduct.setPrice(existingProduct.getPrice()); }
	 * existingProduct.setQuantity(product.getQuantity()); if
	 * (existingProduct.getTitle() != null) {
	 * existingProduct.setTitle(product.getTitle()); } else {
	 * existingProduct.setTitle(existingProduct.getTitle()); } //
	 * existingProduct.setUser() return productRepository.save(existingProduct);
	 * }).orElseThrow(() -> new RuntimeException("Product is not found with id: " +
	 * id)); }
	 */
}
