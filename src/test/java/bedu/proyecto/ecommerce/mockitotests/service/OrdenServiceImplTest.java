package bedu.proyecto.ecommerce.mockitotests.service;

import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.repository.IOrdenRepository;
import bedu.proyecto.ecommerce.service.OrdenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrdenServiceImplTest {

    @Mock
    private IOrdenRepository ordenRepository;

    @InjectMocks
    private OrdenServiceImpl ordenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Orden orden = new Orden();
        when(ordenRepository.save(orden)).thenReturn(orden);

        Orden savedOrden = ordenService.save(orden);

        verify(ordenRepository, times(1)).save(orden);
        assertEquals(orden, savedOrden);
    }

    @Test
    void testFindAll() {
        List<Orden> ordenes = new ArrayList<>();
        ordenes.add(new Orden());
        when(ordenRepository.findAll()).thenReturn(ordenes);

        List<Orden> result = ordenService.findAll();

        verify(ordenRepository, times(1)).findAll();
        assertEquals(ordenes, result);
    }

    @Test
    void generarNumeroOrden() {
        // Mock the Orden objects
        Orden orden1 = new Orden();
        orden1.setNumero("0000000010");
        Orden orden2 = new Orden();
        orden2.setNumero("0000000005");
        Orden orden3 = new Orden();
        orden3.setNumero("0000000020");

        // Create a list of Orden objects
        List<Orden> ordenes = new ArrayList<>(Arrays.asList(orden1, orden2, orden3));

        // Mock the behavior of the ordenRepository
        when(ordenRepository.findAll()).thenReturn(ordenes);

        // Call the method under test
        String generatedNumber = ordenService.generarNumeroOrden();

        // Verify the result
        assertEquals("0000000021", generatedNumber);
    }

    @Test
    void testFindByUsuario() {
        Usuario usuario = new Usuario();
        List<Orden> ordenes = new ArrayList<>();
        when(ordenRepository.findByUsuario(usuario)).thenReturn(ordenes);

        List<Orden> result = ordenService.findByUsuario(usuario);

        verify(ordenRepository, times(1)).findByUsuario(usuario);
        assertEquals(ordenes, result);
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Orden orden = new Orden();
        when(ordenRepository.findById(id)).thenReturn(Optional.of(orden));

        Optional<Orden> result = ordenService.findById(id);

        verify(ordenRepository, times(1)).findById(id);
        assertEquals(Optional.of(orden), result);
    }
}

