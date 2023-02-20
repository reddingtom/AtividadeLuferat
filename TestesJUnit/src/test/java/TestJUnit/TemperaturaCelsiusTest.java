package TestJUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Reddingtom
 */
public class TemperaturaCelsiusTest {
    
    public TemperaturaCelsiusTest() {
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
    public void testConverteCelsiusParafahrenheit() {
        System.out.println("converteCelsiusParafahrenheit");
        TemperaturaCelsius instance = new TemperaturaCelsius(32.0);
        double expResult = 89.6;
        double result = instance.converteCelsiusParafahrenheit();
        assertEquals(expResult, result, 0);
    }
    
}
