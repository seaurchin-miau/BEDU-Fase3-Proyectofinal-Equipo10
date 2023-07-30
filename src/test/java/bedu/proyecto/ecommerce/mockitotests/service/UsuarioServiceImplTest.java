package bedu.proyecto.ecommerce.mockitotests.service;

import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.repository.IUsuarioRepository;
import bedu.proyecto.ecommerce.service.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findById_ValidId_ReturnsUsuario() {
        // Arrange
        Integer userId = 1;
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = usuarioService.findById(userId);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(usuario, result.get());
        verify(usuarioRepository, times(1)).findById(userId);
    }

    @Test
    void save_ValidUsuario_ReturnsSavedUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario result = usuarioService.save(usuario);

        // Assert
        Assertions.assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void findByEmail_ValidEmail_ReturnsUsuario() {
        // Arrange
        String email = "test@ejemplo.com";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = usuarioService.findByEmail(email);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(usuario, result.get());
        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    void findAll_ReturnsListOfUsuarios() {
        // Arrange
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = usuarioService.findAll();

        // Assert
        Assertions.assertEquals(usuarios, result);
        verify(usuarioRepository, times(1)).findAll();
    }
}
