package collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class EmployeeDetails {
	private String designation;
	private int experience;
	
	public EmployeeDetails(String designation, int experience) {
		this.designation = designation;
		this.experience = experience;
	}

	public String getDesignation() {
		return designation;
	}

	public int getExperience() {
		return experience;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof EmployeeDetails)) return false;
		
		EmployeeDetails employeeDetails = (EmployeeDetails)obj;
		
		return this.designation.equals(employeeDetails.designation) && this.experience == employeeDetails.experience;
	}

	@Override
	public int hashCode() {
		return designation.hashCode();
	}

	@Override
	public String toString() {
		return "EmployeeDetails [designation=" + designation + ", experience=" + experience + "]";
	}
	
}

class Employee {
	private int id;
	private String name;
	private EmployeeDetails employeeDetails;
	
	public Employee(int id, String name, EmployeeDetails employeeDetails) {
		this.id = id;
		this.name = name;
		this.employeeDetails = employeeDetails;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Employee)) return false;
		
		Employee employee = (Employee)obj;
		
		if(this.id == employee.id && this.name.equals(employee.name) && this.employeeDetails.equals(employee.employeeDetails)) return true;
		
		return false;
		
	}

	@Override
	public int hashCode() {
		return name.hashCode()^employeeDetails.hashCode();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", employeeDetails=" + employeeDetails + "]";
	}
}

class EmployeeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		EmployeeDetailsComparator employeeDetailsComparator = new EmployeeDetailsComparator();
		if(o1.getId() == o2.getId() && o1.getName() == o2.getName() && 
				employeeDetailsComparator.compare(o1.getEmployeeDetails(), o2.getEmployeeDetails()) == 0) {
			return 0;
		}
		else {
			return -1;
		}
	}
}

class EmployeeDetailsComparator implements Comparator<EmployeeDetails> {

	@Override
	public int compare(EmployeeDetails o1, EmployeeDetails o2) {
		if(o1.getDesignation().equals(o2.getDesignation()) && o1.getExperience() == o2.getExperience()) {
			return 0;
		}
		else {
			return -1;
		}
	}
}

public class CompareSetsOfCustomObjects {
	public static void main(String[] args) {
		
		Set<Employee> s1 = new HashSet<>();
		s1.add(new Employee(11, "John", new EmployeeDetails("Java Developer", 2)));
		s1.add(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 1)));
		
		Set<Employee> s2 = new HashSet<>();
		s2.add(new Employee(11, "John", new EmployeeDetails("Java Developer", 2)));
		s2.add(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 1)));
		
		System.out.println("Set 1: " + s1);
		System.out.println("Set 2: " + s2);
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 2))
				.equals(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 1))));
		
		boolean flag = true;
		for(Employee e: s1) {
			if(!(s2.contains(e))) {
				flag = false;
				System.out.println("s1 != s2"); break;
			}
		}
		if(flag) System.out.println("s1 = s2");
		
		EmployeeComparator employeeComparator = new EmployeeComparator();
		Set<Employee> s3 = new HashSet<>();
		s3.add(new Employee(11, "John", new EmployeeDetails("Java Developer", 2)));
		s3.add(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 1)));
		
		Set<Employee> s4 = new HashSet<>();
		s4.add(new Employee(11, "John", new EmployeeDetails("Java Developer", 2)));
		s4.add(new Employee(15, "Bob", new EmployeeDetails("Angular Developer", 1)));
		
//		Employee emp1 = new Employee(11, "John", new EmployeeDetails("Java Developer", 2));
//		Employee emp2 = new Employee(11, "John", new EmployeeDetails("Java Developer", 2));
		
		
		
	}
}
