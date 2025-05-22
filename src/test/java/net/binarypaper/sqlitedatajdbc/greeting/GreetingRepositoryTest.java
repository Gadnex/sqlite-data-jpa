package net.binarypaper.sqlitedatajdbc.greeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GreetingRepositoryTest {

  @Autowired private GreetingRepository greetingRepository;

  @BeforeEach
  void setUp() {
    greetingRepository.save(new Greeting("Greeting 1"));
    greetingRepository.save(new Greeting("Greeting 3"));
    greetingRepository.save(new Greeting("Greeting 2"));
    greetingRepository.save(new Greeting("Greeting 4"));
    greetingRepository.save(new Greeting("Greeting 5"));
    greetingRepository.save(new Greeting("Greeting 6"));
    greetingRepository.save(new Greeting("Greeting 7"));
    greetingRepository.save(new Greeting("Greeting 8"));
    greetingRepository.save(new Greeting("Greeting 9"));
    greetingRepository.save(new Greeting("Greeting 10"));
  }

  @Test
  void addGreetingAndFindById() {
    Greeting greeting = new Greeting("Hello World");
    greeting = greetingRepository.save(greeting);
    assertNotNull(greeting.getId());
    assertEquals("Hello World", greeting.getMessage());
    Greeting findGreeting = greetingRepository.findById(greeting.getId()).orElse(null);
    assertNotNull(findGreeting);
    assertEquals(greeting.getId(), findGreeting.getId());
    assertEquals("Hello World", findGreeting.getMessage());
    assertEquals(LocalDate.now(), findGreeting.getMessageDate());
    Iterable<Greeting> greetings = greetingRepository.findAll();
    assertEquals(11, StreamSupport.stream(greetings.spliterator(), false).count());
  }

  @Test
  void findAll() {
    Iterable<Greeting> greetings = greetingRepository.findAll();
    assertEquals(10, StreamSupport.stream(greetings.spliterator(), false).count());
  }

  @Test
  void findByMessage() {
    Greeting greeting = greetingRepository.findByMessage("Hello World");
    assertNull(greeting);
    greeting = greetingRepository.findByMessage("Greeting 10");
    assertNotNull(greeting);
    assertEquals(10, greeting.getId());
    assertEquals("Greeting 10", greeting.getMessage());
  }
}
