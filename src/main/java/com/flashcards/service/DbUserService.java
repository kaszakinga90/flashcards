package com.flashcards.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.flashcards.domain.User;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.domain.exceptions.UserNotFoundException;
import com.flashcards.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.flashcards.repository.UserRepository;

@Component
public class DbUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long id) throws UserNotFoundException {
        return userRepository.findById(id);
    }

    public void deleteUser(final Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public User updateUser(final Long id, User user) throws UserNotFoundException {
        User toUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        toUpdate.setEmail(user.getEmail());
        return userRepository.save(toUpdate);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public boolean registerUser(UserDto userDto){
        boolean alreadyExists = getAllUsers().stream().anyMatch((u -> Objects.equals(u.getEmail(), userDto.getEmail())));
        if (alreadyExists) {
            return false;
        } else {
            saveUser(userMapper.mapToUser(userDto));
            return true;
        }
    }

}
