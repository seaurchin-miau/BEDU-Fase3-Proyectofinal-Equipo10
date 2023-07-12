package bedu.proyecto.ecommerce.mockitotests.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.repository.IOrdenRepository;
import bedu.proyecto.ecommerce.service.OrdenServiceImpl;

public class OrdenServiceImplTest {

    @Test
    public void testSave() {
        // Arrange
        Orden orden = new Orden();
        orden.setId(1);
        orden.setNumero("000001");
        // ... set otras propiedades

        IOrdenRepository ordenRepository = mock(IOrdenRepository.class);
        when(ordenRepository.save(orden)).thenReturn(orden);

        OrdenServiceImpl ordenService = new OrdenServiceImpl();

        // Act
        Orden result = ordenService.save(orden);

        // Assert
        assertEquals(orden, result);
        verify(ordenRepository, times(1)).save(orden);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Orden orden1 = new Orden();
        orden1.setId(1);
        // ... set propiedades de la orden1

        Orden orden2 = new Orden();
        orden2.setId(2);
        // ... set propiedades de la orden2

        List<Orden> expectedOrdenes = new ArrayList<>();
        expectedOrdenes.add(orden1);
        expectedOrdenes.add(orden2);

        IOrdenRepository ordenRepository = mock(IOrdenRepository.class);
        when(ordenRepository.findAll()).thenReturn(expectedOrdenes);

        OrdenServiceImpl ordenService = new OrdenServiceImpl();

        // Act
        List<Orden> result = ordenService.findAll();

        // Assert
        assertEquals(expectedOrdenes, result);
        verify(ordenRepository, times(1)).findAll();
    }

    @Test
    public void testGenerarNumeroOrden() {
        // Arrange
        OrdenServiceImpl ordenService = new OrdenServiceImpl();

        List<Orden> existingOrdenes = new ArrayList<>();
        // ... añadir órdenes con diferentes números a órdenes existentes

        // Act
        String result = ordenService.generarNumeroOrden();

        // Assert
        // añadir assertions basados en lo que esperamos que haga generarNumeroOrden()
    }

    @Test
    public void testFindByUsuario() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1);
        // ... set propiedades del usuario

        Orden orden1 = new Orden();
        orden1.setId(1);
        // ... set propiedades  de la orden1

        Orden orden2 = new Orden();
        orden2.setId(2);
        // ... set propiedades de la orden2

        List<Orden> expectedOrdenes = new ArrayList<>();
        expectedOrdenes.add(orden1);
        expectedOrdenes.add(orden2);

        IOrdenRepository ordenRepository = mock(IOrdenRepository.class);
        when(ordenRepository.findByUsuario(usuario)).thenReturn(expectedOrdenes);

        OrdenServiceImpl ordenService = new OrdenServiceImpl();

        // Act
        List<Orden> result = ordenService.findByUsuario(usuario);

        // Assert
        assertEquals(expectedOrdenes, result);
        verify(ordenRepository, times(1)).findByUsuario(usuario);
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer orderId = 1;
        Orden expectedOrden = new Orden();
        expectedOrden.setId(orderId);
        // ... set propiedades para expectedOrden

        IOrdenRepository ordenRepository = mock(IOrdenRepository.class);
        when(ordenRepository.findById(orderId)).thenReturn(Optional.of(expectedOrden));

        OrdenServiceImpl ordenService = new OrdenServiceImpl();

        // Act
        Optional<Orden> result = ordenService.findById(orderId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedOrden, result.get());
        verify(ordenRepository, times(1)).findById(orderId);
    }
}
