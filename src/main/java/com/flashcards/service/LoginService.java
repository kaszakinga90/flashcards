package com.flashcards.service;

import com.flashcards.domain.dto.UserDto;
import com.flashcards.mapper.UserMapper;
import com.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Klasa będąca częścią warstwy logiki biznesowej
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserRepository userRepository;
    private Optional<UserDto> currentUser = Optional.empty();


    public void zaloguj(String email, String password) {
        currentUser = userRepository.findByEmail(email).map(userMapper::mapToUserDto);
    }

    public Optional<UserDto> currentLoggedInUser() {
        return currentUser.map(u -> new UserDto(u.getId(), u.getEmail(), u.getPassword()));
    }
}
