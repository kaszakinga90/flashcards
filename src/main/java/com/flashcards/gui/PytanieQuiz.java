package com.flashcards.gui;

import com.flashcards.gui.Odpowiedz;

public class PytanieQuiz
{
	private String pytanie;
	private Odpowiedz odp1;
	private Odpowiedz odp2;
	private Odpowiedz odp3;
	private Odpowiedz odp4;
	
	public String getPytanie() {
		return pytanie;
	}
	public void setPytanie(String pytanie) {
		this.pytanie = pytanie;
	}
	public Odpowiedz getOdp1() {
		return odp1;
	}
	public void setOdp1(Odpowiedz odp1) {
		this.odp1 = odp1;
	}
	public Odpowiedz getOdp2() {
		return odp2;
	}
	public void setOdp2(Odpowiedz odp2) {
		this.odp2 = odp2;
	}
	public Odpowiedz getOdp3() {
		return odp3;
	}
	public void setOdp3(Odpowiedz odp3) {
		this.odp3 = odp3;
	}
	public Odpowiedz getOdp4() {
		return odp4;
	}
	public void setOdp4(Odpowiedz odp4) {
		this.odp4 = odp4;
	}
	
	public PytanieQuiz(String pytanie, Odpowiedz odp1, Odpowiedz odp2, Odpowiedz odp3, Odpowiedz odp4)
	{
		this.pytanie = pytanie;
		this.odp1 = odp1;
		this.odp2 = odp2;
		this.odp3 = odp3;
		this.odp4 = odp4;
	}
	
}
