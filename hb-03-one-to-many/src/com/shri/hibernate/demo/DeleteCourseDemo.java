package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			//begin the transaction
			session.beginTransaction();
			
			//get the course from the db
			int theId = 2;
			
			Course tempCourse = session.get(Course.class, theId);
			
			try {
				
				//print the deleted course
				System.out.println("Deleting course: "+tempCourse);
				
				//delete the course
				session.delete(tempCourse);
				
				//commit the transaction
				session.getTransaction().commit();
				System.out.println("Done!");
			}
			catch(NullPointerException e) {
				e.printStackTrace();
			}
			
		}
		finally {
			
			//add clean up code
			session.close();
			
			sessionFactory.close();
			
		}
	}

}
