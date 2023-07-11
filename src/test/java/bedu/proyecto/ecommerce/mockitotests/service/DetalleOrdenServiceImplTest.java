package bedu.proyecto.ecommerce.mockitotests.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import bedu.proyecto.ecommerce.model.DetalleOrden;
import bedu.proyecto.ecommerce.repository.IDetalleOrdenRepository;
import bedu.proyecto.ecommerce.service.DetalleOrdenServiceImpl;

public class DetalleOrdenServiceImplTest {

    @Test
    public void testSave() {
        // Arrange
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setId(1);
        detalleOrden.setNombre("DetalleOrden 1");

        IDetalleOrdenRepository detalleOrdenRepository = mock(IDetalleOrdenRepository.class);
        when(detalleOrdenRepository.save(detalleOrden)).thenReturn(detalleOrden);

        DetalleOrdenServiceImpl detalleOrdenService = new DetalleOrdenServiceImpl();

        // Act
        DetalleOrden result = detalleOrdenService.save(detalleOrden);

        // Assert
        assertEquals(detalleOrden, result);
        verify(detalleOrdenRepository, times(1)).save(detalleOrden);
    }
}
