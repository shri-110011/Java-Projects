
public class Employee extends AbstractClass{
	private String role;
	
	public Employee(String name, String mail, int id, String role) {
		//Note: If the parent class has a no argument constructor(i.e. default constructor) 
		//then it is not necessary to have the super() inside the child class constructor 
		//because java compiler will automatically insert the super() otherwise it is 
		//necessary to explicitly call the super() with the proper arguments.
		super(name, mail, id);
//		super();
		this.role = role;
	}
	public Employee() {
		
	}
//	public Employee() {
//		
//	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void mail() {
		System.out.println("Mailing to "+getEname()+" "+getEmail()+" "+getEmpid());
	}

	@Override
	public String toString() {
		return getEname()+" "+getEmail()+" "+getEmpid()+" "+role; 
	}
	
	

}
