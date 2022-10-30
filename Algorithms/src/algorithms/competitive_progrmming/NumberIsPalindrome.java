package algorithms.competitive_progrmming;

import java.util.ArrayList;

public class NumberIsPalindrome {
	
	public boolean isPalindrome(int x) {
		if(x<0) return false;
		
		ArrayList<Integer> al = getDigits(x);
		int i=0, j=al.size()-1;
		boolean flag = false;
		
		while(i<j) {
			if(al.get(i) != al.get(j)) {
				flag = true;
				break;
			}
			i++;j--;
		}
		return !flag;
	}
	
	public ArrayList<Integer> getDigits(int x) {
		int q;
		ArrayList<Integer> al = new ArrayList<>();
		do{
			q=x/10;
			al.add(x%10);
			x=q;
		} while(q != 0);
		System.out.println(al);
		return al;
	}
	
	public static void main(String[] args) {
		NumberIsPalindrome obj = new NumberIsPalindrome();
		System.out.println(obj.isPalindrome(-121));

	}

}
