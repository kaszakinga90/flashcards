package com.flashcards.gui;

import com.flashcards.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * Klasa obslugująca logowanie użytkownika
 */
@Component
public class Logowanie extends  AbstractCredentialWindow {

    @Autowired
    private OknoGlowne oknoGlowne;

    @Autowired
    private LoginController loginController;

    public Logowanie() {
        super("Login", "Sign in", "");
        ukryjOkno();
        buttonPrzelacz.setEnabled(false);
        buttonZapiszZarejestruj.addActionListener(e -> {
            if(loginController.login(textFieldEmail.getText(), textFieldPassword.getText())){
                JOptionPane.showMessageDialog(frame, "Successfully logged in :)");
                ukryjOkno();
                oknoGlowne.pokazOkno();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect login details");
            }

        });
    }


}
