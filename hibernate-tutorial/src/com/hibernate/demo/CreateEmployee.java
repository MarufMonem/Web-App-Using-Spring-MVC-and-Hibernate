package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Employee;

public class CreateEmployee {

	public static void main(String[] args) {
	
		//create a session factory
		
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
				//create a session
				Session session = factory.getCurrentSession();
				try {
				//create the student object
				System.out.println("Craeting a new employee object");
				Employee tempEmployee1 =  new Employee("Rahim", "Mincha", "Mincha Brothers");		
				Employee tempEmployee2 =  new Employee("Karim", "Mincha", "Mincha Brothers");		
				Employee tempEmployee3 =  new Employee("Ashaq", "Rahman", "Cookers and co");		
				Employee tempEmployee4 =  new Employee("Alif", "Rahman", "Timex");		
				
				//start a transaction
				session.beginTransaction();
				
				//save the student object
				System.out.println("Saving employee1");
				session.save(tempEmployee1);
				System.out.println("Saving  employee2");
				session.save(tempEmployee2);
				System.out.println("Saving  employee3");
				session.save(tempEmployee3);
				System.out.println("Saving  employee4");
				session.save(tempEmployee4);
				
				//commit transaction
				session.getTransaction().commit();
				System.out.println("\n\n Added  employees to the table");
			} finally {
				factory.close();
			}
	}

}
