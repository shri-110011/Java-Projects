package collections;

import java.util.EnumSet;

enum Color {
	RED,
	GREEN,
	BLUE
}
public class EnumSetDemo {

	public static void main(String[] args) {
		EnumSet<Color> ens = EnumSet.of(Color.RED);
		
		System.out.println(ens);
		
		System.out.println(EnumSet.complementOf(ens));
		
		ens =  EnumSet.allOf(Color.class);
		
		System.out.println(ens);
		
		System.out.println(EnumSet.complementOf(ens));
		
		System.out.println(EnumSet.range(Color.RED, Color.RED));

	}

}
