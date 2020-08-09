package br.com.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.model.Marca;

public class MarcaDto {

	private Long id;
	private String nome;
	
	public MarcaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public MarcaDto(Marca marca) {
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

	public static List<MarcaDto> converter(List<Marca> marcas) {
		return marcas.stream().map(MarcaDto::new).collect(Collectors.toList());
	}

}
