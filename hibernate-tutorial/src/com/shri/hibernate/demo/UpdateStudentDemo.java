package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//My New Code
			
			int studentId = 1;
			
			//now get a new session object and start the transaction
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			
			//Note: Unless we do a commit the changes made to the student object will only be there in memory and not 
			//be reflected in the database
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//New Code
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			//update the email for all students
			session.createQuery("update Student set email = 'foo@luv2code.com'").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			sessionFactory.close();
		}
	}

}
