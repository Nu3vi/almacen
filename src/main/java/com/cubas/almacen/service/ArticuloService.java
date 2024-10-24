package com.cubas.almacen.service;

import com.cubas.almacen.entity.Articulo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloService {
    public List<Articulo> findAll(Pageable page);

    public List<Articulo> findByNombre(String nombre, Pageable page);

    public Articulo findById(int id);

    public Articulo save(Articulo articulo);

    public Articulo update(Articulo articulo);

    public void delete(int id);
}
