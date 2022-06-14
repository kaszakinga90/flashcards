package com.flashcards.gui;

import com.flashcards.controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * Klasa obslugująca rejestrację użytkownika
 * Z tego miejsca istnieje możliwość przełączenia się na okno logowania
 * (z myślą o użytkownikach uprzednio zarejestrowanych)
 */
@Component
public class Rejestracja extends AbstractCredentialWindow {

    @Autowired
    private RegisterController registerController;

    @Autowired
    private OknoGlowne oknoGlowne;

    @Autowired
    private Logowanie logowanie;

    public Rejestracja(){
        super("Rejestracja", "Zarejestruj", "Zaloguj się");
        buttonZapiszZarejestruj.addActionListener(e -> {
            if(registerController.register(textFieldEmail.getText(), textFieldPassword.getText())){
                JOptionPane.showMessageDialog(frame, "pomyslnie zarejestrowano");
                ukryjOkno();
                logowanie.ukryjOkno();
                oknoGlowne.pokazOkno();
            } else {
                JOptionPane.showMessageDialog(frame, "Taki uzytkownik juz istnieje w bazie!");
            }
        });

        buttonPrzelacz.addActionListener(e -> {
            ukryjOkno();
            logowanie.pokazOkno();

        });
    }

}
