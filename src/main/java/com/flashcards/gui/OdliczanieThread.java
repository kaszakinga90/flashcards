package com.flashcards.gui;

import javax.swing.*;

/**
 * Ten wątek odlicza czas na odpowiedz.
 * Pozostały czas pokazuje się na przycisku "Start"
 */
public class OdliczanieThread extends Thread {
	private JButton przycisk;
	private int czas;
		
	public JButton getPrzycisk() {
		return przycisk;
	}
	public void setPrzycisk(JButton przycisk) {
		this.przycisk = przycisk;
	}
	public int getCzas() {
		return czas;
	}
	public void setCzas(int czas) {
		this.czas = czas;
	}
	
	public OdliczanieThread(JButton przycisk, int czas) {
		this.przycisk = przycisk;
		this.czas = czas * 1000;
	}
	
	public void run() {
		while(getCzas() >= 0) {
			try {
				getPrzycisk().setText(getCzas()/1000 + "");
				Thread.sleep(1000);
				setCzas(getCzas() - 1000);
				//if(getCzas() < 0) getPrzycisk().setText("0");
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
}
