package com.heroe.demo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa un superhéroe.
 * 
 * @author W45776304
 */

@Entity
@Table(name = "superheroe")
public class Superheroe implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    @Column (name = "nombre", nullable = false)
    private String nombre;
    
    @Column (name = "fuerza", nullable = false)
    private Integer fuerza;
    
    public Superheroe() {}
    
    public Superheroe(String nombre, Integer fuerza) {
        this.nombre = nombre;
        this.fuerza = fuerza;
    }
    
    public Superheroe(String nombre, Integer fuerza, Long id) {
        this.id = id;
        this.nombre = nombre;
        this.fuerza = fuerza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + Objects.hashCode(this.fuerza);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superheroe other = (Superheroe) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fuerza, other.fuerza)) {
            return false;
        }
        return true;
    }
}
