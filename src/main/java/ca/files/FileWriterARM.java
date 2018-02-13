package ca.files;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterARM implements AutoCloseable {

	private final FileWriter writer;
	
	public FileWriterARM(String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}
	
	public void writeStuff(String message) throws IOException {
		writer.write(message);
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("close called automatically ...");
		writer.close();
	}
}
