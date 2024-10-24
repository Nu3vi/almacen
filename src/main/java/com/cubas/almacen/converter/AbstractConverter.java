package com.cubas.almacen.converter;

import java.util.List;
import java.util.stream.Collectors;



public abstract class AbstractConverter<E, D> {
    public abstract D fromEntity(E entity);
    public abstract E fromDTO(D entity);

    public List<D> fromEntity(List<E> entitys){
        return entitys.stream()
                .map(e -> fromEntity(e))
                .collect(Collectors.toList());
    }

    public List<E> fromDTO(List<D> dtos){
        return dtos.stream()
                .map(e -> fromDTO(e))
                .collect(Collectors.toList());
    }
}
