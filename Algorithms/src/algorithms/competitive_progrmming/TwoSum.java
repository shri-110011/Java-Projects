package algorithms.competitive_progrmming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Pair {
	int value, index;
	
	public Pair(int value, int index) {
		this.value = value;
		this.index = index;
	}
}
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		
		List<Pair> list = new ArrayList<>();
		for(int i=0; i<nums.length; i++) {
			list.add(new Pair(nums[i], i));
		}
		
		Collections.sort(list, (o1, o2)-> o1.value - o2.value);

		int i = 0, j = nums.length - 1, indices[] = new int[2];

		while (i < j) {
			if (list.get(i).value + list.get(j).value > target)
				j--;
			else if (list.get(i).value + list.get(j).value < target)
				i++;
			else {
				indices[0] = list.get(i).index;
				indices[1] = list.get(j).index;
				break;
			}
		}

		return indices;
	}

	public static void main(String[] args) {
		int nums[] = { 3, 2, 4, 7 };
		TwoSum obj = new TwoSum();
		System.out.println(Arrays.toString(obj.twoSum(nums, 9)));
		System.out.println(Arrays.toString(nums));
	}

}
