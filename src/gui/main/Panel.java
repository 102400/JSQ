package gui.main;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import gui.calculator.shell.Shell;
import gui.help.about.About;
import gui.help.updates.Updates;
import gui.help.welcome.Welcome;

public class Panel extends JPanel{
	
	static final Welcome welcome = new Welcome();
	static final Shell shell = new Shell();
	static final About about = new About();
	static final Updates updates = new Updates();
	
	private Frame frame;
	
	public Panel()
	{
		this.frame = frame;
		setLayout(new BorderLayout());
//		add(shell);
		add(welcome);
	}
	
	public void setAdd(JPanel show)  //设置显示的panel
	{
		show.setVisible(false);
		removeAll();
		add(show);
		show.setVisible(true);
		repaint();
	}

}
