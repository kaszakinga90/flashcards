package com.flashcards.gui;

import com.flashcards.controller.FiszkiTestWynikController;
import com.flashcards.domain.FiszkiTestWynik;
import com.flashcards.repository.FiszkiTestWynikRepository;
import com.flashcards.repository.QuizWynikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stareKlasy.Statystyki;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@Component
public class OknoStatystyki {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel label1;
    private final JLabel label2;
    private final JTextArea poleFiszki;
    private final JTextArea poleQuiz;
    private final JTextField wynikFiszki;
    private final JTextField wynikQuiz;

    @Autowired
    private FiszkiTestWynikRepository fiszkiTestWynikRepository;

    @Autowired
    private QuizWynikRepository quizWynikRepository;

    @Autowired
    private FiszkiTestWynikController fiszkiTestWynikController;

    public OknoStatystyki() {
        frame = new JFrame("Statistics");
        frame.setSize(600, 400);
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel panelGora = new JPanel(new BorderLayout());
        JPanel panelDol = new JPanel(new BorderLayout());
        JPanel panelFiszki = new JPanel();
        JPanel panelQuiz = new JPanel();

        label1 = new JLabel("Flashcards - results");
        label2 = new JLabel("Quiz - results");
        poleFiszki = new JTextArea(5, 30);
        poleQuiz = new JTextArea(5, 30);
        wynikFiszki = new JTextField(30);
        wynikQuiz = new JTextField(30);
        JScrollPane scroll1 = new JScrollPane(poleFiszki);
        JScrollPane scroll2 = new JScrollPane(poleQuiz);

        poleFiszki.setEditable(false);
        poleQuiz.setEditable(false);
        wynikFiszki.setEditable(false);
        wynikQuiz.setEditable(false);

        panel.add(panelGora);
        panel.add(panelDol);
        panelGora.add(label1, BorderLayout.WEST);
        panelGora.add(panelFiszki, BorderLayout.CENTER);
        panelDol.add(label2, BorderLayout.WEST);
        panelDol.add(panelQuiz, BorderLayout.CENTER);
        panelFiszki.add(scroll1);
        panelFiszki.add(wynikFiszki);
        panelQuiz.add(scroll2);
        panelQuiz.add(wynikQuiz);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        aktualizujFiszki();
        aktualizujQuiz();

        zamknijOkno();
    }


    private void zamknijOkno() {
//        frame.addWindowListener (new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                frame.dispose();
//            }
//            public void windowClosed(WindowEvent e) {
//                getOkno().getFrame().setVisible(true);
//            }
//        });
//        frame.setVisible(true);
    }

    private void aktualizujFiszki() {
        if (!Statystyki.RozegraneFiszki.isEmpty()) {
            for (int i = 1; i <= Statystyki.WynikFiszek.size(); i++)
                poleFiszki.append("Partia fiszek nr " + i + ": " + Statystyki.WynikFiszek.get(i - 1) + "/" + Statystyki.RozegraneFiszki.get(i - 1) + "\n");
            wynikFiszki.setText("Wszystkie zdobyte punkty: " + Statystyki.CalkowityWynik(Statystyki.WynikFiszek) + "/" + Statystyki.CalkowityWynik(Statystyki.RozegraneFiszki));
        }
    }

    private void aktualizujQuiz() {
        if (!Statystyki.RozegraneFiszki.isEmpty()) {
            for (int i = 1; i <= Statystyki.WynikQuizu.size(); i++)
                poleQuiz.append("Partia quizu nr " + i + ": " + Statystyki.WynikQuizu.get(i - 1) + "/" + Statystyki.RozegraneQuizy.get(i - 1) + "\n");
            wynikQuiz.setText("Wszystkie zdobyte punkty: " + Statystyki.CalkowityWynik(Statystyki.WynikQuizu) + "/" + Statystyki.CalkowityWynik(Statystyki.RozegraneQuizy));
        }
    }

    /**
     * Metoda inicjalizuje okno Statistics
     */
    public void init(JFrame oknoGlowne) {
        frame.setVisible(true);
        //wynikFiszki.setText(String.valueOf(fiszkiTestWynikController.fiszkiTestWynikForLoggedInUser()));

        if (fiszkiTestWynikRepository.getLiczbaFiszek() == 0) {
            wynikFiszki.setText("No results");
        } else {
            double result = fiszkiTestWynikRepository.getWyniki() / fiszkiTestWynikRepository.getLiczbaFiszek();
            //result = Math.round(result);
            result *= 100;

            wynikFiszki.setText(result + "%");
        }

        if (quizWynikRepository.getLiczbaPytan() == 0) {
            wynikQuiz.setText("No results");
        } else {
            double result = quizWynikRepository.getWyniki() / quizWynikRepository.getLiczbaPytan();
            result *= 100;
            result = Math.round(result);
            wynikQuiz.setText(result + "%");
        }

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
                oknoGlowne.setVisible(true);
            }
        });
    }
}
