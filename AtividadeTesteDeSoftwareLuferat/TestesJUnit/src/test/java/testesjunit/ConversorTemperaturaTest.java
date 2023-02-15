/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testesjunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kaik
 */
public class ConversorTemperaturaTest {
    
    public ConversorTemperaturaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConverteCelsiusParaFahrenheit() {
        System.out.println("converteCelsiusParaFahrenheit");
        double celsius = 32.0;
        double expResult = 89.6;
        double result = ConversorTemperatura.converteCelsiusParaFahrenheit(celsius);
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testConverteFahrenheitParaCelsius() {
        System.out.println("converteFahrenheitParaCelsius");
        double fahrenheit = 89.6;
        double expResult = 32.0;
        double result = ConversorTemperatura.converteFahrenheitParaCelsius(fahrenheit);
        assertEquals(expResult, result, 0);
    }
}