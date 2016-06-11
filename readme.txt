运行环境:>=1.8
需通过run.bat来运行程序
本程序可处理:

数字:0,1,2...
运算符:+ - * / ^ =
括号:( )  #慎用!有bug
变量:v0,v1,v2... 
命令:get clear sort help

数字:
  合法的: 1 2.5
  不合法的: .2
	
运算符:
  优先级: ^ > * / > + -
  =:
    要放在最左边
    v2=1+2
	
括号:
  无括号应该是完美的
  有括号有bug

变量:
  每处理一次运算会自动保存运算结果
  从v0开始,v1,v2,v3...
  使用方式是直接输入名字
  使用的前提是这个变量已经创建
  正确的用法:
    v0 = v1 + 2
    v2 + 3
    3 + v2
    v0 + v1
    (v0 + v1)*v3  #括号慎用
    v0=(v1+v2)*v3
  错误的用法:
    v1 + 2 = v0  #虽然也能出结果,但结果是错的

命令:
  get:
    获取所有变量的值
  clear:
    清空文本及所有变量
  sort:
    显示所有升序后的变量
  help:
    显示运算符优先级

注意:
  不要把>>>删了
  如果回车了没什么反应说明抛异常了
	
	
实现原理:
表达式自动增加括号(将运算符同级的放入一个括号里)
输入:
2+3^2/3-8
则程序自动增加括号:
((2+((3^2)/3)-8))


Shell:
将textArea中的文本使用toCharArray()存储到 char[] content,
从content最后一个下标迭代,当检测到'>'时判断是不是>>>
从最后出现的>位置开始一个一个字符开始判断
	是否为变量
		true:从ArrayList<Double> value中提取这个变量
	是否是get命令
		true:遍历value并输出
	是否是clear命令
		true:清空value和textArea
	是否是sort命令
		true:对value的值进行升序排序
	是否是help命令
		true:输出Calculate.map
	传入String x(处理后的表达式)并运算
	是否是赋值运算符
		false:将运算结果存入value
		true:将运算结果赋值给要赋值的变量
		
Calculate:
void start(String x)
	将传入的x使用toCharArray()存储到char[] chararray0
	使用putlinkedlist()
	将x0保存到ArrayList<String> x1,原始数据
	使用chooseAorB()
	将x0保存到ArrayList<String> x2,增加括号后的表达式

void putlinkedlist()
	识别chararray0中每个的元素并放入LinkedList<String> x0中

void chooseAorB()
	查找这个表达式是否有无括号,无括号使用addparenthesis_a(),有括号使用addparenthesis_b()

void addparenthesis_a(),void addparenthesis_b()
	判断是否是运算符(从TreeMap<String,Integer> map中查找)
		catch:跳过I
	向前插括号
		*            j  i   j
		* 3*2+1/2^3-2
		*i为当前运算符,j为检测到的运算符
		如果检测到)则一直查找(,当)与(数量相同时就跳出循环
		当下标为0时则x0.add(0, "(");
		判断当前运算符是否大于前运算符
			true:x0.add(j+1, "(");
	向后插括号
		与向前插类似

int getWeight(String x)
	查找x对应的值
	部分被HashMap<String,Integer> map取代

void cal()
	LinkedList<Integer> x3 检测括号位置并放入"("所在位置的地址
	查询到(塞入x3
	查询到)
		Integer temps= x3.peekLast()  temps为最后一个出现"("的位置地址
	LinkedList<String> temp2为(...)的表达式
	String temp1 = calculate(temp2); //temp1为 (...) 运算结果
	将(...)里的内容及)用占位符@填充
	将计算结果填充到(位置

String calculate(LinkedList<String> temp0)
	判断这个表达式的运算符
	识别运算符前面及后面的数字
	计算结果

boolean isNumber(String x)
	判断x是否是个数字

void getall()
	查询x0,x1,x2