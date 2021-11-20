
abstract class AbstractClass {
	private String ename;
	private String email;
	private int empid;
	
	private String str;
	
	public AbstractClass() {
		System.out.println("Inside AbstractClass default constructor");
	}
	
	public AbstractClass(String name, String mail, int id) {
		ename = name;
		email = mail;
		empid = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	public abstract void mail();
	
	public String toString() {
		return getEname()+" "+getEmail()+" "+getEmpid();
	}
	

}
