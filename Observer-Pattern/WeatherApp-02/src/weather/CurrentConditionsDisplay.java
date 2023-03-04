package weather;

public class CurrentConditionsDisplay implements DisplayElement, Observer {
	
	private float temperature;
	private float humidity;
	private float pressure;
	
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
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
