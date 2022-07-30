import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Employee1 implements Comparable<Employee1> {
	
	int empid;
	double salary;
	String name, role;
	static String organization = "TCS";
	
	public Employee1(int empid, double salary, String name, String role) {
		this.empid = empid;
		this.salary = salary;
		this.name = name;
		this.role = role;		
	}
	
	public int getEmpid() {
		return empid;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "{Empid:"+empid+",Salary:"+salary+",Name:"+name+",Role:"+role+"}";
	}

	@Override
	public int compareTo(Employee1 e) {
		return this.getEmpid()-e.getEmpid();
	}
	
}

class SortByEmpid implements Comparator<Employee1> {
	
	@Override
	public int compare(Employee1 e1, Employee1 e2) {
		return e1.getEmpid() - e2.getEmpid();
	}
	
}

class SortByEmpName implements Comparator<Employee1> {

	@Override
	public int compare(Employee1 e1, Employee1 e2) {
		return e1.getName().compareTo(e2.getName());
	}
	
}

class SortByEmpSalary implements Comparator<Employee1> {

	@Override
	public int compare(Employee1 e1, Employee1 e2) {
		return (int)(e1.getSalary() - e2.getSalary());
	}
	
}

public class PracticeArrayOfObjects2 {
	
	public static void sortByEmpid(ArrayList<Employee1> al1) {
		Collections.sort(al1, new SortByEmpid());
		System.out.println("Employees sorted by Empid!");
	}
	
	public static void sortBySalary(ArrayList<Employee1> al1) {
		Collections.sort(al1, new SortByEmpSalary());
		System.out.println("Employees sorted by Salary!");
	}
	
	public static void sortByName(ArrayList<Employee1> al1) {
		Collections.sort(al1, new SortByEmpName());
		System.out.println("Employees sorted by Name!");
	}

	public static void addEmployee(ArrayList<Employee1> al1) {
		
		int empid;
		double salary;
		String name, role;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee details:");
		System.out.println("Enter employee id");
		empid = sc.nextInt();
		
		System.out.println("Enter employee salary");
		salary = sc.nextDouble();
		
		sc.nextLine();
		
		System.out.println("Enter employee name");
		name = sc.nextLine();
		
		System.out.println("Enter employee role");
		role = sc.nextLine();
		
		al1.add(new Employee1(empid, salary, name, role));
		
	}
	
	public static void displayEmployDetails(ArrayList<Employee1> al1) {
		for(Employee1 e: al1) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char ch;
		int choice;
		ArrayList<Employee1> al1 = new ArrayList<Employee1>();
		al1.add(new Employee1(101, 20000, "A.Shrikant", "Quality Assurance Engineer"));
		al1.add(new Employee1(105, 13000, "John", "Android Developer"));
		al1.add(new Employee1(103, 25000, "Bob", "Angular Developer"));
		displayEmployDetails(al1);
//		sortByEmpid(al1);
//		sortByName(al1);
//		sortBySalary(al1);
		System.out.println("---------------------------");
		Collections.sort(al1);
		displayEmployDetails(al1);
		
//		do {
//			System.out.println("Press 1: To add an employee");
//			System.out.println("Press 2: To sort employees by empid");
//			System.out.println("Press 3: To sort employees by salary");
//			System.out.println("Press 4: To sort employees by name");
//			System.out.println("Press 5: To display employees");
//			choice = sc.nextInt();
//				switch(choice) {
//				case 1:
//					addEmployee(al1);
//					break;
//				case 2:
//					sortByEmpid(al1);
//					break;
//				case 3:
//					sortBySalary(al1);
//					break;
//				case 4:
//					sortByName(al1);
//					break;
//				case 5:
//					displayEmployDetails(al1);
//					break;
//				default:
//					System.out.println("Invalid choice!");
//				}
//				sc.nextLine();
//				System.out.println("Do you wish to continue(y/n)?");
//				ch = sc.nextLine().charAt(0);
//		}while(ch != 'n' || ch != 'N');
		sc.close();
	}

}


/*
101
20000
A.Shrikant
Quality Assurance Engineer


 */