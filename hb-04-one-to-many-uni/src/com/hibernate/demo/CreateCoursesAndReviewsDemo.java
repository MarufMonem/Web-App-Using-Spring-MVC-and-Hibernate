package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

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
			
			Course tempCourse = new Course("CSE220");
			tempCourse.addReview(new Review("VERY HARD"));
			tempCourse.addReview(new Review("Loved it"));
			tempCourse.addReview(new Review("Hard but can be managed"));
			
			session.save(tempCourse); //save the course in the DB and save all the reviews because of cascade feature.
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
