package com.flashcards.gui;

public class Odpowiedz {
	private String tekstOdp;
	private boolean prawdziwoscOdp;
	
	public String getTekstOdp() {
		return tekstOdp;
	}
	public void setTekstOdp(String tekstOdp) {
		this.tekstOdp = tekstOdp;
	}
	public boolean isPrawdziwoscOdp() {
		return prawdziwoscOdp;
	}
	public void setPrawdziwoscOdp(boolean prawdziwoscOdp) {
		this.prawdziwoscOdp = prawdziwoscOdp;
	}
	
	public Odpowiedz(String tekstOdp, boolean prawdziwoscOdp) {
		this.tekstOdp = tekstOdp;
		this.prawdziwoscOdp = prawdziwoscOdp;
	}
	public Odpowiedz(String tekstOdp) {
		this.tekstOdp = tekstOdp;
		this.prawdziwoscOdp = false;
	}
}
