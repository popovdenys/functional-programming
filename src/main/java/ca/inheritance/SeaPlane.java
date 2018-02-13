package ca.inheritance;
/**
public interface Fly {
	default void takeOff() { System.out.println("Fly::takeOff"); }
	default void land() { System.out.println("Fly::land"); }
	default void turn() { System.out.println("Fly::turn"); }
	default void cruise() { System.out.println("Fly::cruise"); }
}

public interface FastFly extends Fly {
	default void takeOff() { System.out.println("FastFly::takeOff"); }
}

public interface Sail {
	default void turn() { System.out.println("Sail::turn"); }
	default void cruise() { System.out.println("Sail::cruise"); }
}

public class Vehicle {
	public void turn() {System.out.println("Vehicle::turn");}
}
*/

public class SeaPlane extends Vehicle implements FastFly, Sail {

	private int altitude;
	
	public void cruise() {
		System.out.println("SeaPlane::cruise");
		if(altitude > 0)
			FastFly.super.cruise();
		else
			Sail.super.cruise();
	}
	
	public static void main(String[] args) {
		
		SeaPlane seaPlane = new SeaPlane();
		seaPlane.takeOff();
		seaPlane.turn();
		seaPlane.cruise();
		seaPlane.land();

	}

}
