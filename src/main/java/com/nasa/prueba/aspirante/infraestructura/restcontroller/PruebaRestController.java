package com.nasa.prueba.aspirante.infraestructura.restcontroller;

import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import com.nasa.prueba.aspirante.infraestructura.restcontroller.service.PruebaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/nasa")
public class PruebaRestController {

    @Autowired
    PruebaServiceImpl pruebaService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<PruebaEntity>> getAllRecords() {
        List<PruebaEntity> records = pruebaService.findAll();
        if (!records.isEmpty()) {
            return ResponseEntity.ok(records); // Devuelve un código de estado 200 OK y la lista de usuarios en el cuerpo
        } else {
            return ResponseEntity.noContent().build(); // Devuelve un código de estado 204 No Content sin cuerpo
        }
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<PruebaEntity> createRecord(@RequestBody PruebaEntity pruebaEntity) {
        pruebaService.save(pruebaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(pruebaEntity);
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<PruebaEntity> getRecord(@PathVariable Long id) {
        PruebaEntity record = pruebaService.findOne(id);
        if (record != null) {
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        pruebaService.delete(id);
        return ResponseEntity.ok(!pruebaService.existById(id));
        //return ResponseEntity.noContent().build(); // Devuelve un código de estado 204 No Content sin cuerpo
    }

}
