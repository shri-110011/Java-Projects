

class HouseFullException extends Exception {
	
	HouseFullException(Throwable cause) {
		super(cause);
	}
	
	public String toString() {
		return "HouseFullException:";
	}
}

public class ChainedExcDemo2 {
	
	
	static void demoproc() throws HouseFullException {
		
		//create an exception
		HouseFullException e = new HouseFullException(new IllegalArgumentException("No of seats asked exceeeds the remaining seats."));
		
		throw e;
		
	}

	public static void main(String[] args) {
		try {
			demoproc();
		}
		catch(HouseFullException e) {
			//display top level exception
			System.out.println("Caught: "+e);
			
			//diaplay cause exception
			System.out.println("Original cause: "+e.getCause());
		}

	}

}
