package ca.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class GoogleFinance {
	
	public static BigDecimal getPrice(String ticker) {
		
		try {
			//http://finance.google.com/finance/historical?q=AAPL&output=csv
			URL url = new URL("https://finance.google.com/finance/historical?q=" + ticker + "&output=csv");
			
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(url.openStream()));
			
			String data = reader.lines().skip(1).findFirst().get();

			String[] dataItems = data.split(",");
			
			return new BigDecimal(dataItems[dataItems.length - 2]);
			
		} catch(Exception ex) {
			
			System.out.println("Information could be received");
			
		}
		
		return BigDecimal.ZERO;
		
	}
}
