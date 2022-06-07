package com.flashcards.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardDto {

    private Long id;
    private String slowoAngielskie;
    private String slowoPolskie;
    UserDto userDto;
}
