package ca.filters;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class Camera {
	
	private Function<Color, Color> filter;
	
	public Color capture(Color inputColor) {
		Color processedColor = filter.apply(inputColor);
		
		return processedColor;
	}
	
	public void setFilters(Function<Color, Color>... filters) {
		filter = Stream.of(filters)
				.reduce((filter, next) -> filter.compose(next))
				.orElseGet(Function::identity);
	}
	
	public Camera() {
		setFilters();
	}
	
	public static void main(String[] args) {
		Camera camera = new Camera();
		
		Consumer<String> printCaptured = (filterInfo) -> 
		System.out.println(String.format("with %s : %s", filterInfo,
					camera.capture(new Color(180, 110, 180))));
		
		printCaptured.accept("no filter");
		
		camera.setFilters(Color::brighter);
		printCaptured.accept("brigher filter");
		
		camera.setFilters(Color::darker);
		printCaptured.accept("dacker filter");
		
		camera.setFilters(Color::brighter, Color::darker);
		printCaptured.accept("brigher and dacker filter");
		
	}
}
