package bedu.proyecto.ecommerce.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bedu.proyecto.ecommerce.model.Producto;
import bedu.proyecto.ecommerce.repository.IProductoRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private IProductoRepository productoRepository;

	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public Optional<List<Producto>> findByCategoria(String categoria){
		return productoRepository.findByCategoria(categoria.toLowerCase());
	}

	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);		
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);		
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

}
