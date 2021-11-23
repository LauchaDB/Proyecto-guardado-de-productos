package crudProductos.app.service;

import crudProductos.app.model.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductosServices {

    public void guardar(Productos productos);
    public void eliminar(Productos productos);
    public List<Productos> listar();
    public Productos listProductoById(long id);
}
