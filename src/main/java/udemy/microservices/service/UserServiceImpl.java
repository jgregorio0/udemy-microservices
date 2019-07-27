package udemy.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udemy.microservices.domain.User;
import udemy.microservices.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
