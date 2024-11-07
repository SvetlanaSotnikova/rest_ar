package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryInt extends CrudRepository<User, UUID> {
    @Query("SELECT u FROM User u")
    List<User> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteById(UUID id);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :#{#user.name}, u.age = :#{#user.age} WHERE u.id = :#{#user.id}")
    void update(User user);
}
