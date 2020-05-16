package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List <Student> theStudent = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudent);
			
			//query students with last name = Monem
			theStudent = session.createQuery("from Student s where s.lastName = 'Monem' ").getResultList();
			
			//Printing the new students
			System.out.println("\n\n The students with last name of Monem" + "\n");
			displayStudents(theStudent);
			
			//query students: last name: monem or first name Sanjida
			theStudent = session.createQuery("from Student s where s.lastName = 'Monem' OR s.firstName = 'Sanjida' ").getResultList();
			//Printing the new students
			System.out.println("\n\n The students with last name of Monem and first name is Alif" + "\n");
			displayStudents(theStudent);
			
			
			//query students: email ends with yahoo
			theStudent = session.createQuery("from Student s where s.email LIKE '%yahoo.com' ").getResultList();
			//Printing the new students
			System.out.println("\n\n The students with email address that ends with yahoo.com" + "\n");
			displayStudents(theStudent);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudent) {
		for(Student tempStudent : theStudent) {
			System.out.println(tempStudent + "\n");
		}
	}

}
