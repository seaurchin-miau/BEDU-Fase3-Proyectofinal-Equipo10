package bedu.proyecto.ecommerce.mockitotests.service;
import static org.mockito.Mockito.*;

import bedu.proyecto.ecommerce.model.DetalleOrden;
import bedu.proyecto.ecommerce.repository.IDetalleOrdenRepository;
import bedu.proyecto.ecommerce.service.DetalleOrdenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetalleOrdenServiceImplTest {

    @Mock
    private IDetalleOrdenRepository detalleOrdenRepository;

    @InjectMocks
    private DetalleOrdenServiceImpl detalleOrdenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Create a DetalleOrden object for testing
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setId(1);
        detalleOrden.setCantidad(5);
        detalleOrden.setNombre("cosa");
        detalleOrden.setPrecio(30.0);
        detalleOrden.setTotal(150.0);
        // Set up the behavior of the mock repository
        when(detalleOrdenRepository.save(detalleOrden)).thenReturn(detalleOrden);
        // Call the method under test
        DetalleOrden savedDetalleOrden = detalleOrdenService.save(detalleOrden);
        // Verify that the repository's guardar method was called with the correct object
        verify(detalleOrdenRepository).save(detalleOrden);
        // Verify that the returned object is the same as the one returned by the repository
        assertEquals(detalleOrden, savedDetalleOrden);
    }
}
