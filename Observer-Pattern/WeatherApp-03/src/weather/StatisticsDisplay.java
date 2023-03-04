package weather;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements DisplayElement, Observer {
	
	private float maxTemp = 0.0f;
	private float minTemp = 48.0f;
	private float tempSum= 0.0f;
	private int numReadings;
	
	public StatisticsDisplay(Observable observable) {
		observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
				+ "/" + maxTemp + "/" + minTemp);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)o;
			float temperature = weatherData.getTemperature();
			
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
	}

}
