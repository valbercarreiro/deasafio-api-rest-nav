package br.com.desafio.controller.response;

import br.com.desafio.model.Marca;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a resposta das chamadas aos endpoints de marca")
public class MarcaResponse {

	@ApiModelProperty(value = "Identificador da marca", name = "Identificador marca", position = 1)
	private Long id;

	@ApiModelProperty(value = "Nome da marca", name = "Nome marca", position = 2)
	private String nome;
	
	public MarcaResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public MarcaResponse(Marca marca) {
		this.id = marca.getId();
		this.nome = marca.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public static MarcaResponse converter(Marca marca) {
		return new MarcaResponse(marca.getId(), marca.getNome());
	}

}
