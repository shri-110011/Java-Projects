import java.time.LocalTime;

public class LearnLocalTime {

	public static void main(String[] args) {	
		LocalTime currentTime = LocalTime.now();//LocalTime class doesn't have a constructor
		System.out.println(currentTime);
		
		LocalTime customTime = LocalTime.of(1, 59, 35);
		System.out.println(customTime);
		
		System.out.println(LocalTime.parse("10:15:30"));
		System.out.println(LocalTime.MAX);//23:59:59.999999999
		System.out.println(LocalTime.MIN);//00:00
		System.out.println(LocalTime.MIDNIGHT);//00:00
	}

}
