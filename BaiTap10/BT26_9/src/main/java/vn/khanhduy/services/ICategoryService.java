package vn.khanhduy.services;

import java.util.List;
import java.util.Optional;

import vn.khanhduy.entities.Category;

public interface ICategoryService {

	void deleteById(Integer id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Optional<Category> findById(Integer id);
}
