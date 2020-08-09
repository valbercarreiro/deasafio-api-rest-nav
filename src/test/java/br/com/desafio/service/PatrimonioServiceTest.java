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
import br.com.desafio.model.Patrimonio;
import br.com.desafio.repository.PatrimonioRepository;

/**
 * @author valbercarreiro
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
public class PatrimonioServiceTest {
	
	private static final Long ID_DEFAULT = 1L;
	
	private static final Long ID_INVALIDO = -1L;
	
	private static final String NOME_PATRIMONIO = "Patrimônio Teste";
	
	private static final String PARTE_NOME_PATRIMONIO = "Patrimônio";
	
	@InjectMocks
	private PatrimonioService serviceMock;
	
	@Mock
	private MarcaService marcaServiceMock;
	
	@Mock
	private PatrimonioRepository repositorio;

	private Patrimonio patrimonio;
	
	private Optional<Patrimonio> optional;
	
	private List<Patrimonio> patrimonios;
	
	private Marca marca;
	
	private Optional<Marca> optMarca;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(serviceMock, "repositorio", repositorio);
		
		this.marca = new Marca();
		this.marca.setId(1l);
		this.marca.setNome("Marca Teste");
		this.optMarca = Optional.of(this.marca);
		
		this.patrimonio = new Patrimonio();
		this.patrimonio.setId(1l);
		this.patrimonio.setNome(NOME_PATRIMONIO);
		this.patrimonio.setDescricao("Descrição Patrimônio");
		this.patrimonio.setNumeroTombamento("1234asd21312dasda");
		this.patrimonio.setMarca(marca);
		
		this.optional = Optional.of(this.patrimonio);
		
		this.patrimonios = new ArrayList<Patrimonio>();
		this.patrimonios.add(this.patrimonio);
	}
	
	@Test(expected = Exception.class)
	public void savePatrimonioSemNomeTest() throws Exception {
		this.patrimonio.setNome(null);
		serviceMock.saveOrUpdate(this.patrimonio);
	}
	
	@Test(expected = Exception.class)
	public void savePatrimonioSemMarcaTest() throws Exception {
		this.patrimonio.setMarca(null);
		serviceMock.saveOrUpdate(this.patrimonio);
	}
	
	@Test
	public void savePatrimonioTest() throws Exception {
		when(this.repositorio.save(Mockito.any(Patrimonio.class))).thenReturn(this.patrimonio);
		when(this.marcaServiceMock.findById(Mockito.anyLong())).thenReturn(this.optMarca);
		Patrimonio retorno = serviceMock.saveOrUpdate(this.patrimonio);
		
		assertEquals(retorno.getId(), this.patrimonio.getId());
		assertEquals(retorno.getNome(), this.patrimonio.getNome());
		assertEquals(retorno.getDescricao(), this.patrimonio.getDescricao());
	}
	
	@Test
	public void consultarPatrimonioSemRetornoTest() {
		this.optional = Optional.of(new Patrimonio());
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Patrimonio> retorno = serviceMock.findById(ID_INVALIDO);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), null);
	}
	
	@Test
	public void consultarPatrimonioTest() {
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Patrimonio> retorno = serviceMock.findById(ID_DEFAULT);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), this.patrimonio.getId());
	}
	
	@Test
	public void listarPatrimonioTest() {
		when(this.repositorio.findAll()).thenReturn(this.patrimonios);
		
		List<Patrimonio> retorno = serviceMock.findAll();
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
	
	
	@Test
	public void consultaPatrimonioParteNome() {
		when(this.repositorio.findByNomeContains(Mockito.anyString())).thenReturn(this.patrimonios);
		
		List<Patrimonio> retorno = serviceMock.findByNomeContains(PARTE_NOME_PATRIMONIO);
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
}
