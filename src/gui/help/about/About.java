package gui.help.about;

import gui.help.updates.Updates;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JPanel {
	
	public About()
	{
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel labe0 = new JLabel();
		labe0.setText("作者:xzy");
		JLabel label = new JLabel();
		label.setText("版本:"+Updates.version);
		JLabel labe2 = new JLabel();
		labe2.setText("项目地址:https://github.com/102400/JSQ1");
		
		add(labe0);
		add(label);
		add(labe2);
	}

}
