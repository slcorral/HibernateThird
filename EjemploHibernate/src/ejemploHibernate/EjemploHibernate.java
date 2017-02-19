/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploHibernate;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author EMiSaRiO
 */
public class EjemploHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        /**MAIN FUNCIONAL 
        
        ContactosDAO contactosDao=new ContactosDAO();
        
        Contacto contactoRecuperado=null;
        long idAEliminar=0;
        
        //Creamos tres objetos de tipo Contacto.
        Contacto contacto1=new Contacto("Contacto 1","contacto1@contacto.com","12345678");
        Contacto contacto2=new Contacto("Contacto 2","contacto2@contacto.com","87654321");
        Contacto contacto3=new Contacto("Contacto 3","contacto3@contacto.com","45612378");
        
        //Guardamos las tres instancias, guardamos el id del contacto 1 para usarlo posteriormente.
        
        idAEliminar= contactosDao.guardaContacto(contacto1);
        
        contactosDao.guardaContacto(contacto2);
        contactosDao.guardaContacto(contacto3);
        
        //Modificamos el contacto2 y lo actualizamos.
        
        contacto2.setNombre("Nuevo Contacto 2");
        contactosDao.actualizaContacto(contacto2);
        
        //Recuperamos el contacto1 de la base de datos
        
        contactoRecuperado=contactosDao.obtenContacto(idAEliminar);
        System.out.println("Recuperamos a "+contactoRecuperado.getNombre());
        
        //Eliminamos el contactoRecuperado(que es el contacto1)
        contactosDao.eliminaContacto(contactoRecuperado);
        
        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos.
        
        List<Contacto> listaContactos= contactosDao.obtenListaContactos();
        
        System.out.println("Hay "+ listaContactos.size()+ "contactos en la base de datos");
        
        for(Contacto c: listaContactos)
        {
            System.out.println("-> "+ c.getNombre());
            
        }
        * 
        * **/
        
        //Clase que contiene los metodos de CRUD
        ContactosDAO contactosDao=new ContactosDAO();
 
        Contacto contacto1=new Contacto("Saul","socemisario@hotmail.com","123123");
        long id=contactosDao.guardaContacto(contacto1);
        System.out.println("ID "+id);
        Contacto contacto2=new Contacto("Luis","luis@hotmail.com","23423423");
        
        id=contactosDao.guardaContacto(contacto2);
        
        System.out.println("Actualizamos un contacto");
        
        contacto1.setEmail("slcorral@gmail.com");
        contactosDao.actualizaContacto(contacto1);
        
        contacto2.setEmail("luis@gmail.com");
        contactosDao.actualizaContacto(contacto2);
        
        //creamos un contacto y luego lo borramos.
        Contacto contacto3=new Contacto("Antonio","antonio@hotmail.com","3435677");
        
        id=contactosDao.guardaContacto(contacto3);
        System.out.println("Id del contacto que insertamos y luego eliminamos "+id);
        contactosDao.eliminaContacto(contacto3);
        
        //bucle recuperacion de los 3 contactos.
    
        Contacto contactoRecuperado=null;
        
        for(int i=1;i<3;i++)
        {
            contactoRecuperado=contactosDao.obtenContacto(i);
            System.out.println("Id "+contactoRecuperado.getId()+" Nombre "+contactoRecuperado.getNombre()+
                    " Email "+contactoRecuperado.getEmail());
           
        }
        
        /** EN el caso de utilizar un identificador que no existe en la base de datos
         * Devolveria un null, es decir, no encontraria un objeto en la base de datos.
         * Se traduciria a select * from contactos where id=-1; y nos da 0 rows, empty set.
         * Por lo tanto, NO FALLA, SOLO QUE NO DEVUELVE OBJETOS
         * **/
        
        contactoRecuperado=contactosDao.obtenContacto(-1);
        
        /**Recuperamos Todos los contactos de la tabla Contactos
         * y los metemos en una lista para procesarlos.
         * 
         * */
  
        List<Contacto> lista=contactosDao.obtenListaContactos();
        
        System.out.println("Tamaño de la Lista "+lista.size());
        
        Iterator<Contacto> it=null;
        
        //Recorremos la lista para sacar toda la informacion
        
        it=lista.iterator();
       
        for(it=lista.iterator();it.hasNext();)
        {
            //Casteamos y mostramos.
            Contacto aux=(Contacto)it.next();
            System.out.println(aux);   
        }
        
        //Obtenemos los objetos de la lista los modificamos y los guardamos.
        
        it=lista.iterator();

        for(it=lista.iterator();it.hasNext();)
        {
            Contacto aux=(Contacto)it.next();
            //Modificamos el telefono de todos
            aux.setTelefono("34");

            /**
             * Para modificar el objeto necesitamos la propiedad ID que ya se encargo
             * la base de datos de asignar y Hibernate de cargar en la propiedad ID de la clase Contacto.
             * 
             * Se encargara de hacerlo todo el método actualizarContacto.
             */
             contactosDao.actualizaContacto(aux); 
        }
        
        
        
        /**PRUEBAS PARA LA COMPRENSIÓN
         * 
         * 
         * En el caso de realizar dos inserciones del mismo objeto.
         * Insertaria dos objetos iguales en la base de datos, pero con distinto ID
         * Siendo el ID la clave primaria, por lo tanto serian dos registros distintos.
         * En nuestro caso teniamos 1 saul saul@hotmail.com 123123
         * A continuacion tendriamos 4 saul saul@hotmail.com 123123
         * 
         * CONCLUSIÓN: EL ID te lo asigna la base de datos y es disntinto.
         * 
         * 
         * contactosDao.guardaContacto(contacto1);
         * 
         * 
         */
 
    
    }
    
}
