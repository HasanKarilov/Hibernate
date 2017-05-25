package com.devcolibri;


import com.devcolibri.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EnterPoint
{
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("Maven + Hibernate + MySQL");
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        User user = new User();

        user.setFirstName("Hasan");
        user.setLastName("Karilov");

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }


}
