package me.jmll.utm.web;

import java.nio.file.Path;
import java.util.List;

public interface FileService {
	public Path getFile(String fileName);
	public List<Path> walkDir(Path path, List<Path> paths);
}
