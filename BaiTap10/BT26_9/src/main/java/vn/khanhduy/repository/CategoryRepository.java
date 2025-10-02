package vn.khanhduy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.khanhduy.entities.Category;
import vn.khanhduy.entities.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findById(Integer id);
}
