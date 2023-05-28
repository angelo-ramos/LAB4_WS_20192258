package com.example.lab4_ws_20192258.Repositories;

import com.example.lab4_ws_20192258.Entities.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
}