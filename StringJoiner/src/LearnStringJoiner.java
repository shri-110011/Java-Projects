import java.util.ArrayList;
import java.util.StringJoiner;

public class LearnStringJoiner {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
        
       al.add("Ram");
       al.add("Shyam");
       al.add("Alice");
       al.add("Bob");
       
       StringJoiner joiner1 = new StringJoiner(",");
       
       // setEmptyValue() method
       joiner1.setEmptyValue("joiner1 is empty!");
       
       //add() method
       joiner1.add(al.get(0)).add(al.get(1));
       
       // length() method
       System.out.println("Length of joiner1: " + joiner1.length());
       
       System.out.println("joiner1 : " + joiner1);
       
       StringJoiner joiner2 = new StringJoiner(":");
       joiner2.add(al.get(2)).add(al.get(3));
         
       //merge() method
       joiner1.merge(joiner2);
         
       // toString() method
       System.out.println(joiner1.toString());
         
       System.out.println("Length of new sj1 : " + joiner1.length());

	}

}
