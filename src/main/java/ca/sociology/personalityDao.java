package ca.sociology;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class personalityDao {
	
	public static void main(String[] args) {
		
		final List<Personality> personalities = Arrays.asList(
				new Personality("Choleric", 1000)
				, new Personality("Choleric", 900)
				, new Personality("Melancholic", 1500)
				, new Personality("Melancholic", 1550)
				, new Personality("Sanguine", 1200)
				, new Personality("Phlegmatic", 1300));
		
		final List<Personality> personalitiesClean = Arrays.asList();

		List<Personality> ascendingEnergy =
				personalities.stream()
				.sorted((p2, p1) -> p1.getDopaminDifference(p2))
				.collect(Collectors.toList());
		
		personalities.stream()
				.min(Personality::getDopaminDifference)
				.ifPresent(lowerestLevel ->
					System.out.println(String.format("Lowerest dopamin level of %d relevants to %s"
							, lowerestLevel.getDopaminLevel(), lowerestLevel.getCharacter())));
				
		personalities.stream()
		.max(Personality::getDopaminDifference)
		.ifPresentOrElse(lowerestLevel ->
			System.out.println(String.format("Maximum dopamin level of %d relevants to %s"
					, lowerestLevel.getDopaminLevel(), lowerestLevel.getCharacter()))
			, new Thread() {
				public void run() {
					System.out.println("nothing found");
				}
			});
		
		System.out.println(ascendingEnergy);
		
		System.out.println("Sort by characters :");
		
		/**
		 * COMPARING
		 */
		
		final Function<Personality, String> byCharacter = personality -> personality.getCharacter();
		final Function<Personality, Integer> byDopaminLevelFunction = personality -> personality.getDopaminLevel();
		final Comparator<Personality> byDopaminLevelComparator = Comparator.comparing(Personality::getDopaminLevel);
		
		
		personalities.stream()
				.sorted(Comparator.comparing(byCharacter).thenComparing(byDopaminLevelFunction))
				.forEach(System.out::println);
		
		Set<Personality> withDopaminLevelHigherThen = personalities.stream()
				.filter(p -> p.getDopaminLevel() > 1200)
				.collect(HashSet::new, HashSet::add, HashSet::addAll);
		System.out.println("Personalitis with high dopamin level are : " + withDopaminLevelHigherThen);
		
		ConcurrentMap<String, List<Integer>> personalityByCharacter =
				personalities.stream()
					.collect(Collectors.groupingByConcurrent(
							Personality::getCharacter
							, Collectors.mapping(Personality::getDopaminLevel, Collectors.toList())));
		
		System.out.println("Grouped dopamin level by character : " + personalityByCharacter);
		
		Map<Character, Optional<Personality>> highestDopaminLevel =
				personalities.stream()
						.collect(Collectors.groupingBy(personality -> personality.getCharacter().charAt(0)
								, Collectors.reducing(BinaryOperator.maxBy(byDopaminLevelComparator))));
		
		System.out.println("Highest domapin level of each letter : " + highestDopaminLevel);

	}

}
