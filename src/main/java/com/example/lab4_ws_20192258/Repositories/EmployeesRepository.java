package com.example.lab4_ws_20192258.Repositories;

import com.example.lab4_ws_20192258.Entities.Employees;
import com.example.lab4_ws_20192258.Entities.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    List<Employees> findAllByJobIdNot(String jobs);
}