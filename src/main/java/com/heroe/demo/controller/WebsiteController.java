package com.heroe.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author W45776304
 */

@RestController
public class WebsiteController {
    
    @RequestMapping("/")
    public String index() {
        return "¡Bienvenido al gestor de Superhéroes!";
    }
}
