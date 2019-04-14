package me.jmll.utm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *  Establece el escaneo de componentes del
 *  paquete me.jmll.utm.web con filtro de exclusión
 *  org.springframework.stereotype.Controller
 * */
@Configuration
@ComponentScan(basePackages = "me.jmll.utm.web", 
			   excludeFilters = @ComponentScan.Filter(Controller.class) )
public class RootContextConfig {
	/**
	 * Busca y registra los módulos para serialización
	 * y deserialización
	 * */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		return mapper;
	}
	
	/**
	 * Busca paquetes con entidades anotadas con XML
	 * */
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[] { "me.jmll.utm.model" });
		return marshaller;
	}
}
