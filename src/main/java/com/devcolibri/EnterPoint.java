package com.devcolibri;


import com.devcolibri.entity.Developer;
import com.devcolibri.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EnterPoint
{
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        System.out.println("Maven + Hibernate + MySQL");

        EnterPoint enterPoint = new EnterPoint();

        enterPoint.addDev("Adilet", "Ruby Developer", 35000);
        enterPoint.addDev("Ulan", "Php Developer", 30000);
        enterPoint.updateDev(3, 40000);


        for(Developer developer: enterPoint.listDevs())
        {
            System.out.println(developer.toString());
        }
    }

    private List<Developer> listDevs(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Developer> devs = session.createQuery("from Developer").list();

        transaction.commit();
        session.close();
        return devs;
    }

    private void addDev(String name, String specialty, int salary){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Developer dev = new Developer(name, specialty, salary);
        session.save(dev);
        session.getTransaction().commit();
        session.close();
    }

    private void updateDev(int iD, int newSalary){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Developer dev = (Developer) session.get(Developer.class, iD);
        dev.setSalary(newSalary);
        session.update(dev);

        transaction.commit();
        session.close();
    }

}
