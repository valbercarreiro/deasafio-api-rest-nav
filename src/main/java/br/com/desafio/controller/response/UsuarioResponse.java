package br.com.desafio.controller.response;

import br.com.desafio.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa a resposta das chamadas aos endpoints de usuário")
public class UsuarioResponse {

	@ApiModelProperty(value = "Identificador do usuário", name = "Identificador usuário", position = 1)
	private Long id;

	@ApiModelProperty(value = "Nome do usuário", name = "Nome usuário", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "E-mail do usuário", name = "E-mail usuário", position = 3)
	private String email;
	
	public UsuarioResponse(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioResponse(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
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

	public static UsuarioResponse converter(Usuario user) {
		return new UsuarioResponse(user.getId(), user.getNome(), user.getEmail());
	}

}
