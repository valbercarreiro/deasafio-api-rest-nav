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

import br.com.desafio.model.Usuario;
import br.com.desafio.repository.UsuarioRepository;

/**
 * @author valbercarreiro
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
public class UsuarioServiceTest {
	
	private static final Long ID_DEFAULT = 1L;
	
	private static final Long ID_INVALIDO = -1L;
	
	private static final String NOME_USUARIO = "User Teste";
	
	private static final String PARTE_NOME_USUARIO = "User";
	
	@InjectMocks
	private UsuarioService serviceMock;
	
	@Mock
	private UsuarioRepository repositorio;

	private Usuario usuario;
	
	private Optional<Usuario> optional;
	
	private List<Usuario> usuarios;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(serviceMock, "repositorio", repositorio);
		
		this.usuario = new Usuario();
		this.usuario.setId(1l);
		this.usuario.setNome(NOME_USUARIO);
		this.usuario.setEmail("email@usuario.com.br");
		this.usuario.setSenha("1234");
		
		this.optional = Optional.of(this.usuario);
		
		this.usuarios = new ArrayList<Usuario>();
		this.usuarios.add(this.usuario);
	}
	
	@Test(expected = Exception.class)
	public void saveUsuarioSemNomeTest() throws Exception {
		this.usuario.setNome(null);
		serviceMock.saveOrUpdate(this.usuario);
	}
	
	@Test(expected = Exception.class)
	public void saveUsuarioSemEmailTest() throws Exception {
		this.usuario.setEmail(null);
		serviceMock.saveOrUpdate(this.usuario);
	}
	
	@Test(expected = Exception.class)
	public void saveUsuarioSemSenhaTest() throws Exception {
		this.usuario.setSenha(null);;
		serviceMock.saveOrUpdate(this.usuario);
	}
	
	@Test
	public void saveUsuarioTest() throws Exception {
		when(this.repositorio.save(Mockito.any(Usuario.class))).thenReturn(this.usuario);
		Usuario retorno = serviceMock.saveOrUpdate(this.usuario);
		
		assertEquals(retorno.getId(), this.usuario.getId());
		assertEquals(retorno.getNome(), this.usuario.getNome());
		assertEquals(retorno.getEmail(), this.usuario.getEmail());
	}
	
	@Test
	public void consultarUsuarioSemRetornoTest() {
		this.optional = Optional.of(new Usuario());
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Usuario> retorno = serviceMock.findById(ID_INVALIDO);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), null);
	}
	
	@Test
	public void consultarUsuarioTest() {
		when(this.repositorio.findById(Mockito.anyLong())).thenReturn(this.optional);
		
		Optional<Usuario> retorno = serviceMock.findById(ID_DEFAULT);
		
		assertEquals(retorno.isPresent(), true);
		assertEquals(retorno.get().getId(), this.usuario.getId());
	}
	
	@Test
	public void listarUsuarioTest() {
		when(this.repositorio.findAll()).thenReturn(this.usuarios);
		
		List<Usuario> retorno = serviceMock.findAll();
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
	
	
	@Test
	public void consultaUsuarioParteNome() {
		when(this.repositorio.findByNomeContains(Mockito.anyString())).thenReturn(this.usuarios);
		
		List<Usuario> retorno = serviceMock.findByNomeContains(PARTE_NOME_USUARIO);
		
		assertEquals(retorno.isEmpty(), false);
		assertEquals(retorno.size(), ID_DEFAULT.intValue());
	}
}
