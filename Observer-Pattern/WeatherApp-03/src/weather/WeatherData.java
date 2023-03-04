package weather;

import java.util.Observable;

public class WeatherData extends Observable {
	
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
	
	// measurementsChanged() will be called whenever the weather measurements gets updated 
	public void measurementsChanged() {
		notifyObservers();
	}
	
	/* setMeasurements() is a custom method to simulate the calling of measurementsChanged() by the
	 * WeatherStation on measurements updates.
	 */
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		/* Note: This setChanged(); has to be called whenever the state of this Observable/Subject  
		 * changes. Otherwise the observers won't get notified.
		 */
		setChanged();
		measurementsChanged();
	}

}
