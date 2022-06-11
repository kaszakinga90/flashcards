//package program;
//
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.KeyStroke;
//
//import listeners.ButtonAction;
//import listeners.QuizOdpowiedzListener;
//import listeners.StartQuizListener;
//
//public class OknoQuiz
//{
//    private final JFrame frame;
//    private final JTextArea tekstPytania;
//    private final JTextField tekstOdpA;
//    private final JTextField tekstOdpB;
//    private final JTextField tekstOdpC;
//    private final JTextField tekstOdpD;
//    private JTextField wybranaOdp;
//    private final JButton buttonA;
//    private final JButton buttonB;
//    private final JButton buttonC;
//    private final JButton buttonD;
//    private final JButton startQuiz;
//    private final JPanel panel;
//    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
//    private static final String odpA = "odpA";
//    private static final String odpB = "odpB";
//    private static final String odpC = "odpC";
//    private static final String odpD = "odpD";
//    private OknoGlowne okno;
//    private boolean czyStart;
//
//    public OknoGlowne getOkno() {
//        return okno;
//    }
//    public void setOkno(OknoGlowne okno) {
//        this.okno = okno;
//    }
//    public JButton getStartQuiz() {
//        return startQuiz;
//    }
//    public boolean isCzyStart() {
//        return czyStart;
//    }
//    public void setCzyStart(boolean czyStart) {
//        this.czyStart = czyStart;
//    }
//    public JTextArea getTekstPytania() {
//        return tekstPytania;
//    }
//    public JTextField getTekstOdpA() {
//        return tekstOdpA;
//    }
//    public JTextField getTekstOdpB() {
//        return tekstOdpB;
//    }
//    public JTextField getTekstOdpC() {
//        return tekstOdpC;
//    }
//    public JTextField getTekstOdpD() {
//        return tekstOdpD;
//    }
//    public JTextField getWybranaOdp() {
//        return wybranaOdp;
//    }
//    public void setWybranaOdp(JTextField wybranaOdp) {
//        this.wybranaOdp = wybranaOdp;
//    }
//
//    public OknoQuiz(OknoGlowne okno)
//    {
//        frame = new JFrame("QUIZ");
//        this.okno = okno;
//        frame.setSize(600, 300);
//        panel = new JPanel();
//        frame.setContentPane(panel);
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        panel.setLayout(new GridLayout(4, 1, 10, 10));
//        JOptionPane.showMessageDialog(null, "Po naciœniêciu przycisku <Start> zobaczysz 5 pytañ. Wybierz jedn¹ z odpowiedzi: A, B, C, D (mo¿esz u¿yæ klawiatury!). Na odpowiedz masz 10 sekund. Powodzenia :)");
//        tekstPytania = new JTextArea("Tutaj pojawi siê treœæ pytania.");
//        tekstPytania.setEditable(false);
//        tekstOdpA = new JTextField(20);
//        tekstOdpA.setEditable(false);
//        tekstOdpB = new JTextField(20);
//        tekstOdpB.setEditable(false);
//        tekstOdpC = new JTextField(20);
//        tekstOdpC.setEditable(false);
//        tekstOdpD = new JTextField(20);
//        tekstOdpD.setEditable(false);
//        buttonA = new JButton(" A ");
//        buttonB = new JButton(" B ");
//        buttonC = new JButton(" C ");
//        buttonD = new JButton(" D ");
//        startQuiz = new JButton("START");
//
//        JPanel panelOdpowiedzi1 = new JPanel();
//        JPanel panelOdpowiedzi2 = new JPanel();
//        JPanel panelStart = new JPanel();
//        panelOdpowiedzi1.setLayout(new FlowLayout());
//        panelOdpowiedzi2.setLayout(new FlowLayout());
//        panelStart.setLayout(new FlowLayout(FlowLayout.CENTER));
//        panel.add(tekstPytania);
//        panel.add(panelOdpowiedzi1);
//        panel.add(panelOdpowiedzi2);
//        panel.add(panelStart);
//
//        panelOdpowiedzi1.add(buttonA);
//        panelOdpowiedzi1.add(tekstOdpA);
//        panelOdpowiedzi1.add(buttonB);
//        panelOdpowiedzi1.add(tekstOdpB);
//        panelOdpowiedzi2.add(buttonC);
//        panelOdpowiedzi2.add(tekstOdpC);
//        panelOdpowiedzi2.add(buttonD);
//        panelOdpowiedzi2.add(tekstOdpD);
//        panelStart.add(startQuiz);
//
//        startQuiz.addActionListener(new StartQuizListener(this));
//        QuizOdpowiedzListener buttonListener = new QuizOdpowiedzListener(this);
//        buttonA.addActionListener(buttonListener);
//        buttonB.addActionListener(buttonListener);
//        buttonC.addActionListener(buttonListener);
//        buttonD.addActionListener(buttonListener);
//
//        obslugaKlawiatury();
//
//        zamknijOkno();
//    }
//
//    /**
//     * Key Binding dla klawiszy klawiatury a, b, c, d;
//     * Przyciœniêcie klawisza wywo³uje akcjê ButtonAction(JButton)
//     */
//    private void obslugaKlawiatury()
//    {
//        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), odpA);
//        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("B"), odpB);
//        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("C"), odpC);
//        tekstPytania.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), odpD);
//
//        tekstPytania.getActionMap().put(odpA, new ButtonAction(buttonA));
//        tekstPytania.getActionMap().put(odpB, new ButtonAction(buttonB));
//        tekstPytania.getActionMap().put(odpC, new ButtonAction(buttonC));
//        tekstPytania.getActionMap().put(odpD, new ButtonAction(buttonD));
//    }
//
//    /**
//     * Zamykanie okna "QUIZ"
//     *  Ta metoda zamyka okno Quiz, wysy³a sygna³ do w¹tku o zakoñczeniu i aktywuje z powrotem okno g³ówne
//     */
//    private void zamknijOkno()
//    {
//        frame.addWindowListener (new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                setCzyStart(false);
//                frame.dispose();
//            }
//            public void windowClosed(WindowEvent e) {
//                getOkno().getFrame().setVisible(true);
//            }
//        });
//        frame.setVisible(true);
//    }
//
//}
