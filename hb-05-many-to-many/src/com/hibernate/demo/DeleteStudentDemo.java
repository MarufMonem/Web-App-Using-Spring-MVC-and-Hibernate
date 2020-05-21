package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			int sanId=1;
			Student tempStudent= session.get(Student.class, sanId);
			System.out.println("\n The Student is: " + tempStudent.getFirstName());
			System.out.println("\n Her courses are: " + tempStudent.getCourses());
			
			System.out.println("Deleting Student");
			session.delete(tempStudent);
			
			printCourses(tempStudent);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

	private static void printCourses(Student tempStudent) {
		List<Course> list = tempStudent.getCourses();
		for (Course a:list) {
			System.out.println(a.getTitle());
		}
	}

}
