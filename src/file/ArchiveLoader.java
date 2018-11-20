package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchiveLoader {
	private String _content = "";
	
	public ArchiveLoader(String archivePath) throws IOException {
		_loadArchive(archivePath);
	}
	
	private void _loadArchive(String archivePath) throws IOException {
		_content = new String(Files.readAllBytes(Paths.get(archivePath)));
	}
	
	public String getContent() {
		return _content;
	}
}
