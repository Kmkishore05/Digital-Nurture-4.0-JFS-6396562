package com.example;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
public class CalculatorTest {
	private Calculator calculator;

    @Before
    public void setUp() {
        System.out.println("Setting up calculator...");
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning up calculator...");
        calculator = null;
    }

    @Test
    public void testAddition() {
        // Arrange
        int a = 7;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(12, result);
    }

    @Test
    public void testSubtraction() {
        // Arrange
        int a = 10;
        int b = 4;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(6, result);
    }
}
