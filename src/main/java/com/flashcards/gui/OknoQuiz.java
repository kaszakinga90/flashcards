package com.flashcards.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.flashcards.controller.QuizWynikController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

@Component
public class OknoQuiz {

    @Autowired
    private QuizWynikController quizWynikController;

    private final JFrame frame;
    private final JTextArea tekstPytania;
    private final JTextField tekstOdpA;
    private final JTextField tekstOdpB;
    private final JTextField tekstOdpC;
    private final JTextField tekstOdpD;
    private JTextField wybranaOdp;
    private final JButton buttonA;
    private final JButton buttonB;
    private final JButton buttonC;
    private final JButton buttonD;
    private final JButton startQuiz;
    private final JPanel panel;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String odpA = "odpA";
    private static final String odpB = "odpB";
    private static final String odpC = "odpC";
    private static final String odpD = "odpD";
    private boolean czyStart;

    public JButton getStartQuiz() {
        return startQuiz;
    }

    public boolean isCzyStart() {
        return czyStart;
    }

    public void setCzyStart(boolean czyStart) {
        this.czyStart = czyStart;
    }

    public JTextArea getTekstPytania() {
        return tekstPytania;
    }

    public JTextField getTekstOdpA() {
        return tekstOdpA;
    }

    public JTextField getTekstOdpB() {
        return tekstOdpB;
    }

    public JTextField getTekstOdpC() {
        return tekstOdpC;
    }

    public JTextField getTekstOdpD() {
        return tekstOdpD;
    }

    public JTextField getWybranaOdp() {
        return wybranaOdp;
    }

    public void setWybranaOdp(JTextField wybranaOdp) {
        this.wybranaOdp = wybranaOdp;
    }

    public OknoQuiz() {
        frame = new JFrame("QUIZ");
        frame.setSize(600, 400);
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(Kolory.Tlo);

        tekstPytania = new JTextArea("The text of the question will appear here.");
        tekstPytania.setEditable(false);
        tekstPytania.setFont(new Font("Calibri", Font.PLAIN, 18));
        tekstOdpA = new JTextField(20);
        tekstOdpA.setEditable(false);
        tekstOdpB = new JTextField(20);
        tekstOdpB.setEditable(false);
        tekstOdpC = new JTextField(20);
        tekstOdpC.setEditable(false);
        tekstOdpD = new JTextField(20);
        tekstOdpD.setEditable(false);
        buttonA = new JButton(" A ");
        buttonB = new JButton(" B ");
        buttonC = new JButton(" C ");
        buttonD = new JButton(" D ");
        startQuiz = new JButton("START");
        startQuiz.setFont(new Font("Calibri", Font.BOLD, 18));

        JPanel panelOdpowiedzi1 = new JPanel();
        JPanel panelOdpowiedzi2 = new JPanel();
        JPanel panelStart = new JPanel();
        panelOdpowiedzi1.setLayout(new FlowLayout());
        panelOdpowiedzi2.setLayout(new FlowLayout());
        panelStart.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(tekstPytania);
        panel.add(panelOdpowiedzi1);
        panel.add(panelOdpowiedzi2);
        panel.add(panelStart);

        panel.setBackground(Kolory.Tlo);
        panelOdpowiedzi1.setBackground(Kolory.Tlo);
        panelOdpowiedzi2.setBackground(Kolory.Tlo);
        panelStart.setBackground(Kolory.Tlo);

        panelOdpowiedzi1.add(buttonA);
        panelOdpowiedzi1.add(tekstOdpA);
        panelOdpowiedzi1.add(buttonB);
        panelOdpowiedzi1.add(tekstOdpB);
        panelOdpowiedzi2.add(buttonC);
        panelOdpowiedzi2.add(tekstOdpC);
        panelOdpowiedzi2.add(buttonD);
        panelOdpowiedzi2.add(tekstOdpD);
        panelStart.add(startQuiz);

        startQuiz.addActionListener(new StartQuizListener(this));
        QuizOdpowiedzListener buttonListener = new QuizOdpowiedzListener(this);
        buttonA.addActionListener(buttonListener);
        buttonB.addActionListener(buttonListener);
        buttonC.addActionListener(buttonListener);
        buttonD.addActionListener(buttonListener);

        obslugaKlawiatury();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setCzyStart(false);
                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    /**
     * Key Binding dla klawiszy klawiatury a, b, c, d;
     * Przyciœniêcie klawisza wywo³uje akcjê ButtonAction(JButton)
     */
    private void obslugaKlawiatury() {
        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), odpA);
        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("B"), odpB);
        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("C"), odpC);
        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), odpD);

        tekstPytania.getActionMap().put(odpA, new ButtonAction(buttonA));
        tekstPytania.getActionMap().put(odpB, new ButtonAction(buttonB));
        tekstPytania.getActionMap().put(odpC, new ButtonAction(buttonC));
        tekstPytania.getActionMap().put(odpD, new ButtonAction(buttonD));
    }

    /**
     * Zamykanie okna "QUIZ"
     *  Ta metoda zamyka okno Quiz, wysy³a sygna³ do w¹tku o zakoñczeniu i aktywuje z powrotem okno g³ówne
     */
//    private void zamknijOkno()
//    {
//        frame.addWindowListener (new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                setCzyStart(false);
//                frame.dispose();
//            }
//            public void windowClosed(WindowEvent e) {
//                okno.pokazOkno();
//            }
//        });
//        frame.setVisible(true);
//    }


    /**
     * Metoda odpowiadaj¹ca za inicjalizacjê okna quiz
     * Przywraca widocznoœæ Okna g³ównego po zamkniêciu okna Quiz
     */
    public void init(JFrame oknoGlowne) {
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "After pressing the <Start> button you will see 5 questions. Choose one of the answers: A, B, C, D (you can use the keyboard!). You have 10 seconds to answer. Good luck :)");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
                oknoGlowne.setVisible(true);
            }
        });
    }

    private class ButtonAction extends AbstractAction {
        private JButton button;

        public ButtonAction(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button.doClick();
        }
    }

    private class QuizOdpowiedzListener implements ActionListener {
        private OknoQuiz oknoQuiz;

        public OknoQuiz getOknoQuiz() {
            return oknoQuiz;
        }

        public void setOknoQuiz(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        public QuizOdpowiedzListener(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                wyczyscKolor();
                switch (e.getActionCommand()) {
                    case " A ":
                        getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpA());
                        wybierzPole(getOknoQuiz().getTekstOdpA());
                        break;
                    case " B ":
                        getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpB());
                        getOknoQuiz().getTekstOdpB().setBackground(new Color(0, 180, 255));
                        break;
                    case " C ":
                        getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpC());
                        getOknoQuiz().getTekstOdpC().setBackground(new Color(0, 180, 255));
                        break;
                    case " D ":
                        getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpD());
                        getOknoQuiz().getTekstOdpD().setBackground(new Color(0, 180, 255));
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void wyczyscKolor() {
            getOknoQuiz().getTekstOdpA().setBackground(null);
            getOknoQuiz().getTekstOdpB().setBackground(null);
            getOknoQuiz().getTekstOdpC().setBackground(null);
            getOknoQuiz().getTekstOdpD().setBackground(null);
        }

        private void wybierzPole(JTextField wybranePole) {
            wybranePole.setBackground(Kolory.Niebieski);
        }
    }


    private class StartQuizListener implements ActionListener {
        private OknoQuiz oknoQuiz;

        public OknoQuiz getOknoQuiz() {
            return oknoQuiz;
        }

        public void setOknoQuiz(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        public StartQuizListener(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (getOknoQuiz().getStartQuiz().getText().equals("START")) {
                    getOknoQuiz().setCzyStart(true);
                    new QuizThread(getOknoQuiz()).start();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private class QuizThread extends Thread {
        private OknoQuiz oknoQuiz;

        public OknoQuiz getOknoQuiz() {
            return oknoQuiz;
        }

        public void setOknoQuiz(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        public QuizThread(OknoQuiz oknoQuiz) {
            this.oknoQuiz = oknoQuiz;
        }

        @Override
        public void run() {
            JTextArea pytanie = new JTextArea();
            JTextField poleA = new JTextField();
            JTextField poleB = new JTextField();
            JTextField poleC = new JTextField();
            JTextField poleD = new JTextField();
            int wynik = 0;
            int liczbaPytan = 0;

            pytanie = getOknoQuiz().getTekstPytania();
            poleA = getOknoQuiz().getTekstOdpA();
            poleB = getOknoQuiz().getTekstOdpB();
            poleC = getOknoQuiz().getTekstOdpC();
            poleD = getOknoQuiz().getTekstOdpD();

            List<PytanieQuiz> pytaniaQuiz = new ArrayList<>();
            pytaniaQuiz = PytaniaQuiz.losujPytania();

            for (var elem : pytaniaQuiz) {
                try {
                    new OdliczanieThread(getOknoQuiz().getStartQuiz(), 10).start();
                    pytanie.setText(elem.getPytanie());
                    poleA.setText(elem.getOdp1().getTekstOdp());
                    poleB.setText(elem.getOdp2().getTekstOdp());
                    poleC.setText(elem.getOdp3().getTekstOdp());
                    poleD.setText(elem.getOdp4().getTekstOdp());
                    JTextField prawidlowaOdp = new JTextField();

                    if (poprawnaOdpowiedz(elem) == 1) prawidlowaOdp = poleA;
                    if (poprawnaOdpowiedz(elem) == 2) prawidlowaOdp = poleB;
                    if (poprawnaOdpowiedz(elem) == 3) prawidlowaOdp = poleC;
                    if (poprawnaOdpowiedz(elem) == 4) prawidlowaOdp = poleD;

                    Thread.sleep(10000);

                    if (getOknoQuiz().getWybranaOdp() == prawidlowaOdp) {
                        wynik++;
                        liczbaPytan++;
                        dobraOdpowiedz(prawidlowaOdp);
                        Thread.sleep(1000);
                    } else {
                        liczbaPytan++;
                        zlaOdpowiedz(prawidlowaOdp);
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    System.out.println("QuizThread has been terminated");
                    break;
                } finally {
                    resetujZaznaczenie();
                    if (!getOknoQuiz().isCzyStart()) break;
                }
            }
//            Statystyki.WynikQuizu.add(wynik);
//            Statystyki.RozegraneQuizy.add(liczbaPytan);
            quizWynikController.saveQuizWynik(wynik,liczbaPytan);

            if (getOknoQuiz().isCzyStart()) JOptionPane.showMessageDialog(null, "Quiz finished! Your result: " + wynik + "/" + liczbaPytan);
            getOknoQuiz().getTekstPytania().setText("The content of the question will appear here.");
            getOknoQuiz().getStartQuiz().setText("START");
        }

        private short poprawnaOdpowiedz(PytanieQuiz pytanie) {
            if (pytanie.getOdp1().isPrawdziwoscOdp()) return 1;
            if (pytanie.getOdp2().isPrawdziwoscOdp()) return 2;
            if (pytanie.getOdp3().isPrawdziwoscOdp()) return 3;
            if (pytanie.getOdp4().isPrawdziwoscOdp()) return 4;
            else return 0;
        }

        private void resetujZaznaczenie() {
            getOknoQuiz().getTekstPytania().setText("");
            getOknoQuiz().getTekstPytania().setBackground(new Color(255, 255, 255));
            getOknoQuiz().getTekstOdpA().setText("");
            getOknoQuiz().getTekstOdpA().setBackground(null);
            getOknoQuiz().getTekstOdpB().setText("");
            getOknoQuiz().getTekstOdpB().setBackground(null);
            getOknoQuiz().getTekstOdpC().setText("");
            getOknoQuiz().getTekstOdpC().setBackground(null);
            getOknoQuiz().getTekstOdpD().setText("");
            getOknoQuiz().getTekstOdpD().setBackground(null);
        }

        private void dobraOdpowiedz(JTextField prawidlowaOdp) {
            getOknoQuiz().getTekstPytania().setBackground(Kolory.Zielony);
            prawidlowaOdp.setBackground(Kolory.Zielony);
        }

        private void zlaOdpowiedz(JTextField prawidlowaOdp) {
            getOknoQuiz().getTekstPytania().setBackground(Kolory.Czerwony);
            prawidlowaOdp.setBackground(Kolory.Zielony);
            if(getOknoQuiz().getWybranaOdp() != null)
                getOknoQuiz().getWybranaOdp().setBackground(Kolory.Czerwony);
        }
    }
}
