package me.jmll.utm.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger log = LogManager.getLogger();
	private FileService fileService;

	@RequestMapping(value = "upload", 
				    method = RequestMethod.GET)
	public String upload(Map<String, Object> model){
		log.info("Upload requested file.");
		return "upload/file";
	}
	/**
	 * 8 (a) Valida que el path destino con java.nio.file.Files
	 * si no existe crea el directorio
	 * 8 (b) Utiliza FileCopyUtils de spring para escribir el contenido
	 * en el path indicado
	 * */
	@RequestMapping(value = "upload",
					method = RequestMethod.POST)
	public String fileUpload(Map<String, Object> model, 
			HttpSession session, HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("path") String path,
            @RequestParam("file") MultipartFile file) throws IOException {
		log.debug("Uploading attachment");
		List<String> warnings = new ArrayList<String>();
		List<String> errors = new ArrayList<String>();
		if (!file.isEmpty()) {
            try {
            	Path filePath = Paths.get(path);
            	// Escribe tu código Aquí { 

            	// }
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(filePath.toString() + File.separator + name)));
                //Escribe tu código Aquí {

                // }
                String message = String.format("File %s successfully uploaded to %s", name, path);
                warnings.add(message);
                log.info(message);

            }
            catch (Exception e) {
            	String message = String.format("Failed to upload file %s to %s: %s", name, path, e.getMessage());
            	errors.add(message);
            	log.info(message);
            }
        }
        else {
        	String message = String.format("Failed to upload file %s to %s. File was empty.", name, path);
        	errors.add(message);
        	log.info(message);
        }
		
        model.put("warnings", warnings);
        model.put("errors", errors);
		return "upload/done";
	}
	
	@Inject
	public void setFileService(FileService fileService){
		this.fileService = fileService;
	}
}
