package br.com.desafio.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.desafio.model.Marca;
import br.com.desafio.repository.MarcaRepository;

/**
 * @author valbercarreiro
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
public class MarcaServiceTest {
	
	private static final Long ID_DEFAULT = 1L;
	
	private static final Long ID_INVALIDO = -1L;
	
	private static final String NOME_MARCA = "Marca Teste";
	
	private static final String PARTE_NOME_MARCA = "Marca";
	
	@InjectMocks
	private MarcaService serviceMock;
	
	@Mock
	private MarcaRepository repositorio;

	private Marca marca;
	
	private Optional<Marca> optional;
	
	private List<Marca> marcas;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(serviceMock, "repositorio", repositorio);
		
		this.marca = new Marca();
		this.marca.setId(1l);
		this.marca.setNome("Marca Teste");
		
		this.optional = Optional.of(this.marca);
		
		this.marcas = new ArrayList<Marca>();
		this.marcas.add(this.marca);
	}
	
	@Test(expected = Exception.class)
	public void saveMarcaSemNomeTest() throws Exception {
		this.marca.setNome(null);
		serviceMock.saveOrUpdate(this.marca);
	}
	
	@Test
	public void saveMarcaTest() throws Exception {
		when(this.repositorio.save(Mockito.any(Marca.class))).thenReturn(this.marca);
		Marca retorno = serviceMock.saveOrUpdate(this.marca);
		
		assertEquals(retorno.getId(), this.marca.getId());
		assertEquals(retorno.getNome(), this.marca.getNome());
	}
	
	@Test
	public void consultarMarcaSemRetornoTest() {
		this.optional = Optional.of(new Marca());
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Marca> retorno = serviceMock.findById(ID_INVALIDO);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), null);
	}
	
	@Test
	public void consultarMarcaTest() {
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Marca> retorno = serviceMock.findById(ID_DEFAULT);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), this.marca.getId());
	}
	
	@Test
	public void listarMarcaTest() {
		when(this.repositorio.findAll()).thenReturn(this.marcas);
		
		List<Marca> retorno = serviceMock.findAll();
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
	
	@Test
	public void consultaMarcaPorNome() {
		when(this.repositorio.findByNome(Mockito.anyString())).thenReturn(this.marca);
		
		Marca retorno = serviceMock.findByNome(NOME_MARCA);
		
		assertEquals(retorno.getId(), this.marca.getId());
		assertEquals(retorno.getNome(), this.marca.getNome());
	}
	
	@Test
	public void consultaMarcaParteNome() {
		when(this.repositorio.findByNomeContains(Mockito.anyString())).thenReturn(this.marcas);
		
		List<Marca> retorno = serviceMock.findByNomeContains(PARTE_NOME_MARCA);
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
}
