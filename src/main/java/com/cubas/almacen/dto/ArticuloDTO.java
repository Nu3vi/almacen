package com.cubas.almacen.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloDTO {
    private int id;
    private String nombre;
    private double precio;
}
