package collections_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UnmodifiableVsImmutableList {

	@Test
	public void testList() {
		List<String> modifiableList = new ArrayList<String>();
	    modifiableList.add("a");

	    System.out.println("modifiableList:"+modifiableList);
	    System.out.println("--");

	    //unModifiableList

	    Assert.assertEquals(1, modifiableList.size());

	    List<String> unModifiableList = Collections.unmodifiableList(
	                                        modifiableList);

	    modifiableList.add("b");

	    boolean exceptionThrown=false;
	    try {
	        unModifiableList.add("b");
	        Assert.fail("add supported for unModifiableList!!");
	    } catch (UnsupportedOperationException e) {
	        exceptionThrown = true;
	        System.out.println("unModifiableList.add() not supported");
	    }
	    Assert.assertTrue(exceptionThrown);

	    System.out.println("modifiableList:"+modifiableList);
	    System.out.println("unModifiableList:"+unModifiableList);

	    Assert.assertEquals(2, modifiableList.size());
	    Assert.assertEquals(2, unModifiableList.size());
	    System.out.println("--");
	    
	    //immutableList

        List<String> immutableList=Collections.unmodifiableList(
                                new ArrayList<String>(modifiableList));

        modifiableList.add("c");
        modifiableList.remove(0);

        exceptionThrown=false;
        try {
            immutableList.add("c");
            Assert.fail("add supported for immutableList!!");
        } catch (UnsupportedOperationException e) {
            exceptionThrown=true;
            System.out.println("immutableList.add() not supported");
        }
        Assert.assertTrue(exceptionThrown);


        System.out.println("modifiableList:"+modifiableList);
        System.out.println("unModifiableList:"+unModifiableList);
        System.out.println("immutableList:"+immutableList);
        System.out.println("--");

        Assert.assertEquals(3, modifiableList.size());
        Assert.assertEquals(3, unModifiableList.size());
        Assert.assertEquals(2, immutableList.size());

//        Output
//
//        modifiableList:[a]
//        --
//        unModifiableList.add() not supported
//        modifiableList:[a, b]
//        unModifiableList:[a, b]
//        --
//        immutableList.add() not supported
//        modifiableList:[a, b, c]
//        unModifiableList:[a, b, c]
//        immutableList:[a, b]
//        --  
	            
	}

}
