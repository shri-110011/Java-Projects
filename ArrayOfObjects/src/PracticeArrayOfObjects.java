import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Student {
	private int rn;
	private String name;
	private float marks;
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
	public Student(int rn, String name, float marks) {
		this.rn = rn;
		this.name = name;
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "student [rn=" + rn + ", name=" + name + ", marks=" + marks + "]";
	}
}

class SortByRn implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return (s1.getRn() - s2.getRn());
	}
}
class SortByStudentName implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return s1.getName().compareTo(s2.getName());
	}
}
class SortByMarks implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return (s1.getMarks()-s2.getMarks())>0?1:-1;
	}
	
}
public class PracticeArrayOfObjects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student1[] = new Student[5];
		int rn;
		String name;
		float marks;
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<5; i++) {
			rn = sc.nextInt();
			sc.nextLine();
			name = sc.nextLine();
			marks =sc.nextFloat();
			student1[i] = new Student(rn, name, marks);
//			student[i].toString();
		}
		
		for(int i=0; i<5; i++) {
			System.out.println(student1[i].toString());
		}
		
		Student student2[] = PracticeArrayOfObjects.SortStudents(student1);
		for(int i=0; i<5; i++) {
			System.out.println(student2[i].toString());
		}
		
		sc.close();
		
	}
	
	public static Student[] SortStudents(Student[] students) {
//		Arrays.sort(students, new SortByRn());
//		Arrays.sort(students, new SortByStudentName());
		Arrays.sort(students, new SortByMarks());
		return students;
	}
}

/*
12
John
68.5
5
Billy
68.2
9
Mandy
68.6
17
Ben
68.9
11
Bob
68.1
 */
