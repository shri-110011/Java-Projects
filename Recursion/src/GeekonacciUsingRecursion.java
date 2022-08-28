import java.util.Scanner;

/*
Geek created a random series and given a name geek-onacci series. Given four integers A, B, C, N. A, B, C represents the first three numbers of geek-onacci series. Find the Nth number of the series. The nth number of geek-onacci series is a sum of the last three numbers (summation of N-1th, N-2th, and N-3th geek-onacci numbers)

Input:
1. The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
2. The first line of each test case contains four space-separated integers A, B, C, and N.

Output: For each test case, print Nth geek-onacci number

Constraints:
1. 1 <= T <= 3
2. 1 <= A, B, C <= 100
3. 4 <= N <= 10

Example:
Input:
3
1 3 2 4
1 3 2 5
1 3 2 6

Output:
6
11
19 
*/
public class GeekonacciUsingRecursion {
	public static int calcNthGeekonacci(int a, int b, int c, int n) {
		if(n==1)
			return a;
		if(n==2)
			return b;
		if(n==3)
			return c;
		else {
			return calcNthGeekonacci(a,b,c,n-1) + calcNthGeekonacci(a,b,c,n-2) + calcNthGeekonacci(a,b,c,n-3);
		}
	}
	public static void main(String[] args) {
		int a,b,c,n;
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		while(T-- > 0) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			n = sc.nextInt();
			System.out.println(calcNthGeekonacci(a,b,c,n));
		}
		sc.close();
	}
}

//Code without recursion

/*
class GFG {
    
    public static ArrayList<Integer> findNthGeekonacciSeries(ArrayList<Integer> al1, int N, ArrayList<Integer> al2) {
        if(N<=3) {
            al2.add(al1.get(N-1));
        }
        else{
            int i = 3;
            while(i<N) {
                al1.add(al1.get(i-1)+al1.get(i-2)+al1.get(i-3));
                i++;
            }
            al2.add(al1.get(N-1));
        }
        return al2;
    } 
	public static void main (String[] args) {
		//code
		Scanner sc = new Scanner(System.in);
		int T;
        ArrayList<Integer> al1 = new ArrayList<Integer>();
	    ArrayList<Integer> al2 = new ArrayList<Integer>();
		T = sc.nextInt();
		while(T-- > 0) {
		    for(int i=0;i<3;i++) {
		        al1.add(sc.nextInt());
		    }
		    int N = sc.nextInt(); 
		    al2 = findNthGeekonacciSeries(al1, N, al2);
		  //  System.out.println(al1);
		  //  System.out.println(al2);
		    al1.clear();
		}
		for(int i: al2) {
		    System.out.println(i);
		}
	}
}
*/
