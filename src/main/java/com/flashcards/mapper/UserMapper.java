package com.flashcards.mapper;

import com.flashcards.domain.User;
import com.flashcards.domain.dto.UserDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(t -> new UserDto(t.getId(), t.getEmail(), t.getPassword()))
                .collect(Collectors.toList());
    }

}
