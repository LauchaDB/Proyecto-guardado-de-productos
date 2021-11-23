package crudProductos.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import crudProductos.app.model.Productos;
import crudProductos.app.service.ProductosServices;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductosController {

    @Autowired
    private ProductosServices productosServices;

    @GetMapping("/")
    public String home(Model model){
        List<Productos> productos = productosServices.listar();
        model.addAttribute("productos", productos);
        return "index";
    }

    @PostMapping("/save")
    @ResponseBody
    public ObjectNode save(@RequestBody JsonNode json) throws JsonProcessingException {
        if(!json.isEmpty()){
            Productos prod = new ObjectMapper().convertValue(json, Productos.class);
            productosServices.guardar(prod);
            String jsonResponse = "{\"key\":true\"}";
            return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
        }
        String jsonResponse = "{\"key\":false\"}";
        return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
    }

    @PostMapping("/listById")
    @ResponseBody
    public ObjectNode listProductoById(@RequestBody JsonNode json) throws IOException {
        JsonNode jsonProductos = null;
        try{
            if(json != null ){
                Long id = Long.parseLong(json.get("idProducto").asText());
                Productos productos = productosServices.listProductoById(id);
                jsonProductos = new ObjectMapper().readTree(new ObjectMapper().writeValueAsString(productos));
            }
        } catch(NumberFormatException ex){
            System.out.println("ERROR --->"+ex);
        }
        return (ObjectNode) jsonProductos;
    }

    @PostMapping("/editById")
    @ResponseBody
    public ObjectNode editProductoById(@RequestBody JsonNode json) throws JsonProcessingException {
        if(!json.isEmpty()){
            Productos prod = new ObjectMapper().convertValue(json, Productos.class);
            productosServices.guardar(prod);
            String jsonResponse = "{\"key\":true\"}";
            return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
        }
        String jsonResponse = "{\"key\":false\"}";
        return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
    }

    @PostMapping("/deleteById")
    @ResponseBody
    public ObjectNode deleteProductoById(@RequestBody JsonNode json) throws JsonProcessingException {
        try{
            if(json != null ){
                Long id = Long.parseLong(json.get("idProducto").asText());
                Productos productos = productosServices.listProductoById(id);
                productosServices.eliminar(productos);
                String jsonResponse = "{\"key\":true\"}";
                return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
            }
        } catch(NumberFormatException ex){
            System.out.println("ERROR --->"+ex);
        }
        String jsonResponse = "{\"key\":false\"}";
        return (ObjectNode) new ObjectMapper().readTree(jsonResponse);
    }





}
