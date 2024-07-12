package service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.entities.enums.TipoUsuario;
import com.estagio.Api_Banco.repositories.UsuarioRepository;
import com.estagio.Api_Banco.services.UsuarioService;
import com.estagio.Api_Banco.services.exceptions.DatabaseException;
import com.estagio.Api_Banco.services.exceptions.ResourceNotFound;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

	@Mock
	private PasswordEncoder encoder;

	@InjectMocks
	private UsuarioService service;
	
	@Captor
    private ArgumentCaptor<Specification<Usuario>> specificationCaptor;

    @Test
    void findAll_Sucesso() {
        Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546", TipoUsuario.COMUM);

        List<Usuario> listaUsers = Arrays.asList(user);

        when(repository.findAll(any(Specification.class))).thenReturn(listaUsers);

        List<Usuario> retorno = service.findAll(TipoUsuario.COMUM);

        assertNotNull(retorno);
        assertEquals(listaUsers, retorno);
    }

	@Test
	void findById_Sucesso() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.findById(anyLong())).thenReturn(Optional.of(user));

		Usuario retorno = service.findById(1L);

		assertNotNull(retorno);
		assertEquals(user, retorno);
	}

	@Test
	void insert_Sucesso() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.save(any())).thenReturn(user);
		when(encoder.encode("batata")).thenReturn("senhaCodificada");

		service.insert(user);

		verify(encoder).encode("batata");

		assertEquals("senhaCodificada", user.getSenha());
	}

	@Test
	void insert_ErroEmail() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.existsByEmail("paulo@gmail.com")).thenReturn(true);

		DatabaseException exception = assertThrows(DatabaseException.class, () -> {
			service.insert(user);
		});

		assertEquals("Email já cadastrado", exception.getMessage());
	}

	@Test
	void insert_ErroCPF() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.existsByEmail("paulo@gmail.com")).thenReturn(true);

		DatabaseException exception = assertThrows(DatabaseException.class, () -> {
			service.insert(user);
		});

		assertEquals("Email já cadastrado", exception.getMessage());
	}

	@Test
	void delete_UsuarioEncontrado() {

		Usuario user = new Usuario("paulo@gmail.com", "BATATA", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.findById(1L)).thenReturn(Optional.of(user));

		assertDoesNotThrow(() -> service.delete(1L));

		verify(repository).deleteById(1L);
	}

	@Test
	void delete_UsuarioNaoEncontrado() {
		Long id = 1L;

		when(repository.findById(id)).thenReturn(java.util.Optional.empty());

		ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
			service.delete(id);
		});

		assertEquals("Resource not found. Id " + id, exception.getMessage());
	}

	@Test
	void delete_ViolacaoIntegridadeDados() {
		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);
		when(repository.findById(anyLong())).thenReturn(Optional.of(user));

		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(1L);

		assertThrows(DatabaseException.class, () -> service.delete(1L));

	}

	@Test
	void update_Sucesso() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		when(repository.save(any())).thenReturn(user);
		when(repository.getReferenceById(1L)).thenReturn(user);
		when(repository.findById(anyLong())).thenReturn(Optional.of(user));

		service.insert(user);

		Usuario userNovo = new Usuario("Artur@gmail.com", "batata21", "Artur Muniz", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		service.update(1L, userNovo);

		assertEquals(user.getNomeCompleto(), userNovo.getNomeCompleto());
	}

	@Test
	void update_CamposVazios() {

		Usuario user = new Usuario("paulo@gmail.com", "batata", "Paulo Silva", "8196962285", "14389790546",
				TipoUsuario.COMUM);

		Usuario copia = user;

		when(repository.save(any())).thenReturn(user);
		when(repository.getReferenceById(1L)).thenReturn(user);
		when(repository.findById(anyLong())).thenReturn(Optional.of(user));

		service.insert(user);

		Usuario userNovo = new Usuario(null, null, null, null, null, null);

		service.update(1L, userNovo);
		assertNotEquals(user.getCpf(), userNovo.getCpf());
	}

}
