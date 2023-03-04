package weather;

public class CurrentConditionsDisplay implements DisplayElement {
	
	private float temperature;
	private float humidity;
	private float pressure;
	
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		display();
	}


	@Override
	public void display() {
		System.out.println("Current Conditions: " + temperature + "C degrees and " + humidity + 
				"% humidity and " + pressure + "mbar");
	}

}
