package com.cubas.almacen.controller;

import com.cubas.almacen.converter.ArticuloConverter;
import com.cubas.almacen.dto.ArticuloDTO;
import com.cubas.almacen.entity.Articulo;
import com.cubas.almacen.service.ArticuloService;
import com.cubas.almacen.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService service;

    @Autowired
    private ArticuloConverter converter;

    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> findAll(
        @RequestParam(value = "nombre", required = false, defaultValue = " ") String nombre,
        @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
        @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
        ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Articulo> articulos;
        if(nombre == null){
            articulos = service.findAll(page);
        }else{
            articulos = service.findByNombre(nombre, page);
        }

        if(articulos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ArticuloDTO> articuloDTO = converter.fromEntity(articulos);
        return new WrapperResponse(true, "sucess", articuloDTO).createResponse(HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<WrapperResponse<ArticuloDTO>> findById(@PathVariable("id") int id){
        Articulo articulo = service.findById(id);
//        if(articulo == null){
//            return ResponseEntity.notFound().build();
//        }
        ArticuloDTO articuloDTO = converter.fromEntity(articulo);
        return new WrapperResponse<ArticuloDTO>(true, "sucess", articuloDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ArticuloDTO> create(@RequestBody ArticuloDTO articuloDTO){
        Articulo registro = service.save(converter.fromDTO(articuloDTO));
        ArticuloDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "sucess", registroDTO).createResponse(HttpStatus.CREATED);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArticuloDTO> update(@PathVariable("id") int id, @RequestBody ArticuloDTO articuloDTO){
        Articulo registro = service.update(converter.fromDTO(articuloDTO));
//        if(registro==null){
//            return ResponseEntity.notFound().build();
//        }
        ArticuloDTO registroDTO = converter.fromEntity(registro);
        return new WrapperResponse(true, "sucess", registroDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ArticuloDTO> delete(@PathVariable("id") int id){
        service.delete(id);
        return new WrapperResponse(true, "sucess", null).createResponse(HttpStatus.OK);
    }
}
