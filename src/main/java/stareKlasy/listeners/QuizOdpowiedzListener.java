//package stareKlasy.listeners;
//
//import program.OknoQuiz;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class QuizOdpowiedzListener implements ActionListener
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
//	public QuizOdpowiedzListener(OknoQuiz oknoQuiz)
//	{
//		this.oknoQuiz = oknoQuiz;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		try
//		{
//			wyczyscKolor();
//			switch(e.getActionCommand())
//			{
//				case " A ":
//					getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpA());
//					wybierzPole(getOknoQuiz().getTekstOdpA());
//					break;
//				case " B ":
//					getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpB());
//					getOknoQuiz().getTekstOdpB().setBackground(new Color(0,180,255));
//					break;
//				case " C ":
//					getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpC());
//					getOknoQuiz().getTekstOdpC().setBackground(new Color(0,180,255));
//					break;
//				case " D ":
//					getOknoQuiz().setWybranaOdp(getOknoQuiz().getTekstOdpD());
//					getOknoQuiz().getTekstOdpD().setBackground(new Color(0,180,255));
//					break;
//			}
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//	private void wyczyscKolor()
//	{
//		getOknoQuiz().getTekstOdpA().setBackground(null);
//		getOknoQuiz().getTekstOdpB().setBackground(null);
//		getOknoQuiz().getTekstOdpC().setBackground(null);
//		getOknoQuiz().getTekstOdpD().setBackground(null);
//	}
//	private void wybierzPole(JTextField wybranePole)
//	{
//		wybranePole.setBackground(new Color(0,180,255));
//	}
//
//}
