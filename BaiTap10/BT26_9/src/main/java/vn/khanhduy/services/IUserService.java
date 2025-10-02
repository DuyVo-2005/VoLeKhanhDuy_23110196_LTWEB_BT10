package vn.khanhduy.services;

import java.util.List;
import java.util.Optional;

import vn.khanhduy.entities.User;
import vn.khanhduy.models.DeleteUserInput;
import vn.khanhduy.models.UpdateUserInput;

public interface IUserService {

	void deleteUser(DeleteUserInput input);

	Optional<User> findById(Integer id);

	List<User> findAll();

	<S extends User> S save(S entity);
	
	User updateUser(UpdateUserInput input);

	Optional<User> findByEmail(String email);
}
