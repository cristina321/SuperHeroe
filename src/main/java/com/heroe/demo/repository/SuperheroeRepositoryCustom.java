/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heroe.demo.repository;

import com.heroe.demo.model.Superheroe;
import java.util.List;

/**
 * Repositorio personalizado para incluir el método de buscar un listado de
 * superhéroes por una subcadena contenida en su nombre
 * 
 * @author W45776304
 */
public interface SuperheroeRepositoryCustom {
    
    List<Superheroe> findByCadenaInNombre(String cadena);
}
