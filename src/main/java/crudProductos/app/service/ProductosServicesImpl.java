package crudProductos.app.service;

import crudProductos.app.model.Productos;
import crudProductos.app.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductosServicesImpl implements ProductosServices{

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public void guardar(Productos productos) {
        productosRepository.save(productos);
    }

    @Override
    public void eliminar(Productos productos) {
        productosRepository.delete(productos);
    }

    @Override
    public List<Productos> listar() {
        return productosRepository.findAll();
    }

    @Override
    public Productos listProductoById(long id) {
        Optional<Productos> optionalProduct = productosRepository.findById(id);
        return optionalProduct.isEmpty() ? null : optionalProduct.get();
    }



}
