package bedu.proyecto.ecommerce.repository;

import java.util.List;

import bedu.proyecto.ecommerce.model.Orden;
import bedu.proyecto.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
	List<Orden> findByUsuario (Usuario usuario);
}
