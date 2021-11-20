import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class LearnGrayCodeGeneration {
	
	public static void GrayCodeGeneratorUsingBitSet(int N) {
		int len = (int)Math.pow(2, N);
		for(int i=0; i<len; i++) {
			String bin_i = Integer.toBinaryString(i);
			BitSet b1 = new BitSet(N);
			BitSet b2 = new BitSet(N);
			int bin_iLen = bin_i.length(); 
//			System.out.println("bin_i:"+bin_i);
//			System.out.println("b1.length():"+b1.length()+", b2.length()"+b2.length());
			for(int j=0;j<bin_iLen;j++) {
				if(bin_i.charAt(j)=='1') {
					b1.set((N-bin_iLen+j));
				}
			}
//			System.out.println("b1:"+b1);
			for(int k=0;k<bin_iLen;k++) {
				if(k==0) {
					if(b1.get(N-bin_iLen+k)) {
						b2.set(N-bin_iLen+k);
					}
				}
				else {
					if(b1.get(N-bin_iLen+k) && !b1.get(N-bin_iLen+k-1) || !b1.get(N-bin_iLen+k) && b1.get(N-bin_iLen+k-1)) {
						b2.set(N-bin_iLen+k);
					}
				}
			}
			String str = new String("");
			String a[] = new String[N];
			Arrays.fill(a, "0");
			ArrayList<String> al1 = new ArrayList<String>(Arrays.asList(a));
			int idx = b2.nextSetBit(0);
//			System.out.println("idx:"+idx);
			while(idx>=0) {
				al1.set(idx, "1");
				idx = b2.nextSetBit(idx+1);
			}
//			System.out.println("b2:"+b2);
			System.out.println("al1:"+al1);
		}
	}
	
	public static void GrayCodeGenerator(int N) {
		int len = (int)Math.pow(2, N);
		for(int i=0; i < len; i++) {
			String bin_i = Integer.toBinaryString(i);
			String gray_i = "";
			for(int j=0;j<bin_i.length();j++) {
				if(j==0) {
					gray_i = gray_i + bin_i.charAt(0);
				}
				else {
					if(bin_i.charAt(j)=='0' && bin_i.charAt(j-1)=='1' || bin_i.charAt(j)=='1' && bin_i.charAt(j-1)=='0') {
						gray_i = gray_i+'1';
					}
					else {
						gray_i = gray_i+'0';
					}
				}
			}
			int extraZerosCount = N-gray_i.length();
			if(gray_i.length()<N) {
				for(int k=0;k<extraZerosCount;k++) {
					gray_i = '0'+gray_i;
				}
			}
			System.out.println(gray_i);
			ArrayList<String> al1 = new ArrayList<String>();
			al1.add(gray_i);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//GrayCodeGenerator(N);
		GrayCodeGeneratorUsingBitSet(N);
	}

}
