package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Alisa");
        user.setAge(20);

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals("Alisa", savedUser.getName());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Kirill");
        user.setAge(25);

        User savedUser = userRepository.save(user);

        savedUser.setName("Kostea");
        savedUser.setAge(21);
        userRepository.update(savedUser);

        Optional<User> updatedUser = Optional.ofNullable(userRepository.findById(savedUser.getId()));
        assertTrue(updatedUser.isPresent());
        assertEquals("Kostea", updatedUser.get().getName());
        assertEquals(21, updatedUser.get().getAge());
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Alisa");
        user.setAge(20);
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        Optional<User> deletedUser = Optional.ofNullable(userRepository.findById(savedUser.getId()));
        assertFalse(deletedUser.isPresent());
    }
}
