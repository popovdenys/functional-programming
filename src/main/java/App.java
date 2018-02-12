import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
	
	static final Predicate<String> startsWith = name -> name.trim().startsWith("J");
	
	static final Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.trim().startsWith(letter);
	}
	
	static final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.trim().startsWith(letter);
	
    public static void main(String[] args) {
    
    	final List<BigDecimal> coordinates =Arrays.asList(new BigDecimal("50")
														  , new BigDecimal("500")
														  , new BigDecimal("30")
														  , new BigDecimal("100")
														  , new BigDecimal("1200")
														  , new BigDecimal("800")); 

    	
    	final BigDecimal intersection = coordinates.stream()
    												.filter(c->c.compareTo(BigDecimal.valueOf(300)) > 0)
    												.map(c -> c.multiply(BigDecimal.valueOf(1.1)))
    												.reduce(BigDecimal.ZERO, BigDecimal::add);
    	
    	System.out.println(intersection);
    	
    	final List<String> amis = Arrays.asList(" Jeckie"
    											, " Solomon"
    											, " Denys"
    											, " Jess"
    											, " Ted"
    											, " Aniss"
    											, " Lucky"
    											, " Jeck");
    	
    	List<String> amiAnglais = amis.stream()
    		.filter(startsWith)
    		.map(n -> n.toUpperCase())
    		.collect(Collectors.toList());
    	
    	long amisQuantity = amis.stream()
        		.filter(checkIfStartsWith("J"))
        		.count();
        	
    	ConcurrentMap<Integer, String> amisMap = amis.stream()
    												.filter(startsWithLetter.apply("J"))
    												.collect(Collectors.toConcurrentMap(String::length, n->n.trim(), (n1, n2) -> n1 + "," + n2));
    	
    	System.out.println("Amis form List : " + amiAnglais + " - in total : " + amisQuantity);
    	
    	System.out.println("Amis form Map : " + amisMap);
    	
    	String startingLetter = "X";
    	
    	final Optional<String> whenNothingFound = amis.stream()
    												  .filter(startsWithLetter.apply(startingLetter))
    												  .findFirst();
    	
    	System.out.println(String.format("Amis starting with %s is : %s", startingLetter, whenNothingFound.orElse("No ami found")));
    	
    	// or that is better
    	
    	System.out.println("Start : There is no output if found nothing :");
    	whenNothingFound.ifPresent(System.out::println);
    	System.out.println("End");
    	
    	// reduce the List : to take total sum
    	
    	System.out.printf("Total length of all amis is %d\n", amis.stream()
    															.mapToInt(String::length)
    															.sum());
    	
    	// reduce the List : to take the longest name
    	
    	Optional<String> longestName = amis.stream()
    									   .reduce((n1, n2) -> (n1.length() >= n2.length()) ? n1 : n2);
    	
    	longestName.ifPresent(ami -> System.out.println(String.format("Ami with the longest name is %s", ami.trim())));

    	longestName.get().chars().skip(1).forEach( ch -> System.out.print(String.format("%d-%c ", ch, ch)));
    	System.out.println();
    	
    	longestName.get().chars().skip(1).forEach(ch -> System.out.print(ch + " "));
    	System.out.println();
    	
    	String predefindLongestName = amis.stream()
    									  .reduce("AmiWithLongestName", (n1, n2) -> (n1.length() >= n2.length()) ? n1 : n2);
    	
    	System.out.println(String.format("Longest name with predifind one is  : %s", predefindLongestName));
    	
    	System.out.println(String.join(",",amis));
    	
    	System.out.println(amis.parallelStream()
    							.map(String::toUpperCase)
    							.collect(Collectors.joining(",")));
    	
    	String qc = "Quebec8";
    	
    	qc.chars()
    	  .mapToObj(ch -> Character.valueOf((char) ch))
    	  .forEach(System.out::print);
    	
    	System.out.println();
    	
    	qc.chars()
    	  .filter(ch -> Character.isDigit(ch))
    	  .forEach(ch -> System.out.print(Integer.toHexString(ch)));
    }
}
