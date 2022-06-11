package com.flashcards.domain.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userDto"})
@EqualsAndHashCode(exclude = {"id", "userDto"})
public class FlashcardDto {

    private Long id;
    private String slowoAngielskie;
    private String slowoPolskie;
    private UserDto userDto;


}
