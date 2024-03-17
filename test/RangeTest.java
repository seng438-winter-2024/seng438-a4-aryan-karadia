package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    
	private Range range;
	private Range positiveRange;
	private Range negativeRange;


    @Before
    public void setUp() throws Exception { 
    	range = new Range(-1, 1);
    	positiveRange = new Range(1,4);
    	negativeRange = new Range(-4,-1);
    	
    }
   
    
    // Tests for getLowerBound
    
    @Test
    public void getLowerBoundReturnsCorrectValue() {
        assertEquals("Lower bound should be -1", -1, range.getLowerBound(), 0.000000001d);
    }

    @Test
    public void correctLowerBoundForPositiveRange() {
        assertEquals("Lower bound should for postive range be 1", 1, positiveRange.getLowerBound(), 0.000000001d);
    }
    
    @Test
    public void correctLowerBoundForNegativeRange() {
        assertEquals("Lower bound should for negative range be -4", -4, negativeRange.getLowerBound(), 0.000000001d);
    }

    
    
    
    // Tests for getUppedBound
    
    @Test
    public void getUpperBoundReturnsCorrectValue() {
        assertEquals("Upper bound should be 1", 1, range.getUpperBound(), 0.000000001d);
    }

    @Test
    public void correctUpperBoundForPositiveRange() {
        assertEquals("Upper bound for postive range should be 4", 4, positiveRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void correctUpperBoundForNegativeRange() {
        assertEquals("Upper bound for negative range should be -1", -1, negativeRange.getUpperBound(), 0.000000001d);
    }
    
    
    
    
    // Tests for contains
    
    @Test
    public void valueWithinRange() {
    	assertTrue("0 falls within the range (-1,1)", 
    			range.contains(0));
    }
    
    @Test
    public void valueOnLowerBoundOfRange() {
    	assertTrue("-1 is the upper bound of the range (-1,1)", 
    			range.contains(-1));
    }
    
    @Test
    public void valueOnUpperBoundOfRange() {
     	assertTrue("1 is the upper bound of the range (-1,1)", 
     			range.contains(1));
    }

    @Test
    public void valueLowerThanLowerBound() {
     	assertFalse("-1.5 is outside of the range (-1,1)", 
     			range.contains(-1.5));
    }
    
    @Test
    public void valueHigherThanUpperBound() {
    	assertFalse("1.5 is outside of the range (-1,1)", 
     			range.contains(1.5));
    }
    
    
    
    
    // Tests for constrain
    
    @Test
    public void constrainValueWithinRange() {
        assertEquals("Constraining a value within the range should return the same value",
                0, range.constrain(0), 0.000000001d);
    }
    
    @Test
    public void constrainValueOnLowerBoundOfRange() {
        assertEquals("Constraining a value on the lower bound should return the lower bound",
                -1, range.constrain(-1), 0.000000001d);
    }
    
    @Test
    public void constrainValueOnUpperBoundOfRange() {
        assertEquals("Constraining a value on the upper bound should return the upper bound",
                1, range.constrain(1), 0.000000001d);
    }
    
    @Test
    public void constrainValueBelowLowerBound() {
        assertEquals("Constraining a value below the lower bound should return the lower bound",
                -1, range.constrain(-1.4), 0.000000001d);
    }

    @Test
    public void constrainValueAboveUpperBound() {
        assertEquals("Constraining a value above the upper bound should return the upper bound",
                1, range.constrain(1.6), 0.000000001d);
    }    
    
    
    
    
    // Tests for intersects
    
    @Test
    public void rangesWithSameBoundariesIntersect() {
        assertTrue("Ranges (-1,1) and (-1,1) should intersect", 
        		range.intersects(-1, 1));
    }
    
    @Test
    public void rangeNestingAnotherRangeIntersect() {
        assertTrue("Ranges (-1,1) and (-0.5,0.5) should intersect", 
        		range.intersects(-0.5, 0.5));
    }
    
    @Test
    public void rangeNestedByAnotherRangeIntersect() {
        assertTrue("Ranges (-1,1) and (-2,2) should intersect", 
        		range.intersects(-2, 2));
    }
    
    @Test
    public void rangeIntesectingFromUpperBoundOnly() {
        assertTrue("Ranges (-1,1) and (0,2) should intersect", 
        		range.intersects(0, 2));
    }    
    
    @Test
    public void rangeIntesectingFromLowerBoundOnly() {
        assertTrue("Ranges (-1,1) and (-2,0) should intersect", 
        		range.intersects(-2, 0));
    }    
    
    
    @Test
    public void notIntersectingIfOnlyUpperBoundTouchingAnotherRange() {
        assertFalse("Ranges (-1,1) and (1,3) should not intersect", 
        		range.intersects(1, 3));
    }
    
    @Test
    public void notIntersectingIfOnlyLowerBoundTouchingAnotherRange() {
        assertFalse("Ranges (-1,1) and (1,3) should not intersect", 
        		range.intersects(-3, -1));
    }
    
    @Test
    public void rangeSeperatedByGapNearUpperrBoundDoNotIntersect() {
        assertFalse("Ranges (-1,1) and (3,5) should not intersect", 
        		range.intersects(3, 5));
    }
    
    @Test
    public void rangeSeperatedByGapNearLowerBoundDoNotIntersect() {
        assertFalse("Ranges (-1,1) and (-5,-3) should not intersect", 
        		range.intersects(-5, -3));
    }
    
    @Test
    public void testGetCentralValue() {
        // Expected central value is (lower + upper) / 2
        double expectedCentralValue = (-1 + 1) / 2.0;
        assertEquals("Central value should be 0", expectedCentralValue, range.getCentralValue(), 0.000000001d);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLowerBoundWithInvalidRange() {
        // Creating a range with lower bound greater than upper bound
        Range invalidRange = new Range(2, 1);
        try {
            invalidRange.getLowerBound(); // this should throw IllegalArgumentException
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Verifying that the correct error message is generated
            assertEquals("Error message does not match", "Range(double, double): require lower (2.0) <= upper (1.0).", e.getMessage());
            // Adding an additional assertion to confirm that the exception was caught
            assertTrue("Expected IllegalArgumentException was thrown", true);
        }
    }
    
    @Test
    public void testGetUpperBoundWithInvalidRange() {
        try {
            // Creating a range with lower bound greater than upper bound
            Range invalidRange = new Range(2, 1);
            invalidRange.getUpperBound(); // this should throw IllegalArgumentException
            // Fail if the exception is not thrown
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Verifying that the correct error message is generated
            assertEquals("Error message does not match", "Range(double, double): require lower (2.0) <= upper (1.0).", e.getMessage());
            // Adding an additional assertion to confirm that the exception was caught
            assertTrue("Expected IllegalArgumentException was thrown", true);
        }
    }
    
    @Test
    public void testRangeInitializationWithValidRange() {
        // Test initialization with valid range
        Range validRange = new Range(1, 2);
        assertNotNull("Valid range should not be null", validRange);
    }
    
//    @Test
//    public void testIntersects() {
//        // Creating two ranges that intersect
//        Range range1 = new Range(1, 5);
//        Range range2 = new Range(3, 7);
//        assertTrue("Ranges should intersect", range1.intersects(range2));
//
//        // Creating two ranges that do not intersect
//        Range range3 = new Range(1, 5);
//        Range range4 = new Range(6, 10);
//        assertFalse("Ranges should not intersect", range3.intersects(range4));
//
//        // Creating two ranges where one range contains the other
//        Range range5 = new Range(1, 10);
//        Range range6 = new Range(2, 8);
//        assertTrue("Ranges should intersect", range5.intersects(range6));
//
//        // Creating two ranges where they share a boundary
//        Range range7 = new Range(1, 5);
//        Range range8 = new Range(5, 10);
//        assertTrue("Ranges should intersect", range7.intersects(range8));
//
//        // Creating two ranges with one common point
//        Range range9 = new Range(1, 5);
//        Range range10 = new Range(5, 10);
//        assertTrue("Ranges should intersect", range9.intersects(range10));
//
//        // Creating two ranges where one range is completely within the other
//        Range range11 = new Range(1, 10);
//        Range range12 = new Range(2, 8);
//        assertTrue("Ranges should intersect", range11.intersects(range12));
//    }
    
    @Test
    public void testCombine() {
        // Creating ranges
        Range range1 = new Range(1, 5);
        Range range2 = new Range(3, 7);
        Range range3 = new Range(6, 10);
        Range range4 = new Range(8, 12);
        Range range5 = new Range(-5, -1);
        Range range6 = new Range(-10, -6);

        // Testing when one range is null
        assertEquals("Should return non-null range", range1, Range.combine(range1, null));
        assertEquals("Should return non-null range", range2, Range.combine(null, range2));

        // Testing when both ranges are null
        assertNull("Should return null", Range.combine(null, null));

        // Testing when ranges do not overlap
        Range result1 = Range.combine(range1, range3);
        assertEquals("Should return combined range", new Range(1, 10), result1);

        // Testing when ranges overlap partially
        Range result2 = Range.combine(range1, range4);
        assertEquals("Should return combined range", new Range(1, 12), result2);

        // Testing when ranges are identical
        Range result3 = Range.combine(range1, range1);
        assertEquals("Should return identical range", range1, result3);

        // Testing when ranges have negative bounds
        Range result4 = Range.combine(range5, range6);
        assertEquals("Should return combined range with negative bounds", new Range(-10, -1), result4);
    }
    
//    @Test
//    public void testCombineIgnoringNaN() {
//        // Creating ranges
//        Range range1 = new Range(1, 5);
//        Range range2 = new Range(3, 7);
//        Range range3 = new Range(Double.NaN, 10);
//        Range range4 = new Range(8, Double.NaN);
//        Range range5 = new Range(Double.NaN, Double.NaN);
//
//        // Testing when one range is null
//        assertEquals("Should return non-null range", range1, Range.combineIgnoringNaN(range1, null));
//        assertEquals("Should return non-null range", range2, Range.combineIgnoringNaN(null, range2));
//
//        // Testing when both ranges are null
//        assertNull("Should return null", Range.combineIgnoringNaN(null, null));
//
//        // Testing when ranges do not overlap
//        Range result1 = Range.combineIgnoringNaN(range1, range3);
//        assertEquals("Should return combined range", new Range(1, 10), result1);
//
//        // Testing when ranges overlap partially
//        Range result2 = Range.combineIgnoringNaN(range1, range4);
//        assertEquals("Should return combined range", new Range(1, 8), result2);
//
//        // Testing when ranges are identical
//        Range result3 = Range.combineIgnoringNaN(range1, range1);
//        assertEquals("Should return identical range", range1, result3);
//
//        // Testing when ranges have NaN bounds
//        Range result4 = Range.combineIgnoringNaN(range3, range4);
//        assertNull("Should return null because of NaN bounds", result4);
//
//        // Testing when ranges have NaN bounds for both lower and upper
//        Range result5 = Range.combineIgnoringNaN(range4, range5);
//        assertNull("Should return null because of NaN bounds for both lower and upper", result5);
//    }
    
//    @Test
//    public void testExpand() {
//        // Creating a range
//        Range originalRange = new Range(0, 10);
//
//        // Testing with positive margins
//        Range expandedRange1 = Range.expand(originalRange, 0.1, 0.2);
//        assertEquals("Lower bound should be expanded correctly", -1, expandedRange1.getLowerBound(), 0.000001);
//        assertEquals("Upper bound should be expanded correctly", 11, expandedRange1.getUpperBound(), 0.000001);
//
//        // Testing with negative margins
//        Range expandedRange2 = Range.expand(originalRange, -0.2, -0.1);
//        assertEquals("Lower bound should be expanded correctly", 2, expandedRange2.getLowerBound(), 0.000001);
//        assertEquals("Upper bound should be expanded correctly", 8, expandedRange2.getUpperBound(), 0.000001);
//
//        // Testing with one negative and one positive margin
//        Range expandedRange3 = Range.expand(originalRange, -0.1, 0.2);
//        assertEquals("Lower bound should be expanded correctly", 1, expandedRange3.getLowerBound(), 0.000001);
//        assertEquals("Upper bound should be expanded correctly", 11, expandedRange3.getUpperBound(), 0.000001);
//
//        // Testing with zero margins
//        Range expandedRange4 = Range.expand(originalRange, 0, 0);
//        assertEquals("Range should remain the same", originalRange, expandedRange4);
//
//        // Testing with zero length range
//        Range zeroLengthRange = new Range(5, 5);
//        Range expandedRange5 = Range.expand(zeroLengthRange, 0.1, 0.2);
//        assertEquals("Range should remain the same", zeroLengthRange, expandedRange5);
//    }
    
    @Test
    public void testExpandToInclude() {
        // Creating a range
        Range range = new Range(0, 10);

        // Test expanding with a value below the lower bound
        Range expandedRange1 = Range.expandToInclude(range, -5);
        assertEquals("Lower bound should be expanded to include -5", -5, expandedRange1.getLowerBound(), 0.000001);
        assertEquals("Upper bound should remain unchanged", 10, expandedRange1.getUpperBound(), 0.000001);

        // Test expanding with a value above the upper bound
        Range expandedRange2 = Range.expandToInclude(range, 15);
        assertEquals("Lower bound should remain unchanged", 0, expandedRange2.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be expanded to include 15", 15, expandedRange2.getUpperBound(), 0.000001);

        // Test expanding with a value inside the range
        Range expandedRange3 = Range.expandToInclude(range, 5);
        assertEquals("Range should remain unchanged", range, expandedRange3);

        // Test expanding with a value equal to the lower bound
        Range expandedRange4 = Range.expandToInclude(range, 0);
        assertEquals("Range should remain unchanged", range, expandedRange4);

        // Test expanding with a value equal to the upper bound
        Range expandedRange5 = Range.expandToInclude(range, 10);
        assertEquals("Range should remain unchanged", range, expandedRange5);

        // Test expanding with null range
        Range expandedRange6 = Range.expandToInclude(null, 5);
        assertEquals("Lower bound should be 5", 5, expandedRange6.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be 5", 5, expandedRange6.getUpperBound(), 0.000001);
    }
    
    @Test
    public void testExpandWithLowerGreaterThanUpper() {
    	// Create a range
        Range range = new Range(0.0, 10.0);
        
        // Define margins that will cause lower > upper
        double lowerMargin = -0.6;
        double upperMargin = -0.5;
        
        // Test the expand method with the defined margins
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
     
        
        // Check if the expanded range matches the expected values
        assertEquals("Expected lower range not correct when lower > upper", 5.5, expandedRange.getLowerBound(), 0.0001);
        assertEquals("Expected lower range not correct when lower > upper", 5.5, expandedRange.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testShift() {
        // Creating a base range
        Range baseRange = new Range(0, 10);

        // Shifting the base range by a positive delta
        Range shiftedRange1 = Range.shift(baseRange, 5);
        assertEquals("Lower bound should be shifted by 5", 5, shiftedRange1.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be shifted by 5", 15, shiftedRange1.getUpperBound(), 0.000001);

        // Shifting the base range by a negative delta
        Range shiftedRange2 = Range.shift(baseRange, -5);
        assertEquals("Lower bound should be shifted by -5", -5, shiftedRange2.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be shifted by -5", 5, shiftedRange2.getUpperBound(), 0.000001);

        // Shifting the base range by a zero delta
        Range shiftedRange3 = Range.shift(baseRange, 0);
        assertEquals("Range should remain unchanged", baseRange, shiftedRange3);
    }
    
    @Test
    public void testShiftWithNoZeroCrossing() {
    	Range baseRange = new Range(-5.0,20.0);
        // Test when value is negative
        Range resultRange = Range.shift(baseRange, -5.0, false);
        
        // Expected
        Range expected = new Range(-10.0, 15.0);
        assertEquals("Range shift with no zero crossing", expected, resultRange);
    }
    
//    @Test
//    public void testScale() {
//        // Creating a base range
//        Range baseRange = new Range(0, 10);
//
//        // Scaling the base range by a factor of 2
//        Range scaledRange1 = Range.scale(baseRange, 2);
//        assertEquals("Lower bound should be scaled by a factor of 2", 0, scaledRange1.getLowerBound(), 0.000001);
//        assertEquals("Upper bound should be scaled by a factor of 2", 20, scaledRange1.getUpperBound(), 0.000001);
//
//        // Scaling the base range by a factor of 0.5
//        Range scaledRange2 = Range.scale(baseRange, 0.5);
//        assertEquals("Lower bound should be scaled by a factor of 0.5", 0, scaledRange2.getLowerBound(), 0.000001);
//        assertEquals("Upper bound should be scaled by a factor of 0.5", 5, scaledRange2.getUpperBound(), 0.000001);
//
//        // Scaling the base range by a factor of 1 (no change)
//        Range scaledRange3 = Range.scale(baseRange, 1);
//        assertEquals("Range should remain unchanged", baseRange, scaledRange3);
//
//        // Testing scaling with a negative factor (should throw an exception)
//        try {
////            Range scaledRange4 = Range.scale(baseRange, -1);
//            fail("Expected IllegalArgumentException was not thrown for negative factor");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Wrong exception message", "Negative 'factor' argument.", e.getMessage());
//        }
//    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidFactorScale() {
    	Range base = new Range(0.0, 5.0);
    	Range.scale(base, -0.5);
    }
    
	 // This test case checks the hashCode() method with two Range objects that have the same lower and upper bounds.
	 // It verifies that equal ranges produce the same hash code.
	 @Test
	 public void testHashCodeEqualRanges() {
	     Range range1 = new Range(0, 10);
	     Range range2 = new Range(0, 10);
	     assertEquals("Equal ranges should produce the same hash code", range1.hashCode(), range2.hashCode());
	 }
	
	 // This test case checks the hashCode() method with two Range objects that have different upper bounds.
	 // It verifies that different ranges ideally produce different hash codes.
	 @Test
	 public void testHashCodeDifferentRanges() {
	     Range range3 = new Range(0, 10);
	     Range range4 = new Range(0, 20);
	     assertNotEquals("Different ranges should ideally produce different hash codes", range3.hashCode(), range4.hashCode());
	 }
    
    @Test
    public void testShiftWithAllowZeroCrossing() {
        // Creating a base Range object
        Range base = new Range(0, 10);

        // Scaling with allowZeroCrossing set to true
        Range result1 = Range.shift(base, 5, true);

        // Expected result: Lower bound should be 5 units higher, upper bound should be 5 units higher
        assertEquals("Lower bound should be shifted by delta with allowZeroCrossing true", 5, result1.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be shifted by delta with allowZeroCrossing true", 15, result1.getUpperBound(), 0.000001);

        // Scaling with allowZeroCrossing set to false
        Range result2 = Range.shift(base, 5, false);

        // Expected result: Lower and upper bounds should be shifted by delta units, without allowing zero crossing
        assertEquals("Lower bound should be shifted by delta with allowZeroCrossing false", 5, result2.getLowerBound(), 0.000001);
        assertEquals("Upper bound should be shifted by delta with allowZeroCrossing false", 15, result2.getUpperBound(), 0.000001);
    }
    
    @Test
    public void testEqualsWithDifferentClass() {
        // Creating a Range object
        Range range = new Range(0, 10);

        // Creating an object of another class
        Object otherObject = new Object();

        // Comparing the Range object with the other object
        boolean result = range.equals(otherObject);

        // Expected result: Since otherObject is not an instance of Range, equals should return false
        assertFalse("Equals should return false when comparing with an object of a different class", result);
    }
    
    @Test
    public void testEqualsWithActaulRange() {
        // Creating a Range object
        Range range = new Range(5, 10);

        // Comparing the Range object with the other object
        boolean result = range.equals(new Range(0.0, 10.0));

        // Expected result: Since otherObject is not an instance of Range, equals should return false
        assertFalse("Equals should return false when comparing with an object of a different class", result);
    }
    
    @Test
    public void testIsNaNRangeWithNaNValues() {
        // Creating a Range object with NaN values
        Range nanRange = new Range(Double.NaN, Double.NaN);

        // Checking if the range is NaN
        assertTrue("Range with NaN values should be identified as NaN range", nanRange.isNaNRange());
    }

    @Test
    public void testIsNaNRangeWithNonNaNValues() {
        // Creating a Range object with non-NaN values
        Range nonNaNRange = new Range(0, 10);

        // Checking if the range is NaN
        assertFalse("Range with non-NaN values should not be identified as NaN range", nonNaNRange.isNaNRange());
    }
    
    @Test
    public void testCombineUsingNullFirstNullSecond() {
        // Test case 1: range1 is null and range2 is an NaN range
        Range range1 = null;
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void testCombineUsingNullSecondNullFirst() {
        // Test case 2: range2 is null and range1 is an NaN range
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = null;
        assertNull(Range.combineIgnoringNaN(range1, range2));
    }
    
//    @Test
//    public void testCombineUsingNullFirstValidSecondNaN() {
//        // Test case 3: Both range1 and range2 are non-null, but resulting range is NaN range
//        Range range1 = new Range(5.0, 10.0);
//        Range range2 = new Range(Double.NaN, Double.NaN);
//        assertNull(Range.combineIgnoringNaN(range1, range2));
//    }
    
//    @Test
//    public void testCombineUsingNullSecondValidFirstNaN() {
//        // Test case 4: Both range1 and range2 are non-null, resulting range is NaN range
//        Range range1 = new Range(Double.NaN, Double.NaN);
//        Range range2 = new Range(5.0, 10.0);
//        assertNull(Range.combineIgnoringNaN(range1, range2));
//    }
    
    @Test
    public void testCombineWithTwoNaN() {
    	Range NaNRange = new Range(Double.NaN, Double.NaN);
    	assertNull(Range.combineIgnoringNaN(NaNRange, NaNRange));
    }
   
    @Test
    public void testIsNaNRangeWithNaNRange() {
    	// NaN Range
    	Range NaNRange = new Range(Double.NaN, Double.NaN);
    	
    	boolean actual = NaNRange.isNaNRange();
    	
    	assertEquals("Given NaN Range should be true", true, actual);
    }
    
    @Test
    public void testIsNaNRangeWithActualRange() {
    	// regular range
    	Range testRange = new Range(0.0, 5.0);
    	
    	boolean actual = testRange.isNaNRange();
    	
    	assertEquals("Gives actaul range to isNanRange()", false, actual);
    }
    
    // -------------------------- START OF ASSIGNMENT 4 --------------------------
    
    // This test case checks the getLength() method with a range from 1.0 to 5.0. The expected length is 4.0. This kills a mutant where the lower field is negated or subtraction is replaced with addition.
    @Test
    public void testGetLength() {
        Range range = new Range(1.0, 5.0);
        assertEquals(4.0, range.getLength(), 0.0001);
    }
    
    // This test case checks the intersects() method with a range from 1.0 to 5.0. It checks two scenarios: one where the given range intersects with the original range (0.0 to 2.0), 
    // and one where it doesn’t (0.0 to 0.5). If the conditional boundary is changed, the result would be different
    @Test
    public void testIntersects() {
        Range range = new Range(1.0, 5.0);
        assertTrue(range.intersects(0.0, 2.0));
        assertFalse(range.intersects(0.0, 0.5));
    }

	 // This test case checks the constrain() method with a value less than the lower bound.
	 // It kills a mutant where the inequality checks in the constrain() method are changed.
	 @Test
	 public void testConstrainLessThanLower() {
	     Range range = new Range(1.0, 5.0);
	     assertEquals("Failed to constrain value less than lower bound", 1.0, range.constrain(0.0), 0.0001);
	 }
	
	 // This test case checks the constrain() method with a value greater than the upper bound.
	 // It kills the same mutant as the previous test case.
	 @Test
	 public void testConstrainGreaterThanUpper() {
	     Range range = new Range(1.0, 5.0);
	     assertEquals("Failed to constrain value greater than upper bound", 5.0, range.constrain(6.0), 0.0001);
	 }
	
	 // This test case checks the shift() method with a range from 1.0 to 5.0 and a shift of 2.0.
	 // It kills a mutant where the conditional at line 4 in the shift() method is negated.
	 @Test
	 public void testShiftLowerBound() {
	     Range range = new Range(1.0, 5.0);
	     Range shiftedRange = Range.shift(range, 2.0, false);
	     assertEquals("Failed to shift lower bound correctly", 3.0, shiftedRange.getLowerBound(), 0.0001);
	 }
	
	 // This test case checks the shift() method with a range from 1.0 to 5.0 and a shift of 2.0.
	 // It kills the same mutant as the previous test case.
	 @Test
	 public void testShiftUpperBound() {
	     Range range = new Range(1.0, 5.0);
	     Range shiftedRange = Range.shift(range, 2.0, false);
	     assertEquals("Failed to shift upper bound correctly", 7.0, shiftedRange.getUpperBound(), 0.0001);
	 }

	// This test case checks the equals() method with two Range objects that have the same lower bound but different upper bounds.
	// It kills a mutant where the conditional `if (!(this.upper == range.upper))` is replaced with false.
	@Test
	public void testEqualsDifferentUpperBounds() {
	    Range range1 = new Range(1.0, 5.0);
	    Range range2 = new Range(1.0, 6.0);
	    assertFalse("Failed to detect different upper bounds", range1.equals(range2));
	}

	// This test case checks the equals() method with two identical Range objects.
	// It kills the same mutant as the previous test case.
	@Test
	public void testEqualsIdenticalRanges() {
	    Range range1 = new Range(1.0, 5.0);
	    Range range2 = new Range(1.0, 5.0);
	    assertTrue("Failed to detect identical ranges", range1.equals(range2));
	}
	
	// This test case checks the scale() method with a non-negative factor.
    // It kills a mutant where the conditional `if (factor < 0)` is replaced with true.
    @Test
    public void testScaleNonNegativeFactor() {
        Range base = new Range(1.0, 5.0);
        double factor = 2.0;
        try {
            Range scaledRange = Range.scale(base, factor);
            assertEquals("Failed to correctly scale the lower bound", base.getLowerBound() * factor, scaledRange.getLowerBound(), 0.0001);
            assertEquals("Failed to correctly scale the upper bound", base.getUpperBound() * factor, scaledRange.getUpperBound(), 0.0001);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not be thrown for non-negative factor");
        }
    }
	
	


   
}