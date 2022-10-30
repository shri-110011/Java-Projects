package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;
import com.shri.hibernate.demo.entity.Review;
import com.shri.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//create a course
			
			Course tempCourse = new Course("Pacman - How To Score One Million Points");
			
			
			System.out.println("Saving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: "+tempCourse);
			
			//create the students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			//add students to the course
			System.out.println("Saving the students ...");
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
		
//			save the students
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: "+tempCourse.getStudents());
				
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
