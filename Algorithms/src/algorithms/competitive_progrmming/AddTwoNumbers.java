package algorithms.competitive_progrmming;

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = null, last, prev = null;
		int unitPlaceDigit, carry=0, partialSum, digit1, digit2;
		
		while(l1 != null || l2 != null || carry != 0) {
			if(l3 == null) {
				l3 = new ListNode(0);
				last = l3;
				prev = l3;
			}
			else {
				last = new ListNode(0);
				prev.next = last;
			}
			if(l1==null) digit1 = 0;
			else digit1 = l1.val;
			
			if(l2==null) digit2 = 0;
			else digit2 = l2.val;
			
			partialSum = digit1+digit2+carry;
			unitPlaceDigit = partialSum%10;
			carry = partialSum/10;
			last.val = unitPlaceDigit;
			
			prev = last;
			
			if(l1!=null)
				l1 = l1.next;
			if(l2!=null)
				l2 = l2.next;
		}
		return l3;
	}
	
	public void printListNode(ListNode l) {
		for(ListNode temp = l; temp!=null; temp=temp.next) {
			System.out.print(temp.val);
			if(temp.next!=null)
				System.out.print("->");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// 12
		ListNode l1 = new ListNode(9, null);
		ListNode l2 = new ListNode(9, l1);
		
		// 35
		ListNode l3 = new ListNode(9, null);
		ListNode l4 = new ListNode(9, l3);
		ListNode l5 = new ListNode(9, l4);
		ListNode l6 = new ListNode(9, l5);
		
		AddTwoNumbers obj = new AddTwoNumbers();

		obj.printListNode(l2);
		obj.printListNode(l6);
		
		obj.printListNode(obj.addTwoNumbers(l2, l6));
		
	}

}
