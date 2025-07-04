package com.cognizant.orm_learn.hibernate;

import com.cognizant.orm_learn.model.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateEmployeeDao {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @SuppressWarnings("deprecation")
	public Integer addEmployee(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employeeID;
    }
}
