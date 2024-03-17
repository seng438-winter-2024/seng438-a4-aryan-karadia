package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DataUtilitiesTest extends DataUtilities {

    private DefaultKeyedValues2D values;
    private DefaultKeyedValues2D values2;
    private DefaultKeyedValues2D values3;
    private DefaultKeyedValues2D values4;
    private DefaultKeyedValues2D negativeValues;
    private DefaultKeyedValues2D valuesRow1;
    private DefaultKeyedValues2D valuesRow3;
    private DefaultKeyedValues2D valuesRow2;
    @Before
    public void setUp() {
    	// Creates object of DefaultKeyedValues2D with 2 rows and 1 column
        
        values = new DefaultKeyedValues2D();
        values.addValue(7.5, 0, 0);
        values.addValue(2.5, 1, 0);
        values.addValue(2.5, 2, 0);
        values.addValue(2.5, 3, 0);;
        
        
        // Creates object of DefaultKeyedValues2D with 1 row and 2 columns
        
        values2 = new DefaultKeyedValues2D();
        values2.addValue(0, 0, 0);
        values2.addValue(3.5, 0, 1);
        // Creates a object of DefaultKeyedValues2D with 2 columns and 2 rows
        values3 = new DefaultKeyedValues2D();
        values3.addValue(2.5, 0, 0);    
        values3.addValue(2.5, 1, 0);
        values3.addValue(-2.5, 0, 1);        
        values3.addValue(-2.5, 1, 1);
        
        
        // Creates object of DefaultKeyedValues2D with 0 rows and 0 columns
        values4 = new DefaultKeyedValues2D();
       
        // Creates a object of DefaultKeyedValues2D with 2 rows and 1 column
        // with negative values
        negativeValues = new DefaultKeyedValues2D();
        negativeValues.addValue(-2.5, 0, 0);
        negativeValues.addValue(-2.5, 1, 0);
  
        
        //row data
        // Creates an object object with 1 row and 2 columns
        valuesRow1 = new DefaultKeyedValues2D();
        valuesRow1.addValue(7.5, 0, 0);
        valuesRow1.addValue(2.5, 0, 1);


        // Creates an object object with 1 row and 3 columns
        valuesRow2 = new DefaultKeyedValues2D();
        valuesRow2.addValue(3.5, 0, 0);
        valuesRow2.addValue(2.5, 0, 1);
        valuesRow2.addValue(1.5, 0, 2);
        
        
        // Creates  object with 0 rows and 0 columns
        valuesRow3 = new DefaultKeyedValues2D();
       
    }
    
    // This test uses an object object with 1 row and 2 columns and tests the output
    // of calculateColumnTotal given the highest column index for the matrix (column2).
    @Test
    public void calculateColumnTotalWithMaxColumns() {
        double result = DataUtilities.calculateColumnTotal(values2, 1);
        assertEquals(3.5, result, .000000001d);
    }
    
    
    // This test uses an object object with 3 columns and 2 rows and tests the output
    // of calculateColumnTotal given a column index of 1, the middle column of 
    // the matrix (column2).
   @Test
    public void calculateColumnTotalWithThreeColumn() {
        double result = DataUtilities.calculateColumnTotal(values3, 0);
        assertEquals(5.0, result, .000000001d);
    }
    
    // This test uses an object object with 4 rows and 1 column and tests output of
    // calculateColumnTotal given 4 values being added together
    @Test
    public void calculateColumnTotalForFourValues() {
        // setup  
        double result = DataUtilities.calculateColumnTotal(values, 0);
        // verify
        assertEquals(15.0, result, .000000001d);
        // tear-down: None in this test method
    }
    
    // This test uses an object object with 2 rows and 1 column to test output of
    // calculateColumnTotal method with two negative values
    @Test
    public void calculateColumnTotalForNegativeValues() {
    	double result = DataUtilities.calculateColumnTotal(negativeValues, 0);
    	assertEquals(-5.0, result, .000000001d);
    }
    
    
    // This test uses an object object with 0 rows and 0 columns to test output
    // of calculateColumnTotal which should return 0 with invalid data.
    @Test
    public void calculateColumnTotalWithInvalidData() {
        // Pass null as the data object
        double result = DataUtilities.calculateColumnTotal(values4, 0);
        assertEquals(0.0, result, .000000001d);
    }
    
    //end of testing columns 
    
    //beginning of testing rows
    
	 // This test verifies the calculation of the total for a row with two columns using mock object valuesRow1.
	 // It ensures that the calculateRowTotal method correctly computes the total and matches the expected value (10.0).
	 @Test
	 public void calculateRowTotalWithTwoColumns() {
		 double result = DataUtilities.calculateRowTotal(valuesRow1, 0);
		 assertEquals(10.0, result, .000000001d);
	 }
	
	 // This test examines the calculation of the total for a row with three columns using mock object valuesRow2.
	 // It validates that the calculateRowTotal method accurately computes the total and matches the expected value (7.5).
	 @Test
	 public void calculateRowTotalWithThreeColumns() {
		 double result = DataUtilities.calculateRowTotal(valuesRow2, 0);
		 assertEquals(7.5, result, .000000001d);
	 }
	
	 // This test evaluates the behavior of calculateRowTotal when provided with invalid data using mock object valuesRow3.
	 // It ensures that the calculateRowTotal method correctly handles invalid data and returns 0.0 as the total.
	 @Test
	 public void calculateRowTotalWithInvalidData() {
		 double result = DataUtilities.calculateRowTotal(valuesRow3, 0);
		 assertEquals(0.0, result, .000000001d);
	 }

    //end of calculateRowTotal method
    
    //beginning of createNumberArray method
	 
	// This test verifies the creation of a Number array from empty data.
		// It ensures that the createNumberArray method correctly handles the case of an empty double array.
		@Test
		public void createNumberArrayWithEmptyData() {
		    // Given
		    double[] emptyData = {};
		    
		    // When
		    Number[] result = DataUtilities.createNumberArray(emptyData);
		    
		    // Then
		    assertEquals(0, result.length);
		}
    
		// This test verifies the creation of a Number array from valid data.
		// It confirms that the createNumberArray method correctly converts the double array into a Number array.
		@Test
		public void testCreateNumberArrayLength() {
		    double[] validData = {1.5, 2.5, 3.5};
		    Number[] result = DataUtilities.createNumberArray(validData);
		    assertEquals("Failed to create Number array with correct length", 3, result.length);
		}

		@Test
		public void testCreateNumberArrayElement1() {
		    double[] validData = {1.5, 2.5, 3.5};
		    Number[] result = DataUtilities.createNumberArray(validData);
		    assertEquals("Failed to correctly convert first element", 1.5, result[0].doubleValue(), 0.000000001d);
		}

		@Test
		public void testCreateNumberArrayElement2() {
		    double[] validData = {1.5, 2.5, 3.5};
		    Number[] result = DataUtilities.createNumberArray(validData);
		    assertEquals("Failed to correctly convert second element", 2.5, result[1].doubleValue(), 0.000000001d);
		}

		@Test
		public void testCreateNumberArrayElement3() {
		    double[] validData = {1.5, 2.5, 3.5};
		    Number[] result = DataUtilities.createNumberArray(validData);
		    assertEquals("Failed to correctly convert third element", 3.5, result[2].doubleValue(), 0.000000001d);
		}

		// This test validates the creation of a 2D Number array from valid data.
		// It confirms that the createNumberArray2D method properly converts the 2D double array into a 2D Number array.
		@Test
		public void testCreateNumberArray2DRowCount() {
		    double[][] validData = {{1.5, 2.5, 3.5}, {4.5, 5.5, 6.5}};
		    Number[][] result = DataUtilities.createNumberArray2D(validData);
		    assertEquals("Failed to create Number array with correct number of rows", 2, result.length);
		}

		@Test
		public void testCreateNumberArray2DColumnCountRow1() {
		    double[][] validData = {{1.5, 2.5, 3.5}, {4.5, 5.5, 6.5}};
		    Number[][] result = DataUtilities.createNumberArray2D(validData);
		    assertEquals("Failed to create Number array with correct number of columns in first row", 3, result[0].length);
		}

		@Test
		public void testCreateNumberArray2DColumnCountRow2() {
		    double[][] validData = {{1.5, 2.5, 3.5}, {4.5, 5.5, 6.5}};
		    Number[][] result = DataUtilities.createNumberArray2D(validData);
		    assertEquals("Failed to create Number array with correct number of columns in second row", 3, result[1].length);
		}

		// This test examines the calculation of cumulative percentages from valid data.
		// It ensures that the getCumulativePercentages method correctly computes cumulative percentages.
		@Test
		public void testGetCumulativePercentagesItemCount() {
		    DefaultKeyedValues data = new DefaultKeyedValues();
		    data.addValue("0", 5);
		    data.addValue("1", 9);
		    data.addValue("2", 2);
		    KeyedValues result = DataUtilities.getCumulativePercentages(data);
		    assertEquals("Failed to compute cumulative percentages with correct number of items", 3, result.getItemCount());
		}

		@Test
		public void testGetCumulativePercentagesValue0() {
		    DefaultKeyedValues data = new DefaultKeyedValues();
		    data.addValue("0", 5);
		    data.addValue("1", 9);
		    data.addValue("2", 2);
		    KeyedValues result = DataUtilities.getCumulativePercentages(data);
		    assertEquals("Failed to correctly compute cumulative percentage for first item", 0.3125, result.getValue("0").doubleValue(), 0.000000001d);
		}

		@Test
		public void testGetCumulativePercentagesValue1() {
		    DefaultKeyedValues data = new DefaultKeyedValues();
		    data.addValue("0", 5);
		    data.addValue("1", 9);
		    data.addValue("2", 2);
		    KeyedValues result = DataUtilities.getCumulativePercentages(data);
		    assertEquals("Failed to correctly compute cumulative percentage for second item", 0.875, result.getValue("1").doubleValue(), 0.000000001d);
		}

		@Test
		public void testGetCumulativePercentagesValue2() {
		    DefaultKeyedValues data = new DefaultKeyedValues();
		    data.addValue("0", 5);
		    data.addValue("1", 9);
		    data.addValue("2", 2);
		    KeyedValues result = DataUtilities.getCumulativePercentages(data);
		    assertEquals("Failed to correctly compute cumulative percentage for third item", 1.0, result.getValue("2").doubleValue(), 0.000000001d);
		}
	
	
		// This test verifies the equal() method when both arrays are null.
		@Test
		public void testEqualBothNull() {
		    assertTrue("Both null arrays should be considered equal", DataUtilities.equal(null, null));
		}

		// This test verifies the equal() method when one array is null and the other is not.
		@Test
		public void testEqualOneNull() {
		    assertFalse("A null array and a non-null array should not be considered equal", DataUtilities.equal(new double[][]{{1.0}}, null));
		    assertFalse("A non-null array and a null array should not be considered equal", DataUtilities.equal(null, new double[][]{{1.0}}));
		}

		// This test verifies the equal() method when arrays have different lengths.
		@Test
		public void testEqualDifferentLengths() {
		    assertFalse("Arrays of different lengths should not be considered equal", DataUtilities.equal(new double[][]{{1.0}}, new double[][]{{1.0}, {2.0}}));
		}

		// This test verifies the equal() method when arrays have the same length but different values.
		@Test
		public void testEqualSameLengthDifferentValues1() {
		    assertFalse("Arrays with the same length but different values should not be considered equal", DataUtilities.equal(new double[][]{{1.0}}, new double[][]{{2.0}}));
		}

		@Test
		public void testEqualSameLengthDifferentValues2() {
		    assertFalse("Arrays with the same length but different values should not be considered equal", DataUtilities.equal(new double[][]{{1.0}, {2.0}}, new double[][]{{1.0}, {3.0}}));
		}

		// This test verifies the equal() method when arrays are equal.
		@Test
		public void testEqualEqualArrays1() {
		    assertTrue("Equal arrays should be considered equal", DataUtilities.equal(new double[][]{{1.0}}, new double[][]{{1.0}}));
		}

		@Test
		public void testEqualEqualArrays2() {
		    assertTrue("Equal arrays should be considered equal", DataUtilities.equal(new double[][]{{1.0}, {2.0}}, new double[][]{{1.0}, {2.0}}));
		}

		// This test verifies the clone() method when source is empty.
		@Test
		public void testCloneEmptySource() {
		    double[][] source = {};
		    assertArrayEquals("Cloning empty source should return empty array", source, DataUtilities.clone(source));
		}

		// This test verifies the clone() method when source contains null rows.
		@Test
		public void testCloneNullRows() {
		    double[][] source = {{1.0, 2.0}, null, {3.0, 4.0}};
		    assertArrayEquals("Cloning source with null rows should return the same", source, DataUtilities.clone(source));
		}

		// This test verifies the clone() method when source contains non-null rows.
		@Test
		public void testCloneNonNullRows() {
		    double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
		    assertArrayEquals("Cloning source with non-null rows should return the same", source, DataUtilities.clone(source));
		}

		// This test verifies the clone() method when source contains rows with different lengths.
		@Test
		public void testCloneDifferentLengths() {
		    double[][] source = {{1.0, 2.0}, {3.0}};
		    assertArrayEquals("Cloning source with rows of different lengths should return the same", source, DataUtilities.clone(source));
		}

		// This test verifies the clone() method when source contains large array.
		@Test
		public void testCloneLargeSource() {
		    double[][] source = new double[1000][1000];
		    for (int i = 0; i < 1000; i++) {
		        for (int j = 0; j < 1000; j++) {
		            source[i][j] = i + j;
		        }
		    }
		    double[][] cloned = DataUtilities.clone(source);
		    assertArrayEquals("Cloning large source should return the same array", source, cloned);
		    assertNotSame("Cloning large source should return a different array instance", source, cloned);
		}

	
	@Test
	public void testCalculateColumnTotal_AllValidRows() {
	    int[] validRows = {0, 1, 2, 3};
	    double total = DataUtilities.calculateColumnTotal(values, 0, validRows);
	    assertEquals(15.0, total, 0.000001);
	}

	@Test
	public void testCalculateColumnTotal_SomeValidRows() {
	    int[] validRows = {0, 1};
	    double total = DataUtilities.calculateColumnTotal(values, 0, validRows);
	    assertEquals(10.0, total, 0.000001);
	}

	@Test
	public void testCalculateColumnTotal_NoValidRows() {
	    int[] validRows = {};
	    double total = DataUtilities.calculateColumnTotal(values, 0, validRows);
	    assertEquals(0.0, total, 0.000001);
	}

	@Test
	public void testCalculateRowTotal_AllValidCols() {
	    int[] validCols = {0, 1};
	    double total = DataUtilities.calculateRowTotal(values, 0, validCols);
	    assertEquals(7.5, total, 0.000001);
	}

	@Test
	public void testCalculateRowTotal_SomeValidCols() {
		int[] validCols = {0};
	    double total = DataUtilities.calculateRowTotal(values, 1, validCols);
	    assertEquals(2.5, total, 0.000001);
	}

	@Test
	public void testCalculateRowTotal_NoValidCols() {
	    int[] validCols = {};
	    double total = DataUtilities.calculateRowTotal(values, 1, validCols);
	    assertEquals(0.0, total, 0.000001);
	}

	@Test
	public void testCalculateRowTotal_WithValidDataAndZeroColumnCount() {
	    int[] validCols = {};
	    double total = DataUtilities.calculateRowTotal(valuesRow3, 0, validCols);
	    assertEquals(0.0, total, 0.000001);
	}
	
	// ------------------------------ START OF ASSIGNMENT 4 ------------------------------
	
	// This test case checks the clone() method with a null source array.
	// It kills a mutant where the call to ParamChecks.nullNotPermitted(source, "source") is removed.
	@Test(expected = IllegalArgumentException.class)
	public void testCloneNullSource() {
	    double[][] source = null;
	    double[][] clone = DataUtilities.clone(source);
	}

	// This test case checks the createNumberArray2D() method with a data array that has more than 11 elements.
	// It kills a mutant where the less than `i < 11` is changed to not equal.
	@Test
	public void testCreateNumberArray2DMoreThan11Elements() {
	    double[][] data = new double[12][];
	    for (int i = 0; i < data.length; i++) {
	        data[i] = new double[]{i};
	    }
	    Number[][] result = DataUtilities.createNumberArray2D(data);
	    assertEquals("Failed to handle more than 11 elements", data.length, result.length);
	}
	
    @After
    public void tearDown() {
        values = null;
    }
}