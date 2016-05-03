package gui.main;

import gui.help.updates.Updates;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	
	public Frame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Panel panel = new Panel();  //内容
		MenuBar menubar = new MenuBar(panel);  //菜单栏
		setJMenuBar(menubar);
		setContentPane(panel);
	}
}
