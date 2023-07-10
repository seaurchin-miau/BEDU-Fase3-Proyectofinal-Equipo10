package bedu.proyecto.ecommerce.mockitotests.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import bedu.proyecto.ecommerce.controller.ProductoController;
import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import bedu.proyecto.ecommerce.service.ProductoService;
import bedu.proyecto.ecommerce.service.UploadFileService;

public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private UploadFileService uploadFileService;

    @InjectMocks
    private ProductoController productoController;

    private MockMvc mockMvc;

    @Test
    public void testShow() throws Exception {
        // Arrange
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Product 1", "Description 1", "image1.jpg", 10.0, 5, new Usuario()));
        productos.add(new Producto(2, "Product 2", "Description 2", "image2.jpg", 20.0, 3, new Usuario()));
        when(productoService.findAll()).thenReturn(productos);

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/show"))
                .andExpect(model().attributeExists("productos"));
    }

    @Test
    public void testCreate() throws Exception {
        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();

        // Act & Assert
        mockMvc.perform(get("/productos/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/create"));
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        Producto producto = new Producto();
        MultipartFile file = mock(MultipartFile.class);
        when(usuarioService.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(uploadFileService.saveImage(file)).thenReturn("image.jpg");

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        mockMvc.perform(post("/productos/save")
                        .flashAttr("producto", producto)
                        .param("img", "dummy-file"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productos"));

        verify(productoService).save(producto);
    }

    @Test
    public void testEdit() throws Exception {
        // Arrange
        Integer productId = 1;
        Producto producto = new Producto(1, "Product 1", "Description 1", "image1.jpg", 10.0, 5, new Usuario());
        when(productoService.get(productId)).thenReturn(Optional.of(producto));

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        mockMvc.perform(get("/productos/edit/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/edit"))
                .andExpect(model().attributeExists("producto"));
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        Producto existingProducto = new Producto(1, "Product 1", "Description 1", "image1.jpg", 10.0, 5, new Usuario());
        Producto updatedProducto = new Producto(1, "Updated Product", "Updated Description", "updated-image.jpg", 15.0, 3, new Usuario());
        MultipartFile file = mock(MultipartFile.class);
        when(productoService.get(anyInt())).thenReturn(Optional.of(existingProducto));
        when(uploadFileService.saveImage(file)).thenReturn("updated-image.jpg");

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        mockMvc.perform(post("/productos/update")
                        .flashAttr("producto", updatedProducto)
                        .param("img", "dummy-file"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productos"));

        verify(productoService).update(updatedProducto);
        verify(uploadFileService).deleteImage(existingProducto.getImagen());
    }

    @Test
    public void testDelete() throws Exception {
        // Arrange
        Integer productId = 1;
        Producto producto = new Producto(1, "Product 1", "Description 1", "image1.jpg", 10.0, 5, new Usuario());
        when(productoService.get(productId)).thenReturn(Optional.of(producto));

        // Act & Assert
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        mockMvc.perform(get("/productos/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/productos"));

        verify(uploadFileService).deleteImage(producto.getImagen());
        verify(productoService).delete(productId);
    }
}
