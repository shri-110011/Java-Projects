package algorithms.competitive_progrmming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	
	public void findThreeSumUsingNaiveApproach(int arr[], int targetSum) {

		int aLen = arr.length;
		Arrays.sort(arr);
		
		for(int i=0; i<aLen-2; i++) {
			for(int j=1; j<aLen-1; j++) {
				for(int k=2; k<aLen; k++) {
					int requiredVal = targetSum - (arr[i] + arr[j]);
					if(arr[k] == requiredVal) {
						System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
					}
					else if(arr[k] > requiredVal) {
						break;
					}
				}
			}
		}
		
	}
	
	public List<List<Integer>> findThreeSum(int arr[], int targetSum) {
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
        Arrays.sort(arr);
        
        int  arrLen = arr.length, rp=arrLen-1, lp;
        
        for(int i=0; i<arrLen-2; i++) {
            lp = i+1;
            int requiredSum = targetSum-arr[i];
            rp = arrLen-1;
            while(lp<rp) {
                if(arr[lp]+arr[rp] < requiredSum) {
                    lp++;
                }
                else if(arr[lp]+arr[rp] > requiredSum) {
                    rp--;
                }
                else {
                	if(i>0 && arr[i]==arr[i-1]) {
                		break;
                	}
                    if(arr[lp]==arr[lp-1] && (lp-1) != i) {
                        lp++;
                        rp--;
                        continue;
                    }
                   
                    list.add(Arrays.asList(arr[i], arr[lp], arr[rp]));
                    lp++;
                    rp--;
                }
            }
        }
        return list;
	}

	public static void main(String[] args) {
		
		int arr[] = {-1,0,1,2,-1,-4,-2,-3,3,0,4},
				targetSum = 0;
		
		ThreeSum obj = new ThreeSum();
//		obj.findThreeSumUsingNaiveApproach(arr, targetSum);
		System.out.println(obj.findThreeSum(arr, targetSum));

	}

}
