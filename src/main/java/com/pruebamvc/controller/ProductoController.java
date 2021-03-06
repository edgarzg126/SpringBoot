package com.pruebamvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pruebamvc.entity.Producto;
import com.pruebamvc.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    public static final String VISTA_LISTA = "lista";
    public static final String VISTA_FORMULARIO = "formulario";
    
    @Value("${aplicacion.nombre}")
    private String nombreAplicacion;

    @Autowired
    private ProductoService productoService;
    
    @GetMapping(value = "/lista")
    public String listar(Model model) {
        model.addAttribute("titulo", nombreAplicacion);
        model.addAttribute("productos", productoService.obtenerTodosProductos());
        model.addAttribute("name", "hola mundo");
        return VISTA_LISTA;
    }
    
    @GetMapping(value = "/listaModelMap")
    public String listarModelMap(ModelMap model) {
        model.addAttribute("titulo", nombreAplicacion);
        model.addAttribute("productos", productoService.obtenerTodosProductos());

        return VISTA_LISTA;
    }
    
    @GetMapping("/listaModelAndView")
    public ModelAndView listarModelAndView() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("titulo", nombreAplicacion);
        mav.addObject("productos", productoService.obtenerTodosProductos());
        mav.setViewName(VISTA_LISTA);

        return mav;
    }
    
    @GetMapping("/crear")
    public String crear(Map<String, Object> model) {
        Producto producto = new Producto();
        model.put("producto", producto);
        model.put("titulo", nombreAplicacion);
        
        return VISTA_FORMULARIO;
    }
    
    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        productoService.guardar(producto);
        
        return "redirect:" + VISTA_LISTA;
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Integer idProducto) {
        productoService.eliminar(idProducto);

        return "redirect:../" + VISTA_LISTA;
    }

}
