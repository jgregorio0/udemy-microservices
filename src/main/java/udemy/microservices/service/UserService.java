package udemy.microservices.service;

import java.util.List;

import udemy.microservices.domain.User;

public interface UserService {
	User getUserByName(String name);

	List<User> getAllUsers();
}
