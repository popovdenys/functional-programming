package ca.stock;

import java.util.List;
import java.util.function.Predicate;

import ca.stock.Asset.AssetType;

public class AssetManipulations {
	public static int totalAssetValues(List<Asset> assets, Predicate<Asset> assetSelector) {
		return assets.stream()
				.filter(assetSelector)
				.mapToInt(Asset::getValue)
				.sum();
	}
	
	public static Predicate<Asset> bonds = asset -> asset.getType() == AssetType.BOND;
	public static Predicate<Asset> stocks = asset -> asset.getType() == AssetType.STOCK;
	
	public static int totalBondValues(List<Asset> assets) {
		return assets.stream()
				.mapToInt(asset ->
						asset.getType() == AssetType.BOND ? asset.getValue() : 0)
				.sum();
	}
	
	public static int totalStockValues(List<Asset> assets) {
		return assets.stream()
				.mapToInt(asset ->
						asset.getType() == AssetType.STOCK ? asset.getValue() : 0)
				.sum();
	}
	
	
}
