
class UserDefinedException extends Exception {
	public UserDefinedException(String str) {
		super(str);
	}
}

public class LearnExceptionHandling1  {

	public static void main(String[] args) throws UserDefinedException {
		// TODO Auto-generated method stub
		
		try {
			throw new UserDefinedException("UDE");
		}
		catch(UserDefinedException e1) {
			System.out.println("User defined exception occurred");
			throw new UserDefinedException("UDE");
//			try {
//				throw new ArrayIndexOutOfBoundsException("UDE");
//			}
//			catch(Exception e2) {
//				e2.printStackTrace();
//			}
		}
		catch(Exception e2) {
			System.out.println("##");
//			e2.printStackTrace();
		}
		finally {
			System.out.println("Hello");

		}
//		throw new UserDefinedException("UDE");
//		System.out.println("Hello");
		try {
			System.out.println("Hello");
		}
		catch(Exception e) {
			
		}
	}

}
