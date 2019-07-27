package udemy.microservices.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import udemy.microservices.domain.User;
import udemy.microservices.repository.UserRepository;

/**
 * Test service layout
 *
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@TestConfiguration
	static class UserServiceImplTestConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp() {
		User alex = new User("alex");

		Mockito.when(userRepository.findByName(alex.getName())).thenReturn(alex);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String name = "alex";
		User found = userService.getUserByName(name);
		assertEquals(found.getName(), name);
	}
}
