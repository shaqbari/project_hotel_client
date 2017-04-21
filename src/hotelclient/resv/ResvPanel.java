package hotelclient.resv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hotelclient.ClientMain;

public class ResvPanel extends JPanel{
	ClientMain main;
	Connection con;

	public ResvPanel(ClientMain main) {
		this.main=main;
		con=main.con;

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1100, 900));
		setVisible(false);	
	}
}
