package com.devjdt.emwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devjdt.emwa.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
	
}
