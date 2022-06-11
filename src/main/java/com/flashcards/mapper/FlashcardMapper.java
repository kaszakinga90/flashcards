package com.flashcards.mapper;

import com.flashcards.domain.Flashcard;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.flashcards.service.DbUserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlashcardMapper {

    private final DbUserService dbUserService;
    private final UserMapper userMapper;

    public Flashcard mapToFlashcard(FlashcardDto flashcardDto) throws UserNotFoundException {
        return new Flashcard(
                flashcardDto.getSlowoPolskie(),
                flashcardDto.getSlowoAngielskie(),
                dbUserService.getUser(flashcardDto.getUserDto().getId()).get()
        );
    }

    public FlashcardDto mapToFlashcardDto(Flashcard flashcard) {
        return new FlashcardDto(
                flashcard.getId(),
                flashcard.getSlowoPolskie(),
                flashcard.getSlowoAngielskie(),
                userMapper.mapToUserDto(flashcard.getUser()));
    }


    public List<FlashcardDto> mapToFlashcardDtoList(final List<Flashcard> orderList) {
        return  orderList.stream()
                .map(o -> new FlashcardDto(
                        o.getId(),
                        o.getSlowoPolskie(),
                        o.getSlowoAngielskie(),
                        userMapper.mapToUserDto(o.getUser())))
                .collect(Collectors.toList());
    }
}
