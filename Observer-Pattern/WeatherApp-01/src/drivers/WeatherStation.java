package drivers;

import weather.WeatherData;

public class WeatherStation {
	
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		weatherData.setMeasurements(18.2f, 71F, 1015f);
	}

}
