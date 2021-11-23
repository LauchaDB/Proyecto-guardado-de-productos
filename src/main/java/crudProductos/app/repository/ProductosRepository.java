package crudProductos.app.repository;

import crudProductos.app.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductosRepository extends JpaRepository<Productos, Long> {
}
