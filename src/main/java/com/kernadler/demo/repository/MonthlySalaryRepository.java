package com.kernadler.demo.repository;

import com.kernadler.demo.model.MonthlySalaries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlySalaryRepository extends JpaRepository<MonthlySalaries, Long> {
}