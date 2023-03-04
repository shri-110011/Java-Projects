package beverages;

public abstract class Beverage {
	
	protected String description = "Unknown beverage";
	
	private boolean milk;
	private boolean soy;
	private boolean mocha;
	private boolean whip;
	
	public String getDescription() {
		return description;
	}
	
	public double cost() {
		double cost = 0.0;
		if(hasMilk()) cost += 0.10;
		if(hasSoy()) cost+= 0.15;
		if(hasMocha()) cost += 0.20;
		if(hasWhip()) cost += 0.10;
		
		return cost;
	}

	public boolean hasMilk() {
		return milk;
	}

	public void setMilk(boolean milk) {
		this.milk = milk;
		description += ", Steamed Milk";
	}

	public boolean hasSoy() {
		return soy;
	}

	public void setSoy(boolean soy) {
		this.soy = soy;
		description += ", Soy";
	}

	public boolean hasMocha() {
		return mocha;
	}

	public void setMocha(boolean mocha) {
		this.mocha = mocha;
		description += ", Mocha";
	}

	public boolean hasWhip() {
		return whip;
	}

	public void setWhip(boolean whip) {
		this.whip = whip;
		description += ", Whip";
	}

}
