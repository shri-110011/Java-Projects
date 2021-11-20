import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LearnDateTimeFormatter {

	public static void main(String[] args) {
		LocalDateTime obj = LocalDateTime.now();
		obj = obj.parse("0507-12-03T10:15:30");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy");// The argument for DateTimeFormatter.ofPattern() can be customized. 
		//E for weekday name
		//d or dd for day
		//MM for month number
		//YYYY or uuuu for year
		//D for day of the year
		//MMM for month name
		String formattedDate = obj.format(dtf);//Will return a string
		System.out.println(obj);
		System.out.println(formattedDate);
	}

}
