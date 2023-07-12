package bedu.proyecto.ecommerce.mockitotests.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.controller.UsuarioController;
import bedu.proyecto.ecommerce.service.IOrdenService;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UsuarioControllerTest {

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IOrdenService ordenService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setTipo("USER");
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        String result = usuarioController.save(usuario);

        // Assert
        verify(usuarioService, times(1)).save(usuario);
        verify(usuario).setPassword(encodedPassword);
        assert result.equals("redirect:/");
    }

    @Test
    void testAcceder_WithExistingUser() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        Optional<Usuario> userOptional = Optional.of(usuario);

        // Mocking behavior
        when(usuarioService.findByEmail(eq(usuario.getEmail()))).thenReturn(userOptional);
        when(session.getAttribute("idusuario")).thenReturn(1);

        // Act
        String result = usuarioController.acceder(usuario, session);

        // Assert
        verify(usuarioService, times(1)).findByEmail(eq(usuario.getEmail()));
        verify(session, times(1)).setAttribute(eq("idusuario"), eq(1));
        assert result.equals("redirect:/");
    }

    @Test
    void testAcceder_WithNonExistingUser() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        Optional<Usuario> userOptional = Optional.empty();

        // Mocking behavior
        when(usuarioService.findByEmail(eq(usuario.getEmail()))).thenReturn(userOptional);

        // Act
        String result = usuarioController.acceder(usuario, session);

        // Assert
        verify(usuarioService, times(1)).findByEmail(eq(usuario.getEmail()));
        assert result.equals("redirect:/");
    }

    @Test
    void testObtenerCompras() {
        // Arrange
        int userId = 1;
        Usuario usuario = new Usuario();
        usuario.setId(userId);

        List<Orden> ordenes = new ArrayList<>();
        // Add some orders to the list

        // Mocking behavior
        when(session.getAttribute("idusuario")).thenReturn(userId);
        when(usuarioService.findById(userId)).thenReturn(Optional.of(usuario));
        when(ordenService.findByUsuario(usuario)).thenReturn(ordenes);

        // Act
        String result = usuarioController.obtenerCompras(model, session);

        // Assert
        verify(session, times(2)).getAttribute("idusuario"); // Update the expected invocation count to 2
        verify(usuarioService, times(1)).findById(userId);
        verify(ordenService, times(1)).findByUsuario(usuario);
        verify(model, times(1)).addAttribute(eq("ordenes"), eq(ordenes));
        assert result.equals("usuario/compras");
    }

    @Test
    void testDetalleCompra() {
        // Arrange
        int orderId = 1;
        Orden orden = new Orden();

        // Mocking behavior
        when(ordenService.findById(orderId)).thenReturn(Optional.of(orden));
        when(session.getAttribute("idusuario")).thenReturn(1);

        // Act
        String result = usuarioController.detalleCompra(orderId, session, model);

        // Assert
        verify(ordenService, times(1)).findById(orderId);
        verify(model, times(1)).addAttribute(eq("detalles"), eq(orden.getDetalle()));
        verify(session, times(1)).getAttribute("idusuario");
        assert result.equals("usuario/detalledecompra");
    }

    @Test
    void testCerrarSesion() {
        // Act
        String result = usuarioController.cerrarSesion(session);

        // Assert
        verify(session, times(1)).removeAttribute("idusuario");
        assert result.equals("redirect:/");
    }
}
