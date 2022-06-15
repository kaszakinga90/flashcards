package com.flashcards.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PytanieQuiz {
	private String pytanie;
	private Odpowiedz odp1;
	private Odpowiedz odp2;
	private Odpowiedz odp3;
	private Odpowiedz odp4;
}
