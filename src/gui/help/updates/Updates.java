package gui.help.updates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Updates extends JPanel {
	
	public static String version = "-1";  //当前版本
	private String latest = "-1";  //最新版本
	
	static
	{
		try(InputStream is = new FileInputStream("changelog.txt"))
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			version = reader.readLine();
			version = version + "\n" + reader.readLine();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public Updates(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JLabel label0 = new JLabel();
		label0.setText("当前版本:" + Updates.version);
		add(label0);
		JLabel label1 = new JLabel();
		label1.setText("最新版本:" + "???");
		add(label1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);  //不可编辑
		textArea.setLineWrap(true);  //自动换行
		textArea.setWrapStyleWord(true);  //断行不断字
		textArea.setText("下载地址:https://github.com/102400/JSQ1/archive/master.zip\n网络问题可能会造成程序假死或崩溃");
		add(new JScrollPane(textArea));  //滚动条
		
		JButton button = new JButton();
		button.setText("检查更新");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					URL url = new URL("https://github.com/102400/JSQ1/raw/master/changelog.txt");  //changelog.txt地址
					URLConnection conn = url.openConnection();
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					latest = reader.readLine();
					latest = latest + "\n" + reader.readLine();
					label1.setText("最新版本:"+latest);
					String line;
					StringBuilder temp = new StringBuilder();
					temp.append(latest+"\n");
					while((line = reader.readLine()) !=null)
					{
						temp.append(line+"\n");
					}
					temp.append("\n下载地址:https://github.com/102400/JSQ1/archive/master.zip");
					textArea.setText(new String(temp.toString()));
					reader.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		add(button);
	}

}
