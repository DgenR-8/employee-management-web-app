package com.devjdt.emwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devjdt.emwa.entity.Employee;
import com.devjdt.emwa.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeManagementWebAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementWebAppApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Employee employee1 = new Employee("Eli", "Tabios", "elitabios@gmail.com");
		employeeRepository.save(employee1);
		
		Employee employee2 = new Employee("Sam", "Winchester", "samwinchester@gmail.com");
		employeeRepository.save(employee2);
		
		Employee employee3 = new Employee("Dean", "Winchester", "deanwinchester@gmail.com");
		employeeRepository.save(employee3);
	}

}
