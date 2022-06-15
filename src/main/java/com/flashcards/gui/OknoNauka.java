package com.flashcards.gui;

import com.flashcards.controller.FlashcardController;
import com.flashcards.controller.ImportExportController;
import com.flashcards.controller.LoginController;
import com.flashcards.domain.dto.FlashcardDto;
import com.flashcards.service.DbFlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Okno to służy do uruchomienia obsługi fiszek i ich przeglądania
 * Użytkownik może dodawać fiszki, wczytywać je z pliku i do pliku zapisywać
 */
@Component
public class OknoNauka {

    @Autowired
    private ImportExportController importExportController;

    @Autowired
    DbFlashcardService dbFlashcardService;
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
    private int counter = 0;

    @Autowired
    public OknoNauka(LoginController loginController, FlashcardController flashcardController) {
        this.loginController = loginController;
        this.flashcardController = flashcardController;

        frame = new JFrame("Learning");
        frame.setSize(600, 400);
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        labelWordPl = new JLabel("PL word");
        labelWordEn = new JLabel("En word");
        textFieldWordPl = new JTextField();
        textFieldWordEn = new JTextField();
        buttonNext = new JButton("Next");
        buttonAdd = new JButton("Add");
        buttonSave = new JButton("Save");
        wczytajFiszki = new JButton("Load flashcards");
        zapiszFiszki = new JButton("Save to file");
        panel.add(labelWordPl);
        panel.add(labelWordEn);
        panel.add(textFieldWordPl);
        panel.add(textFieldWordEn);
        panel.add(buttonNext);
        panel.add(buttonAdd);
        panel.add(buttonSave);
        panel.add(wczytajFiszki);
        panel.add(zapiszFiszki);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(false);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        buttonNext.addActionListener(e -> {
            if (flashcards.size() == 0) {
                JOptionPane.showMessageDialog(frame, "No flashcards!");
            } else {
                counter++;
                counter %= flashcards.size();
                wyswietlFiszke(flashcards.get(counter));
            }
        });
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        buttonAdd.addActionListener(e -> {
            wyczyscPola();
        });

        buttonSave.addActionListener(e -> {
            if (flashcardController.saveFlashCard(textFieldWordPl.getText(), textFieldWordEn.getText())) {
                flashcards.add(new FlashcardDto(null, textFieldWordPl.getText(), textFieldWordEn.getText(), null));
            }
        });

        zapiszFiszki.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            fileChooser.showOpenDialog(frame);

            if (importExportController.zapiszDoPliku(fileChooser.getSelectedFile())) {
                JOptionPane.showMessageDialog(frame, "File saved successfully");
            } else {
                JOptionPane.showMessageDialog(frame, "File saving failed");
            }
        });

        wczytajFiszki.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            fileChooser.showOpenDialog(frame);

            if (importExportController.wczytajZpliku(fileChooser.getSelectedFile())) {
                flashcards = flashcardController.flashcardsForLoggedInUser();
                JOptionPane.showMessageDialog(frame, "File saved successfully");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to load");
            }
        });
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
            }
        });
    }


}
