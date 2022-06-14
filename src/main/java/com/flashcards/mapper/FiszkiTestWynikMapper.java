package com.flashcards.mapper;

import com.flashcards.domain.FiszkiTestWynik;
import com.flashcards.domain.Flashcard;
import com.flashcards.domain.dto.FiszkiTestWynikDto;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.exceptions.UserNotFoundException;
import com.flashcards.service.DbUserService;
import com.flashcards.service.FiszkiTestWynikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FiszkiTestWynikMapper {

    private final DbUserService dbUserService;
    private final UserMapper userMapper;

    public FiszkiTestWynik mapToFiszkiTestWynik(FiszkiTestWynikDto fiszkiTestWynikDto) throws UserNotFoundException {
        return new FiszkiTestWynik(
                fiszkiTestWynikDto.getWynik(),
                fiszkiTestWynikDto.getLiczbaFiszek(),
                dbUserService.getUser(fiszkiTestWynikDto.getUserDto().getId()).get()
        );
    }

    public FiszkiTestWynikDto mapToFiszkiTestWynikDto(FiszkiTestWynik fiszkiTestWynik) {
        return new FiszkiTestWynikDto(
                fiszkiTestWynik.getId(),
                fiszkiTestWynik.getWynik(),
                fiszkiTestWynik.getLiczbaFiszek(),
                userMapper.mapToUserDto(fiszkiTestWynik.getUser()));
    }

    public List<FiszkiTestWynikDto> mapToFiszkiTestWynikDtoList(final List<FiszkiTestWynik> fiszkitestWynikList) {
        return  fiszkitestWynikList.stream()
                .map(f -> new FiszkiTestWynikDto(
                        f.getId(),
                        f.getWynik(),
                        f.getLiczbaFiszek(),
                        userMapper.mapToUserDto(f.getUser())))
                .collect(Collectors.toList());
    }
}
