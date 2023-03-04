package weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
	
	private float temperature;
	private float humidity;
	private float pressure;
	
	private List<Observer> observersList;
	
	public WeatherData() {
		observersList = new ArrayList<>();
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
		
		measurementsChanged();
	}

	@Override
	public void registerObserver(Observer observer) {
		observersList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int index = observersList.indexOf(observer);
		
		if(index > -1) {
			observersList.remove(index);
		}
	}

	@Override
	public void notifyObservers() {
		for(Observer observer: observersList) {
			observer.update(temperature, humidity, pressure);
		}
	}

}
