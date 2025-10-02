package vn.khanhduy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.khanhduy.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAllByOrderByPriceAsc();
	
	@Query("select p from Product p join p.user u join u.categories c where c.id = :categoryId")
	List<Product> findProductsByCategoryId(@Param("categoryId") Integer categoryId);
}
