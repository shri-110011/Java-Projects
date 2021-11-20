import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Marathon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Float> al1 = new ArrayList<Float>();
		String str;
		int flag=0;
		while(true) {
			str = sc.nextLine();
			if(str.charAt(0) != 'q') {
				float dist = Float.parseFloat(str);
				if(dist <42.174 && dist >0)
					al1.add(dist);
				if(dist < 0)
	                   flag=1;
			}
			else {
				break;
			}
		}
		Collections.sort(al1, Collections.reverseOrder());
		//System.out.println(al1);
		if(flag==0)
			System.out.println(al1.subList(0, 3));
		else if(flag==1)
			System.out.println("Invalid Input");	
	}
}
