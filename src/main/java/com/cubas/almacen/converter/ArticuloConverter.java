package com.cubas.almacen.converter;

import com.cubas.almacen.dto.ArticuloDTO;
import com.cubas.almacen.entity.Articulo;
import org.springframework.stereotype.Component;


@Component
public class ArticuloConverter extends AbstractConverter<Articulo, ArticuloDTO>{

    @Override
    public ArticuloDTO fromEntity(Articulo entity) {
        if (entity == null) return null;
        return ArticuloDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .build();
    }

    @Override
    public Articulo fromDTO(ArticuloDTO dto) {
        if(dto == null) return null;
        return Articulo.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .build();
    }
}
