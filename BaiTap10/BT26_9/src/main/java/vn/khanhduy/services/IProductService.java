package vn.khanhduy.services;

import java.util.List;
import java.util.Optional;

import vn.khanhduy.entities.Product;

public interface IProductService {

	<S extends Product> S save(S entity);

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	List<Product> findAllByOrderByPriceAsc();
	
	List<Product> findProductsByCategoryId(Integer categoryId);

	void deleteById(Integer id);
	
	//Product updateProduct(Integer id, Product product);
}
