import java.util.EventObject;

public class FormEvent extends EventObject {
	private String name;
	private String occupation;
	private int ageCat;
	private String empCat;
	
	public FormEvent(Object source) {
		super(source);	
	}
	public FormEvent(Object source, String name, String occupation, int ageCat, String empCat) {
		super(source);	
		this.name = name;
		this.occupation = occupation;
		this.ageCat = ageCat;
		this.empCat = empCat;
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
	
}
