package br.com.desafio.controller.request;

import javax.validation.constraints.NotNull;

import br.com.desafio.model.Marca;
import br.com.desafio.model.Patrimonio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a request das chamadas aos endpoints de patrimônio")
public class PatrimonioRequest {

	@ApiModelProperty(value = "Nome do patrimônio", name = "Nome patrimônio", position = 1)
	@NotNull
	private String nome;
	
	@ApiModelProperty(value = "Descrição do patrimônio", name = "Descrição patrimônio", position = 2)
	private String descricao;
	
	@ApiModelProperty(value = "Marca do patrimônio", name = "Marca patrimônio", position = 3)
	@NotNull
	private Long idMarca;
	
	public PatrimonioRequest() {
	}
	
	public PatrimonioRequest(String nome, String descricao, Long idMarca) {
		this.nome = nome;
		this.descricao = descricao;
		this.idMarca = idMarca;
	}
	
	public PatrimonioRequest(Patrimonio patrimonio) {
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
		this.idMarca = patrimonio.getMarca().getId();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Patrimonio converter() {
		return new Patrimonio(null, nome, descricao, null, new Marca(idMarca, null));
	}

}
