package drivers;

import weather.CurrentConditionsDisplay;
import weather.ForecastDisplay;
import weather.StatisticsDisplay;
import weather.WeatherData;

public class WeatherStation {
	
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		
		/* Note: While using observable one should not rely on the order in which the observers are 
		 * notified.
		 * 
		 */
		weatherData.setMeasurements(17f, 66F, 1012f);
		
//		weatherData.deleteObserver(currentConditionsDisplay);
		
		weatherData.setMeasurements(15f, 73F, 1013f);
		
	}

}
