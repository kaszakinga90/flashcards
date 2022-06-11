package com.flashcards.gui;

import com.flashcards.controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

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
