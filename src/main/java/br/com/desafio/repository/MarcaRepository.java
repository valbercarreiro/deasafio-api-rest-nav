package br.com.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
	
	Marca findByNome(String nomeMarca);

	List<Marca> findByNomeContains(String nome);
}
