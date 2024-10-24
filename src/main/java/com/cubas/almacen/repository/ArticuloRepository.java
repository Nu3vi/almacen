package com.cubas.almacen.repository;

import com.cubas.almacen.entity.Articulo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    List<Articulo> findByNombreContaining(String nombre, Pageable page);

    Articulo findByNombre(String nombre);


}
