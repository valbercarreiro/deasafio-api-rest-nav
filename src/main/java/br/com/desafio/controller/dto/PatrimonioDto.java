package br.com.desafio.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.desafio.model.Patrimonio;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
public class PatrimonioDto implements Serializable {

	private static final long serialVersionUID = 5106003168680476447L;
	
	private Long id;
	private String nome;
	private String descricao;
	private String numeroTombamento;
	private MarcaDto marca;
	
	public PatrimonioDto(Long id, String nome, String descricao, String numeroTombamento, MarcaDto marca) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.numeroTombamento = numeroTombamento;
		this.marca = marca;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroTombamento() {
		return numeroTombamento;
	}

	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}

	public MarcaDto getMarca() {
		return marca;
	}

	public void setMarca(MarcaDto marca) {
		this.marca = marca;
	}

	public static List<PatrimonioDto> converter(List<Patrimonio> patrimonios) {
		List<PatrimonioDto> retorno = new ArrayList<PatrimonioDto>();
		patrimonios.stream().forEach(patrim -> {
			PatrimonioDto dto = setaCampos(patrim);
			retorno.add(dto);
		});
		return retorno;
	}
	
	private static PatrimonioDto setaCampos(Patrimonio patrimonio) {
		return new PatrimonioDto(patrimonio.getId(), 
									patrimonio.getNome(), 
									patrimonio.getDescricao(), 
									patrimonio.getNumeroTombamento(), 
									new MarcaDto(patrimonio.getMarca()));
	}

}
