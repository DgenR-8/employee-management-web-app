package com.devjdt.emwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.devjdt.emwa.entity.User;
import com.devjdt.emwa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.devjdt.emwa.entity.Employee;
import com.devjdt.emwa.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private UserService userService;
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, Model model) {
		try {
			userService.registerUser(user);
			return "redirect:/register?success";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", user);
			return "register";
		}
	}
	
	@GetMapping("/employees")
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/employees/new")
	@PreAuthorize("hasRole('ADMIN')")  // Only ADMIN can access
	public String createEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
	}
	
	@PostMapping("/employees")
	@PreAuthorize("hasRole('ADMIN')")  // Only ADMIN can access
	public String saveEmployee(@ModelAttribute Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")  // Only ADMIN can access
	public String editEmployeeForm(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	@PreAuthorize("hasRole('ADMIN')")  // Only ADMIN can access
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
		Employee existingEmployee = employeeService.getEmployeeById(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")  // Only ADMIN can access
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";
	}
}