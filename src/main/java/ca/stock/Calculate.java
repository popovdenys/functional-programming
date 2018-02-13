package ca.stock;

import java.math.BigDecimal;
import java.util.function.Function;

public class Calculate {
	
	private Function<String, BigDecimal> priceFinder;
	
	public Calculate(Function<String, BigDecimal> priceFinder) {
		this.priceFinder = priceFinder;
	}
	
	public BigDecimal computeStock(String ticker, int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}
	
}
