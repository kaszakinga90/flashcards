//package stareKlasy.listeners;
//
//import program.OknoQuiz;
//import threads.QuizThread;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class StartQuizListener implements ActionListener
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
//	public StartQuizListener(OknoQuiz oknoQuiz)
//	{
//		this.oknoQuiz = oknoQuiz;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		try
//		{
//			if(getOknoQuiz().getStartQuiz().getText().equals("START"))
//			{
//				getOknoQuiz().setCzyStart(true);
//				new QuizThread(getOknoQuiz()).start();
//			}
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//}
