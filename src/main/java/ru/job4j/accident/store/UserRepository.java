package ru.job4j.accident.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("from User u where u.username = :aName")
    Optional<User> findByUser(@Param("aName") String authority);
}
