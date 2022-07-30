import java.util.BitSet;

public class LearnBitSetBasics {

	public static void main(String[] args) {

		//Here we are using the default constructor BitSet() to instantiate a BitSet object.
		//The default size of the BitSet object is 64 bit or 8 bytes.
		BitSet b1 = new BitSet();
		System.out.println("b1.size():"+b1.size());
		//Sets the bits from the specified fromIndex (inclusive) to the specified toIndex (exclusive) to the specified value.
		//void java.util.BitSet.set(int fromIndex, int toIndex, boolean value)
		b1.set(0, 63, true);
		//Returns the value of the bit with the specified index. The value is true if the bit with the index bitIndex is 
		//currently set in this BitSet; otherwise, the result is false.
		System.out.println("b1.get():"+b1.get(62));
		
		//Creates a bit set whose initial size is large enough to explicitly represent bits with indices in the range 0 through 
		//nbits-1. All bits are initially false.
		//java.util.BitSet.BitSet(int nbits)
		BitSet b2 = new BitSet(64);
		b2.set(0, 60, true);
		System.out.println("b2.size():"+b2.size());
		//Returns the "logical size" of this BitSet: the index ofthe highest set bit in the BitSet plus one. Returns zero if the 
		//BitSet contains no set bits.		
		System.out.println("b2.length():"+b2.length());
		System.out.println("b1.get():"+b2.get(65));
		
		BitSet b3 =  new BitSet(4);
		BitSet b4 = new BitSet(4);
		System.out.println("b3:"+b3);
		System.out.println("b4:"+b4);
		b4.set(3);
		b3.xor(b4);
		System.out.println("b3"+b3);
		System.out.println("b4:"+b4);
		b3.xor(b4);
		System.out.println("b3"+b3);
	}

}
