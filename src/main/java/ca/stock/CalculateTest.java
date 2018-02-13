package ca.stock;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;


public class CalculateTest {
	
	@Test
	public void computeStock() {
		
		Calculate calculate = new Calculate(ticker -> new BigDecimal("6.001"));
		
		BigDecimal expected = new BigDecimal("600.1");
		
		assertEquals(0
					, calculate.computeStock("AAPL", 100).compareTo(expected)
					, 0.01);
	}
}
