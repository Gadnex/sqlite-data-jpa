package net.binarypaper.sqlitedatajdbc.greeting;

import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

  Greeting findByMessage(String message);
}
