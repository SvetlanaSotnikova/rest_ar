package com.example.demo;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRepositoryInt;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Spy
    private UserRepositoryInt userRepositoryInt;

    @Mock
    private UserRepository userRepository;

    @Test
    public void updateUser() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("Alisa");
        user1.setAge(20);

        User user2 = new User();
        user2.setId(user1.getId());
        user2.setName("Katea");
        user2.setAge(25);

        given(userRepositoryInt.findById(user1.getId())).willReturn(Optional.of(user1));
        given(userRepositoryInt.findById(user2.getId())).willReturn(Optional.of(user2));

        userService.update(user1.getId(), user2);

        verify(userRepositoryInt).update(user2);
    }

    @Test
    public void updateUserNotFound() {
        // Блок предусловия
        UUID id = UUID.randomUUID();
        User userToUpdate = new User();
        userToUpdate.setId(id);
        userToUpdate.setName("Not Found User");
        userToUpdate.setAge(40);

        given(userRepositoryInt.findById(id)).willReturn(Optional.empty());

        // Блок действия и проверки
        assertThrows(UserNotFoundException.class, () -> userService.update(id, userToUpdate));

        // Проверка, что метод update не вызывался
        verify(userRepositoryInt, never()).update(any());
    }
}
