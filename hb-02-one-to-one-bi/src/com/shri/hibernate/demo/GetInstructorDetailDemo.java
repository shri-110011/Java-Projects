package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
			
			//get the instructor detail object
			int theId = 51;
			
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("The associated instructor: "+tempInstructorDetail.getInstructor());
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			sessionFactory.close();
		}
	}

}
