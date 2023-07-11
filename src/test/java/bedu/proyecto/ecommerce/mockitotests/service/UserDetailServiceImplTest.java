package bedu.proyecto.ecommerce.mockitotests.service;

import bedu.proyecto.ecommerce.model.Usuario;
import bedu.proyecto.ecommerce.service.IUsuarioService;
import bedu.proyecto.ecommerce.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void loadUserByUsername_UserFound_ReturnsUserDetails() {
        MockitoAnnotations.openMocks(this);

        // Create a test user
        String username = "testuser";
        String password = "testpassword";
        String encodedPassword = "encodedpassword";
        String role = "USER";
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
        usuario.setPassword(password);
        usuario.setTipo(role);

        // Mock the behavior of usuarioService.findByEmail
        when(usuarioService.findByEmail(username)).thenReturn(Optional.of(usuario));

        // Mock the behavior of bCryptPasswordEncoder.encode
        when(bCryptPasswordEncoder.encode(password)).thenReturn(encodedPassword);

        // Call the loadUserByUsername method
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        // Verify that the userDetails object is correctly created
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(encodedPassword, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(role)));
    }

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
