package ca.stock;

import java.util.Arrays;
import java.util.List;

public class OnStock {
	public static void main(String[] args) {
		final List<Asset> assets = Arrays.asList(
				new Asset(Asset.AssetType.BOND, 5000)
				, new Asset(Asset.AssetType.STOCK, 5500)
				, new Asset(Asset.AssetType.BOND, 700)
				, new Asset(Asset.AssetType.BOND, 3500)
				, new Asset(Asset.AssetType.STOCK, 8000));
		
		System.out.println(String
				.format("Total assets value is %d"
						, AssetManipulations.totalAssetValues(assets, asset -> true)));
		
		System.out.println(String
				.format("Total bonds value is %d"
						, AssetManipulations.totalAssetValues(assets, AssetManipulations.bonds)));
		
		System.out.println(String
				.format("Total bonds value is %d"
						, AssetManipulations.totalBondValues(assets)));
		
		System.out.println(String
				.format("Total stocks value is %d"
						, AssetManipulations.totalAssetValues(assets, AssetManipulations.stocks)));
		
		System.out.println(String
				.format("Total stocks value is %d"
						, AssetManipulations.totalStockValues(assets)));
		
		Calculate calculate = new Calculate(GoogleFinance::getPrice);
		
		System.out.println(String.format("100 shares of Google worth : $%.2f"
				, calculate.computeStock("AAPL", 100)));
	}
}
