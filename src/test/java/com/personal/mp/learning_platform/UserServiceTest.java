package com.personal.mp.learning_platform;

import com.personal.mp.learning_platform.model.User;
import com.personal.mp.learning_platform.repository.UserRepository;
import com.personal.mp.learning_platform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        user.setRole("STUDENT");
    }

    @Test
    public void testRegisterUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.registerUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user.getId(), user);

        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(user.getId());

        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }
}
