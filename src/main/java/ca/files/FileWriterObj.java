package ca.files;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterObj {
	
	private FileWriter writer;
	
	public FileWriterObj(String fileName) throws IOException {
		this.writer = new FileWriter(fileName);
	}
	
	public void writeStuff(String message)  throws IOException {
		writer.write(message);
	}
	
	public void close() throws IOException {
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		FileWriterObj fileWriter = new FileWriterObj("test.file");
		
		try {
			
			fileWriter.writeStuff("ping");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			fileWriter.close();
			
		}
		
		try(FileWriterARM writerARM = new FileWriterARM("peek.inc")) {
			writerARM.writeStuff("lub dub");
			System.out.println("Accomplished with the resource...");
		}
		
		FileWriterEAM.use("db", writer -> {
			writer.writeStuff("Heinrich Hertz");
			writer.writeStuff(" is calling");
		});
	}
}
