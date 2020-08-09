package br.com.desafio.controller.request;

import javax.validation.constraints.NotNull;

import br.com.desafio.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a request das chamadas aos endpoints de usuário")
public class UsuarioRequest {

	@ApiModelProperty(value = "Identificador do usuário", name = "Identificador usuário", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Nome do usuário", name = "Nome usuário", position = 2)
	@NotNull
	private String nome;
	
	@ApiModelProperty(value = "E-mail do usuário", name = "E-mail usuário", position = 3)
	@NotNull
	private String email;
	
	@ApiModelProperty(value = "Senha do usuário", name = "Senha usuário", position = 4)
	@NotNull
	private String senha;
	
	public UsuarioRequest() {
	}
	
	public UsuarioRequest(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioRequest(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioRequest(Usuario user) {
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

	public Usuario converter() {
		return new Usuario(null, nome, email, senha);
	}

}
