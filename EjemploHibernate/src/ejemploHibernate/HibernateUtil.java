/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploHibernate;

import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author EMiSaRiO
 */
public class HibernateUtil {
    
   //Antiguo private static final SessionFactory sessionFactory;
    private  static  SessionFactory sessionFactory;
    private static  ServiceRegistry serviceRegistry;
    
  
    //bloque de inicialización de la variable statica.
    static
    {
        try
        {
           /**
           Al llamar al método configure() sin parametros del Objeto Configuration,
           * 
           * Le decimos automaticamente que escoja el archivo hibernate.cfg.xml.
            * **/
            
             sessionFactory= new Configuration().configure().buildSessionFactory();
           
        }
        catch(HibernateException he)
        {
            System.err.println("Ocurrio un error en la inicializacion de la SessionFactory: "+he);
            throw new ExceptionInInitializerError(he);
        }
        
    }
  

    public static SessionFactory getSessionFactory()
    {
    /**
       System.out.println("1");
       Configuration configuration = new Configuration();
       System.out.println("2");
       configuration.configure();
       System.out.println("3");
       serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
       System.out.println("4");
       sessionFactory =  configuration.buildSessionFactory(serviceRegistry);
       System.out.println("5");
       if(sessionFactory!=null)
           System.out.println("Distinto null");
       else
           System.out.println("Null");
           * **/
        
        return sessionFactory;
    }



}


