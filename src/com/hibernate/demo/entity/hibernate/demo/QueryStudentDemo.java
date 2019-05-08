package com.hibernate.demo.entity.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {


        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();


        try {
            //start a transaction
            session.beginTransaction();


            List<Student> theStudents= session.createQuery("from Student").list();

            displayStudents(theStudents);

            theStudents= session.createQuery("from Student s where s.lastName='Doe'").list();

            System.out.println("\nfrom Student s where s.lastName='Doe'");

            displayStudents(theStudents);

            theStudents =session.createQuery("from Student s where"
                                            + " s.lastName='Doe' OR s.firstName='Daffy'").list();
            System.out.println("\nfrom Student s where s.lastName='Doe' OR s.firstName='Daffy'");
            displayStudents(theStudents);

            // another query with like

            System.out.println("\nfrom Student with Gmail account");
            theStudents = session.createQuery("from Student s where"
                    + " s.email like '%gmail.com'").list();
            displayStudents(theStudents);

            //displaying students

            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception exc) {
            exc.printStackTrace();


        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent:theStudents){
            System.out.println(tempStudent);
        }
    }
}
