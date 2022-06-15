package com.flashcards.gui;

import com.flashcards.controller.FiszkiTestWynikController;
import com.flashcards.controller.FlashcardController;
import com.flashcards.domain.dto.FlashcardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Okno do rozwi�zywania i wprowadzania fiszek
 * Jego otwarcie powoduje ukrycie okna g��wnego
 * Zamkni�cie okna fiszek powoduje pokazanie okna g��wnego
 * Zamkni�cie okna fiszek NIE powoduje zamkni�cia ca�ego programu
 */
@Component
public class OknoFiszki {
    private JTextField fiszkaAngielska;
    private JTextField fiszkaPolska;
    private JCheckBox polski;
    private JCheckBox angielski;
    private JButton start;
    private JButton zakoncz;
    private boolean czyStart;
    private final JFrame frame;

    private List<FlashcardDto> flashcards = List.of();

    @Autowired
    private FlashcardController flashcardController;

    @Autowired
    private FiszkiTestWynikController fiszkiTestWynikController;

    public OknoFiszki() {
        frame = new JFrame("Flashcards - test");
        frame.setSize(600, 400);
        JPanel panelGlowny = new JPanel(new GridLayout(5, 1, 10, 10));
        panelGlowny.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(panelGlowny);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER));

        fiszkaPolska = new JTextField(15);
        fiszkaAngielska = new JTextField(15);
        polski = new JCheckBox("polish");
        angielski = new JCheckBox("english");
        start = new JButton("START");
        zakoncz = new JButton("End");

        JLabel fiszki = new JLabel("FLASHCARDS");
        fiszki.setFont(new Font("Calibri", Font.BOLD, 26));

        panel1.add(fiszki);
        panel2.add(polski);
        panel2.add(angielski);
        panel3.add(start);
        panel4.add(new JLabel("POLISH"));
        panel4.add(fiszkaPolska);
        panel4.add(new JLabel("ENGLISH"));
        panel4.add(fiszkaAngielska);
        panel5.add(zakoncz);

        panelGlowny.add(panel1);
        panelGlowny.add(panel2);
        panelGlowny.add(panel3);
        panelGlowny.add(panel4);
        panelGlowny.add(panel5);

        StartFiszkiListener startListener = new StartFiszkiListener();
        polski.addActionListener(new JezykCheckListener(polski, angielski, fiszkaPolska, fiszkaAngielska));
        angielski.addActionListener(new JezykCheckListener(angielski, polski, fiszkaAngielska, fiszkaPolska));
        start.addActionListener(startListener);
        zakoncz.addActionListener(startListener);

        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                czyStart = false;
                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    /**
     * Ta metoda inicjalizuje pojawienie si� okna dla fiszek-test�w
     * Zbi�r fiszek jest indywidualny dla ka�dego u�ytkownika
     */
    public void init(JFrame oknoGlowne) {
        //oknoGlowne.setVisible(false);
        flashcards = flashcardController.flashcardsForLoggedInUser();

        if (flashcards.size() < 5) {
            oknoGlowne.setVisible(true);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "The test cannot be performed. There must be at least 5 cards in the database!");
            czyStart = false;
            frame.setVisible(false);
            oknoGlowne.setVisible(true);
        } else {
            czyStart = true;
            frame.setVisible(true);
            JOptionPane.showMessageDialog(null, "Choose the language you want to solve the flashcards in. Then press <Start> and guess 5 words!");
        }

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                oknoGlowne.setVisible(true);
            }

            public void windowClosed(WindowEvent e) {
                oknoGlowne.setVisible(true);
            }
        });
    }

    /**
     * Listener do przycisku START w oknie Fiszki
     * Gdy kt�ry� checkbox jest zaznaczony, po naci�ni�ciu tego przycisku w��czy si� tryb odgadywania fiszek (FiszkiThread)
     * Ponadto ten przycisk po wci�ni�ciu zamieni si� w timer odliczaj�cy czas (ten w�tek zostaje uruchomiony ju� w FiszkiThread)
     */
    private class StartFiszkiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getActionCommand().equals("START") && start.getText().equals("START")) {

                    if ((polski.isSelected() && !angielski.isSelected())
                            || (!polski.isSelected() && angielski.isSelected())) {
                        dezaktywujPrzyciski();
                        czyStart = true;
                        new FiszkiThread().start();
                    }
                }
                if (e.getActionCommand().equals("End")) {
                    czyStart = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void dezaktywujPrzyciski() {
            polski.setEnabled(false);
            angielski.setEnabled(false);
        }
    }

    /**
     * W�tek zaczyna pokazywa� s�owa po polsku lub angielsku (w zale�no�ci od wybranego trybu) i nale�y dopasowa� do nich t�umaczenie.
     * U�ytkownik ma 5 sekund na odpowiedz. Po tym czasie pole tekstowe zostanie zaznaczone na zielono lub czerwono zale�nie, czy zosta�a udzielona prawid�owa odpowiedz.
     * Dodatkowo na czas 1 sekundy zostanie pokazana prawid�owa odpowiedz.
     * Ten w�tek uruchamia wewn�trz kolejny w�tek: odliczanie czasu odpowiedzi (OdliczanieThread)
     */
    private class FiszkiThread extends Thread {
        @Override
        public void run() {
            JTextField pole1 = new JTextField();
            JTextField pole2 = new JTextField();
            boolean jezyk;
            int wynik = 0;
            int liczbaFiszek = 0;


            if (polski.isSelected()) {
                pole1 = fiszkaPolska;
                pole2 = fiszkaAngielska;
                jezyk = true;
            } else {
                pole2 = fiszkaPolska;
                pole1 = fiszkaAngielska;
                jezyk = false;
            }

            for (var elem : flashcards) {
                try {
                    new OdliczanieThread(start, 5).start();
                    pole1.setText(polski.isSelected() ? elem.getSlowoPolskie() : elem.getSlowoAngielskie());
                    Thread.sleep(5000);
                    if (pole2.getText().equals(polski.isSelected() ? elem.getSlowoAngielskie() : elem.getSlowoPolskie())) {
                        wynik++;
                        liczbaFiszek++;
                        dobraOdpowiedz(pole2);
                        Thread.sleep(1000);
                        wyzerujPole(pole2);
                    } else {
                        liczbaFiszek++;
                        zlaOdpowiedz(pole2, elem, jezyk);
                        Thread.sleep(1000);
                        wyzerujPole(pole2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("The thread stops");
                    break;    //wyskakuje z for
                } finally {
                    fiszkaAngielska.setText("");
                    fiszkaPolska.setText("");
                }
            }
            fiszkiTestWynikController.saveFiskiTestWynik(wynik, liczbaFiszek);

//			Statystyki.RozegraneFiszki.add(liczbaFiszek);
            if (czyStart) JOptionPane.showMessageDialog(null, "Series completed!");
            aktywujPrzyciski();
        }

        private void dobraOdpowiedz(JTextField pole) {
            pole.setEditable(false);
            pole.setBackground(new Color(50, 205, 50));
        }

        private void zlaOdpowiedz(JTextField pole, FlashcardDto flashcardDto, boolean jezyk) {
            pole.setBackground(new Color(255, 0, 0));
            pole.setText(jezyk ? flashcardDto.getSlowoAngielskie() : flashcardDto.getSlowoPolskie());
        }

        private void wyzerujPole(JTextField pole) {
            pole.setBackground(new Color(255, 255, 255));
            pole.setText("");
            pole.setEditable(true);
        }

        private void aktywujPrzyciski() {
            polski.setEnabled(true);
            angielski.setEnabled(true);
            start.setText("START");
        }
    }


}
