import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConvertArrayIntoArrayList {

	public static void main(String[] args) {
		int arr[] = new int[] {45,34,78,23,98,12};
		String[] geeks = {"Rahul", "Utkarsh", 
                "Shubham", "Neelam"}; 
		
		List<String> al1 = new ArrayList<String>(Arrays.asList(geeks));
		System.out.println(al1);
		
	}

}
