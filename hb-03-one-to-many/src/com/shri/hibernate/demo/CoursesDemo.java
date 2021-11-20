package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class CoursesDemo {

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
			
			
			//get the instructor from db
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			try {
				
				// Note: We can't create these Course objects and save along while saving Instructor object
				// in CreateInstructorDemo class because while saving the Instructor, this Instructor object
				// along with its associated object(InstructorDetail) will be stored since instructor_detail_id
				// is the foreign key in instructor table and associates instructor to instructor_detail.
				
				//create the Course objects
				Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
				Course tempCourse2 = new Course("The Pinball Masterclass");
				
				tempInstructor.addCourse(tempCourse1);
				tempInstructor.addCourse(tempCourse2);
				
				//save the Course objects
				session.save(tempCourse1);
				session.save(tempCourse2);
				
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
