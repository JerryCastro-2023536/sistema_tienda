package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Productos;
import com.jerrycastro.tienda.Exception.NotFoundException;
import com.jerrycastro.tienda.Exception.ProductoValidation;
import com.jerrycastro.tienda.Repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImplements implements ProductosService{

    private final ProductosRepository productosRepository;
    private final ProductoValidation productoValidation;

    public ProductosServiceImplements(ProductosRepository productosRepository, ProductoValidation productoValidation) {
        this.productosRepository = productosRepository;
        this.productoValidation = productoValidation;
    }

    @Override
    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Productos getByIdProductos(Integer id) {
        return productosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));
    }

    @Override
    public Productos saveProductos(Productos productos) {
        productoValidation.codigoValidar(productos);
        productoValidation.precioValidar(productos.getPrecio());
        productoValidation.stockValidar(productos.getStock());
        productoValidation.estadoValidar(productos.getEstado());
        return productosRepository.save(productos);
    }

    @Override
    public Productos updateProductos(Integer id, Productos productos) {
        Productos productos1 = productosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));

        productos1.setNombre_producto(productos.getNombre_producto());
        productos1.setPrecio(productos.getPrecio());
        productos1.setStock(productos.getStock());
        productos1.setEstado(productos.getEstado());

        productoValidation.precioValidar(productos1.getPrecio());
        productoValidation.stockValidar(productos1.getStock());
        productoValidation.estadoValidar(productos1.getEstado());

        return productosRepository.save(productos1);
    }

    @Override
    public void deleteProductos(Integer id) {
        Productos productos = productosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));
        productosRepository.delete(productos);
    }
}
