package weather;

public class WeatherData {
	
	private float temperature;
	private float humidity;
	private float pressure;

	private CurrentConditionsDisplay currentConditionsDisplay;
	
	public WeatherData() {
		currentConditionsDisplay = new CurrentConditionsDisplay();
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
		
		/* Note: Here we are coding to a concrete implementation(currentConditionsDisplay) so 
		 * we have no way to add or remove displays without making changes to this WeatherData class.
		 */
		currentConditionsDisplay.update(temperature, humidity, pressure);
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

}
