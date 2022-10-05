/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ASUS
 */
@Entity
public class Editorial {
     @Id 
    @GeneratedValue(generator = "uuid")// el valor de el Id se va general de manera automatica al momento que el repositorio persista la entidad 
    @GenericGenerator( name = "uuid",strategy = "uuid2")
    private String id ;
    private String nombre ;
    
    public Editorial() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
