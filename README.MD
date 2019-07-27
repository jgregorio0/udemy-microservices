# Spring Boot Test
> Ref: [https://www.baeldung.com/spring-boot-testing](https://www.baeldung.com/spring-boot-testing)
> Github code: []()

Test repository, service and controller layer using Spring Boot, Mockito and H2 Database.

# Dependencies
- Spring Boot Starter Parent
org.springframework.boot:spring-boot-starter-parent:2.1.6.RELEASE

- Sprint Boot Starter Web
org.springframework.boot:spring-boot-starter-web

- Spring Boot Starter Data JPA 
org.springframework.boot:spring-boot-starter-data-jpa:2.1.6.RELEASE

- Spring Boot Starter Test
org.springframework.boot:spring-boot-starter-test:2.1.6.RELEASE

- H2 Database
com.h2database:h2:1.4.194

# Repository Test
```
package udemy.microservices.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import udemy.microservices.domain.User;

/**
 * Test repository layer
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
 
    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        User alex = new User("alex");
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        User found = userRepository.findByName(alex.getName());
     
        // then
        assertEquals(alex.getName(), found.getName());
    }

}
```

# Service Test
```
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
```

# Controller Test
```
package udemy.microservices.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import udemy.microservices.domain.User;
import udemy.microservices.service.UserService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {

		User alex = new User("alex");

		List<User> allUsers = Arrays.asList(alex);

		given(userService.getAllUsers()).willReturn(allUsers);

		mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(alex.getName())));
	}
}

```