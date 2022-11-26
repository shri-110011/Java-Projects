package com.shri.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	// define field for entity manager
	private EntityManager entityManager;
	
	
	// set up constructor injection for entity manager
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		
		// execute the query
		List<Employee> employees = query.getResultList();
		
		// return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
				
		// return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save the employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete the  object with primary key 
		Query<Employee> query = currentSession.createQuery("delete from Employee where id=:employeeId", Employee.class);
		
		query.setParameter("employeeId", theId);
		
		query.executeUpdate();
	}

}
