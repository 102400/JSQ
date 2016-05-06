package gui.main;

import java.awt.EventQueue;

public class Start{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		System.setProperty("sun.jnu.encoding","utf-8");
//		System.setProperty("file.encoding","utf-8");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		
	}
}
