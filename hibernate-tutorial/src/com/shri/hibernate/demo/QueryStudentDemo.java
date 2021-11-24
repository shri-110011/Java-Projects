package com.shri.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Student;



public class QueryStudentDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			//query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student where lastName = 'Doe' OR firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe OR first name of Daffy");
			displayStudents(theStudents);
			
			//query students: email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student where email like '%gmail.com'").getResultList();
			System.out.println("\n\nStudents who have email ending with gmail.com");
			displayStudents(theStudents);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			sessionFactory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
