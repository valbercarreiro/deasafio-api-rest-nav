package br.com.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Patrimonio;

public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
	
	Patrimonio findByNome(String nomePatrimonio);

	List<Patrimonio> findByMarcaNome(String nomeMarca);
	
	List<Patrimonio> findByNomeContains(String nomePatrimonio);

}
