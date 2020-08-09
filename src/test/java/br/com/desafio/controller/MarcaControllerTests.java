package br.com.desafio.controller;

import javax.annotation.PostConstruct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.desafio.controller.request.MarcaRequest;
import br.com.desafio.model.Marca;
import br.com.desafio.util.EntityGenericUtil;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarcaControllerTests {
	
	@LocalServerPort
    private int serverPort;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;
    
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PRIVATE)
    private RequestSpecification requestSpecification;
    
    @PostConstruct
    public void init() {
    	configureRestAssured();
    }
    
    private void configureRestAssured() {
    	RestAssured.port = serverPort;
    	RestAssured.basePath = contextPath;
    }
    
    private Marca criaMarca() {
    	return new Marca(EntityGenericUtil.getLong(), EntityGenericUtil.getString());
    }
    
    @Test
    public void salvarMarcaTest() throws Exception {
        Marca marca = criaMarca();
        marca.setId(null);
        MarcaRequest req = MarcaRequest.convertToRequest(marca);
        
        this.getRequestSpecification()
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.body(new ObjectMapper().writeValueAsString(req))
        		.post("/v1/marcas")
        		.then().statusCode(HttpStatus.OK.value());
    }    
    
}