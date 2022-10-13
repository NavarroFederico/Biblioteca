package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.services.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            autorServicio.crearAutor(nombre);//persiste el nombre

            modelo.put("exito", "El autor fue registrado correctamente");
        } catch (MiException ex) {
            // Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();

        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {

        try {
            autorServicio.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (MiException ex) {
         modelo.put("error",ex.getMessage());
         return "autor_modificar.html";
        }

    }

}
