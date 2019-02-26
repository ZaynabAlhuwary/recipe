package recipe.embeddablesRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import recipe.embeddables.Address;
import recipe.embeddables.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
     //   MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByAddress_Country() throws Exception {

        Address address = new Address("Egypt");

        User user = new User("zaynab",address);

        userRepository.save(user);

        assertEquals("Egypt",userRepository.findById(1l).get().getAddress().getCountry());
    }

}