package udemy.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.microservices.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
