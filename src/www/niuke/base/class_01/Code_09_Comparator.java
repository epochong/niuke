package www.niuke.java;

import java.util.*;

import static com.sun.org.apache.bcel.internal.classfile.Utility.printArray;

public class Code_09_Comparator {

	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	public static class IdAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;
		}

	}

	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}

	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}

	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
		System.out.println("===========================");
	}

	public static void main(String[] args) {
		Student student1 = new Student("A", 1, 23);
		Student student2 = new Student("B", 2, 21);
		Student student3 = new Student("C", 3, 22);

		Student[] students = new Student[] { student3, student2, student1 };
		printStudents(students);

		Arrays.sort(students, new IdAscendingComparator());
		printStudents(students);

		Arrays.sort(students, new IdDescendingComparator());
		printStudents(students);

		Arrays.sort(students, new AgeAscendingComparator());
		printStudents(students);

		Arrays.sort(students, new AgeDescendingComparator());
		printStudents(students);

		/*//add
		Integer[] arr = {3,2,4,1,0};
		Arrays.sort(arr,new MyComp());
		printArray(arr);
		//优先级队列(堆)，系统提供的堆                            此处将比较器放入堆中，给之规则，ID大的在前面，大根堆
		PriorityQueue<Student> heap = new PriorityQueue<>(new IdDescendingComparator());
		//扔到堆里
		heap.add(student3);
		heap.add(student1);
		heap.add(student2);
		System.out.println("优先队列排序：");
		while(!heap.isEmpty()) {
			//每次弹出堆顶
			Student student = heap.poll();
			System.out.println("Name : " + student.name + ", Id : " + student.name + ", Age : " + student.age);
		}
		//红黑树
		TreeSet<Student> treeSet = new TreeSet<>(new IdAscendingComparator());*/
	}

}
