package br.com.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa o response das chamadas ao endpoint de listar usuários")
public class UsuarioDto {

	@ApiModelProperty(value = "Identificador do usuário", name = "Identificador usuário", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Nome do usuário", name = "Nome usuário", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "E-mail do usuário", name = "E-mail usuário", position = 3)
	private String email;
	
	@ApiModelProperty(value = "Senha do usuário", name = "Senha usuário", position = 4)
	private String senha;
	
	public UsuarioDto(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.senha = user.getSenha();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

}
