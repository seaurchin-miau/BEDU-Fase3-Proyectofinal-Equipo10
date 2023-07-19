package bedu.proyecto.ecommerce.mockitotests.service;

import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import bedu.proyecto.ecommerce.service.UserDetailServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        usuarioService = mock(IUsuarioService.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        session = mock(HttpSession.class);
    }

   /* @Test
    void testLoadUserByUsername_UserFound_ReturnsUserDetails() {
        // Arrange
        String username = "example@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        String role = "ROLE_USER";
        long userId = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(7);
        usuario.setNombre("John Doe");
        usuario.setPassword(password);
        usuario.setTipo(role);
        usuario.setUsername(username);

        when(usuarioService.findByEmail(username)).thenReturn(Optional.of(usuario));
        when(bCryptPasswordEncoder.encode(password)).thenReturn(encodedPassword);

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        // Assert
        verify(session).setAttribute("idusuario", userId);
        verify(usuarioService).findByEmail(username);
        verify(bCryptPasswordEncoder).encode(password);
        verifyNoMoreInteractions(session, usuarioService, bCryptPasswordEncoder);

        assertEquals(usuario.getNombre(), userDetails.getUsername());
        assertEquals(encodedPassword, userDetails.getPassword());
        assertEquals(role, userDetails.getAuthorities().iterator().next().getAuthority());
    }*/

    @Test
    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        MockitoAnnotations.openMocks(this);

        // Create a test username
        String username = "testuser";

        // Mock the behavior of usuarioService.findByEmail
        when(usuarioService.findByEmail(username)).thenReturn(Optional.empty());

        // Call the loadUserByUsername method and expect an exception
        assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername(username));
    }


}
