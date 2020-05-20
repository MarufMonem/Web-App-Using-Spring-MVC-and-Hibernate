package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			
			int courseId=10;
			
			Course tempCourse = session.get(Course.class, courseId);
			System.out.println("The deleteing course is: " + tempCourse.getTitle());
			session.delete(tempCourse); //cascade delete all the reviews
			
			System.out.println("The corresponding Reviews are: ");
			System.out.println(tempCourse.getReviews());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
