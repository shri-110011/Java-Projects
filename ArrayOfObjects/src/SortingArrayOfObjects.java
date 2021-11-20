import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Employee implements Comparable<Employee>{
	int empid;
	String name;
	String email;
	static String organization = "TCS";
	double salary;
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Employee(int empid, String name, double sal, String email) {
		this.empid = empid;
		this.name = name;
		this.email = email;
		this.salary = sal;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", email=" + email + ", salary=" + salary + "]";
	}
	@Override
	public int compareTo(Employee employee) {
		// TODO Auto-generated method stub
//		return this.empid-employee.empid;
		return this.empid-employee.empid;
	}
}
class SortByName implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
}

class SortBySalary implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return (int)(o1.getSalary()-o2.getSalary());
	}
}
public class SortingArrayOfObjects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee employees[] = new Employee[4];
		Scanner sc = new Scanner(System.in); 
		
		for(int i=0;i<4;i++) {
			int id = sc.nextInt();
			sc.nextLine();
			String name = sc.nextLine();
			String mail = sc.nextLine();
			double sal = sc.nextDouble();
			employees[i] = new Employee(id, name, sal, mail);
//			employees[i].setEmpid(id);
//			employees[i].setName(name);
//			employees[i].setEmail(mail);
		}
		for(int i=0;i<4;i++) {
			System.out.println("*");
			System.out.println(employees[i]);
		}
		
//		Arrays.sort(employees);
		Employee[] arrOfObjects = SortEmployee(employees);
		for(Employee employee: arrOfObjects) {
			System.out.println(employee.toString());
		}
		sc.close();
	}
	public static Employee[] SortEmployee(Employee[] obj) {
		Arrays.sort(obj, new SortBySalary());
		return obj;
	}
}

//101
//John
//John@tcs.com
//102
//Billy
//Billy@tcs.com
//103
//Mandy
//Mandy@tcs.com
//104
//Bob
//Bob@tcs.com
