/**
 * 
 */
package br.com.desafio.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Marca;
import br.com.desafio.repository.MarcaRepository;

/**
 * @author valbercarreiro
 *
 */

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repositorio;
	
	public Marca saveOrUpdate(Marca marca) throws Exception {
		
		if(Objects.isNull(marca.getNome())) {
			throw new Exception("Campo Nome obrigatório");
		}
		
		Marca marcaAux = findByNome(marca.getNome());
		if((Objects.nonNull(marcaAux) && Objects.nonNull(marcaAux.getId()) && marca.getId() == null) ||
				(Objects.nonNull(marcaAux) && Objects.nonNull(marcaAux.getId()) && marca.getId() != null && marca.getId() != marcaAux.getId())) {
			throw new Exception("Marca já existente");
		}
		return repositorio.save(marca);
	}
	
	public Optional<Marca> findById(Long id) {
		return repositorio.findById(id);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Marca> findAll() {
		return repositorio.findAll();
	}
	
	public Marca findByNome(String nome) {
		return repositorio.findByNome(nome);
	}
	
	public List<Marca> findByNomeContains(String nome) {
		return repositorio.findByNomeContains(nome);
	}
}
