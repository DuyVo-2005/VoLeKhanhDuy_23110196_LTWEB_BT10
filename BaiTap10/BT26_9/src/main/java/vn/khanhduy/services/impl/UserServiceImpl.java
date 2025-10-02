package vn.khanhduy.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.khanhduy.entities.User;
import vn.khanhduy.models.DeleteUserInput;
import vn.khanhduy.models.UpdateUserInput;
import vn.khanhduy.repository.UserRepository;
import vn.khanhduy.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUser(DeleteUserInput input) {
		User user = userRepository.findById(input.getId())
				.orElseThrow(() -> new RuntimeException("Không tồn tại user có id " + input.getId()));
		userRepository.deleteById(input.getId());
	}

	/*
	 * @Override public User updateUser(Integer id, User user) { return
	 * userRepository.findById(id).map(existingUser -> {
	 * existingUser.setFullname(user.getFullname());
	 * existingUser.setEmail(user.getEmail());
	 * existingUser.setPassword(user.getPassword());
	 * existingUser.setPhone(user.getPhone()); return
	 * userRepository.save(existingUser); }).orElseThrow(() -> new
	 * RuntimeException("Username is not found with id: " + id)); }
	 */

	@Override
	public User updateUser(UpdateUserInput input) {
		User user = userRepository.findById(input.getId())
				.orElseThrow(() -> new RuntimeException("User không tồn tại với id " + input.getId()));

		if (input.getEmail() != null) {
			user.setEmail(input.getEmail());
		}

		if (input.getFullname() != null) {
			user.setFullname(input.getFullname());
		}

		if (input.getPassword() != null) {
			user.setPassword(input.getPassword());
		}

		if (input.getPhone() != null) {
			user.setPhone(input.getPhone());
		}

		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	

}
