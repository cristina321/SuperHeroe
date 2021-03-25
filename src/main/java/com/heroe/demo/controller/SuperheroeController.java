package com.heroe.demo.controller;

import com.heroe.demo.cache.ResourceNotFoundException;
import com.heroe.demo.model.Superheroe;
import com.heroe.demo.service.SuperheroeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de las llamadas a la base de datos de superhéroes
 * 
 * @author W45776304
 */

@RestController
@RequestMapping("/api")
public class SuperheroeController {
    
    @Autowired
    private SuperheroeService superheroeService;
    
    /**
     * Llamada GET que obtiene todos los superhéroes
     * 
     * @return lista de todos los superhéroes
     */
    @GetMapping("/superheroes")
    public List<Superheroe> getAllSuperheroes() {
        return superheroeService.getAll();
    }
    
    /**
     * Llamada GET que obtiene un superhéroe con el id pasado por parámetro
     * 
     * @param superHeroeId
     * @return superhéroe
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @GetMapping("/superheroes/id/{id}")
    public Superheroe getSuperheroeById(@PathVariable(value = "id") Long superHeroeId) throws  ResourceNotFoundException {
        return superheroeService.getById(superHeroeId);
    }
    
    /**
     * Llamada GET que obtiene un listado de superhéroes cuyo nombre contenga la 
     * cadena pasada por parámetro
     * 
     * @param cadena
     * @return listado de superhéroes
     */
    @GetMapping("/superheroes/name/{cadena}")
    public List<Superheroe> getSuperheroeByCadenaInName(@PathVariable(value = "cadena") String cadena) throws ResourceNotFoundException {
        return superheroeService.getByName(cadena);
    }
    
    /**
     * Llamada PUT que actualiza los datos del superhéroe con el id pasado por parámetro.También hay que pasar en formato JSON los nuevos datos del superhéroe.
     * 
     * @param superHeroeId
     * @param superheroeModificado
     * @return superhéroe actualizado
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @PutMapping("/superheroes/id/{id}")
    public Superheroe updateSuperheroe(@PathVariable(value = "id") Long superHeroeId
        , @Validated @RequestBody Superheroe superheroeModificado)throws ResourceNotFoundException {
        return superheroeService.updateSuperhero(superHeroeId, superheroeModificado);
    }
    
    /**
     * Llamada DELETE que elimina el superhéroe con el id pasado por parámetro.
     * 
     * @param superHeroeId
     * @return valor booleano indicando el éxito del borrado
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @DeleteMapping("/superheroes/id/{id}")
    public Map<String, Boolean> deleteSuperheroe(@PathVariable(value = "id") Long superHeroeId) throws ResourceNotFoundException {
        return superheroeService.deleteSuperheroe(superHeroeId);
    }
}
