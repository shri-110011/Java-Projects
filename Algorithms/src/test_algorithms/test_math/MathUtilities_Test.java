package test_algorithms.test_math;

import org.junit.Assert;
import org.junit.Test;

import algorithms.math.MathUtilities;

public class MathUtilities_Test {
	
	@Test
	public void testGCD() {
		
		int inputs[][] = {
				{1, 0}, {0, 1}, {-1, 0}, {0, -1},
				{24, 48}, {49, 7}, {13, 43}, {196, 78},
				{1336, 1796}
		}, results[] = {
				1, 1, 1, 1, 24, 7, 1, 2, 4
		};
		
		MathUtilities obj = new MathUtilities();
		System.out.println("Inside testGCD():");
		for(int i=0; i< inputs.length; i++) {
			System.out.println("Input "+(i+1)+": gcd("+inputs[i][0]+", "+inputs[i][1]+")");
			int actualResult = obj.gcd(inputs[i][0], inputs[i][1]),
					expectedResult = results[i];
			System.out.println("expectedResult: "+expectedResult);
			System.out.println("actualResult: "+actualResult);
			Assert.assertTrue(expectedResult == actualResult);
		}
		
	}
	
	@Test
	public void testModulo() {
		int inputs[][] = {
				{1,1}, {-1,1}, {1,-1}, {-1,-1},
				{7,4}, {-7,4}, {7,-4}, {-7,-4},
				{4,7}, {-4,7}, {4,-7} ,{-4,-7},
				{98,5}, {-126,13}
				
		}, results[] = {
				0, 0, 0, 0,
				3, 1, -1, -3,
				4, 3, -3, -4,
				3, 4
		};
		
		MathUtilities obj = new MathUtilities();
		System.out.println("Inside testModulo():");
		for(int i=0; i< inputs.length; i++) {
			System.out.println("Input "+(i+1)+": ("+inputs[i][0]+" mod "+inputs[i][1]+")");
			int actualResult = obj.modulo(inputs[i][0], inputs[i][1]),
					expectedResult = results[i];
			System.out.println("expectedResult: "+expectedResult);
			System.out.println("actualResult: "+actualResult);
			Assert.assertTrue(expectedResult == actualResult);
		}
	}
	
	@Test
	public void testModuloInverse() {
		int inputs[][] = {
				{1,2}, {2,1}, {-2,1}, {-1,1},
				{3000,7}, {-3000,7}, {7,3000}, {-7, 3000},
				{1,1}, {-1, 2}, {-1, 5}
				
		}, results[] = {
				1, 0, 0, 0,
				2, 5, 2143, 857,
				0, 1, 4
		};
		
		MathUtilities obj = new MathUtilities();
		System.out.println("Inside testModuloInverse():");
		for(int i=0; i< inputs.length; i++) {
			System.out.println("Input "+(i+1)+": (inverse of "+inputs[i][0]+" modulo "+inputs[i][1]+")");
			int actualResult = obj.moduloInv(inputs[i][0], inputs[i][1]),
					expectedResult = results[i];
			System.out.println("expectedResult: "+expectedResult);
			System.out.println("actualResult: "+actualResult);
			Assert.assertTrue(expectedResult == actualResult);
		}
	}

}
