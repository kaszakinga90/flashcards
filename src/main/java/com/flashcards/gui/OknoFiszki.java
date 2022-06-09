//package com.flashcards.gui;
//
//import listeners.JezykCheckListener;
//import listeners.StartFiszkiListener;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * Okno do rozwi¹zywania i wprowadzania fiszek
// * Jego otwarcie powoduje zablokowanie przycisków w oknie g³ównym
// * Zamkniêcie okna fiszek powoduje odblokowanie przycisków w oknie g³ównym
// * Zamkniêcie okna fiszek NIE powoduje zamkniêcia ca³ego programu
// */
//public class OknoFiszki extends JFrame
//{
//	private JTextField fiszkaAngielska;
//	private JTextField fiszkaPolska;
//	private OknoGlowne okno;
//	private JCheckBox polski;
//	private JCheckBox angielski;
//	private JButton start;
//	private JButton zakoncz;
//	private boolean czyStart;
//
//	public static ArrayList<ParaSlow> paraSlow;
//	static {
//		paraSlow = new ArrayList<>(List.of(
//				new ParaSlow("rower", "bicycle"),
//				new ParaSlow("siode³ko", "saddle"),
//				new ParaSlow("widelec", "fork"),
//				new ParaSlow("opona", "tyre"),
//				new ParaSlow("zapiêcie", "lock"),
//				new ParaSlow("rama", "frame"),
//				new ParaSlow("piasta", "hub"),
//				new ParaSlow("hamulec", "brake"),
//				new ParaSlow("amortyzator", "shock absorber"),
//				new ParaSlow("kierownica", "handlebar"),
//				new ParaSlow("przerzutka", "gear"),
//				new ParaSlow("³ancuch", "chain"),
//				new ParaSlow("zêbatka", "sprocket"),
//				new ParaSlow("dêtka", "tube"),
//				new ParaSlow("szprycha", "spoke")
//				));
//	}
//
//	public JTextField getFiszkaAngielska() {
//		return fiszkaAngielska;
//	}
//	public void setFiszkaAngielska(JTextField fiszkaAngielska) {
//		this.fiszkaAngielska = fiszkaAngielska;
//	}
//	public JTextField getFiszkaPolska() {
//		return fiszkaPolska;
//	}
//	public void setFiszkaPolska(JTextField fiszkaPolska) {
//		this.fiszkaPolska = fiszkaPolska;
//	}
//	public OknoGlowne getOkno() {
//		return okno;
//	}
//	public void setOkno(OknoGlowne okno) {
//		this.okno = okno;
//	}
//	public JCheckBox getPolski() {
//		return polski;
//	}
//	public void setPolski(JCheckBox polski) {
//		this.polski = polski;
//	}
//	public JCheckBox getAngielski() {
//		return angielski;
//	}
//	public void setAngielski(JCheckBox angielski) {
//		this.angielski = angielski;
//	}
//	public JButton getStart() {
//		return start;
//	}
//	public void setStart(JButton start) {
//		this.start = start;
//	}
//	public JButton getZakoncz() {
//		return zakoncz;
//	}
//	public void setZakoncz(JButton zakoncz) {
//		this.zakoncz = zakoncz;
//	}
//	public static ArrayList<ParaSlow> getParaSlow() {
//		return paraSlow;
//	}
//	public static void setParaSlow(ArrayList<ParaSlow> paraSlow) {
//		OknoFiszki.paraSlow = paraSlow;
//	}
//	public boolean isCzyStart() {
//		return czyStart;
//	}
//	public void setCzyStart(boolean czyStart) {
//		this.czyStart = czyStart;
//	}
//
//	public OknoFiszki(OknoGlowne okno)
//	{
//		super("Fiszki");
//		this.setOkno(okno);
//		setSize(500, 300);
//		JPanel panelGlowny = new JPanel(new GridLayout(5, 1, 10, 10));
//		panelGlowny.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		setContentPane(panelGlowny);
//
//		JPanel panel1 = new JPanel();
//		JPanel panel2 = new JPanel();
//		JPanel panel3 = new JPanel();
//		JPanel panel4 = new JPanel();
//		JPanel panel5 = new JPanel();
//		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
//		panel2.setLayout(new FlowLayout());
//		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
//		panel4.setLayout(new FlowLayout());
//		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//		fiszkaPolska = new JTextField(15);
//		fiszkaAngielska = new JTextField(15);
//		polski = new JCheckBox("polski");
//		angielski = new JCheckBox("angielski");
//		start = new JButton("START");
//		zakoncz = new JButton("Zakoñcz");
//
//		JLabel fiszki = new JLabel("FISZKI");
//		fiszki.setFont(new Font("Calibri", Font.BOLD, 26));
//
//		panel1.add(fiszki);
//		panel2.add(polski);
//		panel2.add(angielski);
//		panel3.add(start);
//		panel4.add(new JLabel("POLSKI"));
//		panel4.add(fiszkaPolska);
//		panel4.add(new JLabel("ANGIELSKI"));
//		panel4.add(fiszkaAngielska);
//		panel5.add(zakoncz);
//
//		panelGlowny.add(panel1);
//		panelGlowny.add(panel2);
//		panelGlowny.add(panel3);
//		panelGlowny.add(panel4);
//		panelGlowny.add(panel5);
//
//		JOptionPane.showMessageDialog(null, "Wybierz jêzyk, w którym chcesz rozwi¹zywaæ fiszki. Nastêpnie naciœnij <Start> i odgadnij 5 s³ów!");
//
//		StartFiszkiListener startListener = new StartFiszkiListener(this);
//		polski.addActionListener(new JezykCheckListener(polski, angielski, fiszkaPolska, fiszkaAngielska));
//		angielski.addActionListener(new JezykCheckListener(angielski, polski, fiszkaAngielska, fiszkaPolska));
//		start.addActionListener(startListener);
//		zakoncz.addActionListener(startListener);
//
//		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		/**
//		 * Ta metoda zamyka okno "Fiszki" i aktywuje z powrotem okno g³ówne
//		 */
//		addWindowListener (new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				setCzyStart(false);
//				dispose();
//			}
//			public void windowClosed(WindowEvent e) {
//				//getOkno().AktywujPrzyciski();
//				getOkno().getFrame().setVisible(true);
//			}
//		});
//		setVisible(true);
//	}
//
//	public ArrayList<ParaSlow> LosujFiszki()
//	{
//		ArrayList<ParaSlow> WylosowaneFiszki = new ArrayList<>();
//		Random rand = new Random();
//		//int tab[] = new int[5];	//sprawdzanie, czy liczba siê nie powtarza
//
//		for(int i=0; i<5; i++)
//		{
//			int losowaLiczba = rand.nextInt(getParaSlow().size());
//			WylosowaneFiszki.add(getParaSlow().get(losowaLiczba));
//		}
//
//		return WylosowaneFiszki;
//	}
//
//}
