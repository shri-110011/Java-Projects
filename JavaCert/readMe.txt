class Message {
    static void main(String [] args) {
        System.out.println("Welcome " + args[2] + "!");
    }
}
 
public class Guest {
    public static void main(String [] args) {
        Message.main(args);
    }
}

--------------------------

package com.udayan.oca;
 
import java.util.ArrayList;
import java.util.List;
 
public class Test {
     public static void main(String[] args) {
         List<Character> list = new ArrayList<>();
         list.add(0, 'V');
         list.add('T');
         list.add(1, 'E');
         list.add(3, 'O');
 
         if(list.contains('O')) {
             list.remove('O');
         }
 
         for(char ch : list) {
             System.out.print(ch);
         }
     }
}

---------------------------------

package com.udayan.oca;
 
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
 
public class Test {
     public static void main(String[] args) {
         List<LocalDate> dates = new ArrayList<>();
         dates.add(LocalDate.parse("2018-07-11"));
         dates.add(LocalDate.parse("1919-02-25"));
         dates.add(LocalDate.of(2020, 4, 8));
         dates.add(LocalDate.of(1980, Month.DECEMBER, 31));
 
         dates.removeIf(x -> x.getYear() < 2000);
 
         System.out.println(dates);
     }
}

-------------------------------------

A bank's swift code is generally of 11 characters and used in international money transfers. 
An example of swift code: ICICINBBRT4
ICIC: First 4 letters for bank code
IN: Next 2 letters for Country code
BB: Next 2 letters for Location code
RT4: Next 3 letters for Branch code

Which of the following code correctly extracts country code from the swift code referred by String reference variable swiftCode?

---------------------------------------

What will be the result of compiling and executing Test class?
package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         try {
             main(args);
         } catch (Exception ex) {
             System.out.println("CATCH-");
         }
             System.out.println("OUT");
     }
}

---------------------------------------
How many objects of Pen class are eligible for Garbage Collection at Line 4?
package com.udayan.oca;
 
class Pen {
     
}
 
public class TestPen {
     public static void main(String[] args) {
         new Pen(); //Line 1
         Pen p = new Pen(); // Line 2
         change(p); //Line 3
         System.out.println("About to end."); //Line 4
     }
 
     public static void change(Pen pen) { //Line 5
         pen = new Pen(); //Line 6
     }
}

--------------------------------------

package com.udayan.oca;
 
import java.io.FileNotFoundException;
import java.io.IOException;
 
abstract class Super {
     public abstract void m1() throws IOException;
}
 
class Sub extends Super {
     @Override
     public void m1() throws IOException {
         throw new FileNotFoundException();
     }
}
 
public class Test {
     public static void main(String[] args) {
         Super s = new Sub();
         try {
             s.m1();
         } catch (FileNotFoundException e) {
             System.out.print("M");
         } finally {
             System.out.print("N");
         }
     }
}
------------------------------------------

package com.udayan.oca;
 
import java.util.ArrayList;
import java.util.List;
 
public class Test {
     public static void main(String [] args) {
         List<Integer> list = new ArrayList<Integer>();
         list.add(new Integer(2));
         list.add(new Integer(1));
         list.add(new Integer(0));
 
         list.remove(list.indexOf(0));
 
         System.out.println(list);
     }
}

----------------------------------------

package com.udayan.oca;
 
class Point {
     int x;
     int y;
     void assign(int x, int y) {
         x = this.x;
         this.y = y;
     }
 
     public String toString() {
         return "Point(" + x + ", " + y + ")";
     }
}
 
public class Test {
     public static void main(String[] args) {
         Point p1 = new Point();
         p1.x = 10;
         p1.y = 20;
         Point p2 = new Point();
         p2.assign(p1.x, p1.y);
         System.out.println(p1.toString() + ";" + p2.toString());
     }
}

-------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         int [] arr = {2, 1, 0};
         for(int i : arr) {
             System.out.println(arr[i]);
         }
     }
}
------------------------------------------

package com.udayan.oca;
 
import java.util.ArrayList;
import java.util.List;
 
public class Test {
    public static void main(String[] args) {
        String[] names = { "Smith", "Brown", "Thomas", "Taylor", "Jones" };
        List<String> list = new ArrayList<>();
        for (int x = 0; x < names.length; x++) {
            list.add(names[x]);
            switch (x) {
                case 2:
                    continue;
            }
            break;
        }
        System.out.println(list.size());
    }
}

---------------------------------------

package com.udayan.oca;
 
public class Test {
    public static void main(String[] args) {
        /*INSERT*/
        arr[1] = 5;
        arr[2] = 10;
        System.out.println("[" + arr[1] + ", " + arr[2] + "]"); //Line n1
    }
}

And below statements:

1. short arr [] = new short[2];

2. byte [] arr = new byte[10];

3. short [] arr; arr = new short[3];

4. short [2] arr;

5. short [3] arr;

6. int [] arr = new int[]{100, 100};

7. int [] arr = new int[]{0, 0, 0, 0};

8. short [] arr = {};

9. short [] arr = new short[2]{5, 10};



How many above statements can be used to replace /*INSERT*/, such that on execution, code will print [5, 10] on to the console?

----------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         Double [] arr = new Double[2];
         System.out.println(arr[0] + arr[1]);
     }
}


------------------------------------------------

//A.java
package com.udayan.oca;
 
public class A {
     public int i1 = 1;
     protected int i2 = 2;
}


//B.java
package com.udayan.oca.test;
 
import com.udayan.oca.A;
 
public class B extends A {
     public void print() {
         A obj = new A();
         System.out.println(obj.i1); //Line 8
         System.out.println(obj.i2); //Line 9
         System.out.println(this.i2); //Line 10
         System.out.println(super.i2); //Line 11
     }
 
     public static void main(String [] args) {
         new B().print();
     }
}
----------------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         String fruit = "mango";
         switch (fruit) {
             default:
                 System.out.println("ANY FRUIT WILL DO");
             case "Apple":
                 System.out.println("APPLE");
             case "Mango":
                 System.out.println("MANGO");
             case "Banana":
                 System.out.println("BANANA");
                 break;
         }
     }
}

------------------------------------------------------

package com.udayan.oca;
 
import java.util.ArrayList;
import java.util.List;
 
public class Test {
     public static void main(String[] args) {
         List<Integer> list = new ArrayList<>();
         list.add(100);
         list.add(200);
         list.add(100);
         list.add(200);
         list.remove(100);
 
         System.out.println(list);
     }
}
-----------------------------------------------------

package com.udayan.oca;
 
import java.time.LocalDate;
 
class MyLocalDate extends LocalDate {
     @Override
     public String toString() {
         return super.getDayOfMonth() + "-" + super.getMonthValue() + 
            "-" +  super.getYear();
     }
}
 
public class Test {
     public static void main(String [] args) {
         MyLocalDate date = LocalDate.parse("1980-03-16");
         System.out.println(date);
     }
}

-----------------------------------------------------

package com.udayan.oca;
 
public class Test {
    char var1;
    double var2;
    float var3;
	
    public static void main(String[] args) {
        Test obj = new Test();
        System.out.println(">" + obj.var1);
        System.out.println(">" + obj.var2);
        System.out.println(">" + obj.var3);
    }
}

-------------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         m1(); //Line 3
     }
 
     private static void m1() throws Exception { //Line 6
         System.out.println("NOT THROWING ANY EXCEPTION"); //Line 7
     }
}

-------------------------------------------------

package com.udayan.oca;
 
import java.time.LocalDate;
import java.time.LocalTime;
 
public class Test {
     public static void main(String [] args) {
         LocalDate date = LocalDate.parse("1947-08-14");
         LocalTime time = LocalTime.MAX;
         System.out.println(date.atTime(time));
     }
}

--------------------------------------------------

package com.udayan.oca;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class Test {
     public static void main(String[] args) {
         List<String> dryFruits = new ArrayList<>();
         dryFruits.add("Walnut");
         dryFruits.add("Apricot");
         dryFruits.add("Almond");
         dryFruits.add("Date");
 
         Iterator<String> iterator = dryFruits.iterator();
         while(iterator.hasNext()) {
             String dryFruit = iterator.next();
             if(dryFruit.startsWith("A")) {
                 dryFruits.remove(dryFruit);
             }
         }
        
         System.out.println(dryFruits);
    }
}

----------------------------------------------------

package com.udayan.oca.test;
 
abstract class Animal {
     private String name;
 
     Animal(String name) {
        this.name = name;
     }
 
     public String getName() {
         return name;
     }
}
 
class Dog extends Animal {
     private String breed;
     Dog(String breed) {
         this.breed = breed;
     }
 
     Dog(String name, String breed) {
         super(name);
         this.breed = breed;
     }
 
     public String getBreed() {
         return breed;
     }
}
 
public class Test {
     public static void main(String[] args) {
         Dog dog1 = new Dog("Beagle");
         Dog dog2 = new Dog("Bubbly", "Poodle");
         System.out.println(dog1.getName() + ":" + dog1.getBreed() + ":" + 
                             dog2.getName() + ":" + dog2.getBreed());
     }
}
------------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         /*INSERT*/
         switch(var) {
             case 7:
                 System.out.println("Lucky no. 7");
                 break;
             default:
                 System.out.println("DEFAULT");
         }
     }
}

Character var = 7;
Integer var = 7;
char var = '7';
char var = 7;
Character var = '7';
------------------------------------------------


package com.udayan.oca;
 
class PenDrive {
    int capacity;
    PenDrive(int capacity) {
        this.capacity = capacity;
    }
}
 
class OTG extends PenDrive {
    String type;
    String make; 
    OTG(int capacity, String type) {
        /*INSERT-1*/
    }
    OTG(String make) {
        /*INSERT-2*/
        this.make = make;
    }
}
 
public class Test {
    public static void main(String[] args) {
        OTG obj = new OTG(128, "TYPE-C");
        System.out.println(obj.capacity + ":" + obj.type);
    }
}

Replace /*Insert-1*/
super(capacity);
this.type = type;
Replace /*Insert-2*/
super(0)

Replace /*Insert-1*/
super.capacity = capacity;
this.type = type;
Replace /*Insert-2*/
super(128)

Replace /*Insert-1*/
super(capacity);
this.type = type;
Replace /*Insert-2*/
super(128)

Replace /*Insert-1*/
super(capacity);
Replace /*Insert-2*/
super(128)



------------------------------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         int grade = 60;
         if(grade = 60)
             System.out.println("You passed...");
         else
             System.out.println("You failed...");
     }
}
-------------------------------------------------------------

package com.udayan.oca;
 
public class Test {
     public static void main(String[] args) {
         double [] arr = new int[2]; //Line 3
         System.out.println(arr[0]); //Line 4
     }
}
-------------------------------------------------------


