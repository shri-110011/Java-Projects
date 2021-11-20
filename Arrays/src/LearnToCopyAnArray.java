class CopyArray {
	public void method1() {
		int a[] = {1, 8, 3}; 
		  
        // Create an array b[] of same size as a[] 
        int b[] = new int[a.length]; 
  
        // Doesn't copy elements of a[] to b[], only makes 
        // b refer to same location 
        b = a;     
  
        // Change to b[] will also reflect in a[] as 'a' and  
        // 'b' refer to same location. 
        b[0]++; 
        System.out.println("Inside method1(): ");
        System.out.println("Contents of a[] "); 
        for (int i=0; i<a.length; i++) 
            System.out.print(a[i] + " "); 
        System.out.print("\n"); 
  
        System.out.println("Contents of b[] "); 
        for (int i=0; i<b.length; i++) 
            System.out.print(b[i] + " "); 
        System.out.print("\n"); 
	}
	public void method2() {
		int a[] = {1, 8, 3}; 
		  
        // Create an array b[] of same size as a[] 
        int b[] = new int[a.length]; 
  
        // Copy elements of a[] to b[] 
        for (int i=0; i<a.length; i++) 
            b[i] = a[i]; 
  
        // Change b[] to verify that b[] is different 
        // from a[] 
        b[0]++; 
  
        System.out.println("Inside method2(): ");
        System.out.println("Contents of a[] "); 
        for (int i=0; i<a.length; i++) 
            System.out.print(a[i] + " "); 
        System.out.print("\n"); 
  
        System.out.println("Contents of b[] "); 
        for (int i=0; i<b.length; i++) 
            System.out.print(b[i] + " "); 
        System.out.print("\n"); 
	}
	public void method3() {
		int a[] = {1, 8, 3}; 
		
		System.out.println("Inside method3(): ");
	  
        // Copy elements of a[] to b[] 
//        int b[] = a.clone(); 
		int b[];
		//This below statement won't work because b[] is uninitialized.
//		System.out.println("b[0] "+b[0]); 
		b = a.clone();
  
        // Change b[] to verify that b[] is different 
        // from a[] 
        b[0]++; 
  
        System.out.println("Contents of a[] "); 
        for (int i=0; i<a.length; i++) 
            System.out.print(a[i] + " "); 
        
        System.out.print("\n"); 
  
        System.out.println("Contents of b[] "); 
        for (int i=0; i<b.length; i++) 
            System.out.print(b[i] + " "); 
        
        System.out.print("\n"); 
	}
	
	public void method4() {
		int a[] = {1, 8, 3}; 
		  
        // Create an array b[] of same size as a[] 
        int b[] = new int[a.length]; 
        
        System.out.println("Inside method4(): ");
  
        // Copy elements of a[] to b[] 
        System.arraycopy(a, 1, b, 0, 2); 
  
        // Change b[] to verify that b[] is different 
        // from a[] 
        b[0]++; 
  
        System.out.println("Contents of a[] "); 
        for (int i=0; i<a.length; i++) 
            System.out.print(a[i] + " "); 
        
        System.out.print("\n"); 
  
        System.out.println("Contents of b[] "); 
        for (int i=0; i<b.length; i++) 
            System.out.print(b[i] + " "); 
        
        System.out.print("\n"); 
	}
}


public class LearnToCopyAnArray {
	public static void main(String[] args) {
		CopyArray obj = new CopyArray();
		obj.method1();
		obj.method2();
		obj.method3();
		obj.method4();
	}
}
