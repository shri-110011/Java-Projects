package com.shri.springboot.thymeleafdemo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shri.springboot.thymeleafdemo.entity.Employee;
import com.shri.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// get employees from db
		List<Employee> employeeList = employeeService.findAll();
		
		// add the employees list to the model
		theModel.addAttribute("employees", employeeList);
		
		return "employees/list-employees";
	}
	
	// add mapping to show employee-form "/showFormForAdd"
	@GetMapping("/showFormForAdd")
	public String showForm(Model theModel) {
		Employee employee = new Employee();
		
		// create model attribute to bind form data
		theModel.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	// add mapping to save employee "/save"
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee theEmployee) {
		employeeService.save(theEmployee);
		
		// Use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
}
