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

import br.com.desafio.controller.dto.PatrimonioDto;
import br.com.desafio.controller.request.PatrimonioRequest;
import br.com.desafio.controller.response.PatrimonioResponse;
import br.com.desafio.model.Patrimonio;
import br.com.desafio.service.PatrimonioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/patrimonios", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "PATRIMONIOS" })
@RestController
@RequestMapping("/patrimonios")
public class PatrimonioController {
	
	@Autowired
	private PatrimonioService service;
	
	@ApiOperation(value = "Listagem de Patrimonios", notes = "Recurso para listagem de todas os patrimônios cadastrados", response = PatrimonioDto.class, nickname = "listaPatrimonios")
	@GetMapping
	public List<PatrimonioDto> lista(String nomePatrimonio) {
		if (nomePatrimonio == null) {
			List<Patrimonio> patrimonios = service.findAll();
			return PatrimonioDto.converter(patrimonios);
		} else {
			List<Patrimonio> patrimonios = service.findByNomeContains(nomePatrimonio);
			return PatrimonioDto.converter(patrimonios);
		}
	}
	
	@ApiOperation(value = "Salvar Patrimônio", notes = "Recurso para criação de novos patrimônios", response = PatrimonioResponse.class, nickname = "salvarPatrimonios")
	@PostMapping
	@Transactional
	public ResponseEntity<PatrimonioResponse> cadastrar(@RequestBody @Valid PatrimonioRequest req) {
		Patrimonio patrimonio = req.converter();
		try {
			patrimonio = service.saveOrUpdate(patrimonio);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(PatrimonioResponse.converter(patrimonio));
	}
	
	@ApiOperation(value = "Consultar Patrimônio", notes = "Recurso para consulta de patrimônio", response = PatrimonioResponse.class, nickname = "consultarPatrimonio")
	@GetMapping("/{id}")
	public ResponseEntity<PatrimonioResponse> consultar(@PathVariable Long id) {
		Optional<Patrimonio> patrimonio = service.findById(id);
		if (patrimonio.isPresent()) {
			return ResponseEntity.ok(PatrimonioResponse.converter(patrimonio.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Alterar Patrimônio", notes = "Recurso para alteração de patrimônios existentes", response = PatrimonioResponse.class, nickname = "alterarPatrimonio")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PatrimonioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PatrimonioRequest req) {
		Optional<Patrimonio> patrimonio = service.findById(id);
		if (patrimonio.isPresent()) {
			Patrimonio alterar = req.converter();
			alterar.setId(id);
			try {
				alterar = service.saveOrUpdate(alterar);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(PatrimonioResponse.converter(alterar));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Remover Patrimônio", notes = "Recurso para remoção de patrimônios existentes", nickname = "removerPatrimonio")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Patrimonio> patrimonio = service.findById(id);
		if (patrimonio.isPresent()) {
			service.delete(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}