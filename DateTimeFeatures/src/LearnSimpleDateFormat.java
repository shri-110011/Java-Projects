import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LearnSimpleDateFormat {

	public static void main(String[] args) throws ParseException {
		Date currentDate = new Date(2041-1900,9,7);
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, MMMM-dd-yy");
		SimpleDateFormat sdf1 = new SimpleDateFormat();
		System.out.println(currentDate);
		System.out.println(sdf1.format(currentDate));
		System.out.println(sdf2.format(currentDate));
		System.out.println(sdf2.parse("Sat, Jun-7-2041"));
	}

}
