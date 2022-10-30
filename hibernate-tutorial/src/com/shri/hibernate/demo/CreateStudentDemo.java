package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		/* SessionFactory reads the hibernate configuration file and get a 
		 * database connection and create session objects. And it is a 
		 * heavy weight object that is created once in our application.
		 * 
		 * Session on the other hand is a wrapper around a JDBC connection
		 * that is used to save/retrieve objects and it is short lived. 
		 * 
		 */
		
		/* It is not necessary to give the hibernate configuration file name 
		 * in the configure() because by default hibernate will look for the 
		 * default file name "hibernate.cfg.xml". So here we are being explicit.
		 * 
		 */
		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//create the student
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
			
			//begin the transaction
			session.beginTransaction();
			
			//use the session object to save the java object
			System.out.println("Saving the student object");
			session.save(tempStudent);
			System.out.println(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			sessionFactory.close();
		}
	}

}
