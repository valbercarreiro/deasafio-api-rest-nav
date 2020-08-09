package br.com.desafio.controller.response;

import br.com.desafio.model.Patrimonio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a resposta das chamadas aos endpoints de patrimônio")
public class PatrimonioResponse {

	@ApiModelProperty(value = "Identificador do patrimônio", name = "Identificador patrimônio", position = 1)
	private Long id;

	@ApiModelProperty(value = "Nome do patrimônio", name = "Nome patrimônio", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "Descrição do patrimônio", name = "Descrição patrimônio", position = 3)
	private String descricao;
	
	@ApiModelProperty(value = "Número do tombamento do patrimônio", name = "Número Tombamento patrimônio", position = 4)
	private String numeroTombamento;
	
	@ApiModelProperty(value = "Marca do patrimônio", name = "Marca patrimônio", position = 5)
	private MarcaResponse marca;
	
	public PatrimonioResponse(Long id, String nome, String descricao, String numeroTombamento, MarcaResponse marca) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.numeroTombamento = numeroTombamento;
		this.marca = marca;
	}

	public PatrimonioResponse(Patrimonio patrimonio) {
		this.id = patrimonio.getId();
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
		this.numeroTombamento = patrimonio.getNumeroTombamento();
		this.marca = new MarcaResponse(patrimonio.getMarca());
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

	public static PatrimonioResponse converter(Patrimonio patrimonio) {
		return new PatrimonioResponse(patrimonio);
	}

}
