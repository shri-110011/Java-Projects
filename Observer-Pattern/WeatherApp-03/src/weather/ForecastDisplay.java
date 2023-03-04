package weather;

import java.util.Observable;
import java.util.Observer;

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
	
	public ForecastDisplay(Observable observable) {
		observable.addObserver(this);
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

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)o;
			
			lastPressure = currentPressure;
			currentPressure = weatherData.getPressure();

			display();
		}
	}

}
