package com.flashcards.gui;

//import listeners.JezykCheckListener;
//import listeners.StartFiszkiListener;

import com.flashcards.controller.FiszkiTestWynikController;
import com.flashcards.controller.FlashcardController;
import com.flashcards.domain.dto.FlashcardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stareKlasy.Statystyki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Okno do rozwi¹zywania i wprowadzania fiszek
 * Jego otwarcie powoduje zablokowanie przycisków w oknie g³ównym
 * Zamkniêcie okna fiszek powoduje odblokowanie przycisków w oknie g³ównym
 * Zamkniêcie okna fiszek NIE powoduje zamkniêcia ca³ego programu
 */
@Component
public class OknoFiszki extends JFrame
{
	private JTextField fiszkaAngielska;
	private JTextField fiszkaPolska;
	private JCheckBox polski;
	private JCheckBox angielski;
	private JButton start;
	private JButton zakoncz;
	private boolean czyStart;

	private List<FlashcardDto> flashcards = List.of();

	@Autowired
	private FlashcardController flashcardController;

	@Autowired
	private FiszkiTestWynikController fiszkiTestWynikController;


	public OknoFiszki()
	{
		super("Fiszki");
		setSize(500, 300);
		JPanel panelGlowny = new JPanel(new GridLayout(5, 1, 10, 10));
		panelGlowny.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(panelGlowny);

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
		polski = new JCheckBox("polski");
		angielski = new JCheckBox("angielski");
		start = new JButton("START");
		zakoncz = new JButton("Zakoñcz");

		JLabel fiszki = new JLabel("FISZKI");
		fiszki.setFont(new Font("Calibri", Font.BOLD, 26));

		panel1.add(fiszki);
		panel2.add(polski);
		panel2.add(angielski);
		panel3.add(start);
		panel4.add(new JLabel("POLSKI"));
		panel4.add(fiszkaPolska);
		panel4.add(new JLabel("ANGIELSKI"));
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
		/**
		 * Ta metoda zamyka okno "Fiszki" i aktywuje z powrotem okno g³ówne
		 */

		addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				czyStart = false;
				dispose();
			}
			public void windowClosed(WindowEvent e) {
				//getOkno().AktywujPrzyciski();
//				okno.pokazOkno();
			}
		});
		setVisible(false);
	}

	public void init(){
		flashcards = flashcardController.flashcardsForLoggedInUser();

		setVisible(true);
		JOptionPane.showMessageDialog(null, "Wybierz jêzyk, w którym chcesz rozwi¹zywaæ fiszki. Nastêpnie naciœnij <Start> i odgadnij 5 s³ów!");
	}

	/**
	 * Listener do przycisku START w oknie Fiszki
	 * Gdy któryœ checkbox jest zaznaczony, po naciœniêciu tego przycisku w³¹czy siê tryb odgadywania fiszek (FiszkiThread)
	 * Ponadto ten przycisk po wciœniêciu zamieni siê w timer odliczaj¹cy czas (ten w¹tek zostaje uruchomiony ju¿ w FiszkiThread)
	 */
	private class StartFiszkiListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(e.getActionCommand().equals("START") && start.getText().equals("START"))
				{
					if((polski.isSelected() && !angielski.isSelected())
							|| (!polski.isSelected() && angielski.isSelected()))
					{
						dezaktywujPrzyciski();
						//System.out.println("Zgadywanie angielskich s³ów");
						czyStart = true;
						new FiszkiThread().start();
					}
				}
				if(e.getActionCommand().equals("Zakoñcz"))
					czyStart = false;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		}

		private void dezaktywujPrzyciski()
		{
			polski.setEnabled(false);
			angielski.setEnabled(false);
		}

	}

	/**
	 * W¹tek zaczyna pokazywaæ s³owa po polsku lub angielsku (w zale¿noœci od wybranego trybu) i nale¿y dopasowaæ do nich t³umaczenie.
	 * U¿ytkownik ma 5 sekund na odpowiedz. Po tym czasie pole tekstowe zostanie zaznaczone na zielono lub czerwono zale¿nie, czy zosta³a udzielona prawid³owa odpowiedz.
	 * Dodatkowo na czas 1 sekundy zostanie pokazana prawid³owa odpowiedz.
	 * Ten w¹tek uruchamia wewn¹trz kolejny w¹tek: odliczanie czasu odpowiedzi (OdliczanieThread)
	 */
	private class FiszkiThread extends Thread
	{
		@Override
		public void run()
		{
			JTextField pole1 = new JTextField();
			JTextField pole2 = new JTextField();
			boolean jezyk;
			int wynik = 0;
			int liczbaFiszek = 0;


			if(polski.isSelected())
			{
				pole1 = fiszkaPolska;
				pole2 = fiszkaAngielska;
				jezyk = true;
			}
			else
			{
				pole2 = fiszkaPolska;
				pole1 = fiszkaAngielska;
				jezyk = false;
			}

			for(var elem : flashcards)
			{
				try
				{
					new OdliczanieThread(start, 5).start();
					pole1.setText(polski.isSelected()? elem.getSlowoPolskie() : elem.getSlowoAngielskie());
					Thread.sleep(5000);
					if(pole2.getText().equals(polski.isSelected() ? elem.getSlowoAngielskie() : elem.getSlowoPolskie()))
					{
						wynik++;
						liczbaFiszek++;
						dobraOdpowiedz(pole2);
						Thread.sleep(1000);
						wyzerujPole(pole2);
					}
					else
					{
						liczbaFiszek++;
						zlaOdpowiedz(pole2, elem, jezyk);
						Thread.sleep(1000);
						wyzerujPole(pole2);
					}
				}
				catch(InterruptedException e)
				{
					System.out.println("W¹tek siê zatrzymuje");
					break;	//wyskakuje z for
				}
				finally
				{
					fiszkaAngielska.setText("");
					fiszkaPolska.setText("");
				}
			}
			fiszkiTestWynikController.saveFiskiTestWynik(wynik, liczbaFiszek);

			Statystyki.RozegraneFiszki.add(liczbaFiszek);
			if(czyStart) JOptionPane.showMessageDialog(null, "Cykl fiszek zakoñczony!");
			aktywujPrzyciski();
		}

		private void dobraOdpowiedz(JTextField pole)
		{
			pole.setEditable(false);
			pole.setBackground(new Color(50,205,50));
		}
		private void zlaOdpowiedz(JTextField pole, FlashcardDto flashcardDto, boolean jezyk)
		{
			pole.setBackground(new Color(255,0,0));
			pole.setText(jezyk ? flashcardDto.getSlowoAngielskie() : flashcardDto.getSlowoPolskie());
		}
		private void wyzerujPole(JTextField pole)
		{
			pole.setBackground(new Color(255,255,255));
			pole.setText("");
			pole.setEditable(true);
		}
		private void aktywujPrzyciski()
		{
			polski.setEnabled(true);
			angielski.setEnabled(true);
			start.setText("START");
		}
	}


}
