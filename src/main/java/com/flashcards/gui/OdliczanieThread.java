package com.flashcards.gui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * Ten wątek odlicza czas na odpowiedz.
 * Pozostały czas pokazuje się na przycisku "Start"
 */
@Getter
@Setter
public class OdliczanieThread extends Thread {
	private JButton przycisk;
	private int czas;
	
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
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
}
