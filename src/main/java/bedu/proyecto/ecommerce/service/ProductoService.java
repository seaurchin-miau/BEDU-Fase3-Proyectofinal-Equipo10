package bedu.proyecto.ecommerce.service;

import java.util.List;
import java.util.Optional;
import bedu.proyecto.ecommerce.model.Producto;



public interface ProductoService {
	public Producto save( Producto producto);
	public Optional<Producto> get(Integer id);
	public Optional<List<Producto>> findByCategoria(String categoria);
	public void update(Producto producto);
	public void delete(Integer id);
	public List<Producto> findAll();

}
