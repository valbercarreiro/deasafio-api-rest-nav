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

import br.com.desafio.controller.dto.MarcaDto;
import br.com.desafio.controller.request.MarcaRequest;
import br.com.desafio.controller.response.MarcaResponse;
import br.com.desafio.model.Marca;
import br.com.desafio.service.MarcaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/marcas", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "MARCAS" })
@RestController
@RequestMapping("/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	@ApiOperation(value = "Listagem de Marcas", notes = "Recurso para listagem de todas as marcas cadastradas", response = MarcaDto.class, nickname = "listaMarcas")
	@GetMapping
	public List<MarcaDto> lista(String nomeMarca) {
		if (nomeMarca == null) {
			List<Marca> marcas = service.findAll();
			return MarcaDto.converter(marcas);
		} else {
			List<Marca> marcas = service.findByNomeContains(nomeMarca);
			return MarcaDto.converter(marcas);
		}
	}
	
	@ApiOperation(value = "Salvar Marca", notes = "Recurso para criação de novas marcas", response = MarcaResponse.class, nickname = "salvarMarcas")
	@PostMapping
	@Transactional
	public ResponseEntity<MarcaResponse> cadastrar(@RequestBody @Valid MarcaRequest req) {
		Marca marca = req.converter();
		try {
			marca = service.saveOrUpdate(marca);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(MarcaResponse.converter(marca));
	}
	
	@ApiOperation(value = "Consultar Marcas", notes = "Recurso para consulta de marcas", response = MarcaResponse.class, nickname = "consultarMarcas")
	@GetMapping("/{id}")
	public ResponseEntity<MarcaResponse> consultar(@PathVariable Long id) {
		Optional<Marca> marca = service.findById(id);
		if (marca.isPresent()) {
			return ResponseEntity.ok(MarcaResponse.converter(marca.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Alterar Marcas", notes = "Recurso para alteração de marcas existentes", response = MarcaResponse.class, nickname = "alterarMarcas")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MarcaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid MarcaRequest req) {
		Optional<Marca> marca = service.findById(id);
		if (marca.isPresent()) {
			Marca alterar = req.converter();
			alterar.setId(id);
			try {
				alterar = service.saveOrUpdate(alterar);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(MarcaResponse.converter(alterar));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Remover Marcas", notes = "Recurso para remoção de marcas existentes", nickname = "removerMarcas")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Marca> marca = service.findById(id);
		if (marca.isPresent()) {
			service.delete(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}