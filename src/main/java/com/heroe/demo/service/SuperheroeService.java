package com.heroe.demo.service;

import com.heroe.demo.cache.ResourceNotFoundException;
import com.heroe.demo.controller.SuperheroeController;
import com.heroe.demo.model.Superheroe;
import com.heroe.demo.repository.SuperheroeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los servicios de la API.
 * Los métodos de consulta son cacheados.
 * 
 * @author W45776304
 */

@Service
public class SuperheroeService {
    
    private static final Logger log = LoggerFactory.getLogger(SuperheroeController.class);
    private final SuperheroeRepository superheroeRepository;
    
    public SuperheroeService(SuperheroeRepository superheroeRepository) {
        this.superheroeRepository = superheroeRepository;
    }
    
    /**
     * Obtaining all superheroes
     * The result of the method returns a JSON
     * The query result is cached.
     * 
     * @return listado de superhéroes
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "superheroesTodosCache")
    public List<Superheroe> getAll() {
        log.info("Obteniendo todos los superhéroes");
        return superheroeRepository.findAll();
    }
    
    /**
     * Superhero Search by ID Throw an exception if not found.
     * The query result is cached.
     * Throw an exception if the superhero is not found
     * 
     * @param id
     * @return superhéroe
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "superheroePorIdCache")
    public Superheroe getById(Long id) throws ResourceNotFoundException {
        log.info("Obteniendo superhéroe por ID (" + id + ")");
        Superheroe heroe = superheroeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SuperHeroe not found for this id :: " + id));
        return heroe;   
    }
    
    /**
     * Get a list of superheroes given a string that is included in the name.    
     * The query is case insensitive.
     * The query result is cached.
     * Throw an exception if the superhero is not found
     * 
     * @param cadena
     * @return superhero list
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Cacheable(value = "superheroesPorNombre")
    public List<Superheroe> getByName(String cadena) throws ResourceNotFoundException {
        log.info("Obteniendo superhéroes por la subcadena en el nombre ('" + cadena + "')");    
        List<Superheroe> superheroes = superheroeRepository.findByCadenaInNombre(cadena);  
        if(superheroes.isEmpty()){
            throw new ResourceNotFoundException("SuperHeroe not found for this name :: " + cadena);
        }       
        return superheroes;
    }
    
    /**
     * Modify a superhero given his ID.
     * Update superhero name and strength with submitted data
     * Throw an exception if the superhero is not found
     * 
     * @param id
     * @param superheroeModificado
     * @return update superhero
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superheroe updateSuperhero(Long id, Superheroe superheroeModificado) throws ResourceNotFoundException {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHeroe not found for this id :: " + id));
        
        heroe.setNombre(superheroeModificado.getNombre());
        heroe.setFuerza(superheroeModificado.getFuerza());
        final Superheroe superheroeActualizado = superheroeRepository.save(heroe);
        
        return superheroeActualizado;
    }
    
    /**
     * Eliminate a superhero given his ID
     * Throw an exception if the superhero is not found
     * Returns a message confirming the deletion in case of success
     * 
     * @param id
     * @return 'Borrado: true'
     * @throws com.heroe.demo.cache.ResourceNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Map<String, Boolean> deleteSuperheroe(Long id) throws ResourceNotFoundException {
        Superheroe heroe = superheroeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHeroe not found for this id :: " + id));

        superheroeRepository.delete(heroe);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Borrado", Boolean.TRUE);
        
        return respuesta;
    }
}
