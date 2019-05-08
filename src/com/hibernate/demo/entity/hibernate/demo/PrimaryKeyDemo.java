package com.hibernate.demo.entity.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {


        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();


        try {


            //create  3 student object
            System.out.println("Creating 3 Student objects...");
            Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
            Student tempStudent2 = new Student("Marry", "Public", "marry@gmail.com");
            Student tempStudent3 = new Student("Adam", "Nowak", "adam@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
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
