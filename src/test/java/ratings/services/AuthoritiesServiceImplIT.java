package ratings.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ratings.model.Authority;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthoritiesServiceImplIT {

    @Autowired
    AuthoritiesService authoritiesService;

    @Test
    public void testGetAuthorities() {
        List<Authority> authorities = authoritiesService.getAuthorities();
        assertEquals(20, authorities.size());
    }
}