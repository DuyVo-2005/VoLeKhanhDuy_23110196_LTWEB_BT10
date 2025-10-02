package vn.khanhduy.services;

import java.util.Optional;

import vn.khanhduy.entities.UserEntity;

public interface IUserService {

	Optional<UserEntity> findByUsername(String username);

}
