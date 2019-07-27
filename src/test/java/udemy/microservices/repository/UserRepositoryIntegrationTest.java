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
