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
		
		weatherData.setMeasurements(60.9f, 80F, 1012f);
		
//		weatherData.removeObserver(currentConditionsDisplay);
		
		weatherData.setMeasurements(60.8f, 73F, 1013f);
		
	}

}
