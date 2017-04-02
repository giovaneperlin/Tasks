package com.tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lucas O. Queiroz
 * @author Giovane L. S. Perlin
 */
@Controller
public class TasksController {

    @RequestMapping("/")
    public String index() {
        
        return "index";
        
    }
}
