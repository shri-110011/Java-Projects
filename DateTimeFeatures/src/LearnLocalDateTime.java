import java.time.LocalDateTime;

public class LearnLocalDateTime {

	public static void main(String[] args) {
		LocalDateTime currentDateTime = LocalDateTime.now();//LocalDateTime class doesn't have a constructor
		System.out.println(currentDateTime);
		
		//For MonthOfYear in LocalDateTime (valid values 1 - 12)
		LocalDateTime customDateTime = LocalDateTime.of(2020, 1, 6, 4, 30, 25);
		System.out.println(customDateTime);
		
		System.out.println(LocalDateTime.parse("2007-12-03T10:15:30"));
		System.out.println(currentDateTime.getMonthValue());
	}

}
