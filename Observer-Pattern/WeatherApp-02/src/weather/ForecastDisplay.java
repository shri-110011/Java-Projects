package weather;

public class ForecastDisplay implements DisplayElement, Observer {
	
	/* 1atm = 101325pa
	 * density of Hg = 13.5Kg/m^3
	 * presssure = density * g* h
	 * 1atm = 76.587cm
	 * 1in = 2.54cm
	 * So 1atm = 30.152 in of Hg
	 * 
	 */
	private float currentPressure = 1013.25f;  
	private float lastPressure;
	private Subject weatherData;
	
	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		lastPressure = currentPressure;
		currentPressure = pressure;

		display();
	}

	@Override
	public void display() {
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}

}
