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
		
		Employee employee1 = new Employee("Eli", "Tabios", "eli@gmail.com");
		employeeRepository.save(employee1);
		
		Employee employee2 = new Employee("Sam", "Winchester", "samw@gmail.com");
		employeeRepository.save(employee2);
		
		Employee employee3 = new Employee("Dean", "Winchester", "dean@gmail.com");
		employeeRepository.save(employee3);

		Employee employee4 = new Employee("Baki", "Winchester", "bakihanma1@yahoo.com");
		employeeRepository.save(employee4);

		Employee employee5 = new Employee("Sober", "Human", "sober@yahoo.com");
		employeeRepository.save(employee5);

	}

}
