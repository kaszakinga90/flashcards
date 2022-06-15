package com.flashcards.mapper;

import com.flashcards.domain.QuizWynik;
import com.flashcards.domain.dto.QuizWynikDto;
import com.flashcards.domain.exceptions.UserNotFoundException;
import com.flashcards.service.DbUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa mapująca dane
 */
@Component
@RequiredArgsConstructor
public class QuizWynikMapper {

    private final DbUserService dbUserService;
    private final UserMapper userMapper;

    public QuizWynik mapToQuizWynik(QuizWynikDto quizWynikDto) throws UserNotFoundException {
        return new QuizWynik(
                quizWynikDto.getWynik(),
                quizWynikDto.getLiczbaPytan(),
                dbUserService.getUser(quizWynikDto.getUserDto().getId()).get()
        );
    }

    public QuizWynikDto mapToQuizWynikDto(QuizWynik quizWynik) {
        return new QuizWynikDto(
                quizWynik.getId(),
                quizWynik.getWynik(),
                quizWynik.getLiczbaPytan(),
                userMapper.mapToUserDto(quizWynik.getUser()));
    }

    public List<QuizWynikDto> mapToQuizWynikDtoList(final List<QuizWynik> quizWynikList) {
        return quizWynikList.stream()
                .map(f -> new QuizWynikDto(
                        f.getId(),
                        f.getWynik(),
                        f.getLiczbaPytan(),
                        userMapper.mapToUserDto(f.getUser())))
                .collect(Collectors.toList());
    }
}
