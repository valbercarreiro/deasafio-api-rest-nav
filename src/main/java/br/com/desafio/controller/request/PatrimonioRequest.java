package br.com.desafio.controller.request;

import javax.validation.constraints.NotNull;

import br.com.desafio.model.Marca;
import br.com.desafio.model.Patrimonio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a request das chamadas aos endpoints de patrimônio")
public class PatrimonioRequest {

	@ApiModelProperty(value = "Identificador do patrimônio", name = "Identificador patrimônio", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Nome do patrimônio", name = "Nome patrimônio", position = 2)
	@NotNull
	private String nome;
	
	@ApiModelProperty(value = "Descrição do patrimônio", name = "Descrição patrimônio", position = 3)
	@NotNull
	private String descricao;
	
	@ApiModelProperty(value = "Marca do patrimônio", name = "Marca patrimônio", position = 4)
	@NotNull
	private Long idMarca;
	
	public PatrimonioRequest(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public PatrimonioRequest(Marca marca) {
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

	public Patrimonio converter() {
		return new Patrimonio(null, nome, descricao, null, new Marca(idMarca, null));
	}

}
