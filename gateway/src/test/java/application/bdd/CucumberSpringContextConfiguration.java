package application.bdd;

import application.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CucumberSpringContextConfiguration {
    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}