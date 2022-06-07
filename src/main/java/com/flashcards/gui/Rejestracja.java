package com.flashcards.gui;

import javax.swing.*;

public class Rejestracja extends AbstractCredentialWindow {

    public Rejestracja(){
        super("Rejestracja", "Zarejestruj", "Zaloguj się");
    }

    public void showErrorMessage(){
        JOptionPane.showMessageDialog(frame, "Taki uzytkownik juz istnieje w bazie!");
    }

}
