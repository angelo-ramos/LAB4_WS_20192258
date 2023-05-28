package com.example.lab4_ws_20192258.Repositories;

import com.example.lab4_ws_20192258.Entities.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, String> {
}