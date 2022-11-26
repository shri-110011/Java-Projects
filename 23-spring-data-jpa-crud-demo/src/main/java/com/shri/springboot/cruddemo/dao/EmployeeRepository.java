package com.shri.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shri.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
