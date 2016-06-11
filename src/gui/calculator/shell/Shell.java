package gui.calculator.shell;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import algorithm.calculate.Calculate;

public class Shell extends JPanel {
	
	private char[] content;  //textArea中的文本
	private boolean isEqual = false;  //是否是赋值运算
	private int valuetemp = -1;  //赋值运算变量的存储地址
	private ArrayList<Double> value = new ArrayList<>();  //每次运算后的结果
	

	public Shell()
	{
		setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);  //自动换行
		textArea.setWrapStyleWord(true);  //断行不断字
		textArea.setText(">>>");
		textArea.addKeyListener(new KeyListener() {  //键盘监测
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==e.VK_ENTER)  //按下回车时
				{
					content = textArea.getText().toCharArray();
					I:
					for(int i=content.length-1;i>=0;i--)
					{
						if(content[i]=='>'&&content[i-1]=='>'&&content[i-2]=='>')
						{
							String x = "";  //表达式
							J:
							for(int j=i+1;j<content.length;j++)
							{
								if(content[j]=='v')  //判断是否为变量
								{
									String temp = "";  //变量:v+temp,temp为value下标
									K:
									for(int k=j+1;k<content.length;k++)  //提取变量
									{
										S:
										switch(content[k])
										{
											case '0':
												temp = temp + 0;
												break S;
											case '1':
												temp = temp + 1;
												break S;
											case '2':
												temp = temp + 2;
												break S;
											case '3':
												temp = temp + 3;
												break S;
											case '4':
												temp = temp + 4;
												break S;
											case '5':
												temp = temp + 5;
												break S;
											case '6':
												temp = temp + 6;
												break S;
											case '7':
												temp = temp + 7;
												break S;
											case '8':
												temp = temp + 8;
												break S;
											case '9':
												temp = temp + 9;
												break S;
											case '=':
												valuetemp = Integer.valueOf(temp);
												isEqual = true;
												j = k;
												break K;
											default:  //当不匹配到以上字符时
												x = x + value.get(Integer.valueOf(temp));
												j = k;
												break K;
										}
									}
								}
//								else if(content[j]=='+')
//								{
//									x = x + "+";
//								}
//								else if(content[j]=='-')
//								{
//									x = x + "-";
//								}
//								else if(content[j]=='*')
//								{
//									x = x + "*";
//								}
//								else if(content[j]=='/')
//								{
//									x = x + "/";
//								}
//								else if(content[j]=='^')
//								{
//									x = x + "^";
//								}
//								else if(content[j]=='(')
//								{
//									x = x + "(";
//								}
//								else if(content[j]==')')
//								{
//									x = x + ")";
//								}
								else if(content[j]=='g'&&content[j+1]=='e'&&content[j+2]=='t')  //遍历value
								{
									String temp = "\n";
									for(int k=0;k<value.size();k++)
									{
										temp = temp + "[v" + k + "]: " + value.get(k) + "\n";
									}
									textArea.append(temp);
									break I;
								}
								else if(content[j]=='c'&&content[j+1]=='l'&&content[j+2]=='e'&&content[j+3]=='a'&&content[j+4]=='r')  //clear命令,清空value及text
								{
									value.clear();
									textArea.setText(">>>");
									return;
								}
								else if(content[j]=='s'&&content[j+1]=='o'&&content[j+2]=='r'&&content[j+3]=='t')  //sort命令,升序排序
								{
									LinkedList<Double> sort_value = new LinkedList<>();
									LinkedList<Integer> sort_var = new LinkedList<>();
									
									K:
									for(int k=0;k<value.size();k++)
									{
										double temp0 = value.get(k);
										
										if(sort_value.size()==0)
										{
											sort_value.addFirst(temp0);
											sort_var.addFirst(k);
											continue K;
										}
										
										KB:
										for(int kb=0;kb<sort_value.size();kb++)
										{
											if(temp0<=sort_value.get(kb))
											{
												sort_value.add(kb,temp0);
												sort_var.add(kb,k);
												System.out.println(sort_var);
												System.out.println(sort_value);
												System.out.println("*");
												break KB;
											}
											else if(kb==sort_value.size()-1)
											{
												sort_value.add(sort_value.size(), temp0);
												sort_var.add(sort_var.size(),k);
												System.out.println(sort_var);
												System.out.println(sort_value);
												System.out.println("*");
												break KB;
											}
										}
									}
									textArea.append("\n");
									for(int k=0;k<value.size();k++)
									{
										textArea.append("[v"+sort_var.get(k)+"]: "+sort_value.get(k)+"\n");
									}
									
									break I;
								}
								else if(content[j]=='h'&&content[j+1]=='e'&&content[j+2]=='l'&&content[j+3]=='p')  //help命令
								{
									 textArea.append("\n运算符优先级(值越大优先级越高)\n");
									 
									 Iterator<Map.Entry<String, Integer>> iter = Calculate.map.entrySet().iterator();
									 while(iter.hasNext())
									 {
										 Map.Entry<String, Integer> me = iter.next();
										 textArea.append(me+"\n");
									 }
									 System.out.println(Calculate.map);
									 break I;
								}
//								else if(content[j]=='s'&&content[j+1]=='u'&&content[j+2]=='m'&&content[j+3]==':')  //sum命令
//								{
////									value.clear();
////									textArea.setText(">>>");
////									return;
//								}
								x = x + content[j];
							}
							Calculate calculate = new Calculate();
							calculate.start(x);
							if(!isEqual)  //判断是否是赋值运算
							{
								value.add(calculate.getResult());  //增加到value里
								textArea.append("[v"+(value.size()-1)+"]: "+calculate.getResult());
							}
							else
							{
								value.set(valuetemp, calculate.getResult());
								textArea.append(("[v"+valuetemp+"]: "+calculate.getResult()));
								isEqual = false;
								valuetemp = -1;
							}
							break I;
						}
					}
					textArea.append("\n>>>");
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(new JScrollPane(textArea));  //滚动条
	}
}
