package me.jmll.utm.web;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 5 (a) Implementa FileService y es marcado como servicio 
 * por org.springframework.stereotype.Service
 * */
// Escribe tu código aquí {

// }
public class FileServiceImpl implements FileService {

	@Override
	public Path getFile(String fileName) {
		/**
		 * 5 (b) Obtiene la referencia al objeto tipo java.nio.file.Path
		 * por medio de un *método* la clase java.nio.file.Paths
		 * y regresa el archivo
		 * */
		// Escribe tu código aquí {

		// }
	}
	
	public List<Path> walkDir(Path path, List<Path> paths){		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
			for(Path p: stream){
				if (Files.isDirectory(p)){
					walkDir(p, paths);
				}
				paths.add(p);
			}
		} catch (IOException | DirectoryIteratorException ex){
			//log.error(String.format("Error %s: %s", ex.getClass(), ex.getMessage()));
		}
		return paths;
	}

}
