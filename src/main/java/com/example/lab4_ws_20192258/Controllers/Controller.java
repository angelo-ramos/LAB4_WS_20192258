package com.example.lab4_ws_20192258.Controllers;

import com.example.lab4_ws_20192258.Entities.Departments;
import com.example.lab4_ws_20192258.Entities.Employees;
import com.example.lab4_ws_20192258.Entities.Jobs;
import com.example.lab4_ws_20192258.Repositories.DepartmentsRepository;
import com.example.lab4_ws_20192258.Repositories.EmployeesRepository;
import com.example.lab4_ws_20192258.Repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    DepartmentsRepository departmentsRepository;

    /*Listar trabajadores expeto al presidente*/
    @GetMapping("tutor/list")
    public List<Employees> listarCargos() {
        return employeesRepository.findAllByJobIdNot("AD_PRES");
    }

    /*Buscar JOBS*/
    @GetMapping("tutor/job")
    public ResponseEntity<HashMap<String,Object>> obtenerJob(@RequestParam("id") String id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            //int idBuscar = Integer.parseInt(id);
            Optional<Jobs> byId = jobsRepository.findById(id);
            if (byId.isPresent()) {
                hashMap.put("job",byId.get());
            } else {
                hashMap.put("msg","El Job con ese ID no existe");
            }
            return ResponseEntity.ok(hashMap);
        } catch (NumberFormatException e) {
            hashMap.put("msg","El id no es un número!!!");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }

    /*Buscar departments*/
    @GetMapping("tutor/depa")
    public ResponseEntity<HashMap<String,Object>> obtenerDepa(@RequestParam("id") Integer id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            //int idBuscar = Integer.parseInt(id);
            Optional<Departments> byId = departmentsRepository.findById(id);
            if (byId.isPresent()) {
                hashMap.put("depa",byId.get());
            } else {
                hashMap.put("msg","El Department con ese ID no existe");
            }
            return ResponseEntity.ok(hashMap);
        } catch (NumberFormatException e) {
            hashMap.put("msg","El id no es un número!!!");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }

    /*Employee info dado un employee id*/
    @GetMapping("tutor/infoEmployee")
    public ResponseEntity<HashMap<String,Object>> obtenerEmployee(@RequestParam("id") Integer id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            //int idBuscar = Integer.parseInt(id);
            Optional<Employees> byId = employeesRepository.findById(id);
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
    public ResponseEntity<HashMap<String,Object>> obtenerTutor(@RequestParam("id") Integer id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            //int idBuscar = Integer.parseInt(id);
            Optional<Employees> byId = employeesRepository.findById(id);
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

    //Asignar cita
    @PostMapping(value = "/asignar",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String,String>> asignar(Integer id){
        HashMap<String,String> hashMap = new HashMap<>();

        Optional<Employees> optionalProduct = employeesRepository.findById(id);
        if(optionalProduct.isPresent()){
            Employees employees = optionalProduct.get();

            employees.setMeeting((byte) 1);

            employeesRepository.save(employees);
            hashMap.put("status", "actualizado");
            return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
        }else{
            hashMap.put("status","error");
            hashMap.put("msg","el employee a actualizar no existe");
            return ResponseEntity.ok(hashMap);
        }
    }


}
