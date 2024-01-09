package com.beadando.beadando.repository;

import com.beadando.beadando.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="select * from users where name = ?1", nativeQuery = true)
    Optional<User> findUserByName(String name);
}
