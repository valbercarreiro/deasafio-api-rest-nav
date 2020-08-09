/**
 * 
 */
package br.com.desafio.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Usuario;
import br.com.desafio.repository.UsuarioRepository;

/**
 * @author valbercarreiro
 *
 */

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;
	
	public Usuario saveOrUpdate(Usuario usuario) throws Exception {
		
		if(Objects.isNull(usuario.getNome())) {
			throw new Exception("Campo Nome obrigatório");
		}
		
		if(Objects.isNull(usuario.getEmail())) {
			throw new Exception("Campo E-mail obrigatório");
		}
		
		if(Objects.isNull(usuario.getSenha())) {
			throw new Exception("Campo Senha obrigatório");
		}
		
		Usuario userAux = repositorio.findByEmail(usuario.getEmail());
		
		if((Objects.nonNull(userAux) && Objects.nonNull(userAux.getId()) && usuario.getId() == null) || 
				(Objects.nonNull(userAux) && Objects.nonNull(userAux.getId()) && usuario.getId() != null && usuario.getId() != userAux.getId())) {
			
			throw new Exception("Usuário já cadastrado");
			
		}
		return repositorio.save(usuario);
	}
	
	public Optional<Usuario> findById(Long id) {
		return repositorio.findById(id);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Usuario> findAll() {
		return repositorio.findAll();
	}
	
	public List<Usuario> findByNomeContains(String nome) {
		return repositorio.findByNomeContains(nome);
	}
}
