import java.util.EventObject;

public class FormEvent extends EventObject {
	private String name;
	private String occupation;
	private int ageCat;
	private String empCat;
	private boolean usCitizen;
	private String taxId;
	private String gender;
	
	public FormEvent(Object source) {
		super(source);
	}
	public FormEvent(Object source, String name, String occupation, int ageCat, String empCat, 
			boolean usCitizen, String taxId, String gender) {
		super(source);
//		System.out.println("source: "+source);
		this.name = name;
		this.occupation = occupation;
		this.ageCat = ageCat;
		this.empCat = empCat;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAgeCategory() {
		return ageCat;
	}
	public String getEmpCategory() {
		return empCat;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}
	public String getTaxId() {
		return taxId;
	}
	
	
}
