/**
 * 
 */
package br.com.desafio.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Marca;
import br.com.desafio.model.Patrimonio;
import br.com.desafio.repository.PatrimonioRepository;

/**
 * @author valbercarreiro
 *
 */

@Service
public class PatrimonioService {
	
	private static final Integer TAMANHO_TOMBAMENTO = 35;
	
	@Autowired
	private PatrimonioRepository repositorio;
	
	@Autowired
	private MarcaService marcaService;
	
	public Patrimonio saveOrUpdate(Patrimonio patrimonio) throws Exception {
		
		if(Objects.isNull(patrimonio.getNome())) {
			throw new Exception("Campo Nome obrigatório");
		}
		
		if(Objects.isNull(patrimonio.getMarca()) || Objects.isNull(patrimonio.getMarca().getId())) {
			throw new Exception("Campo Marca obrigatório");
		}
		
		Optional<Marca> optMarca = marcaService.findById(patrimonio.getMarca().getId());
		if(!optMarca.isPresent()) {
			throw new Exception("Marca não encontrada");
		}
		patrimonio.setMarca(optMarca.get());
		if(Objects.isNull(patrimonio.getId())) {
			patrimonio.setNumeroTombamento(generateTombamento(TAMANHO_TOMBAMENTO));
		}
		return repositorio.save(patrimonio);
	}
	
	public Optional<Patrimonio> findById(Long id) {
		return repositorio.findById(id);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
	}
	
	public List<Patrimonio> findAll() {
		return repositorio.findAll();
	}
	
	public List<Patrimonio> findByMarcaNome(String nomeMarca) {
		return repositorio.findByMarcaNome(nomeMarca);
	}
	
	public Patrimonio findByNome(String nomePatrimonio) {
		return repositorio.findByNome(nomePatrimonio);
	}

	private String generateTombamento(Integer tamanho) {
		return RandomStringUtils.randomAlphanumeric(tamanho);
	}
	
	public List<Patrimonio> findByNomeContains(String nomePatrimonio) {
		return repositorio.findByNomeContains(nomePatrimonio);
	}
}
