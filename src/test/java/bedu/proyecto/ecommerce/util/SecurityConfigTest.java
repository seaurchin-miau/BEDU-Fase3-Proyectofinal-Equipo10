package bedu.proyecto.ecommerce.util;

import bedu.proyecto.ecommerce.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class SecurityConfigTest {
    private UserDetailServiceImpl userDetailService = mock(UserDetailServiceImpl.class);
    private SecurityConfig securityConfig = new SecurityConfig(userDetailService);

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder encoder = securityConfig.passwordEncoder();
        assertNotNull(encoder);
    }

}