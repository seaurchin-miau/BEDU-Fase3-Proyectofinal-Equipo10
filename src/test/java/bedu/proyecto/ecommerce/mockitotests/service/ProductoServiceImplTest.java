package bedu.proyecto.ecommerce.mockitotests.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.repository.IProductoRepository;
import bedu.proyecto.ecommerce.service.ProductoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplTest {

    @Test
    public void testSave() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1);
        // ... set otras propiedades

        IProductoRepository productoRepository = mock(IProductoRepository.class);
        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Act
        Producto result = productoService.save(producto);

        // Assert
        assertEquals(producto, result);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testGet() {
        // Arrange
        int productId = 1;
        Producto producto = new Producto();
        producto.setId(productId);
        // ... set otras propiedades

        IProductoRepository productoRepository = mock(IProductoRepository.class);
        when(productoRepository.findById(productId)).thenReturn(Optional.of(producto));

        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Act
        Optional<Producto> result = productoService.get(productId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(producto, result.get());
        verify(productoRepository, times(1)).findById(productId);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1);
        // ... set de otras propiedades

        IProductoRepository productoRepository = mock(IProductoRepository.class);

        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Act
        productoService.update(producto);

        // Assert
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testDelete() {
        // Arrange
        int productId = 1;

        IProductoRepository productoRepository = mock(IProductoRepository.class);

        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Act
        productoService.delete(productId);

        // Assert
        verify(productoRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Producto> productos = new ArrayList<>();
        // ... añadir algunos productos más a la lista

        IProductoRepository productoRepository = mock(IProductoRepository.class);
        when(productoRepository.findAll()).thenReturn(productos);

        ProductoServiceImpl productoService = new ProductoServiceImpl();

        // Act
        List<Producto> result = productoService.findAll();

        // Assert
        assertEquals(productos, result);
        verify(productoRepository, times(1)).findAll();
    }

}
