package com.flashcards.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Akcja wywo³ana CheckBoxami polski/angielski w karcie Fiszki
 * Pozwala na rozpoczêcie wpisywania t³umaczeñ w wybranym jezyku
 */
public class JezykCheckListener implements ActionListener
{
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JTextField pole1;
	private JTextField pole2;
	
	public JezykCheckListener(JCheckBox checkbox1, JCheckBox checkbox2, JTextField pole1, JTextField pole2)
	{
		this.checkbox1 = checkbox1;
		this.checkbox2 = checkbox2;
		this.pole1 = pole1;
		this.pole2 = pole2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			if(checkbox1.isSelected())
			{
				dezaktywacja(pole1, pole2);
				pole1.setFont(new Font("Calibri", Font.BOLD, 14));
				pole2.setFont(new Font("Calibri", Font.BOLD, 14));
				checkbox2.setSelected(false);
			}
			else
			{
				aktywacja(pole1, pole2);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	
	private void dezaktywacja(JTextField pole1, JTextField pole2)
	{
		//pole1.setEnabled(false);
		pole1.setEditable(false);
		//pole2.setEnabled(true);
		pole2.setEditable(true);
	}
	private void aktywacja(JTextField pole1, JTextField pole2)
	{
		pole1.setEnabled(true);
		pole1.setEditable(true);
		pole2.setEnabled(true);
		pole2.setEditable(true);
	}
}
