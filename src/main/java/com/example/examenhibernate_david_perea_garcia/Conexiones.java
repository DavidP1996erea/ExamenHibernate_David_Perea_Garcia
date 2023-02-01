package com.example.examenhibernate_david_perea_garcia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Conexiones {

    // Atributos
    private  static SessionFactory sessionFactory;

    private static Transaction transaction;
    private static Session session;


    /**
     * Método que abre una transacción
     * @return
     */
    public static Transaction abrirTransaccion(){

        return  transaction = session.beginTransaction();
    }

    /**
     * Método que realiza un commit
     */
    public static void commitTransaccion(){

        transaction.commit();
    }

    /**
     * Método que realiza un rollback
     */
    public static void rollBackTransaccion(){
        transaction.rollback();
    }
    /**
     * Este método devuelve una sesión abierta
     * @return
     */
    public static Session abrirSesion(){

        return session = sessionFactory.openSession();
    }


    /**
     * Método que cerrará la sesión
     */
    public static void cerrarSesion(){

        sessionFactory.close();
    }


    /**
     * Método setUp que conectará con la base de datos
     * @throws Exception
     */
    public static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {

            e.printStackTrace();
           // StandardServiceRegistryBuilder.destroy( registry );
        }
    }


}
