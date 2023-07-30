package bedu.proyecto.ecommerce.util;

import bedu.proyecto.ecommerce.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SecurityConfigTest {
    private UserDetailServiceImpl userDetailService = mock(UserDetailServiceImpl.class);
    private SecurityConfig securityConfig = new SecurityConfig(userDetailService);

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder encoder = securityConfig.passwordEncoder();
        assertNotNull(encoder);
    }


}