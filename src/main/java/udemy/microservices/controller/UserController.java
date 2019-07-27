package udemy.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import udemy.microservices.domain.User;
import udemy.microservices.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public String crerateUser() {
		return "POST, to create a new resource (or any other non-idempotent operation*)";
	}

	@PutMapping
	public String replaceUser() {
		return "PUT, to replace an existing resource";
	}

	@PatchMapping
	public String updateUser() {
		return "PATCH, to (partially) update an existing resource";
	}

	@DeleteMapping
	public String deleteUser() {
		return "DELETE, to delete an existing resource";
	}
}
