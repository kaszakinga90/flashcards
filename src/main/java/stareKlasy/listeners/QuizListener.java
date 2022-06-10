//package stareKlasy.listeners;
//
//import program.OknoGlowne;
//import program.OknoQuiz;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Akcja wywo³ana przyciskiem QUIZ
// * Otwiera okno quizu oraz ukrywa Okno g³ówne
// */
//public class QuizListener implements ActionListener
//{
//	private OknoGlowne okno;
//
//	public OknoGlowne getOkno() {
//		return okno;
//	}
//	public void setOkno(OknoGlowne okno) {
//		this.okno = okno;
//	}
//
//	public QuizListener(OknoGlowne okno)
//	{
//		super();
//		this.okno = okno;
//	}
//
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		try
//		{
//			new OknoQuiz(getOkno());
//			getOkno().getFrame().setVisible(false);
//
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//	}
//
//}
