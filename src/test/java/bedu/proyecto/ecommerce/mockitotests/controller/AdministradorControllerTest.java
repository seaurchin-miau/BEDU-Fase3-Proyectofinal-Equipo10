package bedu.proyecto.ecommerce.mockitotests.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bedu.proyecto.ecommerce.controller.AdministradorController;
import bedu.proyecto.ecommerce.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import bedu.proyecto.ecommerce.model.DetalleOrden;
import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.service.IOrdenService;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import bedu.proyecto.ecommerce.service.ProductoService;

public class AdministradorControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IOrdenService ordensService;

    @InjectMocks
    private AdministradorController administradorController;

    private MockMvc mockMvc;

    public AdministradorControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(administradorController).build();
    }

    @Test
    void testHome() throws Exception {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Product 1", "Description 1", "Categoria 1", "image1.jpg", 10.0, 5, new Usuario()));
        productos.add(new Producto(2, "Product 2", "Description 2", "Categoria 1", "image2.jpg", 20.0, 3, new Usuario()));

        when(productoService.findAll()).thenReturn(productos);

        mockMvc.perform(get("/administrador"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("productos"))
                .andExpect(view().name("administrador/home"));

        verify(productoService).findAll();
    }

    @Test
    void testUsuarios() throws Exception {
        mockMvc.perform(get("/administrador/usuarios"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("usuarios"))
                .andExpect(view().name("administrador/usuarios"));

        verify(usuarioService).findAll();
    }

    @Test
    void testOrdenes() throws Exception {
        mockMvc.perform(get("/administrador/ordenes"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ordenes"))
                .andExpect(view().name("administrador/ordenes"));

        verify(ordensService).findAll();
    }

    @Test
    void testDetalle() throws Exception {
        int orderId = 1;
        Orden orden = new Orden();
        orden.setId(orderId);
        List<DetalleOrden> detalles = new ArrayList<>();
        detalles.add(new DetalleOrden(1, "Product 1", 5, 10.0, 50.0));
        detalles.add(new DetalleOrden(2, "Product 2", 3, 20.0, 60.0));
        orden.setDetalle(detalles);

        when(ordensService.findById(orderId)).thenReturn(Optional.of(orden));

        mockMvc.perform(get("/administrador/detalle/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("detalles"))
                .andExpect(view().name("administrador/detalleorden"));

        verify(ordensService).findById(orderId);
    }
}
