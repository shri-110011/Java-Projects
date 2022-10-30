package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;
import com.shri.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewsDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//get the course
			int theId = 1;
			Course tempCourse = session.get(Course.class, theId);
			
			//print the course
			System.out.println("Deleting the course...");
			System.out.println(tempCourse);
			
			//print the course reviews
			System.out.println(tempCourse.getReviews());
			
			//delete the course
			session.delete(tempCourse);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		}
		finally {
			
			//add clean up code
			session.close();
			
			sessionFactory.close();
			
		}
	}

}
