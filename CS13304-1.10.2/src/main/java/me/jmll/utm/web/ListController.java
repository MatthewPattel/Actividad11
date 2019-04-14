package me.jmll.utm.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import me.jmll.utm.view.DownloadView;

@Controller
public class ListController {
	private static final Logger log = LogManager.getLogger();
	private FileService fileService;
	/**
	 * 6 (c) Crea una instancia de DownloadView una vez que 
	 * se obtiene el archivo del parámetro fileName por medio de
	 * fileService
	 * */
	@RequestMapping(value = "list/files", method = RequestMethod.GET)
	@ResponseBody
	public View getFile(@RequestParam("fileName")  String fileName) throws IOException {
		// Escribe tu código Aquí {

		// }
	}
	
	@RequestMapping(value="list",
					method = RequestMethod.GET)
	public String doFileList(){
		return "download/file";
	}
	/**
	 * 6 (a) Obtiene el contenido del path proporcionado
	 * por mediod el servicio fileService y el método
	 * walkDir sí y sólo si el path existe Y es directorio.
	 * Utilizar métodos de java.nio.Files
	 * 
	 * 6 (b) agrega las variables
	 * path, paths y warnings al modelo 
	 * */
	@RequestMapping(value = "list/show",
			method = RequestMethod.POST)
	public String getFileList(Map<String, Object> model, 
			HttpSession session, HttpServletRequest request,
			@RequestParam("path") String path){
		List<String> errors = new ArrayList<String>();
		List<String> warnings = new ArrayList<String>();
		Path dir = Paths.get(path);
		List<Path> paths = new ArrayList<>();
		if (Files.exists(dir) && Files.isDirectory(dir)){
			// Escribe tu código aquí {

			// }
		} else {
			errors.add(String.format("Path %s does not exist or is not dir", path));
			model.put("errors", errors);
		}
		return "download/list";
	}
	
	@Inject
	public void setFileService(FileService fileService){
		this.fileService = fileService;
	}
	
}

