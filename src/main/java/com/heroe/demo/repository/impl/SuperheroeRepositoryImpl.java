/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heroe.demo.repository.impl;

import com.heroe.demo.model.Superheroe;
import com.heroe.demo.repository.SuperheroeRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementación del repositorio personalizado. Implementa un método de búsqueda
 * de subcadenas en el nombre de los superhéroes
 * 
 * @author W45776304
 */
public class SuperheroeRepositoryImpl implements SuperheroeRepositoryCustom{
    
    @PersistenceContext
    EntityManager entityManager;
    
    /**
     * Realiza una consulta en HQL para buscar una subcadena en el nombre de los
     * superhéroes. Es insensible a las mayúsculas.
     * 
     * @param cadena
     * @return listado de superhéroes
     */
    @Override
    public List<Superheroe> findByCadenaInNombre(String cadena) {
        Query query = entityManager.createNativeQuery(
                "SELECT sh.* FROM superheroe as sh" + 
                " WHERE sh.nombre ILIKE ?", Superheroe.class);
        query.setParameter(1, "%" + cadena + "%");
        
        return query.getResultList();
    }
}
