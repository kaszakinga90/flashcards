package com.flashcards.gui;
//
//import listeners.FiszkiListener;
//import listeners.PomocListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/** 
 * Klasa przedstawia pocz¹tkowe okno aplikacji. Zosta³a napisana w Swing.
 * W oknie znajduj¹ siê 4 przyciski: 
 * Fiszki - tryb fiszki polega na zgadywaniu s³ów po angielsku albo po polsku
 * Quiz - quiz jêzyka angielskiego w formie pytañ ABCD jednokrotnego wyboru
 * Statystyki - statystyki u¿ytkownika, które mo¿na nastêpnie zapisaæ do pliku
 * "?" - pomoc
 */
@Component
public class OknoGlowne 
{
	@Autowired
	OknoNauka oknoNauka;

	@Autowired
	OknoFiszki oknoFiszki;

	@Autowired
	OknoStatystyki oknoStatystyki;

	@Autowired
	OknoQuiz oknoQuiz;

	private final JFrame frame;
	private final JPanel panel;
	private final JButton przyciskFiszki;
	private final JButton przyciskFiszkiNauka;
	private final JButton przyciskQuiz;
	private final JButton przyciskStatystyki;
	private final JButton przyciskPomoc;
	private final JLabel tekst;
	
	public OknoGlowne()
	{
		frame = new JFrame("Aplikacja do nauki angielskiego");
		panel = new JPanel();
		frame.setSize(500, 300);
		frame.setContentPane(panel);

		panel.setLayout(new GridLayout(4, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new GridLayout(2, 2, 20, 20));
		panel4.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		Color tlo = new Color(235, 246, 255);
		panel.setBackground(tlo);
		panel1.setBackground(tlo);
		panel2.setBackground(tlo);
		panel3.setBackground(tlo);
		panel4.setBackground(tlo);
		
		przyciskFiszki = new JButton("FISZKI - test");
		przyciskFiszki.setFont(new Font("Calibri", Font.BOLD, 18));	
		//przyciskFiszki.setBackground(null);
		przyciskQuiz = new JButton("QUIZ");
		przyciskQuiz.setFont(new Font("Calibri", Font.BOLD, 18));	
		przyciskStatystyki = new JButton("Statystyki");
		przyciskStatystyki.setFont(new Font("Calibri", Font.BOLD, 14));
		przyciskPomoc = new JButton("?");
		przyciskPomoc.setFont(new Font("Calibri", Font.BOLD, 26));	
		tekst = new JLabel("Æwicz swój angielski!");
		tekst.setFont(new Font("Calibri", Font.BOLD, 26));
		przyciskFiszkiNauka = new JButton("Fiszki - nauka");
		
		panel1.add(przyciskStatystyki);
		panel2.add(tekst);
		panel3.add(przyciskFiszki);
		panel3.add(przyciskQuiz);
		panel3.add(przyciskFiszkiNauka);
		panel4.add(przyciskPomoc);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		
//		przyciskFiszki.addActionListener(new FiszkiListener(this));
		przyciskPomoc.addMouseMotionListener(new PomocListener(this));

		przyciskFiszkiNauka.addActionListener(e -> {
			oknoNauka.init(this.frame);
		});

		przyciskFiszki.addActionListener(e -> {
			oknoFiszki.init();
		});

		przyciskStatystyki.addActionListener(e -> {
			oknoStatystyki.init(this.frame);
		});

		przyciskQuiz.addActionListener(e ->
			oknoQuiz.init(this.frame));


		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		
	}
	public void pokazOkno(){
		frame.setVisible(true);
	}
	public void zamknijOkno(){
		frame.setVisible(false);
	}
	public void ukryjOkno() { frame.setVisible(false);}
	
	public void DezaktywujPrzyciski()
	{
		przyciskStatystyki.setEnabled(false);
		przyciskFiszki.setEnabled(false);
		przyciskQuiz.setEnabled(false);
		przyciskPomoc.setEnabled(false);
	}
	
	public void AktywujPrzyciski()
	{
		przyciskStatystyki.setEnabled(true);
		przyciskFiszki.setEnabled(true);
		przyciskQuiz.setEnabled(true);
		przyciskPomoc.setEnabled(true);
	}


	private class PomocListener implements MouseMotionListener {

		OknoGlowne okno;

		public PomocListener(OknoGlowne okno) {
			super();
			this.okno = okno;
		}

		public OknoGlowne getOkno() {
			return okno;
		}


		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

			przyciskPomoc.setToolTipText("Jeœli potrzebujesz pomocy napisz do nas na pomoc@fiszki.com");
		}
	}
	
}
