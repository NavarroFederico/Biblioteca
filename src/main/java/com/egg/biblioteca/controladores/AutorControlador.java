package com.egg.biblioteca.controladores;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * contiene métodos para manejar peticiones HTTP 
para diferentes URLs. 
 */
/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/autor")//locasthost:8080/autor

public class AutorControlador {

    //Creo un componente de la clase AutorServicio
    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")//locasthost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        
        try {
            autorServicio.crearAutor(nombre);//persiste el nombre
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
        return "autor_form.html";
        }
        return "index.html";
    }
}
