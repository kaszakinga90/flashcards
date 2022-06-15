package com.flashcards.gui;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odpowiedz {
	private String tekstOdp;
	private boolean prawdziwoscOdp;

	public Odpowiedz(String tekstOdp, boolean prawdziwoscOdp) {
		this.tekstOdp = tekstOdp;
		this.prawdziwoscOdp = prawdziwoscOdp;
	}
	public Odpowiedz(String tekstOdp) {
		this.tekstOdp = tekstOdp;
		this.prawdziwoscOdp = false;
	}
}
