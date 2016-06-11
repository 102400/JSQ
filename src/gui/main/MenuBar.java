package gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	private Panel panel;
	
	public MenuBar()
	{
		
	}
	
	public MenuBar(Panel panel)
	{
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
		this.panel = panel;
		JMenu menuAlgorithm = new JMenu("计算");
		add(menuAlgorithm);
		
		JMenu menuAlgorithm_Calculate = new JMenu("计算器");
		menuAlgorithm.add(menuAlgorithm_Calculate);
		
		//仿python
		JMenuItem menuitem_Shell = new JMenuItem("shell");
		menuitem_Shell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setAdd(Panel.shell);
			}
		});
		menuAlgorithm_Calculate.add(menuitem_Shell);
		
		JMenu menu_test0 = new JMenu("转换器");
		menuAlgorithm.add(menu_test0);
		
		JMenuItem menuitem_test0 = new JMenuItem("暂无");
		menu_test0.add(menuitem_test0);
		
		JMenu menu_Help = new JMenu("帮助");
		add(menu_Help);
		
		JMenuItem menuitem_Welcome = new JMenuItem("欢迎");
		menuitem_Welcome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setAdd(Panel.welcome);
			}
		});
		menu_Help.add(menuitem_Welcome);
		
		JMenuItem menuitem_Updates = new JMenuItem("更新");
		menuitem_Updates.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setAdd(Panel.updates);
			}
		});
		menu_Help.add(menuitem_Updates);
		
		JMenuItem menuitem_About = new JMenuItem("关于");
		menuitem_About.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setAdd(Panel.about);
			}
		});
		menu_Help.add(menuitem_About);
	}

}
