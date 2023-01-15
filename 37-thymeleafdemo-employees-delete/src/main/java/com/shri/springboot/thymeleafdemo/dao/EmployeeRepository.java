package com.shri.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shri.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// add a method to sort by last name
	
	/* Note here the Spring Data JPA will parse this method name to check for specific format and 
	 * pattern and then create the appropriate query for this method.
	 * 
	 */
	public List<Employee> findAllByOrderByFirstNameAsc();

}
