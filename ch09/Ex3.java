package ch09;


//String Ŭ���� =������(char[]) + �޼���(���ڿ�����)
//���ڿ��� �ٷ�� ���� Ŭ����
//������ ������ �� ���� �Һ�Ŭ������
//���� ������(+)�� �̿��� ���ڿ� ������ ������ ������
//���ڿ��� �����̳� ������ ��ٸ�, ������ ���氡���� StringBuffer(���뺯�� ����)�� ���


public class Ex3 {
	public static void main(String[] args) {
		String a = "a"; //�ּҰ�: 0x100
		String b = "b"; //�ּҰ�: 0x200
		
		//������ ������ �� ���� �Һ�Ŭ���� �̹Ƿ� ���ο� �ּҰ��� ������ 
		a = a + b; //�ּҰ�: 0x300
		
		//���ڿ� ���ͷ������ ���� ������ ���������� ����Ű�� �ּҰ��� ����
		//���ڿ� ���ͷ��� ���α׷� ����� �ڵ����� �����ȴ�. 
		//��� ���ڿ��� constant pool�� �����
		String str1 = "abc"; //�ּҰ�: 0x400
		String str2 = "abc"; //�ּҰ�: 0x400
		
		//���ڿ� new�����ڹ���� ���� ���Ƶ� ���������� ����Ű�� �ּҰ��� �ٸ�
		String str3 = new String("abc"); //�ּҰ�: 0x500
		String str4 = new String("abc"); //�ּҰ�: 0x600
	}
}