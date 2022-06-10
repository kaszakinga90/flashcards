package com.flashcards.gui;

import com.flashcards.controller.FlashcardController;
import com.flashcards.controller.LoginController;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.DbFlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

// okno do dodawania/wczytywania fiszek
@Component
public class OknoNauka {


    private final LoginController loginController;
    private final FlashcardController flashcardController;
    private final JFrame frame;
    private final JLabel labelWordPl;
    private final JLabel labelWordEn;
    private final JTextField textFieldWordPl;
    private final JTextField textFieldWordEn;
    private final JButton buttonNext;
    private final JButton buttonAdd;
    private final JButton buttonSave;
    private final JPanel panel;
    private final JButton wczytajFiszki;
    private final JButton zapiszFiszki;

    private java.util.List<FlashcardDto> flashcards;

    @Autowired
    DbFlashcardService dbFlashcardService;
    private int counter = 0;

    @Autowired
    public OknoNauka(LoginController loginController, FlashcardController flashcardController) {
        this.loginController = loginController;
        this.flashcardController = flashcardController;

        frame = new JFrame("Nauka");
        frame.setSize(500, 400);
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        labelWordPl = new JLabel("Słowo pl");
        labelWordEn = new JLabel("Słowo ang");
        textFieldWordPl = new JTextField();
        textFieldWordEn = new JTextField();
        buttonNext = new JButton("Next");
        buttonAdd = new JButton("Add");
        buttonSave = new JButton("Save");
        wczytajFiszki = new JButton("Wczytaj fiszki");
        zapiszFiszki = new JButton("Zapisz fiszki");
        panel.add(labelWordPl);
        panel.add(labelWordEn);
        panel.add(textFieldWordPl);
        panel.add(textFieldWordEn);
        panel.add(buttonNext);
        panel.add(buttonAdd);
        panel.add(buttonSave);
        panel.add(wczytajFiszki);
        panel.add(zapiszFiszki);


        buttonNext.addActionListener(e -> {
            counter++;
            counter %= flashcards.size();
            wyswietlFiszke(flashcards.get(counter));
        });
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(false);




        /*
        poniżej nowy kod

        */

        buttonAdd.addActionListener(e -> {
            wyczyscPola();;
        });

        buttonSave.addActionListener(e -> {
            flashcardController.saveFlashCard(textFieldWordPl.getText(), textFieldWordEn.getText());
        });

//        zapiszFiszki.addActionListener(e -> {
//            ObslugaPlikow.zapiszDoPliku();
//        });
//
//        wczytajFiszki.addActionListener(e -> {
//            ObslugaPlikow.wczytajFiszki(ObslugaPlikow.wybierzPlik());
//        });
    }


    private void wyswietlFiszke(FlashcardDto f) {
        textFieldWordPl.setText(f.getSlowoPolskie());
        textFieldWordEn.setText(f.getSlowoAngielskie());
        textFieldWordPl.setEnabled(false);
        textFieldWordEn.setEnabled(false);
    }

    public void wyczyscPola() {
        textFieldWordPl.setText("");
        textFieldWordEn.setText("");
        textFieldWordPl.setEnabled(true);
        textFieldWordEn.setEnabled(true);
    }

    public void init(JFrame oknoGlowne) {
        frame.setVisible(true);
        flashcards = flashcardController.flashcardsForLoggedInUser();
        counter = 0;
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
            public void windowClosed(WindowEvent e) {
                oknoGlowne.setVisible(true);
                //getOkno().AktywujPrzyciski();
//				okno.pokazOkno();
            }
        });
    }


    //    public ParaSlow aktualnaFiszka() {
//
//        String firstWord = textFieldWordPl.getText();
//        String secondWord = textFieldWordEn.getText();
//
//        if (Objects.equals(firstWord, "") || Objects.equals(firstWord, null)) {
//            JOptionPane.showMessageDialog(null, "Podaj polskie slowo");
//        } else if (Objects.equals(secondWord, "") || Objects.equals(secondWord, null)) {
//            JOptionPane.showMessageDialog(null, "Podaj angielskie slowo");
//        }
//
//        return new ParaSlow(firstWord, secondWord);
//    }

}
