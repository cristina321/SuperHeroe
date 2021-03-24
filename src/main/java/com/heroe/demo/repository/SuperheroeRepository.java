/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heroe.demo.repository;

import com.heroe.demo.model.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que extiende JpaRepository para realizar las operaciones básicas
 * de CRUD sobre la base de datos
 * 
 * @author W45776304
 */

@Repository
public interface SuperheroeRepository extends JpaRepository<Superheroe, Long>, SuperheroeRepositoryCustom {
    
}
