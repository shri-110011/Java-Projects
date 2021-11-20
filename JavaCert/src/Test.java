
//public class Test {
//     public static void main(String[] args) {
//         System.out.println("Output is: " + 10 != 5);
//     }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<Character> list = new ArrayList<>();
//         list.add(0, 'V');
//         list.add('T');
//         list.add(1, 'E');
//         list.add(3, 'O');
// 
//         if(list.contains('O')) {
//             list.remove('O');
//         }
// 
//         for(char ch : list) {
//             System.out.print(ch);
//         }
//     }
//}

//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<LocalDate> dates = new ArrayList<>();
//         dates.add(LocalDate.parse("2018-07-11"));
//         dates.add(LocalDate.parse("1919-02-25"));
//         dates.add(LocalDate.of(2020, 4, 8));
//         dates.add(LocalDate.of(1980, Month.DECEMBER, 31));
// 
//         dates.removeIf(x -> x.getYear() < 2000);
// 
//         System.out.println(dates);
//     }
//}


//public class Test {
//	static int count = 0; 
//     public static void main(String[] args) {
//         try {
//        	 System.out.println(count++);
//             main(args);
//         } catch (Exception ex) {
//             System.out.println("CATCH-");
//         }
//             System.out.println("OUT");
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        System.out.println(sb.append(null).length());
//    }
//}

//public class Test {
//    public static void main(String[] args) {
        /*INSERT*/
//    	byte [] arr = new byte[10];
//    	short [] arr; arr = new short[3];
//    	int [] arr = new int[]{0, 0, 0, 0};
//        arr[1] = 5;
//        arr[2] = 10;
//        System.out.println("[" + arr[1] + ", " + arr[2] + "]"); //Line n1
//    }
//}

//1. short arr [] = new short[2];
//
//2. byte [] arr = new byte[10];
//
//3. short [] arr; arr = new short[3];
//
//4. short [2] arr;
//
//5. short [3] arr;
//
//6. int [] arr = new int[]{100, 100};
//
//7. int [] arr = new int[]{0, 0, 0, 0};
//
//8. short [] arr = {};
//
//9. short [] arr = new short[2]{5, 10};


//import java.io.FileNotFoundException;
//import java.io.IOException;
// 
//abstract class Super {
//     public abstract void m1() throws IOException;
//}
// 
//class Sub extends Super {
//     @Override
//     public void m1() throws IOException {
//         throw new FileNotFoundException();
//     }
//}
// 
//public class Test {
//     public static void main(String[] args) {
//         Super s = new Sub();
//         try {
//             s.m1();
//         } catch (FileNotFoundException e) {
//             System.out.print("M");
//         } finally {
//             System.out.print("N");
//         }
//     }
//}

//public class Test {
//     public static void main(String[] args) {
//         Double [] arr = new Double[2];
//         double [] arr2 = new double[2];
//         System.out.println(arr2[0] + arr2[1]);
//     }
//}


//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<Integer> list = new ArrayList<>();
//         list.add(100);
//         list.add(200);
//         list.add(100);
//         list.add(200);
//         list.remove(100);
// 
//         System.out.println(list);
//     }
//}

//public class Test {
//     public static void main(String[] args) {
//         m1(); //Line 3
//     }
// 
//     private static void m1() throws Exception { //Line 6
//         System.out.println("NOT THROWING ANY EXCEPTION"); //Line 7
//     }
//}

//import java.time.LocalDate;
//import java.time.LocalTime;
// 
//public class Test {
//     public static void main(String [] args) {
//         LocalDate date = LocalDate.parse("1947-08-14");
//         LocalTime time = LocalTime.MAX;
//         System.out.println(date.atTime(time));
//     }
//}

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<String> dryFruits = new ArrayList<>();
//         dryFruits.add("Walnut");
//         dryFruits.add("Apricot");
//         dryFruits.add("Almond");
//         dryFruits.add("Date");
// 
//         Iterator<String> iterator = dryFruits.iterator();
//         while(iterator.hasNext()) {
//             String dryFruit = iterator.next();
//             if(dryFruit.startsWith("A")) {
////                 dryFruits.remove(dryFruit);
//             }
//         }
//        
//         System.out.println(dryFruits);
//    }
//}

//abstract class Animal {
//     private String name;
// 
//     Animal(String name) {
//        this.name = name;
//     }
// 
//     public String getName() {
//         return name;
//     }
//}
// 
//class Dog extends Animal {
//     private String breed;
//     Dog(String breed) {
//         this.breed = breed;
//     }
// 
//     Dog(String name, String breed) {
//         super(name);
//         this.breed = breed;
//     }
// 
//     public String getBreed() {
//         return breed;
//     }
//}
// 
//public class Test {
//     public static void main(String[] args) {
//         Dog dog1 = new Dog("Beagle");
//         Dog dog2 = new Dog("Bubbly", "Poodle");
//         System.out.println(dog1.getName() + ":" + dog1.getBreed() + ":" + 
//                             dog2.getName() + ":" + dog2.getBreed());
//     }
//}


//public class Test {
//    public static void main(String[] args) {
//        String s1 = "OCAJP";
//        String s2 = "OCAJP" + "";
//        System.out.println(s1 == s2);
//    }
//}

//public class Test {
//     public static void main(String[] args) {
//        char var = 7, var2 = '7';
//        Character var3 = 7;
//        Integer var4 = 7, var6=9;
//        int var5 = 7, var7='a', var8=0;
//        System.out.println("var: "+var);
//        System.out.println(++var);
//        System.out.println("var3: "+var3);
//        System.out.println("var4: "+var4);
//        System.out.println("var5: "+var5);
//        System.out.println("var6: "+var6);
//        System.out.println("var7: "+var7);
//        System.out.println("var8: "+var8);
//        System.out.println("var7: "+String.valueOf(var2));
//        System.out.println("var7: "+Character.getNumericValue(var2));
//        System.out.println(var == var3);
//        System.out.println(var == var5);
//         switch(var3) {
//             case 7:
//                 System.out.println("Lucky no. 7");
//                 break;
//             default:
//                 System.out.println("DEFAULT");
//         }
//     }
//}

//public class Test 
//{
//	public static void main(String[] args) 
//	{
//		Boolean b1 = Boolean.valueOf(null);
//		Boolean b2 = Boolean.valueOf(false);
//		System.out.print((b1 == b2)+" ");
//		System.out.print(b1.equals(b2));
//		
//		System.out.println("\n");
//		
//		Integer i1 = Integer.valueOf(3);
//		Integer i2 = Integer.valueOf(3);
//		System.out.print((i1 == i2)+" ");
//		System.out.print(i1.equals(i2));
//	}
//}

//public class Test 
//{
//	int count;
//	public void Test()
//	{
//		count=7;
//	}
//	public static void main(String[] args) 
//	{
//		Test t = new Test();
//		System.out.println(t.count);
//	}
//}

//public class Test 
//{
//	public static void main(String[] args) 
//	{
//		Boolean b1 = new Boolean(null);
//		Boolean b2 = new Boolean(false);
//		System.out.print((b1 == b2)+" ");
//		System.out.print(b1.equals(b2));
//	}
//}

//public class Test  
//{
//	public static void main(String[] args) 
//	{
//		int[] a = {10,20};
//		char[] b = {'a','b'};
//		a=b;
//		for(int i :a)
//		{
//			System.out.print(i);
//		}		
//	}
//}

//public class Test 
//{
//	public static void main(String[] args) {
//		String[] str = new String[2];
//		System.out.println(str[2]);
//		String[] str2 = new String[2];
//		str2[0] = "2";
//		str2[1] = "a";
//		str2[2] = "car";
//		System.out.println(str2[2]);
//		
//	}
//}

//public class Test {
//	public static void main(String ...args) {
//		for(String str:args) {
//			System.out.println(str);
//		}
//	}
//}


//interface Flyer {
//	String getName();
//}
//class Bird implements Flyer {
//	String name;
//	
//	public Bird(String name) {
//		this.name = name;
//	}
//	public String getName() { return name; }
//}
//class Eagle extends Bird {
//	public Eagle(String name) {
//		super(name);
//	}
//}
//
//public class Test {
//	public static void main(String[] args) {
//		Flyer f = new Eagle("American Bald Eagle");
////		Eagle e = new Eagle("Peacock");
//		
//		System.out.println(f.getName());
//		//Note: While accessing a method or a variable, the compiler will only allow you to access a method 
//		//or variable that is visible through the class of the reference.
//		//Here the class of the reference is Flyer where only getName() is there.
//		//However if we type cast f to Eagle or Bird then the name field will also be visible because 
//		//an Eagle is also a Bird.
//		
////		System.out.println(f.name);
//		System.out.println(((Eagle)f).name);
//		System.out.println(((Bird)f).getName());
////		System.out.println(Eagle.name);
////		System.out.println(Eagle.getName());
//	}
//}

//interface House {
//	int a = 0 ;
//	default String getAddress() {
//		return "101 Main Street";
//	}
//	public default void isAvailableForRent() {
//		System.out.println("Yes, House is available for rent.");
//	}
//}
//interface Bungalow extends House {
//	default String getAddress() {
//		return "101 Smart Street";
//	}
//}
//class MyHouse implements Bungalow, House {
//	
//	 public String getAddress() {
//		return "101 Old Street";
//	}
//	
//}
//public class Test {
//	public static void main(String[] args) {
//		House ci = new MyHouse();
//		System.out.println(ci.getAddress());
//		ci.isAvailableForRent();
////		System.out.println(ci.a);
//		System.out.println(ci.getClass());
//	}
//}

//public class Test {
//	public static long main(String[] args) {
//		System.out.println("Hello");
//		return 10L;
//	}
//}

//public class Test {
//	static boolean b;
//	static int[] ia = new int[1];
//	static char ch;
//	static boolean[] ba = new boolean[1];
//	int c;
//	
//	public  void display() {
//		c=2;
//		System.out.println("Hello");
//	}
//	
//	public static void main(String[] args) {
//		boolean x = false;
//		System.out.println("ch:"+ch+"@");//default value for a char type instance variable is a blank or a space.
//		int a = ch;
//		System.out.println("a:"+a);
//		System.out.println("ia[0]:"+ia[0]);
//		System.out.println("b:"+b);
//		System.out.println("ba[0]:"+ba[0]);
//		Test t = new Test();
//		System.out.println(t.b);
//		t.display();
////		Test.display();
//		System.out.println(t.c);
//		
//		if(b) {
//			x = (ch == ia[ch]);
//			
//		}
//		else x = (ba[ch] = b);
//		System.out.println(x+" "+ba[ch]);
//		
//	}
//}

//class User {
//	Bandwidth bw = new Bandwidth();
//	public void consumeBytes(int bytesUsed) {
//		bw.addUsage(bytesUsed);
//		System.out.println(bw.getTotalBill());
//	}
//}
//class Bandwidth {
//	private int totalUsage;
//	private double totalBill;
//	private double costPerByte = 2;
//	
//	protected void addUsage(int bytesUsed) {
//		if(bytesUsed>0) {
//			totalUsage = bytesUsed;
//			totalBill += totalUsage*costPerByte;
//		}
//	}
//	public double getTotalBill() {
//		return totalBill;
//	}
//}
//public  class Test {
//	public static void main(String[] args) {
//		User user = new User();
//		user.consumeBytes(1000);
//		user.consumeBytes(1000);
//		
//	}
//}

//public  class Test {
//	public static void main(String[] args) {
//		Object a, b, c;
//		a = new String("A");
//		b = new String("B");
//		c = a;
//		a = b;
//		a = 10;
//		b = Integer.valueOf("1111", 4);
//		System.out.println(c);
//		System.out.println(a);
//		System.out.println(b+" "+b.getClass());
//		System.out.println(Integer.parseInt(b.toString())+'a');
//		
//		c = "lion";
//		System.out.println(c+" "+c.getClass());
//		c = String.valueOf(new Character[]{'a', 'f'});
//		System.out.println(c);
//		c='z';
//		System.out.println(c+" "+c.getClass());
////		System.out.println(String.valueOf(null));
//		char d1 = 'p';
//		Character d2 = new Character('p'); 
//		Character d3 = new Character('p'); 
//		Character d4 = Character.valueOf('p'); 
//		char d5 = Character.valueOf('p'); 
//		char d6 = new Character('p');
//		char d7 = 'p';
//		System.out.println("d4 "+d4+" d5: "+d5+" d6: "+d6);
//		System.out.println("1 "+(d1==d2));//true
//		System.out.println("2 "+(d1==d3));//true
//		System.out.println("3 "+(d1==d4));//true
//		System.out.println("4 "+(d1==d5));//true
//		System.out.println("5 "+(d1==d6));//true
//		System.out.println("6 "+(d1==d7));//true
//		System.out.println("7 "+(d2==d3));//false
//		System.out.println("8 "+(d2==d4));//false
//		System.out.println("9 "+(d2==d5));//true
//		System.out.println("10 "+(d2==d6));//true
//		System.out.println("11 "+(d2==d7));//true
//		System.out.println("12 "+(d3==d4));//false
//		System.out.println("13 "+(d3==d5));//true
//		System.out.println("14 "+(d3==d6));//true
//		System.out.println("15 "+(d3==d7));//true
//		System.out.println("16 "+(d4==d5));//true
//		System.out.println("17 "+(d4==d6));//true
//		System.out.println("18 "+(d4==d7));//true
//		System.out.println("19 "+(d5==d6));//true
//		System.out.println("20 "+(d5==d7));//true
//		System.out.println("21 "+(d6==d7));//true
//		System.out.println(d2.equals(d4));//true
//		
//		System.out.println("#####################");
//		int i1 = 5;
//		Integer i2 = new Integer(5);
//		Integer i3 = new Integer(5);
//		Integer i4 = Integer.valueOf(5);
//		int i5 = Integer.valueOf(5);
//		int i6 = new Integer(5);
//		int i7 = 5;
//		System.out.println("1 "+(i1==i2));//true
//		System.out.println("2 "+(i1==i3));//true
//		System.out.println("3 "+(i1==i4));//true
//		System.out.println("4 "+(i1==i5));//true
//		System.out.println("5 "+(i1==i6));//true
//		System.out.println("6 "+(i1==i7));//true
//		System.out.println("7 "+(i2==i3));//false
//		System.out.println("8 "+(i2==i4));//false
//		System.out.println("9 "+(i2==i5));//true
//		System.out.println("10 "+(i2==i6));//true
//		System.out.println("11 "+(i2==i7));//true
//		System.out.println("12 "+(i3==i4));//false
//		System.out.println("13 "+(i3==i5));//true
//		System.out.println("14 "+(i3==i6));//true
//		System.out.println("15 "+(i3==i7));//true
//		System.out.println("16 "+(i4==i5));//true
//		System.out.println("17 "+(i4==i6));//true
//		System.out.println("18 "+(i4==i7));//true
//		System.out.println("19 "+(i5==i6));//true
//		System.out.println("20 "+(i5==i7));//true
//		System.out.println("21 "+(i6==i7));//true
//		System.out.println("#####################");
//		String str1 = "mouse";
//		String str2 = new String("mouse");
//		String str3 = new String("mouse");
//		String  str4 = String.valueOf(new Character[] {'m','o','u','s','e'});
//		String  str5 = String.valueOf(new char[] {'m','o','u','s','e'});
//		String str6 = "mouse";
//		char chs[] = new char[] {'m','o','u','s','e'};
//		System.out.println(str4);
//		System.out.println(str5);
//		System.out.println(chs);
//		System.out.println("1 "+(str1==str2));//false
//		System.out.println("2 "+(str1==str3));//false
//		System.out.println("3 "+(str1==str4));//false
//		System.out.println("4 "+(str1==str5));//false
//		System.out.println("5 "+(str1==str6));//true
//		System.out.println("6 "+(str2==str3));//false
//		System.out.println("7 "+(str2==str4));//false
//		System.out.println("8 "+(str2==str5));//false
//		System.out.println("9 "+(str2==str6));//false
//		System.out.println("10 "+(str3==str4));//false
//		System.out.println("11 "+(str3==str5));//false
//		System.out.println("12 "+(str3==str6));//false
//		System.out.println("13 "+(str4==str5));//false
//		System.out.println("14 "+(str4==str6));//false
//		System.out.println("15 "+(str5==str6));//false
//		System.out.println(str2.equals(str5));//true
//		System.out.println(String.valueOf(null));
//		
//	}
//}

//class X {
//	public X() {
//		System.out.println("In X");
//	}
//}
//class Y extends X {
//	public Y() {
//		System.out.println("In Y");
//	}
//}
//class Z extends Y {
//	public Z() {
//		System.out.println("In Z");
//	}
//}
//public class Test {
//	public static void main(String[] args) {
//		Y y = new Z();
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		Integer i1 = 1;
//		Integer i2 = new Integer(1);
//		int i3 = 1;
//		Integer i4 = 1;
//		Byte b1 = 1;
//		Long g1 = 1L;
//		
//		System.out.println(i1==i2);
//		System.out.println(i1==i3);
//		System.out.println(i1==i4);
////		System.out.println(i1==b1);
//		System.out.println(i1.equals(i2));
//		System.out.println(i1.equals(i3));
//		System.out.println(i2.equals(i3));
//		System.out.println(i1.equals(g1));
//		System.out.println(g1.equals(i1));
//		
////		i4=i1;
//		i1=5;
//		
//		System.out.println(i1);
//		System.out.println(i3);
//		System.out.println(i4);
//		
//		
//		String str1 = "apple";
//		String str2 = "banana";
//		String str3 = "apple";
//		
//		System.out.println(str1==str2);
//		System.out.println(str1==str3);
//		str1 = "mango";
//		System.out.println(str1+" "+str3);
//		System.out.println(str1==str3);
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		Stack s1 = new Stack();
//		Stack s2 = new Stack();
//		processStacks(s1, s2);
//		System.out.println(s1+" "+s2);
//	}
//	static void processStacks(Stack s1, Stack s2) {
//		s1.push(new Integer(100));
//		s2=s1;
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		int a[][] = new int[2][];
//		a[0] = new int[2];
//		a[1] = new int[4];
//		a[0][0] = 1;
//		a[0][1] = 2;
//		a[1][0] = 3;
//		a[1][1] = 4;
//		a[1][2] = 5;
//		a[1][3] = 6;
//		List<int[]> l1 = new ArrayList<>(Arrays.asList(a));
//		
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		int a[];
//		int[] b;
//		Object c[];
//		Object[] d;
//		
//		a = new int[5];
//		b = new int[5];
//		c = new Object[5];
//		d = new Object[5];
//		for(int i=0; i<5; i++) {
//			a[i]=i+1;
//			b[i]=i+1;
//			c[i]=i+1;
//			d[i]=i+1;
//		}
//		for(int i=0; i<5; i++) {
//			System.out.println(a[i]+" "+b[i]+" "+c[i]+" "+d[i]);
//		}
//	}
//}

//class A {
//	public A(int i) {
//		System.out.println(i);
//	}
//}
//class Test {
//	//Here it seems like static variables are read first and then the default ones.
//	A a = new A(5);
//	A b = new A(2);
//	public static void main(String[] args) {
//		Test t = new Test();
//		A a = new A(3);
//	}
//	static A s2 = new A(4);
//	static A s1 = new A(1);
//}

//public class Test {
//	public static void main(String[] args) {
//		Integer value = 1,000,000;
//		switch(value) {
//		default: System.out.println("Default case");
//		break;
//		case 1_000_000: System.out.println("A million 1");
//		break;
//		case 1000000: System.out.println("A million 2");
//		break;
//		}
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		boolean b1 = true;
//		boolean b2 = false;
//		boolean b3 = true;
//		if(b3 != b1 == b2) {
//			System.out.println("true");
//		}
//		else {
//			System.out.println("false");
//		}
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		String str1 = "Java";
//		String str2[] = {"J","a","v","a"};
//		String str3 = "";
//		for(String str: str2) {
//			str3 = str3+str;
//		}
//		boolean b1 = (str1 == str3);
//		boolean b2 = str1.equals(str3);
//		
//		System.out.println(str1+" "+str3);
//		System.out.println(b1+" "+b2);
//		
//		String str4 = "Java";
//		System.out.println(str1==str4);
//		System.out.println(str1.equals(str4));
//		
//		String str5 = new String("Java");
//		System.out.println(str1==str5);
//		System.out.println(str1.equals(str5));
//		
//	}
//}

//class Base {
//	public void test() {
//		System.out.println("Base");
//	}
//}
//class DerivedA extends Base {
//	public void test() {
//		System.out.println("DerivedA");
//	}
//}
//public class Test extends DerivedA {
////	public void test() {
////		System.out.println("DerivedTest");
////	}
//	public static void main(String[] args) {
//		Base b1 = new Test();
//		Base b2 = new DerivedA();
//		Base b3 = new Test();
//		b1 = (Base)b3;
//		Base b4 = (DerivedA) b3;
//		b1.test();
//		b4.test();
//		b2.test();
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		StringBuilder sb = new StringBuilder();
//		System.out.println("#"+sb+"#");
//		String s = "";
//		if(sb.equals(s)) {
//			System.out.println("Match 1");
//		}
//		else if(sb.toString().equals(s.toString())) {
//			System.out.println("Match 2");
//		}
//		else {
//			System.out.println("No Match");
//		}
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		String chs[][] = new String[2][];
//		chs[0] = new String[2];
//		chs[1] = new String[5];
//		int i = 97;
//		System.out.println(chs.length);
//		for(int a=0; a<chs.length; a++) {
//			for(int b=0; b<chs.length; b++) {
//				chs[a][b] = ""+i;
//				i++;
//			}
//		}
//		
//		for(String[] ca: chs) {
//			for(String c: ca) {
//				System.out.print(c+" ");
//			}
//			System.out.println();
//		}
//		
//		int a[] = new int[5];
//		for(int j=0; j< a.length; j++) {
//			System.out.print(a[j]+" ");
//		}
//		System.out.println();
//		float b[] = new float[5];
//		for(int j=0; j< b.length; j++) {
//			System.out.print(b[j]+" ");
//		}
//		System.out.println();
//		String c[] = new String[5];
//		for(int j=0; j< c.length; j++) {
//			System.out.print(c[j]+" ");
//		}
//		System.out.println();
//		char d[] = new char[5];
//		for(int j=0; j< d.length; j++) {
//			System.out.print("#"+d[j]+" ");
//		}
//		System.out.println();
//		double e[] = new double[5];
//		for(int j=0; j< e.length; j++) {
//			System.out.print(e[j]+" ");
//		}
//		System.out.println();
//		long f[] = new long[5];
//		for(int j=0; j< f.length; j++) {
//			System.out.print(f[j]+" ");
//		}
//		System.out.println();
//		short g[] = new short[5];
//		for(int j=0; j< g.length; j++) {
//			System.out.print(g[j]+" ");
//		}
//		System.out.println();
//	}	
//}

//public class Test {
//	public static void main(String args[]) {
//		LocalDate date1 = LocalDate.now();
//		LocalDate date2 = LocalDate.of(2014,  6, 20);
//	
//		System.out.println("date1: "+date1);
//		System.out.println("date2: "+date2);
//		LocalDate date3 = LocalDate.parse("204-06-20", DateTimeFormatter.ISO_DATE);
//		System.out.println("date3: "+date3);
//	}
//}

//abstract class A1 {
//	public abstract void m1();
//	public void m2() {
//		System.out.println("Green");
//	}
//}
//abstract class A2 extends A1 {
//	public abstract void m3();
//	public void m1() {
//		System.out.println("Cyan");
//	}
//	public void m2() {
//		System.out.println("Blue");
//	}
//}
//public class Test extends A2 {
//	public void m1() {
//		System.out.println("Yellow");
//	}
//	public void m2() {
//		System.out.println("Pink");
//	}
//	public void m3() {
//		System.out.println("Red");
//	}
//	public static void main(String[] args) {
//		A2 tp = new Test();
//		tp.m1();
//		tp.m2();
//		tp.m3();
//	}
//}

//public class Test {
//	static void doubling(Integer ref, int pv) {
//		ref = 20;
//		pv = 20;
//	}
//	public static void main(String[] args) {
//		Integer iObj = 10;
//		int iVar = 10;
//		doubling(iObj++, iVar++);
//		System.out.println(iObj+" "+iVar);
//		
//		System.out.println(iVar++ + iVar);
//	}
//}

//public class Test {
//	public static void main(String[] args) {
//		Boolean b1 = Boolean.valueOf(null);
//		boolean b2 = false;
//		Boolean b3 = Boolean.valueOf("as");
//		System.out.println(b1+" "+b2+" "+b3);
//		System.out.println(b1==b3);
//		
//	}
//}

//public class Test {
//    private static void m1() throws Exception {
//        throw new Exception();
//    }
//
//    public static void main(String[] args) {
//        try {
//            m1();
//        } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//            System.out.println("A");
//        }
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder("Java");
//        String s1 = "Java";
//        String s2 = "Java";
//
//        System.out.println(s1 == s2);
//    }
//}


//public class Test {
//    public static void main(String[] args) {
//        final String fName = "James";
//        String lName = "Gosling";
//        String name1 = fName + lName;
//        String name2 = fName + "Gosling";
//        String name3 = "James" + "Gosling";
//        System.out.println(name1 == name2);
//        System.out.println(name2 == name3);
//    }
//}

//class Message {
//    public static void main(String [] args) {
//        System.out.println("Welcome " + args[0] + "!");
//    }
//}
// 
//public class Test {
//    public static void main(String [] args) {
//        Message.main(args);
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        System.out.println(new Boolean("true"));
//    }
//}

//public class Test {
//    public static void main(String[] args) {
////        m1(null);
//    	m1((byte)5);
//    }
//    
//    static void m1(CharSequence s) {
//        System.out.println("CharSequence");
//    }
//    
//    static void m1(String s) {
//        System.out.println("String");
//    }
//    
//    static void m1(Object s) {
//        System.out.println("Object");
//    }
//    
//    static void m1(int s) {
//        System.out.println("int");
//    }
//    
//    static void m1(Integer s) {
//        System.out.println("Integer");
//    }
//    
//    static void m1(double s) {
//        System.out.println("double");
//    }
//    
//    static void m1(Double s) {
//        System.out.println("Double");
//    }
//    static void m1(float s) {
//        System.out.println("float");
//    }
//    
//    static void m1(Float s) {
//        System.out.println("Float");
//    }
//    
//    static void m1(byte s) {
//        System.out.println("byte");
//    }
//    
//    static void m1(Byte s) {
//        System.out.println("Byte");
//    }
//}


//class Parent {
//     public String toString() {
//         return "Inner ";
//     }
//}
// 
//class Child extends Parent {
//     public String toString() {
//         return super.toString().concat("Peace!");
//     }
//}
// 
//public class Test {
//     public static void main(String[] args) {
//         System.out.println(new Child());
//     }
//}

//import java.time.Period;
//
//public class Test {
//     public static void main(String [] args) {
//         Period period = Period.of(2, 1, 0).ofYears(10).ofMonths(5).ofDays(2);
//         System.out.println(period);
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        int grade = 75;
//        if(grade > 60)
//            System.out.println("Congratulations");
//            System.out.println("You passed");
//        else
//            System.out.println("You failed");
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        int [] arr = {3, 2, 1};
////      int [] arr1 = new int[2] {1,2};
//        char [] chs[] = new char[2][];
//        for(int i : arr) {
//            System.out.println(arr[i]);
//        }
//    }
//}

//interface I1 {
//    void m1() throws java.io.IOException;
//}
//public class Test implements I1 {
//	public void m1() throws java.io.FileNotFoundException {}
//	public static void main(String[] args) {
//		Test t1 = new Test();
////		t1.m1();
//	}
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     List list1 = new ArrayList<String>(); //Line 5
//     List<String> list2 = new ArrayList(); //Line 6
//     List<> list3 = new ArrayList<String>(); //Line 7
//     List<String> list4 = new ArrayList<String>(); //Line 8
//     List<String> list5 = new ArrayList<>(); //Line 9
//     public static void main(String[] args) {
//    	 Test t1 = new Test();
//    	 t1.list4.add("ASD");
//     }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
//        list.add(null);
//        System.out.println(list.remove(0) + ":" + list.remove(null));
//        System.out.println(list);
//    }
//}

//public class Test {
//    public static void main(String [] args) {
//        int a = 2;
//        boolean res = false;
//        res = a++ == 2 || --a == 2 && --a == 2;
//        System.out.println(a);
//        System.out.println(res);
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<String> list = new ArrayList<>();
//         list.add(0, "Array");
//         list.add(0, "List");
// 
//         System.out.println(list);
//     }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String [] args) {
//         List<Integer> list = new ArrayList<Integer>();
// 
//         list.add(27);
//         list.add(27);
// 
//         list.add(new Integer(27));
//         list.add(new Integer(27));
// 
//         System.out.println(list.get(0) == list.get(1));
//         System.out.println(list.get(2) == list.get(3));
//         System.out.println(list);
//     }
//}

//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.function.Predicate;
// 
//class Employee {
//     private String name;
//     private int age;
//     private double salary;
// 
//     public Employee(String name, int age, double salary) {
//         this.name = name;
//         this.age = age;
//         this.salary = salary;
//     }
// 
//    public String getName() {
//         return name;
//     }
// 
//    public int getAge() {
//         return age;
//     }
// 
//    public double getSalary() {
//         return salary;
//     }
// 
//     public String toString() {
//         return name;
//     }
//}
// 
//public class Test {
//     public static void main(String [] args) {
//         List<Employee> list = new ArrayList<>();
//         list.add(new Employee("James", 25, 15000));
//         list.add(new Employee("Lucy", 23, 12000));
//         list.add(new Employee("Bill", 27, 10000));
//         list.add(new Employee("Jack", 19, 5000));
//         list.add(new Employee("Liya", 20, 8000));
// 
//         process(list, e -> e.getAge() > 20);
//     }
// 
//     private static void process(List<Employee> list, Predicate<Employee> predicate) {
//         Iterator<Employee> iterator = list.iterator();
//         while(iterator.hasNext()) {
//             Employee e = iterator.next();
//             if(predicate.test(e))
//                 System.out.print(e + " ");
//         }
//     }
//}

//import java.util.function.Predicate;
//
//public class Test {
//     public static void main(String[] args) {
//         String [] arr = {"A", "ab", "bab", "Aa", "bb", "baba", "aba", "Abab"};
// 
//         processStringArray(arr, p->!false);
//     }
// 
//     private static void processStringArray(String [] arr, 
//                                                Predicate<String> predicate) {
//         for(String str : arr) {
//             if(predicate.test(str)) {
//                 System.out.println(str);
//             }
//         }
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        String s1 = "OcA";
//        String s2 = "oCa";
//        System.out.println(s1.equals(s2));
//    }
//}

//class Point {
//    static int x;
//    int y, z;
//    
//    public String toString() {
//        return "Point(" + x + ", " + y + ", " + z + ")";
//    }
//}
// 
//public class Test {
//    public static void main(String[] args) {
//        Point p1 = new Point();
//        p1.x = 17;
//        p1.y = 35;
//        p1.z = -1;
//        
//        Point p2 = new Point();
//        p2.x = 19;
//        p2.y = 40;
//        p2.z = 0;
//        
//        System.out.println(p1); //Line n1
//        System.out.println(p2); //Line n2
//    }
//}

//public class Test {
//    private static boolean flag = !true;
//
//    public static void main(String [] args) {
//        System.out.println(!flag ? args[0] : args[1]);
//    }
//}

//import java.time.LocalDate;
//
//public class Test {
//     public static void main(String [] args) {
//         LocalDate date = LocalDate.parse("1980-03-16");
//         System.out.println(date.minusYears(-5));
//     }
//}

//class Vehicle {
//    public int getRegistrationNumber() {
//        return 1;
//    }
//}
//
//class Car {
//    public int getRegistrationNumber() {
//        return 2;
//    }
//}
//
//public class Test {
//    public static void main(String[] args) {
//        Vehicle obj = new Car();
//        System.out.println(obj.getRegistrationNumber());
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        final int score = 60;
//        switch (score) {
//            default:
//                System.out.println("Not a valid score");
//            case score:
//                System.out.println("Failed");
//                break;
//            case score+1:
//                System.out.println("Passed");
//                break;
//        }
//    }
//}

//public class Test {
//	 
//    private static void add(double d1, double d2) {
//        System.out.println("double version: " + (d1 + d2));
//    }
//
//    private static void add(Double d1, Double d2) {
//       System.out.println("Double version: " + (d1 + d2));
//    }
//
//    public static void main(String[] args) {
//        add(10.0, null);
//    }
//
//}

//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<LocalDate> dates = new ArrayList<>();
//         dates.add(LocalDate.parse("2018-07-11"));
//         dates.add(LocalDate.parse("1919-10-25"));
//         dates.add(LocalDate.of(2020, 4, 8));
//         dates.add(LocalDate.of(1980, Month.DECEMBER, 31));
// 
//         dates.removeIf(x -> x.getYear() < 2000);
// 
//         System.out.println(dates);
//     }
//}

//public class Test {
//    public static void main(String[] args) {
////      Integer var = 10;
////      double var = 10;
////    	long var = 10;
////    	char var = 10;
////    	short var = 10;
//    	byte var = 10;
//        switch(var) {
//            case 10:
//                System.out.println("TEN");
//                break;
//            default:
//                System.out.println("DEFAULT");
//        }
//    }
//}

//import java.time.LocalDate;
//
//public class Test {
//     public static void main(String [] args) {
//         LocalDate date = LocalDate.parse("2018-1-01");
//         System.out.println(date);
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        String str1 = " ";
//        boolean b1 = str1.isEmpty();
//        System.out.println(b1);
//        str1.trim();
//        b1 = str1.isEmpty();
//        System.out.println(b1);
//        System.out.println("#"+str1+"#");
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        char c = 'Z';
//        long l = 100_00l;
//        int i = 9_2;
//        float f = 2.02f; 
//        double d = 10_0.35d;
//        l = c + i;
//        f = c * l * i * f;
//        f = l + i + c;
//        i = (int)d;
//        f = (long)d;
//    }
//}

//import java.time.LocalDate;
//import java.time.Period;
// 
//public class Test {
//     public static void main(String [] args) {
//         LocalDate date = LocalDate.parse("2000-01-01");
//         Period period = Period.ofYears(-3000);
//         System.out.println(period);
//         System.out.println(date.plus(period));
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        String[][] arr = { { "7", "6", "5" }, { "4", "3" }, { "2", "1" } };
//        for (int i = 0; i < arr.length; i++) { //Line n1
//            for (int j = 0; j < arr[i].length; j++) { //Line n2
//                switch (arr[i][j]) { //Line n3
//                    case "2":
//                    case "4":
//                    case "6":
//                        break; //Line n4
//                    default: 
//                        continue; //Line n5
//                }
//                System.out.print(arr[i][j]); //Line n6
//            }
//        }
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<String> list = new ArrayList<>();
//         list.add("X");
//         list.add("Y");
//         list.add("X");
//         list.add("Y");
//         list.add("Z");
//         list.remove(new String("Y"));
//         System.out.println(list);
//     }
//}

//import java.time.LocalDate;
//
//public class Test {
//     public static void main(String [] args) {
//         LocalDate d1 = LocalDate.parse("1999-09-09");
//         LocalDate d2 = LocalDate.parse("1999-09-09");
//         LocalDate d3 = LocalDate.of(1999, 9, 9);
//         LocalDate d4 = LocalDate.of(1999, 9, 9);
//         System.out.println((d1 == d2) + ":" + (d2 == d3) + ":" + (d3 == d4));
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        Boolean b1 = new Boolean("tRuE");
//        Boolean b2 = new Boolean("fAlSe");
//        Boolean b3 = new Boolean("abc");
//        Boolean b4 = null;
//        System.out.println(b1 + ":" + b2 + ":" + b3 + ":" + b4);
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder("Hurrah! I Passed...");
//        System.out.println(sb.length());
//        sb.delete(0, 100);
//        System.out.println(sb.length());
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<String> list = new ArrayList<>();
//         list.add("ONE");
//         list.add("TWO");
//         list.add("THREE");
//         list.add("THREE");
// 
////         if(list.remove(2)) {
////             list.remove("THREE");
////         }
// 
//         System.out.println(list);
//         
//         List<Integer> list1 = new ArrayList<>();
//         
//         list1.add(1);
//         list1.add(2);
//         System.out.println(list1.remove((Object)1));
//         System.out.println(list1);
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Iterator;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<String> dryFruits = new ArrayList<>();
//         dryFruits.add("Walnut");
//         dryFruits.add("Apricot");
//         dryFruits.add("Almond");
//         dryFruits.add("Date");
// 
//         ListIterator<String> iterator = dryFruits.listIterator();
//         while(iterator.hasNext()) {
//             if(iterator.next().startsWith("A")) {
//                 iterator.remove();
//              }
//         }
//         
////         Iterator iterator = dryFruits.iterator();
////         while(iterator.hasNext()) {
//////        	 System.out.println(((String)iterator.next()).startsWith("A"));
////        	 if(((String)iterator.next()).startsWith("A")) {
////        		 iterator.remove();
////        	 }
////         }
// 
//         System.out.println(dryFruits);
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        System.out.println("Output is: " + (10 != 5));
//    }
//}

//import java.io.FileNotFoundException;
//import java.io.IOException;
// 
//abstract class Super {
//     public abstract void m1() throws IOException;
//}
// 
//class Sub extends Super {
//     @Override
//     public void m1() throws IOException {
//         throw new FileNotFoundException();
//     }
//}
// 
//public class Test {
//     public static void main(String[] args) {
//         Super s = new Sub();
//         try {
//             s.m1();
//         } catch (IOException e) {
//             System.out.print("A");
//         } catch(FileNotFoundException e) {
//             System.out.print("B");
//         } finally {
//             System.out.print("C");
//         }
//     }
//}

//public class Test {
//    public static void main(String[] args) {
//        System.out.println(args.length);
//    }
//}


//class Parent {
//    int i = 10;
//    Parent(int i) {
//        super();
//        this.i = i;
//    }
//}
//
//class Child extends Parent {
//    int j = 20;
//
//    Child(int j) {
//        super(0);
//        this.j = j;
//    }
//
//    Child(int i, int j) {
//        super(i);
////        this(j);
//    }
//
//}
//
//public class Test {
//    public static void main(String[] args) {
//        Child child = new Child(1000, 2000);
//        System.out.println(child.i + ":" + child.j);
//    }
//}

//import java.time.LocalDate;
//
//public class Test {
//     public static void main(String [] args) {
//         LocalDate newYear = LocalDate.of(2018, 1, 1);
//         LocalDate eventDate = LocalDate.of(2018, 1, 1);
//         boolean flag1 = newYear.isAfter(eventDate);
//         boolean flag2 = newYear.isBefore(eventDate);
//         System.out.println(flag1 + ":" + flag2);
//     }
//}


//import java.util.function.Predicate;
//
//public class Test {
//     public static void main(String[] args) {
//         String [] arr = {"*", "**", "***", "****", "*****"};
//         Predicate pr1 = s -> s.length() < 4;
//         print(arr, pr1);
//     }
// 
//     private static void print(String [] arr, Predicate<String> predicate) {
//         for(String str : arr) {
//             if(predicate.test(str)) {
//                 System.out.println(str);
//             }
//         }
//     }
//}

//import java.time.LocalDate;
//import java.time.Period;
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
// 
//public class Test {
//     public static void main(String [] args) {
//         LocalDate date = LocalDate.of(2012, 1, 11);
//         LocalDateTime dateTime = LocalDateTime.now();
//         Period period = Period.ofMonths(2);
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
//         System.out.print(formatter.format(date.minus(period)));
//     }
//}


//abstract class Helper {
//    int num = 100;
//    String operation = null;
//    
//    protected abstract void help();
//    
//    void log() {
//        System.out.println("Helper-log");
//    }
//}
// 
//public class Test extends Helper {
//    private int num = 200;
//    protected String operation = "LOGGING";
//    
//    public void help() {
//        System.out.println("LogHelper-help");
//    }
//    
//    void log() {
//        System.out.println("LogHelper-log");
//    }
//    
//    public static void main(String [] args) {
//        new Test().help();
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        int x = 5;
//        while (x < 10) 
//            System.out.println(x);
//            x++;
//    }
//}

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<StringBuilder> days = new ArrayList<>();
//         days.add(new StringBuilder("Sunday"));
//         days.add(new StringBuilder("Monday"));
//         days.add(new StringBuilder("Tuesday"));
// 
//         if(days.contains(new StringBuilder("Sunday"))) {
//             days.add(new StringBuilder("Wednesday"));
//         }
//         
//         String str1 = "asd";
//         String str2 = "asd";
//         StringBuilder sbd1 = new StringBuilder("Sunday");
//         StringBuilder sbd2 = new StringBuilder("Sunday");
//         StringBuffer sbuf1 = new StringBuffer("Sunday");
//         StringBuffer sbuf2 = new StringBuffer("Sunday");
//         Integer i1 = new Integer(1);
//         Integer i2 = new Integer(1);
//         
//         System.out.println(Objects.equals(str1, str2));
//         System.out.println(Objects.equals(new String("Sunday"), new String("Sunday")));
//         System.out.println(Objects.equals(sbd1,sbd2));
//         System.out.println(Objects.equals(sbuf1, sbuf2));
//         System.out.println(Objects.equals(i1, i2));
//         System.out.println(sbd1.capacity()+" "+sbd2.capacity());
//         System.out.println(sbd1.equals(sbd2));
//         System.out.println(days.size());
//     }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//class Student {
//     private String name;
//     private int age;
// 
//     Student(String name, int age) {
//         this.name = name;
//         this.age = age;
//     }
// 
//     public String toString() {
//         return "Student[" + name + ", " + age + "]";
//     }
// 
//     public boolean equals(Object obj) {
//         if(obj instanceof Student) {
//             Student stud = (Student)obj;
//             if(this.name.equals(stud.name) && this.age == stud.age) {
//                 return true;
//             }
//         }
//         return false;
//     }
//}
// 
//public class Test {
//     public static void main(String[] args) {
//         List<Student> students = new ArrayList<>();
//         students.add(new Student("James", 25));
//         students.add(new Student("James", 27));
//         students.add(new Student("James", 25));
//         students.add(new Student("James", 25));
// 
//         System.out.println(students.remove(new Student("James", 25)));
// 
//         for(Student stud : students) {
//             System.out.println(stud);
//         }
//    }
//}

//public class Test {
//    public static void main(String[] args) {
//        String [] arr = new String[7];
//        System.out.println(arr);
//    }
//}

//import java.util.ArrayList;
//
//public class Test {
//     public static void main(String[] args) {
//         ArrayList<Integer> original = new ArrayList<>();
//         original.add(new Integer(10));
// 
//         ArrayList<Integer> cloned = (ArrayList<Integer>) original.clone();
//         Integer i1 = cloned.get(0);
//         System.out.println(i1);
//         System.out.println(++i1);
// 
//         System.out.println(cloned.get(0));
//         
//         Integer i2 = new Integer(5);
//         Integer i3 = i2;
//         System.out.println(i2+ " "+ i3);
//         System.out.println(i2==i3);
//         i2++;
//         System.out.println(i2+ " "+ i3);
//         System.out.println(i2==i3);
//         i3++;
//         System.out.println(i2+ " "+ i3);
//         System.out.println(i2==i3);
//     }
//}

//import java.util.ArrayList;
//import java.util.List;
// 
//public class Test {
//     public static void main(String[] args) {
//         List<Integer> list = new ArrayList<>();
//         list.add(100);
//         list.add(200);
//         list.add(100);
//         list.add(200);
//         list.remove(new Integer(100));
// 
//         System.out.println(list);
//     }
//}

//public class Test {
//	
//	private static void add(Integer i1, Integer i2) {
//        System.out.println("Integer version: " + (i1 + i2));
//    }
//	
//    private static void add(float f1, float f2) {
//        System.out.println("float version: " + (f1 + f2));
//    }
//	 
////    private static void add(double d1, double d2) {
////        System.out.println("double version: " + (d1 + d2));
////    }
////
//    private static void add(Double d1, Double d2) {
//        System.out.println("Double version: " + (d1 + d2));
//    }
//
//    public static void main(String[] args) {
//        add((Integer)10, new Integer(10));                                                                                                                                                                           
//    }
//
//}

//public class Test {
//	
//	public static void main(String[] args) {
//		
//		Boolean b1 = new Boolean(null);
//		Boolean b2 = new Boolean(false);
//		
//		boolean b3 = Boolean.parseBoolean("faLse");
//		
//		Boolean b4 = Boolean.valueOf("False");
//		Boolean b5 = Boolean.valueOf("False");
//		
//		System.out.println("b1:"+b1+",b2:"+b2+",b3:"+b3+",b4:"+b4);
//		
//		System.out.println(b1==b2);//false
//		System.out.println(b1==b3);//true
//		System.out.println(b1==b4);//false
//		System.out.println(b3==b4);//true
//		System.out.println(b1==Boolean.FALSE);
//		System.out.println(b3==Boolean.FALSE);
//		System.out.println(b4==Boolean.TRUE);
//		System.out.println(b3 == b5);
//		
//	}
//}

//public class Test {
//	
//	public static void main(String[] args) {
//		
//		Integer i1 = new Integer("1");
//		Integer i2 = new Integer(1);
//		
//		Integer i3 = Integer.parseInt("1"); 
//		int i4 = Integer.valueOf(1);
//		
//		int i5 = 1;
//		Integer i6 = Integer.valueOf(1);
//		
//		Integer i7 = Integer.valueOf(1);
//		
//		
//		System.out.println("i1:"+i1+",i2:"+i2+",i3:"+i3+",i4:"+i4);
//		
//		System.out.println(i1==i2);
//		System.out.println(i1==i3);
//		System.out.println(i1==i4);
//		System.out.println(i3==i4);
//		System.out.println(i1==i5);
//		System.out.println(i3==i7);
//		
//	}
//}

//public class Test {
//	
//	public static void main(String[] args) {
//		
//		String str1 = new String("A");
//		String str2 = new String("A");
//		
//		String str3 = "A";
//		String str4 = String.valueOf("A");
//		String str5 = String.valueOf("A");
//		
//		
//		System.out.println("str1:"+str1+",str2:"+str2+",str3:"+str3+",str4:"+str4);
//		
//		System.out.println(str1==str2);
//		System.out.println(str1==str3);
//		System.out.println(str1==str4);
//		System.out.println(str3==str4);
//		System.out.println(str4 == str5);
//		
//	}
//}

//interface Vehicle {
//	int noOfWheels = 4;
//	default void displayMaxSpeed() {
//		System.out.println("Inside Vehicle displayMaxSpeed()");
//	}
//	void run();
//}
//abstract class Car implements Vehicle{
//	String name;
//	abstract public void run();
//	abstract public void displayMaxSpeed();
//	
//	public Car(String name) {
//		this.name = name;
//	}
//}
//class MarutiSuzuki extends Car {
//	public MarutiSuzuki(String name) {
//		super(name);
//	}
//	public void run() {
//		System.out.println(name+" is running");
//	}
//	public void displayMaxSpeed() {
//		System.out.println("Inside MarutiSuzuki displayMaxSpeed()");
//	}
//}
//public class Test {
//	public static void main(String[] args) {
//		MarutiSuzuki ms = new MarutiSuzuki("Alto");
//		ms.run();
//		
//		ms.displayMaxSpeed();
//		
//		Vehicle v = new MarutiSuzuki("Swift");
//		
//		v.displayMaxSpeed();
////		v.run();
//		
//		System.out.println("------------------------------------");
//		
//		
//		Class<?> clss = Car.class;
//		
//		Field[] fields = clss.getDeclaredFields();
//		for(Field field: fields) {
//			System.out.println(field.getName()+" "+field.getType()+" "+field.getModifiers());
//		}
//		
//		Method[] methods = clss.getDeclaredMethods();
//		for(Method method: methods) {
//			System.out.println(method.getName()+" "+method.getReturnType()+" "+Modifier.toString(method.getModifiers()));
//		}
//		
//		System.out.println("------------------------------------");
//		
//		Class<?> clss1 = Vehicle.class;
//		Field[] fields1 = clss1.getDeclaredFields();
//		for(Field field: fields1) {
//			System.out.println(field.getName()+" "+field.getType()+" "+Modifier.toString(field.getModifiers()));
//		}
//		
//		Method[] methods1 = clss1.getDeclaredMethods();
//		for(Method method: methods1) {
//			System.out.println(method.getName()+" "+method.getReturnType()+" "+Modifier.toString(method.getModifiers()));
//		}
//		
//		System.out.println("------------------------------------");
//		
//		Class<?> clss2 = MarutiSuzuki.class;
//		Field[] fields2 = clss2.getDeclaredFields();
//		for(Field field: fields2) {
//			System.out.println(field.getName()+" "+field.getType()+" "+Modifier.toString(field.getModifiers()));
//		}
//		
//		Method[] methods2 = clss2.getDeclaredMethods();
//		for(Method method: methods2) {
//			System.out.println(method.getName()+" "+method.getReturnType()+" "+Modifier.toString(method.getModifiers()));
//		}
//		
//		System.out.println("------------------------------------");
//	}
//}

//class Baap {
//	public int h = 4, x=9;
//	public int getH() {
//		System.out.println("Baap "+h);
//		return h;
//	}
//	public void greet() {
//		System.out.println("Inside Baap greet() Hello!");
//	}
//}
//class Beta extends Baap {
//	public int h = 44, y=7;
//	public int getH() {
//		System.out.println("Beta "+h);
//		return h;
//	}
//	public void greet() {
//		System.out.println("Inside Beta greet() Hello!");
//	}
//}
//class A {
//	public int z = 5;
//	public void greet() {
//		System.out.println("Inside A greet() Hello!");
//	}
//}
//public class Test {
//	public static void main(String[] args) {
//		Baap b = new Beta();
//		System.out.println(b.h+" "+b.getH());
//		Beta bb = (Beta)b;
//		System.out.println(bb.h+" "+bb.getH());
//		Baap b1 = new Baap();
//		b.greet();
//		b1.greet();
////		((Beta)b1).greet();
//		
////		System.out.println(b.y);
////		System.out.println(((Beta)b1).y);
////		System.out.println(((A)b1).z);
//		
//		System.out.println(b1.getH()+" "+bb.getH());
//		
//		Beta b2 = new Beta();
////		System.out.println(((Baap)b2).y);
//
//	}
//}

//public class Test {
//	
//	public void display2DArray(int array[][]) {
//		for(int i=0;i<array.length;i++) {
//			for(int j=0;j<array[i].length;j++) {
//				System.out.print(array[i][j]+" ");
//			}
//			System.out.println("");
//		}
//	}
//	public static void main(String[] args) {
//		int[] array1, array2[];
//		int[][] array3 = new int[] [] {new int[] {7,8,9}, new int[] {10,11}, new int[] {12}};
//		int[] array4[] = {new int[] {13,14,15}, new int[] {16,17}, new int[] {18}}, 
//			  array5[] = {{19,20,21}, {22,23}, {24}};
//		
//		array1 = new int[] {};
//		
//		array2 = new int[][] {new int[] {1,2,3}, new int[] {4,5}, new int[] {6}};
//		
////		array3 = {new int[] {7,8,9}, new int[] {10,11}, new int[] {12}};
//		
////		array3 = {{7,8,9}, {10,11}, {12}};
//		array3[0]= new int[] {1};
//		
////		array3 = new int[][] {new int[] {17,8,9}, new int[] {10,11}, new int[] {12}};
//		
//		System.out.println(array1.length);
//		array2 = array3;
//		array2 = array4;
////		array1 = array2;
////		array4 = array1;
//		array5 = array3;
//		
//		Test t = new Test();
//		t.display2DArray(array2);
//		
//		System.out.println("----------------------");
//		
//		t.display2DArray(array3);
//		
//		System.out.println("----------------------");
//		
//		t.display2DArray(array4);
//		
//		System.out.println("----------------------");
//		
//		t.display2DArray(array5);
//	}
//}

//public class Test {
//	void crazyLoop1() {
//		int c = 0;
//		JACK: while(c < 8) {
//			JILL: System.out.println(c);
//			if(c > 3) break JACK; else c++;
//		}
//	}
//	void crazyLoop2() {
//		int c = 0;
//		X:  for(int i=0; i<10; i++) {
//				for(int j=0; j<i; j++) {
//					System.out.println("i="+i+", j="+j);
//					if(i<5) {
//						continue X;
//					}
//					else {
//						break X;
//					}
//				}
//				System.out.println("@"
//						+ "");
//			}
//	}
//	public static void main(String[] args) {
//		Test t = new Test();
//		t.crazyLoop1();
//		t.crazyLoop2();
//	}
//}

class A {}
class B extends A {}

class Base
{
	A fun()
	{
		System.out.println("Base fun()");
		return new A();
	}
}

class Derived extends Base
{
	B fun()
	{
		System.out.println("Derived fun()");
		return new B();
	}
}

public class Test
{
	public static void main(String args[])
	{
	Base base = new Derived();
	base.fun();

	Derived derived = new Derived();
	derived.fun();
	}
}