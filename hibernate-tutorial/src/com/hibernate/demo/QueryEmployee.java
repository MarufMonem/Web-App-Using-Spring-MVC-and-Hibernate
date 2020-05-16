package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Employee;
import com.hibernate.demo.entity.Student;

public class QueryEmployee {

	public static void main(String[] args) {
		//create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();

		try {
			//get a new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();

			//query Employees
			List <Employee> theEmployee = session.createQuery("from Employee where company = 'Mincha Brothers' ").getResultList();
			displayEmployee(theEmployee);

			//commit the transaction
			session.getTransaction().commit();




			System.out.println("done");
		} finally {
			factory.close();
		}



	}
	private static void displayEmployee(List<Employee> theEmployee) {
		for(Employee tempEmployee : theEmployee) {
			System.out.println(tempEmployee + "\n");
		}
	}
}
