package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class AddCoursesForSanjidaDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			//get sanjida from the database
			int sanId=4;
			Student tempStudent= session.get(Student.class, sanId);
			System.out.println("\n The Student is: " + tempStudent.getFirstName());
			System.out.println("\n Her courses are: " + tempStudent.getCourses());
			
			//create more courses
			Course tempCourse1 = new Course("CSE320");
			Course tempCourse2 = new Course("CSE321");
			
			//add mary to the the course
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save course
			System.out.println("Saving .. ... ");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
