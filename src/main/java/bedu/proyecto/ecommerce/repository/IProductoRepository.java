package bedu.proyecto.ecommerce.repository;

import bedu.proyecto.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<List<Producto>> findByCategoria (String categoria);

}
