package com.flashcards.domain.dto;

import lombok.*;

/**
 * Klasa reprezentująca obiekt DTO, który przekazuje dane do innych części aplikacji
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"userDto"})
@EqualsAndHashCode(exclude = {"id", "userDto"})
public class FlashcardDto {

    private Long id;
    private String slowoPolskie;
    private String slowoAngielskie;
    private UserDto userDto;
}
