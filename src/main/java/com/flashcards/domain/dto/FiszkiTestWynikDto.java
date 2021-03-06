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
public class FiszkiTestWynikDto {

    private Long id;
    private double wynik;
    private double liczbaFiszek;
    private UserDto userDto;
}
