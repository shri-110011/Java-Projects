package weather;

public class StatisticsDisplay implements DisplayElement, Observer {
	
	private float maxTemp = 0.0f;
	private float minTemp = 48.0f;
	private float tempSum= 0.0f;
	private int numReadings;
	private Subject weatherData;
	
	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		numReadings++;
		tempSum += temperature;
		
		if(temperature > maxTemp) {
			maxTemp = temperature;
		}
		
		if(temperature < minTemp) {
			minTemp = temperature;
		}
		
		display();

	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
				+ "/" + maxTemp + "/" + minTemp);
	}

}
