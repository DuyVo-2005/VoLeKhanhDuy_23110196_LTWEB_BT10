package vn.khanhduy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.khanhduy.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findById(Integer id);
	
	Optional<User> findByEmail(String email);
}
