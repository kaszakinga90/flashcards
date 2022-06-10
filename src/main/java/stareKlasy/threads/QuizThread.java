//package stareKlasy.threads;
//
//import program.OknoQuiz;
//import program.PytaniaQuiz;
//import program.PytanieQuiz;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuizThread extends Thread
//{
//	private OknoQuiz oknoQuiz;
//
//	public OknoQuiz getOknoQuiz() {
//		return oknoQuiz;
//	}
//	public void setOknoQuiz(OknoQuiz oknoQuiz) {
//		this.oknoQuiz = oknoQuiz;
//	}
//
//	public QuizThread(OknoQuiz oknoQuiz)
//	{
//		this.oknoQuiz = oknoQuiz;
//	}
//
//	@Override
//	public void run()
//	{
//		JTextArea pytanie = new JTextArea();
//		JTextField poleA = new JTextField();
//		JTextField poleB = new JTextField();
//		JTextField poleC = new JTextField();
//		JTextField poleD = new JTextField();
//
//		pytanie = getOknoQuiz().getTekstPytania();
//		poleA = getOknoQuiz().getTekstOdpA();
//		poleB = getOknoQuiz().getTekstOdpB();
//		poleC = getOknoQuiz().getTekstOdpC();
//		poleD = getOknoQuiz().getTekstOdpD();
//
//		List<PytanieQuiz> pytaniaQuiz = new ArrayList<>();
//		pytaniaQuiz = PytaniaQuiz.losujPytania();
//
//		for(var elem : pytaniaQuiz)
//		{
//			try
//			{
//				new OdliczanieThread(getOknoQuiz().getStartQuiz(), 10).start();
//				pytanie.setText(elem.getPytanie());
//				poleA.setText(elem.getOdp1().getTekstOdp());
//				poleB.setText(elem.getOdp2().getTekstOdp());
//				poleC.setText(elem.getOdp3().getTekstOdp());
//				poleD.setText(elem.getOdp4().getTekstOdp());
//				JTextField prawidlowaOdp = new JTextField();
//
//                if(poprawnaOdpowiedz(elem) == 1) prawidlowaOdp = poleA;
//                if(poprawnaOdpowiedz(elem) == 2) prawidlowaOdp = poleB;
//                if(poprawnaOdpowiedz(elem) == 3) prawidlowaOdp = poleC;
//                if(poprawnaOdpowiedz(elem) == 4) prawidlowaOdp = poleD;
//
//				Thread.sleep(10000);
//
//				if(getOknoQuiz().getWybranaOdp() == prawidlowaOdp)
//				{
//					dobraOdpowiedz(prawidlowaOdp);
//					Thread.sleep(1000);
//				}
//				else
//				{
//					zlaOdpowiedz(prawidlowaOdp);
//					Thread.sleep(1000);
//				}
//
//			}
//			catch(InterruptedException e)
//			{
//				System.out.println("W¹tek QuizThread zosta³ przerwany");
//				break;	//wyskakuje z for
//			}
//			finally
//			{
//				resetujZaznaczenie();
//				if(getOknoQuiz().isCzyStart() == false) break;
//			}
//		}
//		if(getOknoQuiz().isCzyStart() == true) JOptionPane.showMessageDialog(null, "Quiz zakoñczony!");
//		getOknoQuiz().getTekstPytania().setText("Tutaj pojawi siê treœæ pytania.");
//		getOknoQuiz().getStartQuiz().setText("START");
//	}
//
//	private short poprawnaOdpowiedz(PytanieQuiz pytanie)
//	{
//		if(pytanie.getOdp1().isPrawdziwoscOdp()) return 1;
//		if(pytanie.getOdp2().isPrawdziwoscOdp()) return 2;
//		if(pytanie.getOdp3().isPrawdziwoscOdp()) return 3;
//		if(pytanie.getOdp4().isPrawdziwoscOdp()) return 4;
//		else return 0;
//	}
//	private void resetujZaznaczenie()
//	{
//		getOknoQuiz().getTekstPytania().setText("");
//		getOknoQuiz().getTekstPytania().setBackground(new Color(255,255,255));
//		getOknoQuiz().getTekstOdpA().setText("");
//		getOknoQuiz().getTekstOdpA().setBackground(null);
//		getOknoQuiz().getTekstOdpB().setText("");
//		getOknoQuiz().getTekstOdpB().setBackground(null);
//		getOknoQuiz().getTekstOdpC().setText("");
//		getOknoQuiz().getTekstOdpC().setBackground(null);
//		getOknoQuiz().getTekstOdpD().setText("");
//		getOknoQuiz().getTekstOdpD().setBackground(null);
//	}
//	private void dobraOdpowiedz(JTextField prawidlowaOdp)
//	{
//		getOknoQuiz().getTekstPytania().setBackground(new Color(50,205,50));
//		prawidlowaOdp.setBackground(new Color(50,205,50));
//	}
//	private void zlaOdpowiedz(JTextField prawidlowaOdp)
//	{
//        getOknoQuiz().getTekstPytania().setBackground(new Color(255,0,0));
//        prawidlowaOdp.setBackground(new Color(50,205,50));
//        if(getOknoQuiz().getWybranaOdp() != null)
//            getOknoQuiz().getWybranaOdp().setBackground(new Color(255,0,0));
//	}
//
//}
