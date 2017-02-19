/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package ejemploHibernate;

import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author EMiSaRiO
 */
public class ContactosDAO {
    
    private Session sesion;
    private Transaction tx;
    
    public long guardaContacto(Contacto contacto) throws HibernateException
    {
        /**Funcional 
        long id=0;
        
        try
        {
            iniciaOperacion();
            
            id=(Long) sesion.save(contacto);
            tx.commit();
        }
        catch(HibernateException he)
        {
            manejaException(he);
            throw he;
        
        } finally
        {
            
            sesion.close();
        }
        
        return id;   
        * **/
        
        long id_devuelto=0;
        
        try
        {
            //Obtenemos una session para esta operacion y abrimos una transaccion
            iniciaOperacion();
            //guardamos el objeto en la base de datos.
             id_devuelto=(Long) sesion.save(contacto);
            //realizamos el commit de la transaccion
            tx.commit();
        }
        catch(HibernateException he)
        {
            //metodo que maneja la excepcion y realiza un rollback de la base de datos.
            manejaException(he);
            throw he;
   
        }finally
        {
            //siempre cerramos la sesion, independientemente de lo que pase.
            sesion.close(); 
        }
        
        return id_devuelto;
    }
    
    public void actualizaContacto(Contacto contacto) throws HibernateException
    {
        /** Bloque Funcional
        try
        {
            iniciaOperacion();
            sesion.update(contacto);
            tx.commit();
        }
        catch(HibernateException he)
        {
            
            manejaException(he);
            throw he;
        } finally //sentencia que se ejecuta siempre, independientemente de si hacemos try o catch
        {
          sesion.close();   
        }
        * **/
        
        try
        {
            //Obtenemos una sesion para esta operacion y iniciamos una transaccion
            iniciaOperacion();
            //actualizamos el objeto
            sesion.update(contacto);
            //iniciamos el commit de la transaccion
            tx.commit();
        }
        catch(HibernateException he)
        {
            //manejamos la transacción y hace rollback de la base de datos
            manejaException(he);
            throw he;
        }
        finally
        {
            //cerramos la sesion siempre
            sesion.close();
        }
                
     }
    
    public void eliminaContacto(Contacto contacto) throws HibernateException
    {
        /**Eliminamos el Contacto
         * 
        try
        {
            iniciaOperacion();
            sesion.delete(contacto);
            tx.commit();
            
        }catch(HibernateException he)
        {
            manejaException(he);
            throw he;
            
        }finally
        {
            sesion.close();
        }
        * 
        * **/
        
        try
        {
            //Abrimos la sesion y iniciamos una transaccion
            iniciaOperacion();
            sesion.delete(contacto);
            //hacemos el commit de la transaccion
            tx.commit();
        }
        catch(HibernateException he)
        {
            //Manejamos la excepcion y  luego realizamos rollback.
            manejaException(he);
            throw he;
            
        }
        finally
        {
            //independientemente cerramos la sesion.
            sesion.close();

        }

    }
    
    public Contacto obtenContacto(long idContacto) throws HibernateException
    {
       
        /** Funcional
        Contacto contacto=null;
        
        try
        {
            iniciaOperacion();
            contacto=(Contacto)sesion.get(Contacto.class,idContacto);
            
        } finally
        {
            sesion.close();
            
        }
        
        return contacto;
       
        **/
        
        Contacto contactoRecuperado=null;
        
        try
        {
            //abrimos una sesion y iniciamos una transaccion
            iniciaOperacion();
            contactoRecuperado=(Contacto)sesion.get(Contacto.class,idContacto);
            tx.commit();
            
        }
        catch(HibernateException he)
        {
            //si ocurre una excepcion la capturamos, y realizamos rollback de la base de datos.
               manejaException(he);
               throw he;
        }
        finally
        {
            //cerramos la sesion siempre.
            sesion.close();
        }
        
        return contactoRecuperado;
   
    }
    
    public List<Contacto> obtenListaContactos() throws HibernateException
    {
        /** Funcional
        List<Contacto> listaContactos=null;
        
        try
        {
            iniciaOperacion();
            listaContactos= sesion.createQuery("from Contacto").list();
        }finally
        {
            sesion.close();
        }
            
        return listaContactos;
        * 
        * **/
        
        List<Contacto> listaContactos=null;
        
        try
        {
            //Abrimos la sesion y iniciamos una transaccion
            iniciaOperacion();
            listaContactos=sesion.createQuery("from Contacto").list();
           
        }catch(HibernateException he)
        {
            //En el caso de error, manejamos la excepcion y hacemos rollback.
            manejaException(he);
            throw he;
        
        }finally
        {
            //cerramos la sesion
            sesion.close();
        }
    
        return listaContactos;
        
    }
   
    private void iniciaOperacion() throws HibernateException
    {
       
        sesion= HibernateUtil.getSessionFactory().openSession();
        tx= sesion.beginTransaction();
        
    }
    
    private void manejaException(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos",he);    
    }
       

}
