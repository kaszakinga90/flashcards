package com.flashcards.gui;

import com.flashcards.controller.FlashcardController;
import com.flashcards.controller.ImportExportController;
import com.flashcards.controller.LoginController;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.domain.dto.UserDto;
import com.flashcards.service.DbFlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

// okno do dodawania/wczytywania fiszek
@Component
public class OknoNauka {

    @Autowired
    private ImportExportController importExportController;
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
        frame.setSize(600, 400);
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
            if (flashcards.size() == 0) {
                JOptionPane.showMessageDialog(frame, "Brak fiszek");
            } else {
                counter++;
                counter %= flashcards.size();
                wyswietlFiszke(flashcards.get(counter));
            }


        });
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(false);


        buttonAdd.addActionListener(e -> {
            wyczyscPola();
        });

        buttonSave.addActionListener(e -> {
            if(flashcardController.saveFlashCard(textFieldWordPl.getText(), textFieldWordEn.getText())){
                flashcards.add(new FlashcardDto(null, textFieldWordPl.getText(), textFieldWordEn.getText(), null));
            }
        });

        zapiszFiszki.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            fileChooser.showOpenDialog(frame);

            if (importExportController.zapiszDoPliku(fileChooser.getSelectedFile())) {
                JOptionPane.showMessageDialog(frame, "Pomyslnie zapisano plik");
            } else {
                JOptionPane.showMessageDialog(frame, "Nie udało sie zapisac pliku");
            }
        });

        wczytajFiszki.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            fileChooser.showOpenDialog(frame);

            if (importExportController.wczytajZpliku(fileChooser.getSelectedFile())) {
                flashcards = flashcardController.flashcardsForLoggedInUser();
                JOptionPane.showMessageDialog(frame, "Pomyslnie wczytano");
            } else {
                JOptionPane.showMessageDialog(frame, "Nie udało sie wczytac");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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
        oknoGlowne.setVisible(false);
        frame.setVisible(true);
        flashcards = flashcardController.flashcardsForLoggedInUser();
        counter = 0;
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                oknoGlowne.setVisible(true);
            }

            public void windowClosed(WindowEvent e) {
                oknoGlowne.setVisible(true);
                //getOkno().AktywujPrzyciski();
//				okno.pokazOkno();
            }
        });
    }


}
