import java.time.LocalDate;
import java.util.Date;

public class LearnLocalDate {

	public static void main(String[] args) {
//		LocalDate ld = new LocalDate(); //LocalDate class doesn't have a constructor
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		
		//For MonthOfYear in LocalDate (valid values 1 - 12)
		LocalDate customDate = LocalDate.of(2000, 1, 29);//LocalDate.of() throws DateTimeException
		System.out.println(customDate);
		
		System.out.println(LocalDate.parse("2012-02-29"));//LocalDate.of() throws DateTimeParseException
		System.out.println(LocalDate.EPOCH);
		System.out.println(LocalDate.MAX);//+999999999-12-31
		System.out.println(LocalDate.MIN);//-999999999-01-01
		
	}

}
