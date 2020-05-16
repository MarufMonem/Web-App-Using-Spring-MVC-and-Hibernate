package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the student object
			System.out.println("Craeting a new student object");
			Student tempStudent = new Student("Daffy", "duck", "DuckDaffy@yahoo.com");
					
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			//get a new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			//retrieve the student based on the id: primary key
			System.out.println("Getting student with id: " + tempStudent.getId());
			
			Student mystudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + mystudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			
			
			System.out.println("done");
			
		
		} finally {
			factory.close();
		}
	}

}
