package weather;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements DisplayElement, Observer {
	
	private float temperature;
	private float humidity;
	private float pressure;
	
	public CurrentConditionsDisplay(Observable observable) {
		observable.addObserver(this);
	}
	
	@Override
	public void display() {
		System.out.println("Current Conditions: " + temperature + "C degrees and " + humidity + 
				"% humidity and " + pressure + "mbar");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)o;
			temperature = weatherData.getTemperature();
			humidity = weatherData.getHumidity();
			pressure = weatherData.getPressure();
			
			display();
		}
	}

}
