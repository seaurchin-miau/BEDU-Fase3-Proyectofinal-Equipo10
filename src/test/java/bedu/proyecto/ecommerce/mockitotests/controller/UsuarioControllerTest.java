package bedu.proyecto.ecommerce.mockitotests.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import bedu.proyecto.ecommerce.controller.UsuarioController;
import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.service.IOrdenService;
import bedu.proyecto.ecommerce.service.IUsuarioService;

public class UsuarioControllerTest {

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IOrdenService ordenService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @Test
    public void testCreate() throws Exception {
        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        // Act & Assert
        mockMvc.perform(get("/usuario/registro"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/registro"));
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(post("/usuario/save")
                        .flashAttr("usuario", usuario))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(usuarioService).save(usuario);
    }

    @Test
    public void testLogin() throws Exception {
        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        // Act & Assert
        mockMvc.perform(get("/usuario/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/login"));
    }

    @Test
    public void testAcceder() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");
        HttpSession session = mock(HttpSession.class);
        when(usuarioService.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(post("/usuario/acceder")
                        .flashAttr("usuario", usuario)
                        .sessionAttr("idusuario", session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(session).setAttribute("idusuario", usuario.getId());
    }

    @Test
    public void testObtenerCompras() throws Exception {
        // Arrange
        Integer userId = 1;
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("idusuario")).thenReturn(userId);
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        List<Orden> ordenes = new ArrayList<>();
        ordenes.add(new Orden());
        ordenes.add(new Orden());
        when(usuarioService.findById(userId)).thenReturn(Optional.of(usuario));
        when(ordenService.findByUsuario(usuario)).thenReturn(ordenes);

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(get("/usuario/compras")
                        .sessionAttr("idusuario", session))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/compras"))
                .andExpect(model().attributeExists("sesion", "ordenes"));
    }

    @Test
    public void testDetalleCompra() throws Exception {
        // Arrange
        Integer orderId = 1;
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("idusuario")).thenReturn(1);
        Orden orden = new Orden();
        orden.setId(orderId);
        when(ordenService.findById(orderId)).thenReturn(Optional.of(orden));

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(get("/usuario/detalle/{id}", orderId)
                        .sessionAttr("idusuario", session))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario/detallecompra"))
                .andExpect(model().attributeExists("detalles", "sesion"));
    }

    @Test
    public void testCerrarSesion() throws Exception {
        // Arrange
        HttpSession session = mock(HttpSession.class);

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(get("/usuario/cerrar")
                        .sessionAttr("idusuario", session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(session).removeAttribute("idusuario");
    }
}
