/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca.services;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarLibros() {
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();//findAll() encuentra todos los autores que esten en libroRepositorio

        return autores;

    }

    public void modificarAutor(String nombre, String id) throws MiException {
        validar(nombre);
       
        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);

        if (respuestaAutor.isPresent()) {
            Autor autor = respuestaAutor.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
     private void validar(String nombre) throws MiException {
         
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre del autor no puede ser nulo o estar vacio");
        }
    }
}
