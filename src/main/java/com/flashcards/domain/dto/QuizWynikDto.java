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
public class QuizWynikDto {
    private Long id;
    private double wynik;
    private double liczbaPytan;
    private UserDto userDto;
}
