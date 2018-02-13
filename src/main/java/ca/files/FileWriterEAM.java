package ca.files;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterEAM {
	
	private final FileWriter writer;
	
	public FileWriterEAM(String fileName) throws IOException {
		writer = new FileWriter(fileName);
	}
	
	public void writeStuff(String message) throws IOException {
		writer.write(message);
	}
	
	private void close() throws Exception {
		System.out.println("close called automatically ...");
		writer.close();
	}
	
	public static void use(String fileName
			, UseInstance<FileWriterEAM, IOException> block) throws Exception {
		
		FileWriterEAM writerEAM = new FileWriterEAM(fileName);
		
		try {
		
			block.accept(writerEAM);
		} finally {
			
			writerEAM.close();
			
		}
	}
}
