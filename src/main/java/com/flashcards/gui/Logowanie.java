package com.flashcards.gui;

import com.flashcards.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class Logowanie extends  AbstractCredentialWindow {

    @Autowired
    private OknoGlowne oknoGlowne;

    @Autowired
    private LoginController loginController;

    public Logowanie() {
        super("logowanie", "zaloguj", "");
        buttonPrzelacz.setEnabled(false);
        buttonZapiszZarejestruj.addActionListener(e -> {
            if(loginController.login(textFieldEmail.getText(), textFieldPassword.getText())){
                JOptionPane.showMessageDialog(frame, "pomyslnie zalogowano");
                ukryjOkno();
                oknoGlowne.pokazOkno();
            } else {
                JOptionPane.showMessageDialog(frame, "Bledne dane logowania");
            }

        });
    }


}
