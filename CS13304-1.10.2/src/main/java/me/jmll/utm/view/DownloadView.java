package me.jmll.utm.view;

import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.file.Paths;
import java.util.Map;

/**
 * Vista encargada de entregar el archivo solicitado
 * con capacidad de org.springframework.web.servlet.View
 * 4 (a) cuenta con tres atributos
 * filename: String, final, privado
 * contentType: String, final, privado
 * bytes: byte[], final, privado
 * */
public class DownloadView implements View {
	/**
	 * 4 (a) cuenta con tres atributos
	 * filename: String, final, privado
	 * contentType: String, final, privado
	 * bytes: byte[], final, privado
	 * */
	
	private final String filename;
	private final String contentType;
	private final byte[] bytes;
	
    public DownloadView(String filename, String contentType, byte[] bytes){
        this.filename = filename;
        this.contentType = contentType;
        this.bytes = bytes;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }
	/**
	 * 4 (b) Método render estructura la respuesta:
	 * - Agrega header: 'Content-Disposition': attachment; filename= this.filename'
	 * - Establece el contenido como 'application/octet-stream'
	 * - Escribe los bytes con response.getOutputStream()
	 * */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request,
                       HttpServletResponse response) throws Exception{
    	response.setHeader("Content Disposition", "attachment; filename=\"" + Paths.get(this.filename).getFileName());
    	response.setContentType("application/octet-stream");
    	response.getOutputStream().write(bytes);
    }
}
