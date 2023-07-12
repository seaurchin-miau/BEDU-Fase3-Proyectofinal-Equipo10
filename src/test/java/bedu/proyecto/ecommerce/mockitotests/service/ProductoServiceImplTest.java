package bedu.proyecto.ecommerce.mockitotests.service;

import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.repository.IProductoRepository;
import bedu.proyecto.ecommerce.service.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductoServiceImplTest {

    @Mock
    private IProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveProducto() {
        // Create a mock Producto
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Product 1");

        // Mock the behavior of the productoRepository
        when(productoRepository.save(producto)).thenReturn(producto);

        // Call the method under test
        Producto savedProducto = productoService.save(producto);

        // Verify the result
        assertEquals(producto, savedProducto);

        // Verify that the save method was called on the productoRepository
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void get_ShouldReturnProductoById() {
        // Create a mock Producto
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Product 1");

        // Mock the behavior of the productoRepository
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // Call the method under test
        Optional<Producto> retrievedProducto = productoService.get(1);

        // Verify the result
        assertEquals(Optional.of(producto), retrievedProducto);
    }

    @Test
    void update_ShouldUpdateExistingProducto() {
        // Create a mock Producto
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Product 1");

        // Mock the behavior of the productoRepository
        when(productoRepository.save(producto)).thenReturn(producto);

        // Call the method under test
        productoService.update(producto);

        // Verify that the save method was called on the productoRepository
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void delete_ShouldDeleteProducto() {
        // Call the method under test
        productoService.delete(1);

        // Verify that the deleteById method was called on the productoRepository
        verify(productoRepository, times(1)).deleteById(1);
    }

    @Test
    void findAll_ShouldReturnAllProductos() {
        // Create a list of mock Productos
        List<Producto> productos = new ArrayList<>();
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Product 1");
        productos.add(producto1);
        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Product 2");
        productos.add(producto2);

        // Mock the behavior of the productoRepository
        when(productoRepository.findAll()).thenReturn(productos);

        // Call the method under test
        List<Producto> retrievedProductos = productoService.findAll();

        // Verify the result
        assertEquals(productos, retrievedProductos);
    }
}
