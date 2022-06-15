package com.flashcards.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa przedstawia pocz¹tkowe okno aplikacji. Zosta³a napisana w Swing.
 * W oknie znajduje siê 5 przycisków:
 * Flashcard - w sekcji LEARN - do nauki jêzyka poprzez fiszki
 * Flashcard - test - tryb ten polega na zgadywaniu s³ów po angielsku albo po polsku
 * Quiz - quiz jêzyka angielskiego w formie pytañ ABCD jednokrotnego wyboru
 * Statystyki - statystyki u¿ytkownika
 * "?" - pomoc pokazuj¹cy komunikat o mo¿liwoœci kontaktu
 */
@Component
public class OknoGlowne {
    @Autowired
    OknoNauka oknoNauka;

    @Autowired
    OknoFiszki oknoFiszki;

    @Autowired
    OknoStatystyki oknoStatystyki;

    @Autowired
    OknoQuiz oknoQuiz;

    @Autowired
    SocketKlienta socketKlienta;

    public static final String KONIEC_POLACZENIA = "KONIEC POLACZENIA";
    private final JFrame frame;
    private final JPanel panel;
    private final JButton przyciskFiszki;
    private final JButton przyciskFiszkiNauka;
    private final JButton przyciskQuiz;
    private final JButton przyciskStatystyki;
    private final JButton przyciskPomoc;
    private final JLabel tekst;
    private final JLabel tekst2;

    public OknoGlowne() {
        frame = new JFrame("App for learning English");
        panel = new JPanel();
        frame.setSize(600, 400);
        frame.setContentPane(panel);
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        tekst = new JLabel("Learn");
        tekst.setHorizontalAlignment(SwingConstants.CENTER);
        tekst.setFont(new Font("Calibri", Font.BOLD, 24));
        tekst2 = new JLabel("Check yourself");
        tekst2.setHorizontalAlignment(SwingConstants.CENTER);
        tekst2.setFont(new Font("Calibri", Font.BOLD, 24));

        panel1.setLayout(new FlowLayout(FlowLayout.TRAILING));
        panel2.setLayout(new GridLayout(1, 2, 20, 20));
        panel3.setLayout(new GridLayout(1, 2, 20, 20));
        panel4.setLayout(new GridLayout(1, 2, 20, 20));
        panel5.setLayout(new FlowLayout(FlowLayout.TRAILING));

        panel.setBackground(Kolory.Tlo);
        panel1.setBackground(Kolory.Tlo);
        panel2.setBackground(Kolory.Tlo);
        panel2.setBackground(Kolory.Tlo);
        panel3.setBackground(Kolory.Tlo);
        panel4.setBackground(Kolory.Tlo);
        panel5.setBackground(Kolory.Tlo);

        przyciskFiszki = new JButton("Flashcards - test");
        przyciskFiszki.setFont(new Font("Calibri", Font.BOLD, 18));
        przyciskQuiz = new JButton("QUIZ");
        przyciskQuiz.setFont(new Font("Calibri", Font.BOLD, 18));
        przyciskStatystyki = new JButton("Statistics");
        przyciskStatystyki.setFont(new Font("Calibri", Font.BOLD, 14));
        przyciskPomoc = new JButton("?");
        przyciskPomoc.setFont(new Font("Calibri", Font.BOLD, 24));
        przyciskFiszkiNauka = new JButton("Flashcards");
        przyciskFiszkiNauka.setFont(new Font("Calibri", Font.BOLD, 18));

        panel1.add(przyciskStatystyki);
        panel2.add(tekst);
        panel2.add(tekst2);
        panel3.add(przyciskFiszkiNauka);
        panel3.add(przyciskFiszki);
        panel4.add(new JLabel());
        panel4.add(przyciskQuiz);
        panel5.add(przyciskPomoc);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);

        przyciskPomoc.addMouseMotionListener(new PomocListener(this));

        przyciskFiszkiNauka.addActionListener(e -> oknoNauka.init(this.frame));

        przyciskFiszki.addActionListener(e -> oknoFiszki.init(this.frame));

        przyciskStatystyki.addActionListener(e -> oknoStatystyki.init(this.frame));

        przyciskQuiz.addActionListener(e ->
                oknoQuiz.init(frame));


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //frame.dispose();
                System.out.println("Zamykanie okna");

//				socketKlienta.getOut().println(KONIEC_POLACZENIA);
//				if (String.valueOf(socketKlienta.getIn()).equals(KONIEC_POLACZENIA)) {
//					try {
//						System.out.println("Zamykanie po³¹czenia");
//						socketKlienta.getIn().close();
//						System.out.println("Zamkniêto in");
//						socketKlienta.getOut().close();
//						System.out.println("Zamkniêto out");
//						socketKlienta.getSocket().close();
//						System.out.println("Zamkniêto socket");
//						socketKlienta.getScanner().close();
//					} catch (IOException ex) {
//						System.out.println("B³¹d przy zamykaniu po³¹czenia");
//					}
//				}
            }

            public void windowClosed(WindowEvent e) {
                //socketKlienta.getOut().println(KONIEC_POLACZENIA);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public void pokazOkno() {
        frame.setVisible(true);
    }

    public void zamknijOkno() {
        frame.setVisible(false);
    }

    public void ukryjOkno() {
        frame.setVisible(false);
    }

    public void DezaktywujPrzyciski() {
        przyciskStatystyki.setEnabled(false);
        przyciskFiszki.setEnabled(false);
        przyciskQuiz.setEnabled(false);
        przyciskPomoc.setEnabled(false);
    }

    public void AktywujPrzyciski() {
        przyciskStatystyki.setEnabled(true);
        przyciskFiszki.setEnabled(true);
        przyciskQuiz.setEnabled(true);
        przyciskPomoc.setEnabled(true);
    }

    /**
     * Ustawienie tooltipa dla przycisku pomocy
     */
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
            przyciskPomoc.setToolTipText("If you need help, please contact us:  pomoc@fiszki.com");
        }
    }
}
