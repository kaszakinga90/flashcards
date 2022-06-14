package com.flashcards.domain.dto;

import lombok.*;

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
