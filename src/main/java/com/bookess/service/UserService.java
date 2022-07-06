package com.bookess.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookess.entity.UserBookEntity;
import com.bookess.entity.UserEntity;
import com.bookess.repository.UserBookRepository;
import com.bookess.repository.UserRepository;
import com.bookess.vo.LoginVO;

@Service
public class UserService {
	public static HashMap<String, UserEntity> map = new HashMap<>();

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserBookRepository userBookRepo;

	public String createUser(UserEntity user) {
		String role = user.getRole();
		if (role != null && (role.equalsIgnoreCase("user") || role.equalsIgnoreCase("admin"))) {
			Optional<UserEntity> findByEmail = userRepository.findByEmail(user.getEmail());
			if (findByEmail.isEmpty()) {
				UserEntity save = userRepository.save(user);
				return "Success";
			} else {
				return "Email already exists";

			}
		} else {
			return "Failed, Role mismatch";
		}

	}

	public String login(LoginVO loginVo) {

		String email = loginVo.getEmail();

		Optional<UserEntity> findByEmail = userRepository.findByEmail(email);
		if (findByEmail.isPresent()) {
			UserEntity userEntity = findByEmail.get();
			String password = userEntity.getPassword();
			if (loginVo.getPassword().equals(password)) {
				if (map.get(email) == null) {
					map.put(email, userEntity);
					return "Login SuccessFull";
				} else {
					return "Already logged in";
				}
			}
		}
		return "Login Failed";
	}

	public String logout(String email) {

		if (map.get(email) != null) {
			map.remove(email);
			return "Logout Success";
		} else {
			return "Please Login First";
		}

	}

	public String updateUser(UserEntity user) {
		Optional<UserEntity> findById = userRepository.findById(user.getId());
		if (findById.isEmpty()) {
			return "User Not Exit in Database with userId " + user.getId();
		}
		UserEntity save = userRepository.save(user);
		return "Success";
	}

	public UserEntity getUserById(Long userId) {
		Optional<UserEntity> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	public String deleteuser(String userEmail,String adminEmail) {
		UserEntity admin = UserService.map.get(adminEmail);
		Optional<UserEntity> user= userRepository.findByEmail(userEmail);
		if (admin == null) {
			return "To delete user, please login as admin first";
		} else if (!admin.getRole().equalsIgnoreCase("admin")) {
			return "Only Admin can delete user";
		}
		if(user.isPresent()) {
			List<UserBookEntity> findByUserEmail = userBookRepo.findByUserEmail(userEmail);
			userBookRepo.deleteAll(findByUserEmail);
			userRepository.deleteById(user.get().getId());
			return "Success";
		}
		return "Failed";
	}

}
