package com.example.employeedemo;

import com.example.employeedemo.entity.Employee;
import com.example.employeedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Demonstrates saving, reading, updating and deleting Employee rows.
 */
public class MainApp {

    public static void main(String[] args) {
        try {
            // CREATE
            Long aliceId;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Employee alice = new Employee("Alice", "Johnson", 50000);
                Employee bob = new Employee("Bob", "Smith", 42000);
                Employee carol = new Employee("Carol", "Davis", 61000);
                session.persist(alice);
                session.persist(bob);
                session.persist(carol);
                tx.commit();
                aliceId = alice.getId();
            }
            System.out.println("\n=== Employees saved ===");
            printAll();

            // UPDATE
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Employee alice = session.find(Employee.class, aliceId);
                alice.setSalary(55000);
                tx.commit();
            }
            System.out.println("\n=== After updating Alice's salary ===");
            printAll();

            // DELETE
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                Employee alice = session.find(Employee.class, aliceId);
                session.remove(alice);
                tx.commit();
            }
            System.out.println("\n=== After deleting Alice ===");
            printAll();

        } finally {
            HibernateUtil.shutdown();
        }
    }

    // READ
    private static void printAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Employee> employees = session
                    .createQuery("from Employee", Employee.class)
                    .list();
            employees.forEach(System.out::println);
        }
    }
}
