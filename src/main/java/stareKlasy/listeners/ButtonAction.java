package stareKlasy.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 
 * @author marci
 *
 */
public class ButtonAction extends AbstractAction
{
	private JButton button;

	public ButtonAction(JButton button)
	{
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		button.doClick();
	}
}
