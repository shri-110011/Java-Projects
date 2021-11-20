package SubsequenceProblems;



public class LongestCommonSubsequence {

	public int findLCS(char ch1[], char ch2[], int i, int j) {
		if(i==ch1.length || j==ch2.length) {
			return 0;
		}
		else {
//			System.out.println("i:"+i+" ch1[i]:"+ch1[i]);
			if(ch1[i]==ch2[j]) {
				return 1+findLCS(ch1, ch2, i+1, j+1);
			}
			else {
				return max(findLCS(ch1, ch2, i+1, j),findLCS(ch1, ch2, i, j+1));
			}
		}
		
	}
	
	private int max(int i, int j) {
		if(i>j) {
			return i;
		}
		else {
			return j;
		}
	}
	
	public static void main(String[] args) {
		
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		
		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();
		
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(); 
		
		System.out.println(lcs.findLCS(ch1, ch2, 0, 0));

	}

}

/*
 * "bd", "abcd";
 * 
 * 
 * 
 */