package vn.khanhduy.services;

import java.util.List;
import java.util.Optional;

import vn.khanhduy.entities.UserEntity;

public interface IUserService {

	Optional<UserEntity> findByUsername(String username);

	List<UserEntity> findAll();

	Optional<UserEntity> findById(Integer id);

}
