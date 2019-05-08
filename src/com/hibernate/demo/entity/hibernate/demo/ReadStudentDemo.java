package com.hibernate.demo.entity.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);
            //commit transaction

            session.getTransaction().commit();

            //------------NEW CODE -------------
            System.out.println("Saved .student Generated id: " + tempStudent.getId());



            //new session and transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\ngetting student with id: " +tempStudent.getId());
            Student myStudent= session.get(Student.class,tempStudent.getId());

            System.out.println("get complete : " + myStudent);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception exc) {
            exc.printStackTrace();


        } finally {
            factory.close();
        }

    }
}
