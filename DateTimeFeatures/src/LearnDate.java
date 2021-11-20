import java.time.LocalTime;
import java.util.Date;

public class LearnDate {

	public static void main(String[] args) {
		Date currentDate = new Date();
		System.out.println(currentDate);
		System.out.println(currentDate.toString().replace(" ", "_").replace(":", "-"));
		
		System.out.println(currentDate.getDate());
		System.out.println(currentDate.getDay());//Returns a value from 0-6, 0 for Sunday.
		System.out.println(currentDate.getMonth());//Returns a value from 0-11, 0 for January.
		System.out.println(currentDate.toLocaleString());//This will create a string representation of this date which is familiar to the user of the Java application.
		
		Date customDate = new Date("06-seP-1997");// "09/06/1997" and "9/6/1997" both are acceptable but we can't use -(hyphen) as the delimiter instead of slash because that would give IllegalArgumentException 
		System.out.println(customDate.parse("Fri, 09/01/1970, 0:00:00 GMT"));
		System.out.println(new Date(customDate.parse("Fri, 15/8/2055 13:00:00 GMT+0530")).getMonth());
		System.out.println(customDate);
		
		Date customDate2 = new Date(2021,0,10);
		System.out.println(customDate2);
		System.out.println(customDate2.getYear());
	}

}
