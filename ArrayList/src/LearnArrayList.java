
import java.util.ArrayList;
import java.util.Arrays;

public class LearnArrayList {
	public static void main(String[] args) {
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		
		al1.add(1);
		al1.add(2);
		al1.add(3);
		System.out.println(al1);
		//In ArrayList the elements are stored in an ordered manner.
		System.out.println(al1.get(1));
		al1.remove(1);
		System.out.println(al1);
		System.out.println(al1.size());
		al1.add(1, 2);
		System.out.println(al1);
		
		System.out.println(Arrays.asList(new Integer[] {2,3,5,7}));
	}
}
