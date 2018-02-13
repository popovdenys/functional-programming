package ca.stock;

public class Asset {

	public enum AssetType {BOND, STOCK};
	
	private final AssetType type;
	
	private final int value;
	
	public Asset(AssetType assetType, int assetValue) {
		this.type = assetType;
		this.value = assetValue;
	}
	
	public AssetType getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
}
