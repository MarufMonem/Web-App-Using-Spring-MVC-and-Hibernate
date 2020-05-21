package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

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
			
			Course tempCourse1 = new Course("CSE110");
			Course tempCourse2 = new Course("CSE111");
			Course tempCourse3 = new Course("CSE220");
			Course tempCourse4 = new Course("CSE221");

			System.out.println("Saving the courses");
			session.save(tempCourse1); 
			System.out.println("saved the courses" + tempCourse1.getTitle());
			session.save(tempCourse2);
			System.out.println("saved the courses" + tempCourse2.getTitle());
			session.save(tempCourse3);
			System.out.println("saved the courses" + tempCourse3.getTitle());
			session.save(tempCourse4);
			System.out.println("saved the courses" + tempCourse4.getTitle());
			
			//create the students
			Student tempStudent1 = new Student("Maruf", "Monem", "Anik@gmail.com");
			Student tempStudent2 = new Student("Rashad", "Ahmed", "Rashad@gmail.com");
			Student tempStudent3 = new Student("Alif", "Ahmad", "Alif@yahoo.com");
			Student tempStudent4 = new Student("Sanjida", "Mim", "Mim@hotmail.com");
			
			//add students to the course
			tempCourse1.addStudent(tempStudent1);
			tempCourse1.addStudent(tempStudent2);
			tempCourse1.addStudent(tempStudent3);
			tempCourse1.addStudent(tempStudent4);
			tempCourse2.addStudent(tempStudent2);
			tempCourse2.addStudent(tempStudent4);
			tempCourse3.addStudent(tempStudent4);
			tempCourse3.addStudent(tempStudent2);
			tempCourse4.addStudent(tempStudent1);
			
			//save the students
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.save(tempStudent4);
			
			System.out.println(session.get(Student.class, 1).getCourses());
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
