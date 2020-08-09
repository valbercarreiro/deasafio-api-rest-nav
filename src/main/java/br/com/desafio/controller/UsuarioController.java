package br.com.desafio.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import br.com.desafio.controller.dto.UsuarioDto;
import br.com.desafio.controller.request.UsuarioRequest;
import br.com.desafio.controller.response.UsuarioResponse;
import br.com.desafio.model.Usuario;

@Api(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "USUARIOS" })
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@ApiOperation(value = "Listagem de Usuários", notes = "Recurso para listagem de todos os usuários cadastrados", response = UsuarioDto.class, nickname = "listaUsuarios")
	@GetMapping
	public List<UsuarioDto> lista(String nomeUsuario) {
		if (nomeUsuario == null) {
			List<Usuario> usuarios = service.findAll();
			return UsuarioDto.converter(usuarios);
		} else {
			List<Usuario> usuarios = service.findByNomeContains(nomeUsuario);
			return UsuarioDto.converter(usuarios);
		}
	}
	
	@ApiOperation(value = "Salvar Usuários", notes = "Recurso para criação de novos usuários", response = UsuarioResponse.class, nickname = "salvarUsuarios")
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest userReq) {
		Usuario user = userReq.converter();
		try {
			user = service.saveOrUpdate(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(UsuarioResponse.converter(user));
	}
	
	@ApiOperation(value = "Consultar Usuários", notes = "Recurso para consulta a usuários", response = UsuarioResponse.class, nickname = "consultarUsuarios")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> consultar(@PathVariable Long id) {
		Optional<Usuario> user = service.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(UsuarioResponse.converter(user.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Alterar Usuários", notes = "Recurso para alteração de usuários existentes", response = UsuarioResponse.class, nickname = "alterarUsuarios")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequest req) {
		Optional<Usuario> user = service.findById(id);
		if (user.isPresent()) {
			Usuario alterar = req.converter();
			alterar.setId(id);
			try {
				alterar = service.saveOrUpdate(alterar);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(UsuarioResponse.converter(alterar));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Remover Usuários", notes = "Recurso para remoção de usuários existentes", nickname = "removerUsuarios")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Usuario> user = service.findById(id);
		if (user.isPresent()) {
			service.delete(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}