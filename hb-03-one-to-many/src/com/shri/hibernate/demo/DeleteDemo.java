package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//get the instructor based on primary key / id
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found instructor: "+tempInstructor);
			
			//delete the instructor
			if(tempInstructor != null) {
				System.out.println("Deleting: "+tempInstructor);
				session.delete(tempInstructor);
			}
			
			//Note: This will also delete the instructor detail object because CascadeType.All
			
			
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			sessionFactory.close();
		}
	}

}
