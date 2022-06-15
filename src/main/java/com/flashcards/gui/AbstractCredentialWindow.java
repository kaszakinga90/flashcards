package com.flashcards.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class AbstractCredentialWindow {

    protected final JFrame frame;
    protected final JTextField textFieldEmail;
    protected final JLabel labelEmail;
    protected final JTextField textFieldPassword;
    protected final JLabel labelPassword;
    protected final JButton buttonZapiszZarejestruj;
    protected final JButton buttonPrzelacz;
    protected JPanel panelGlowny;

    public AbstractCredentialWindow(String oknoGlowne, String zapisz, String przelacz){
        frame = new JFrame(oknoGlowne);
        frame.setSize(300,400);
        textFieldEmail = new JTextField();
        labelEmail = new JLabel("Podaj email");
        textFieldPassword = new JTextField();
        labelPassword = new JLabel("Podaj haslo");
        buttonZapiszZarejestruj = new JButton(zapisz);
        buttonPrzelacz = new JButton(przelacz);
        panelGlowny = new JPanel(new GridLayout(6,1,5,10));
        panelGlowny.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
        frame.setContentPane(panelGlowny);

        panelGlowny.add(labelEmail);
        panelGlowny.add(textFieldEmail);
        panelGlowny.add(labelPassword);
        panelGlowny.add(textFieldPassword);
        panelGlowny.add(buttonZapiszZarejestruj);
        panelGlowny.add(buttonPrzelacz);

        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        pokazOkno();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void pokazOkno(){
        frame.setVisible(true);
    }
    public void zamknijOkno(){
        frame.dispose();
    }
    public void ukryjOkno() { frame.setVisible(false);}
}
