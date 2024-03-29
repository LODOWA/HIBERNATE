package com.hibernate.demo.entity.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {


        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();


        try {


            //create student object
            System.out.println("Creating a new Student object...");
            Student tempStudent = new Student("Paul", "Wall", "demo@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);
            //commit transaction

            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception exc) {
            exc.printStackTrace();


        } finally {
            factory.close();
        }

    }
}
