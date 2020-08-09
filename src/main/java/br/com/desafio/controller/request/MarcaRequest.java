package br.com.desafio.controller.request;

import javax.validation.constraints.NotNull;

import br.com.desafio.model.Marca;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a request das chamadas aos endpoints de marca")
public class MarcaRequest {

	@ApiModelProperty(value = "Nome da marca", name = "Nome marca", position = 2)
	@NotNull
	private String nome;
	
	public MarcaRequest() {
	}
	
	public MarcaRequest(String nome) {
		this.nome = nome;
	}
	
	public MarcaRequest(Marca marca) {
		this.nome = marca.getNome();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca converter() {
		return new Marca(null, nome);
	}

	public static MarcaRequest convertToRequest(Marca marca) {
		return new MarcaRequest(marca);
	}
}
