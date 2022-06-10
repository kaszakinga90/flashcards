package stareKlasy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class StatisticsWindow {

    private final JFrame frame;
    private final JPanel panel;
    private final JLabel labelFiszki;
    private final JLabel labelTesty;
    private final JLabel labelOgolem;
    private final JLabel labelFiszkiValue;
    private final JLabel labelTestyValue;
    private final JLabel labelOgolemValue;

    public StatisticsWindow(){
        frame = new JFrame("Statystyki");
        frame.setSize(500, 400);
        panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        labelFiszki = new JLabel("Fiszki: ");
        labelFiszkiValue = new JLabel("b.d.");
        labelTesty = new JLabel("Testy");
        labelTestyValue = new JLabel("b.d.");
        labelOgolem = new JLabel("Ogółem: ");
        labelOgolemValue = new JLabel("b.d.");
        panel.add(labelFiszki);
        panel.add(labelFiszkiValue);
        panel.add(labelTesty);
        panel.add(labelTestyValue);
        panel.add(labelOgolem);
        panel.add(labelOgolemValue);

        labelFiszki.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelFiszkiValue.setFont(new Font("Calibri", Font.BOLD, 18));
        labelTesty.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelTestyValue.setFont(new Font("Calibri", Font.BOLD, 18));
        labelOgolem.setFont(new Font("Calibri", Font.PLAIN, 18));
        labelOgolemValue.setFont(new Font("Calibri", Font.BOLD, 18));

        addWindowListener (new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
