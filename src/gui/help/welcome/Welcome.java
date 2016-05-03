package gui.help.welcome;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends JPanel{
	
	public Welcome()
	{
		setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText("使用前请仔细阅读readme.txt");
		add(label);
	}

}
