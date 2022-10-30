package algorithms.competitive_progrmming;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingChars {

	public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        
        if(s.length()==1) return 1;
        
        char charArray[] = s.toCharArray();
        
        int longSubstrLen = 0, arr[] = new int[charArray.length];
        
        for(int i=1; i<s.length(); i++) {
            int prev = -1;
            for(int j=0; j<i; j++) {
                if(charArray[j] != charArray[i]) {
            		if(arr[j] > prev) {
            			arr[i]++;
            		}
            		else if(arr[j] < prev) {
            			arr[i] = arr[j]+1;
                	}	
                	prev = arr[j];
                }
                else {
                	arr[i] -= arr[j];
                 	prev = -1;
                }
            }
            if(arr[i]+1 > longSubstrLen) longSubstrLen = arr[i]+1;
        }
        
        return longSubstrLen;
    }
	
	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingChars obj = new LongestSubstringWithoutRepeatingChars();
		System.out.println(obj.lengthOfLongestSubstring(
				"vqblqcb"));

	}

}
// ohvhjdml dvdf abba pwwkew a aa vqblqcb