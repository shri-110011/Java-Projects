package com.shri.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shri.springboot.cruddemo.dao.EmployeeRepository;
import com.shri.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	/* By using Sprint data Jpa we get all the basic crud DAO implementation methods for free.
	 * Like the findAll(), findById(), save(), deleteById(). We don't have to create a DAO interface
	 * and DAO implementations as we did in previous versions of this cruddemo projects. All we need
	 * to do is simply create an repository interface and extend off the JpaRepository interface.
	 * 
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	/* Note: We don't need to put @Transactional on the service implementation methods 
	 * because spring JpaRepository will provide this functionality of transactions.
	 */
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		
		if(result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find Employee id - "+theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
