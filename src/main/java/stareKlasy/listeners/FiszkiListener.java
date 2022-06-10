//package stareKlasy.listeners;
//
//import program.OknoFiszki;
//import program.OknoGlowne;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * Akcja wywo³ana przyciskiem Fiszki
// * Otwiera okno fiszek oraz dezaktywuje przyciski w OknieGlownym
// */
//public class FiszkiListener implements ActionListener
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
//	public FiszkiListener(OknoGlowne okno)
//	{
//		super();
//		this.okno = okno;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e)
//	{
//		try
//		{
//			new OknoFiszki(getOkno());
//			//getOkno().DezaktywujPrzyciski();
//			getOkno().getFrame().setVisible(false);
//
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//}
