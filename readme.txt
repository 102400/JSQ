���л���:>=1.8
��ͨ��run.bat�����г���
������ɴ���:

����:0,1,2...
�����:+ - * / ^ =
����:( )  #����!��bug
����:v0,v1,v2... 
����:get clear sort help

����:
  �Ϸ���: 1 2.5
  ���Ϸ���: .2
	
�����:
  ���ȼ�: ^ > * / > + -
  =:
    Ҫ���������
    v2=1+2
	
����:
  ������Ӧ����������
  ��������bug

����:
  ÿ����һ��������Զ�����������
  ��v0��ʼ,v1,v2,v3...
  ʹ�÷�ʽ��ֱ����������
  ʹ�õ�ǰ������������Ѿ�����
  ��ȷ���÷�:
    v0 = v1 + 2
    v2 + 3
    3 + v2
    v0 + v1
    (v0 + v1)*v3  #��������
    v0=(v1+v2)*v3
  ������÷�:
    v1 + 2 = v0  #��ȻҲ�ܳ����,������Ǵ��

����:
  get:
    ��ȡ���б�����ֵ
  clear:
    ����ı������б���
  sort:
    ��ʾ���������ı���
  help:
    ��ʾ��������ȼ�

ע��:
  ��Ҫ��>>>ɾ��
  ����س���ûʲô��Ӧ˵�����쳣��
	
	
ʵ��ԭ��:
���ʽ�Զ���������(�������ͬ���ķ���һ��������)
����:
2+3^2/3-8
������Զ���������:
((2+((3^2)/3)-8))


Shell:
��textArea�е��ı�ʹ��toCharArray()�洢�� char[] content,
��content���һ���±����,����⵽'>'ʱ�ж��ǲ���>>>
�������ֵ�>λ�ÿ�ʼһ��һ���ַ���ʼ�ж�
	�Ƿ�Ϊ����
		true:��ArrayList<Double> value����ȡ�������
	�Ƿ���get����
		true:����value�����
	�Ƿ���clear����
		true:���value��textArea
	�Ƿ���sort����
		true:��value��ֵ������������
	�Ƿ���help����
		true:���Calculate.map
	����String x(�����ı��ʽ)������
	�Ƿ��Ǹ�ֵ�����
		false:������������value
		true:����������ֵ��Ҫ��ֵ�ı���
		
Calculate:
void start(String x)
	�������xʹ��toCharArray()�洢��char[] chararray0
	ʹ��putlinkedlist()
	��x0���浽ArrayList<String> x1,ԭʼ����
	ʹ��chooseAorB()
	��x0���浽ArrayList<String> x2,�������ź�ı��ʽ

void putlinkedlist()
	ʶ��chararray0��ÿ����Ԫ�ز�����LinkedList<String> x0��

void chooseAorB()
	����������ʽ�Ƿ���������,������ʹ��addparenthesis_a(),������ʹ��addparenthesis_b()

void addparenthesis_a(),void addparenthesis_b()
	�ж��Ƿ��������(��TreeMap<String,Integer> map�в���)
		catch:����I
	��ǰ������
		*            j  i   j
		* 3*2+1/2^3-2
		*iΪ��ǰ�����,jΪ��⵽�������
		�����⵽)��һֱ����(,��)��(������ͬʱ������ѭ��
		���±�Ϊ0ʱ��x0.add(0, "(");
		�жϵ�ǰ������Ƿ����ǰ�����
			true:x0.add(j+1, "(");
	��������
		����ǰ������

int getWeight(String x)
	����x��Ӧ��ֵ
	���ֱ�HashMap<String,Integer> mapȡ��

void cal()
	LinkedList<Integer> x3 �������λ�ò�����"("����λ�õĵ�ַ
	��ѯ��(����x3
	��ѯ��)
		Integer temps= x3.peekLast()  tempsΪ���һ������"("��λ�õ�ַ
	LinkedList<String> temp2Ϊ(...)�ı��ʽ
	String temp1 = calculate(temp2); //temp1Ϊ (...) ������
	��(...)������ݼ�)��ռλ��@���
	����������䵽(λ��

String calculate(LinkedList<String> temp0)
	�ж�������ʽ�������
	ʶ�������ǰ�漰���������
	������

boolean isNumber(String x)
	�ж�x�Ƿ��Ǹ�����

void getall()
	��ѯx0,x1,x2