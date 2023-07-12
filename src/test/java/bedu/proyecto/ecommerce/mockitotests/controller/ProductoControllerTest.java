package bedu.proyecto.ecommerce.mockitotests.controller;

import bedu.proyecto.ecommerce.controller.ProductoController;
import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import bedu.proyecto.ecommerce.service.ProductoService;
import bedu.proyecto.ecommerce.util.UploadFileService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private UploadFileService upload;

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShow() {
        Model model = Mockito.mock(Model.class);
        List<Producto> productos = new ArrayList<>();
        Mockito.when(productoService.findAll()).thenReturn(productos);

        String result = productoController.show(model);

        assertEquals("productos/show", result);
        Mockito.verify(model).addAttribute("productos", productos);
    }

    @Test
    void testCreate() {
        String result = productoController.create();

        assertEquals("productos/create", result);
    }

    @Test
    void testSave() throws IOException {
        HttpSession session = Mockito.mock(HttpSession.class);
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Usuario usuario = Mockito.mock(Usuario.class);
        Mockito.when(session.getAttribute("idusuario")).thenReturn("1");
        Mockito.when(usuarioService.findById(1)).thenReturn(Optional.of(usuario));
        Mockito.when(producto.getId()).thenReturn(null);
        Mockito.when(upload.saveImage(file)).thenReturn("image.jpg");

        String result = productoController.save(producto, file, session);

        assertEquals("redirect:/productos", result);
        Mockito.verify(producto).setUsuario(usuario);
        Mockito.verify(producto).setImagen("image.jpg");
        Mockito.verify(productoService).save(producto);
    }

    @Test
    void testEdit() {
        Integer id = 1;
        Model model = Mockito.mock(Model.class);
        Optional<Producto> optionalProducto = Optional.of(producto);
        Mockito.when(productoService.get(id)).thenReturn(optionalProducto);

        String result = productoController.edit(id, model);

        assertEquals("productos/edit", result);
        Mockito.verify(model).addAttribute("producto", producto);
    }

    @Test
    void testUpdate() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Producto existingProducto = Mockito.mock(Producto.class);
        Mockito.when(file.isEmpty()).thenReturn(true);
        Mockito.when(productoService.get(producto.getId())).thenReturn(Optional.of(existingProducto));

        String result = productoController.update(producto, file);

        assertEquals("redirect:/productos", result);
        Mockito.verify(producto).setImagen(existingProducto.getImagen());
        Mockito.verify(producto).setUsuario(existingProducto.getUsuario());
        Mockito.verify(productoService).update(producto);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        Producto producto = Mockito.mock(Producto.class);
        Mockito.when(productoService.get(id)).thenReturn(Optional.of(producto));
        Mockito.when(producto.getImagen()).thenReturn("image.jpg");

        String result = productoController.delete(id);

        assertEquals("redirect:/productos", result);
        Mockito.verify(upload).deleteImage("image.jpg");
        Mockito.verify(productoService).delete(id);
    }
}
