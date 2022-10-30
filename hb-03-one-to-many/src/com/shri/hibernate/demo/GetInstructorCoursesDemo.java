package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

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
			
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			try {
				
				System.out.println("Instructor: "+tempInstructor);
				
				//get the courses for the instructor
				System.out.println("Courses: "+tempInstructor.getCourses());
				
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
