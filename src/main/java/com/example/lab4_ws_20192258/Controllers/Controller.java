package com.example.lab4_ws_20192258.Controllers;

import com.example.lab4_ws_20192258.Entities.Employees;
import com.example.lab4_ws_20192258.Entities.Jobs;
import com.example.lab4_ws_20192258.Repositories.EmployeesRepository;
import com.example.lab4_ws_20192258.Repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    /*Listar trabajadores expeto al presidente*/
    @GetMapping("tutor/list")
    public List<Employees> listarCargos() {
        return employeesRepository.findAllByJobIdNot("AD_PRES");
    }

    /*Employee info dado un employee id*/
    @GetMapping("tutor/infoEmployee")
    public ResponseEntity<HashMap<String,Object>> obtenerEmployee(@RequestParam("id") String id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            int idBuscar = Integer.parseInt(id);
            Optional<Employees> byId = employeesRepository.findById(idBuscar);
            if (byId.isPresent()) {
                hashMap.put("employee",byId.get());
            } else {
                hashMap.put("msg","El employee con ese ID no existe");
            }
            return ResponseEntity.ok(hashMap);
        } catch (NumberFormatException e) {
            hashMap.put("msg","El id no es un número!!!");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }

    /*Tutor info dado un employee id*/
    @GetMapping("tutor/infoTutor")
    public ResponseEntity<HashMap<String,Object>> obtenerTutor(@RequestParam("id") String id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            int idBuscar = Integer.parseInt(id);
            Optional<Employees> byId = employeesRepository.findById(idBuscar);
            if (byId.isPresent()) {
                hashMap.put("employee",byId.get());
            } else {
                hashMap.put("msg","El employee con ese ID no existe");
            }
            return ResponseEntity.ok(hashMap);
        } catch (NumberFormatException e) {
            hashMap.put("msg","El id no es un número!!!");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }
}
