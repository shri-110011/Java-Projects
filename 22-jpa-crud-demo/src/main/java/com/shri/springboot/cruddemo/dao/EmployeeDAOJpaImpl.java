package com.shri.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shri.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// create a query
		TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
		
		// execute the query
		List<Employee> employees = query.getResultList();
		
		System.out.println("Inside EmployeeDAOJpaImpl: findAll()");
		
		// return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get the employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
				
		// return the employee
		return theEmployee;	
	}

	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// update with id from db .., so that we can get generated id for save/insert
		/* If we don't have this below line of code then we would get the id as 0 when 
		 * we post an employee.
		 */
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {
		// delete the  object with primary key
		// Note: Delete or update queries cannot be typed.
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", theId);
		
		System.out.println("Inside EmployeeDAOJpaImpl: deleteById()");
		
		query.executeUpdate();
	}

}
