package com.cubas.almacen.service.impl;

import com.cubas.almacen.entity.Articulo;
import com.cubas.almacen.exception.GeneralServiceException;
import com.cubas.almacen.exception.NoDataFoundException;
import com.cubas.almacen.exception.ValidateServiceException;
import com.cubas.almacen.repository.ArticuloRepository;
import com.cubas.almacen.service.ArticuloService;
import com.cubas.almacen.validator.ArticuloValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ArticuloServiceImpl implements ArticuloService {
    @Autowired
    private ArticuloRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> findAll(Pageable page) {
        try{
            return repository.findAll(page).toList();
        }catch(NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> findByNombre(String nombre, Pageable page) {
        try{
            return repository.findByNombreContaining(nombre, page);
        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo findById(int id) {
        try{
            Articulo registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe el registro con ese id"));
            return registro;
        } catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Articulo save(Articulo articulo) {
        try{
            ArticuloValidator.save(articulo);
            if(repository.findByNombre(articulo.getNombre()) != null){
                throw new ValidateServiceException("Ya existe un registro con el nombre: " + articulo.getNombre());
            }
            articulo.setActivo(true);
            Articulo registro = repository.save(articulo);
            return registro;
        } catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Articulo update(Articulo articulo) {
        try{
            ArticuloValidator.save(articulo);
            Articulo registro = repository.findById(articulo.getId()).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese id"));
            Articulo registroD = repository.findByNombre(articulo.getNombre());
            if(registroD != null && registroD.getId() != articulo.getId()){
                throw new ValidateServiceException("Ya existe un registro con el nombre: " + articulo.getNombre());
            }
            registro.setNombre(articulo.getNombre());
            registro.setPrecio(articulo.getPrecio());
            repository.save(registro);
            return registro;
        } catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try{
            Articulo registro = repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese id"));
            repository.delete(registro);
        } catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }
        catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
