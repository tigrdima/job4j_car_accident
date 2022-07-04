package ru.job4j.accident.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Authority;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    @Query("from Authority a where a.authority = :aAuth")
    Optional<Authority> findByAuthority(@Param("aAuth") String authority);
}
