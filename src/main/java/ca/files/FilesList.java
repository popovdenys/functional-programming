package ca.files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesList {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Files.list(Paths.get("."))
				.filter(Files::isDirectory)
				.forEach(System.out::println);
		
		System.out.println("\nFiles end with .java : ");
		
		Files.newDirectoryStream(
				Paths.get("src/main/java"), path -> path.toString().endsWith(".java"))
					.forEach(System.out::println);
		
		final File[] hiddenFiles = new File(".").listFiles(File::isHidden);
		
		System.out.println("List of hidden files is : " + Arrays.asList(hiddenFiles));
		
		List<File> files = Stream.of(new File(".").listFiles())
				.flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
				.collect(Collectors.toList());
		
		System.out.println("Count of files in current directory : " + files.size() + " " + files);
		
		final Path path = Paths.get(".");
		final WatchService watchService = path.getFileSystem().newWatchService();
		
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		
		System.out.println("The files changed withing next time ...");
		
		final WatchKey watchKey = watchService.poll(3, TimeUnit.MINUTES);
		
		if(watchKey != null) {
			watchKey.pollEvents()
					.stream()
					.forEach(event -> System.out.println(event.context()));
		}
		
		Stream.of("/src", "/bin")
			.map(p -> {
				try {
					return new File(p).getCanonicalPath();
				} catch(IOException e) {
					return e.getMessage();
				}
			}).forEach(System.out::println);
		
	}
}
