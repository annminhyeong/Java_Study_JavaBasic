package ch14;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import static java.util.Comparator.*;

//collect() : Collector�� �Ű������� �ϴ� ��Ʈ�� ��������
//
//Collector�� ����(collect)�� �ʿ��� �޼��带 ������ ���� �������̽�
//ù��° �Ű����� : ������ ��
//�ι�° �Ű����� : �������
//�¹�° �Ű����� : ���չ��(����,����)
//�ݹ�° �Ű����� : ��������
//
//CollectorsŬ������ �پ��� ����� �÷���(Collector�� ������ Ŭ����)�� ����
//
//collect() : ��������
//Collector : �������̽�
//Collectors : Ŭ����
//
//��Ʈ���� �÷���, �迭�� ��ȯ
//��Ʈ���� �÷������� ��ȯ : toList(), toSet(), toMap(), toCollection()
//
//��Ʈ���� �迭�� ��ȯ : toArray()
//
//��Ʈ���� ���
//��Ʈ���� �׷캰 ������� ���� : counting(), summingInt(), maxBy(), minBy(), ... ���
//
//reducing() : ��Ʈ���� �׷캰 �����, reduce()�� ���
//
//joining() : ���ڿ� ��Ʈ���� ��Ҹ� ��� ����


//��Ʈ���� �׷�ȭ�� ����
//partitioningBy() : ��Ʈ���� 2�����Ѵ�.
//groupingBy() : ��Ʈ���� n���� �Ѵ�.


class Student2{
	String name;
	boolean isMale;
	int hak;
	int ban;
	int score;
	
	Student2(String name, boolean isMale, int hak, int ban, int score) {
		this.name = name;
		this.isMale = isMale;
		this.hak = hak;
		this.ban = ban;
		this.score = score;
	}

	public String getName() { return name; }

	public boolean getIsMale() {	return isMale; }

	public int getHak() { return hak; }

	public int getBan() { return ban; }

	public int getScore() { return score; }
	
	public String toString() {
		return String.format("[%s, %s, %d�г� %d��, %3d��]",
				name, isMale ? "��" : "��", hak, ban, score);
	}
	
	enum Level{HIGH, MID, LOW}
}



public class Ex20 {
	public static void main(String[] args) {
		Student2[] stuArr = {
			new Student2("���ڹ�", true,  1, 1, 300),	
			new Student2("������", false, 1, 1, 250),	
			new Student2("���ڹ�", true,  1, 1, 200),	
			new Student2("������", false, 1, 2, 150),	
			new Student2("���ڹ�", true,  1, 2, 100),	
			new Student2("������", false, 1, 2,  50),	
			new Student2("Ȳ����", false, 1, 3, 100),	
			new Student2("������", false, 1, 3, 150),	
			new Student2("���ڹ�", true,  1, 3, 200),	
			new Student2("���ڹ�", true,  2, 1, 300),	
			new Student2("������", false, 2, 1, 250),	
			new Student2("���ڹ�", true,  2, 1, 200),	
			new Student2("������", false, 2, 2, 150),	
			new Student2("���ڹ�", true,  2, 2, 100),	
			new Student2("������", false, 2, 2,  50),	
			new Student2("Ȳ����", false, 2, 3, 100),	
			new Student2("������", false, 2, 3, 150),	
			new Student2("���ڹ�", true,  2, 3, 200)	
		};
		
		System.out.printf("1. �ܼ�����(������ ����)%n");
		Map<Boolean, List<Student2>> stuBySex =  Stream.of(stuArr)
				.collect(partitioningBy(Student2::getIsMale));
		
		List<Student2> maleStudent = stuBySex.get(true);
		List<Student2> femaleStudent = stuBySex.get(false);
		
		for(Student2 s : maleStudent) System.out.println(s);
		for(Student2 s : femaleStudent) System.out.println(s);
		
		
		System.out.printf("%n2. �ܼ����� ���(����, �л���)%n");
		Map<Boolean, Long> stuNumbySex = Stream.of(stuArr)
				.collect(partitioningBy(Student2::getIsMale, counting()));
		
		System.out.println("���л���: " + stuNumbySex.get(true));
		System.out.println("���л���: " + stuNumbySex.get(false));
		
		
		System.out.printf("%n3. �ܼ����� ���(����1��)%n");
		Map<Boolean, Optional<Student2>> topScoreBySex = Stream.of(stuArr)
				.collect(partitioningBy(Student2::getIsMale,
						maxBy(comparingInt(Student2::getScore))));
		
		System.out.println("���л� �ϵ�: " + topScoreBySex.get(true));
		System.out.println("���л� �ϵ�: " + topScoreBySex.get(false));
		
		
		
		Map<Boolean, Student2> topScoreBySex2 = Stream.of(stuArr)
				.collect(partitioningBy(Student2::getIsMale, 
					collectingAndThen(
						maxBy(comparingInt(Student2::getScore)), Optional::get
					)
				));
		
		System.out.println("���л� �ϵ�: " + topScoreBySex2.get(true));
		System.out.println("���л� �ϵ�: " + topScoreBySex2.get(false));
		
		
		System.out.printf("%n4. ���ߺ���(���� ���հ���, 100�� ����)%n");
		
		Map<Boolean, Map<Boolean, List<Student2>>> failedStuBySex = 
				Stream.of(stuArr).collect(partitioningBy(Student2::getIsMale, 
					partitioningBy(s -> s.getScore() <= 100))
				); 
		
		List<Student2> failedMaleStu   = failedStuBySex.get(true).get(true);
		List<Student2> failedFemaleStu = failedStuBySex.get(false).get(true);

		for(Student2 s : failedMaleStu)   System.out.println(s);
		for(Student2 s : failedFemaleStu) System.out.println(s);
	}
}






















