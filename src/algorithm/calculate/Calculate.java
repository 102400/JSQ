package algorithm.calculate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public class Calculate {
	
	private char[] chararray0;
	private LinkedList<String> x0 = new LinkedList<>();  //一直在运行的linkedlist
	private ArrayList<String> x1 = new ArrayList<>();  //识别char[]原始数据
	private ArrayList<String> x2 = new ArrayList<>();  //插入括号后的原始数据
	public static TreeMap<String,Integer> map = new TreeMap<>();
//	private String[] words = {"+","-","*","/","^"};
//	private LinkedList<Integer> x3 = new LinkedList<>();  //cal方法 放入"("位置指针
//	private LinkedList<Integer> x4 = new LinkedList<>();  //自动增加括号方法 放入"("位置指针
//	private LinkedList<Integer> x5 = new LinkedList<>();  //自动增加括号方法 放入")"位置指针
	private double result;  //结果
	
	static
	{
		map.put("+", 20);
		map.put("-", 20);
		map.put("*", 40);
		map.put("/", 40);
		map.put("^", 60);
	}
	
	public Calculate()
	{
		
	}
	
	public double getResult()
	{
		return result;
	}
	
	public void start(String x)
	{
		chararray0 = x.toCharArray();  //将每个字符串中的字符存入char[]
		putlinkedlist();
		
		x1.addAll(x0);  //x1为原始数据
		chooseAorB();
		x2.addAll(x0);  //x2为增加括号后的数据
		cal();
		dealResult();
		System.out.println("");
		getall();
		System.out.println("-----");
	}
	
	private void dealResult()
	{
		for(int i=0;i<x0.size();i++)
		{
			try
			{
				result = Double.valueOf(x0.get(i));
				return;
			}
			catch(Exception ex)
			{
				
			}
		}
	}
	
	private void putlinkedlist()  //识别x1中的元素并放入到x0中
	{
		String temp0 = "";
		for(int i=0;i<chararray0.length;i++)  //识别char[] chararray0里的每个元素,并放入LinkedList x0
		{
			switch(chararray0[i])  //
			{
				case '1':
					temp0 = temp0 + "1";
					break;
				case '2':
					temp0 = temp0 + "2";
					break;
				case '3':
					temp0 = temp0 + "3";
					break;
				case '4':
					temp0 = temp0 + "4";
					break;
				case '5':
					temp0 = temp0 + "5";
					break;
				case '6':
					temp0 = temp0 + "6";
					break;
				case '7':
					temp0 = temp0 + "7";
					break;
				case '8':
					temp0 = temp0 + "8";
					break;
				case '9':
					temp0 = temp0 + "9";
					break;
				case '0':
					temp0 = temp0 + "0";
					break;
				case '.':
					temp0 = temp0 + ".";
					break;
				case '+':
					x0.add(temp0);
					temp0 = "";
					x0.add("+");
					break;
				case '-':
					x0.add(temp0);
					temp0 = "";
					x0.add("-");
					break;
				case '*':
					x0.add(temp0);
					temp0 = "";
					x0.add("*");
					break;
				case '/':
					x0.add(temp0);
					temp0 = "";
					x0.add("/");
					break;
				case '^':
					x0.add(temp0);
					temp0 = "";
					x0.add("^");
					break;
				case '(':
					x0.add(temp0);
					temp0 = "";
					x0.add("(");
					break;
				case ')':
					x0.add(temp0);
					temp0 = "";
					x0.add(")");
					break;
				default:
					temp0 = temp0 + "";
			}
			if(i==chararray0.length-1)  //增加char[]里的最后一个元素
			{
				x0.add(temp0);
				temp0 = "";
			}
		}
	}
	
	private void chooseAorB()  //!此方法可能弃用
	{
		for(int i=0;i<x0.size();i++)
		{
			if(x0.get(i).equals("(")||x0.get(i).equals(")"))  //判断x0是不是有括号
			{
//				System.out.println("有括号方法");
				addparenthesis_b();  //b()有括号方法,有无括号应该都可以
				return;
			}
			if(i==x0.size()-1)
			{
//				System.out.println("无括号方法");
				addparenthesis_a();  //a()无括号方法,LinkedList<String> x0里如果有括号则会出错
				return;
			}
		}
	}
	
	/**
     *      j i j  
     * 3*2+1/2^3-2
     *i为当前运算符,j为检测到的运算符
     */
	private void addparenthesis_a()  //增加圆括号,此方法只能判断 传入的LinkedList<String> x0无任何括号
	{
		I:
		for(int i=0;i<x0.size();i++)
		{
			try
			{
				boolean trymap = map.get(x0.get(i))>0;
			}
			catch(Exception e)
			{
				continue I;
			}
			if((map.get(x0.get(i)))>0)
//			if(getWeight(x0.get(i))>0)  //查询是否是运算符,并且大于30(大于+,-优先级)
			{
				B:
				for(int j=i;j>=0;j--)  //向前插入括号
				{
					if(x0.get(j).equals(")"))  //如果查询为")"  这个if有问题 (3*2)^(5+3*5/6+7^3)*8/2-7*2  (((3*2))^(5+((3*5/6))+(((7^3)))*8/2))-(7*2)
					{
						int a = 1;  //查询到")"为1次
						int b = 0;  //查询到"("为0次
						do
						{
							j--;
							if(j<0)
							{
								throw new IllegalArgumentException("j<0,\"(\"缺失");  //抛异常
							}
							if(x0.get(j).equals("("))
							{
								b++;
							}
						}
						while(a!=b);  //如果")"与"("查询到的次数不相同,则一直循环
					}
					if(j==0)
					{
//						if(!(x0.get(0).equals("(")))
//						{
//							x0.add(0, "(");
//							i++;
//						}
						x0.add(0, "(");
						i++;  //因为插入了一个括号,所以当前运算符i +1
						break B;
					}
					if(getWeight(x0.get(i))>getWeight(x0.get(j))&&getWeight(x0.get(j))>0)  //判断当前运算符是否大于前运算符
					{
						x0.add(j+1, "(");
						i++;
						break B;
					}
				}
				A:
				for(int j=i;j<x0.size();j++)  //向后插入括号
				{
					if(x0.get(j).equals("("))  //如果查询为")"
					{
						int a = 1;  //查询到"("为1次
						int b = 0;  //查询到")"为0次
						do
						{
							j++;
							if(j>=x0.size())
							{
								throw new IllegalArgumentException("j<0,\")\"缺失");
							}
							if(x0.get(j).equals(")"))
							{
								b++;
							}
						}
						while(a!=b);  //如果")"与"("查询到的次数不相同,则一直循环
					}
					if(j==x0.size()-1)
					{
//						if(!(x0.get(0).equals(")")))
//						{
//							x0.add(x0.size()-1, ")");
//							i++;
//						}
						x0.add(x0.size(), ")");
						i++;  //因为插入了一个括号,所以当前运算符i +1
						break A;
					}
					if(getWeight(x0.get(i))>getWeight(x0.get(j))&&getWeight(x0.get(j))>0)  //判断当前运算符是否大于前运算符
					{
						x0.add(j, ")");
						i++;
						break A;
					}
				}
			}
		}
	}
	
	private void addparenthesis_b()  //增加圆括号,此方法能判断 传入的LinkedList<String> x0有括号
	//BUG 0:如果LinkedList<String> x0里 类似(3*2)^(5+3)这种结构则可能出错
	//如 (3*2)^(5+3*5/6+7^3)*8/2-7*2
	//(((3*2))^(5+((3*5/6))+(((7^3)))*8/2))-(7*2)
	{
		I:
		for(int i=0;i<x0.size();i++)
		{
			try
			{
				boolean trymap = map.get(x0.get(i))>0;
			}
			catch(Exception e)
			{
				continue I;
			}
			if((map.get(x0.get(i)))>30)
//			if(getWeight(x0.get(i))>30)  //查询是否是运算符,并且大于30(大于+,-优先级)
			{
				B:
				for(int j=i;j>=0;j--)  //向前插入括号
				{
					if(x0.get(j).equals(")"))  //如果查询为")"
					{
						int a1 = 1;  //查询到")"为1次
						int b1 = 0;  //查询到"("为0次
						do
						{
							j--;
							if(j<0)
							{
								throw new IllegalArgumentException("j<0,\"(\"缺失");
							}
							if(x0.get(j).equals("("))
							{
								b1++;
							}
						}
						while(a1!=b1);  //如果")"与"("查询到的次数不相同,则一直循环
					}
					if(j==0)
					{
//						if(!(x0.get(0).equals("(")))
//						{
//							x0.add(0, "(");
//							i++;  
//						}
						x0.add(0, "(");
						i++;  //因为插入了一个括号,所以当前运算符i +1
						break B;
					}
					if(x0.get(j).equals("("))
					{
						x0.add(j+1, "(");
						i++;
						break B;
					}
					if(getWeight(x0.get(i))>getWeight(x0.get(j))&&getWeight(x0.get(j))>0)  //判断当前运算符是否大于前运算符
					{
						x0.add(j+1, "(");
						i++;
						break B;
					}
				}
				A:
				for(int j=i;j<x0.size();j++)  //向后插入括号
				{
					if(x0.get(j).equals("("))  //如果查询为")"
					{
						int a1 = 1;  //查询到"("为1次
						int b1 = 0;  //查询到")"为0次
						do
						{
							j++;
							if(j>=x0.size())
							{
								throw new IllegalArgumentException("j<0,\")\"缺失");
							}
							if(x0.get(j).equals(")"))
							{
								b1++;
							}
						}
						while(a1!=b1);  //如果")"与"("查询到的次数不相同,则一直循环
					}
					if(j==x0.size()-1)
					{
//						if(!(x0.get(0).equals(")")))
//						{
//							x0.add(x0.size()-1, ")");
//							i++;
//						}
						x0.add(x0.size(), ")");
						i++;  //因为插入了一个括号,所以当前运算符i +1
						break A;
					}
					if(x0.get(j).equals(")"))
					{
						x0.add(j, ")");
						i++;
						break A;
					}
					if(getWeight(x0.get(i))>getWeight(x0.get(j))&&getWeight(x0.get(j))>0)  //判断当前运算符是否大于前运算符
					{
						x0.add(j, ")");
						i++;
						break A;
					}
				}
			}
		}
	}
	
	
	
	/**
     * 优先级
     * ^ > *,/ > +,-
     *
     */
	private int getWeight(String x)  //值越大,优先级越高
	{
		switch(x)
		{
		case "+":
			return 20;
		case "-":
			return 20;
		case "*":
			return 40;
		case "/":
			return 40;
		case "log":
			return 50;
		case "^":
			return 60;
//		case "(":
//			return 100;
//		case ")":
//			return 100;
		default:
			return -10;
		}
	}
	
	private void cal()  //检测括号位置并传入到运算方法计算
	{
		LinkedList<Integer> x3 = new LinkedList<>();  //检测括号位置并放入"("所在位置的地址
		System.out.println(x1);
		System.out.println(x2);
//		System.out.println(x0);
//		int a0 = 0;  //指针a0
//		int a1 = 1;  //查询到"("为1次
//		int b1 = 0;  //查询到")"为0次
		for(int i=0;i<x0.size();i++)
		{
			if(x0.get(i).equals("("))  //如果查询为"("
			{
				x3.add(i);  //将"("位置塞入 LinkedList<Integer> x3
			}
			if(x0.get(i).equals(")"))  //如果查询为")"
			{
				Integer tempa = x3.peekLast();  //最后一个出现"("的位置
//				String temp2 = "";
				LinkedList<String> temp2 = new LinkedList<>();
				for(int j=tempa+1;j<i;j++)
				{
					temp2.add(x0.get(j));
//					System.out.println(temp2+"!!!");
//					temp2 = temp2 + x0.get(j);
				}
				String temp1 = calculate(temp2); //temp1为 (...) 运算结果
//				temp2.clear();
				for(int j=i;j>tempa;j--)  //填充占位符
				{
//					if(!x0.get(j).equals("@"))
//					{
//						x0.set(j, "@");  //占位符,将(...)用 " "填充
//					}
					x0.set(j, "@");  //占位符,将(...)用 " "填充
				}
				x0.set(tempa, temp1);  //将运算结果填充到"("
				x3.pollLast();
				System.out.println(x0);
			}
		}
	}
	
	private String calculate(LinkedList<String> temp0)  //运算方法
	{
//		String temp = "";
		LinkedList<String> x = new LinkedList<>();
		x.addAll(temp0);
		for(int i=0;i<x.size();i++)
		{
//			System.out.println(x.size());
//			System.out.println(x);
			if(x.get(i).equals("+"))
			{
				double a = 0;  //运算符前一个数字
				double b = 0;  //运算符后一个数字
				double r = 0;  //运算结果
				int j1 = 0;
				int j2 = x.size()-1;
				for(int j=i-1;j!=-1;j--)
				{
//					System.out.println(j);
					if(isNumber(x.get(j)))
					{
						j1 = j;
						a = Double.valueOf(x.get(j));
						break;
					}
				}
				for(int j=i+1;j!=-1;j++)
				{
					if(isNumber(x.get(j)))
					{
						j2 = j;
						b = Double.valueOf(x.get(j));
						break;
					}
				}
				r = a + b;  //此处不同
				for(int j=j2;j>j1;j--)
				{
					x.set(j, "@");  //插入占位符@
				}
				x.set(j1, String.valueOf(r));
			}
			else if(x.get(i).equals("-"))
			{
				double a = 0;
				double b = 0;
				double r = 0;
				int j1 = 0;
				int j2 = x.size()-1;
				for(int j=i-1;j!=-1;j--)
				{
//					System.out.println(j);
					if(isNumber(x.get(j)))
					{
						j1 = j;
						a = Double.valueOf(x.get(j));
						break;
					}
				}
				for(int j=i+1;j!=-1;j++)
				{
					if(isNumber(x.get(j)))
					{
						j2 = j;
						b = Double.valueOf(x.get(j));
						break;
					}
				}
				r = a - b;  //此处不同
				for(int j=j2;j>j1;j--)
				{
					x.set(j, "@");  //插入占位符@
				}
				x.set(j1, String.valueOf(r));
			}
			else if(x.get(i).equals("*"))
			{
				double a = 1;
				double b = 1;
				double r = 1;
				int j1 = 0;
				int j2 = x.size()-1;
				for(int j=i-1;j!=-1;j--)
				{
//					System.out.println(j);
					if(isNumber(x.get(j)))
					{
						j1 = j;
						a = Double.valueOf(x.get(j));
						break;
					}
				}
				for(int j=i+1;j!=-1;j++)
				{
					if(isNumber(x.get(j)))
					{
						j2 = j;
						b = Double.valueOf(x.get(j));
						break;
					}
				}
				r = a * b;
				for(int j=j2;j>j1;j--)
				{
					x.set(j, "@");  //插入占位符@
				}
				x.set(j1, String.valueOf(r));
			}
			else if(x.get(i).equals("/"))
			{
				double a = 1;
				double b = 1;
				double r = 1;
				int j1 = 0;
				int j2 = x.size()-1;
				for(int j=i-1;j!=-1;j--)
				{
//					System.out.println(j);
					if(isNumber(x.get(j)))
					{
						j1 = j;
						a = Double.valueOf(x.get(j));
						break;
					}
				}
				for(int j=i+1;j!=-1;j++)
				{
					if(isNumber(x.get(j)))
					{
						j2 = j;
						b = Double.valueOf(x.get(j));
						break;
					}
				}
				r = a / b;
				for(int j=j2;j>j1;j--)
				{
					x.set(j, "@");  //插入占位符@
				}
				x.set(j1, String.valueOf(r));
			}
			else if(x.get(i).equals("^"))
			{
				double a = 1;
				double b = 1;
				double r = 1;
				int j1 = 0;
				int j2 = x.size()-1;
				for(int j=i-1;j!=-1;j--)
				{
//					System.out.println(j);
					if(isNumber(x.get(j)))
					{
						j1 = j;
						a = Double.valueOf(x.get(j));
						break;
					}
				}
				for(int j=i+1;j!=-1;j++)
				{
					if(isNumber(x.get(j)))
					{
						j2 = j;
						b = Double.valueOf(x.get(j));
						break;
					}
				}
				r = Math.pow(a, b);
				for(int j=j2;j>j1;j--)
				{
					x.set(j, "@");  //插入占位符@
//					System.out.println("@");
//					System.out.println(x);
				}
				x.set(j1, String.valueOf(r));
			}
//			else if(x.get(i).equals("!"))  //log
//			{
//				double a = 1;
//				double b = 1;
//				double r = 1;
//				int j1 = 0;
//				int j2 = x.size()-1;
//				for(int j=i-1;j!=-1;j--)
//				{
////					System.out.println(j);
//					if(isNumber(x.get(j)))
//					{
//						j1 = j;
//						a = Double.valueOf(x.get(j));
//						break;
//					}
//				}
//				for(int j=i+1;j!=-1;j++)
//				{
//					if(isNumber(x.get(j)))
//					{
//						j2 = j;
//						b = Double.valueOf(x.get(j));
//						break;
//					}
//				}
//				r = Math.pow(a, b);
//				for(int j=j2;j>j1;j--)
//				{
//					x.set(j, "@");  //插入占位符@
////					System.out.println("@");
////					System.out.println(x);
//				}
//				x.set(j1, String.valueOf(r));
//			}
			else
			{
				
			}
			
		}
//		System.out.println(x+"!!!!");
		return x.get(0);
	}
	
	public boolean isNumber(String x)
	{
		try
		{
			char a;
			a = x.charAt(0);
			switch(a)
			{
				case '1':
					return true;
				case '2':
					return true;
				case '3':
					return true;
				case '4':
					return true;
				case '5':
					return true;
				case '6':
					return true;
				case '7':
					return true;
				case '8':
					return true;
				case '9':
					return true;
				case '0':
					return true;
	//			case '.':
	//				return true;
				default:
					return false;
			}
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public void getall()
	{
		String a = "";
		String b = "";
		String c = "";
		for(int i=0;i<x0.size();i++)
		{
			a = a + x0.get(i);
			c = c + x2.get(i);
//			System.out.print(x0.get(i));
		}
		for(int i=0;i<x1.size();i++)
		{
			b = b + x1.get(i);
		}
		System.out.println(b);
		System.out.println(c);
		System.out.println(a);
	}
	
}
