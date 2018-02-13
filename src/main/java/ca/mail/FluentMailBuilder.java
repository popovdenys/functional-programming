package ca.mail;

import java.util.function.Consumer;

public class FluentMailBuilder {
	
	public FluentMailBuilder from(String address) { return this; }
	public FluentMailBuilder to(String address)   { return this; }
	public FluentMailBuilder subject(String line) { return this; }
	public FluentMailBuilder body(String message) { return this; }
	
	public static void send( Consumer<FluentMailBuilder> block ) {
		
		FluentMailBuilder mailer = new FluentMailBuilder();
		
		block.accept(mailer);
		
		System.out.println("sending ...");
		
	}
	
	public static void main(String[] args) {
		FluentMailBuilder.send( mailer -> mailer
				.from("int@inc.ca")
				.to("popovdenys@yahoo.fr")
				.subject("stub email")
				.body("Don't read me"));
	}
}
