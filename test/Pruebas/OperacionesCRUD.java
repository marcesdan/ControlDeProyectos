/*
 * UNTDF - Laboratorio de programación y lenguajes (2017)
 */
package Pruebas;

import dao.AsignacionDao;
import dao.DaoFactory;
import dao.EmpleadoDao;
import dao.ProyectoDao;
import dominio.Asignacion;
import dominio.DominioFactory;
import dominio.Empleado;
import dominio.Proyecto;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author marces
 */
public class OperacionesCRUD {
    
    //Factory de las clases de dominio
    private static DominioFactory dominioFactory;
    
    //Clases de dominio
    private static Empleado marces;
    private static Proyecto p1;
    private static Asignacion marcesP1;
    
    //Clases DAO
    private static EmpleadoDao empleadoDao;
    private static ProyectoDao proyectoDao;
    private static AsignacionDao asignacionDao;
    
    @BeforeClass
    public static void setUpClass() {
        
        DaoFactory daoFactory = new DaoFactory();
        
        empleadoDao = daoFactory.crearEmpleadoDao();
        proyectoDao = daoFactory.crearProyectoDao();
        asignacionDao = daoFactory.crearAsignacionDao();
        dominioFactory = new DominioFactory();
        
        // creamos al empleado marces
        marces = dominioFactory.crearEmpleado()
                .withNombre("Marces")
                .withApellido("Dan")
                .withNacimiento(LocalDate.now())
                .withDocumento(37533200)
                .withCelular("test")
                .withEmail("test@gmail.com");

        // creamos al proyecto p1
        p1 = dominioFactory.crearProyecto()
                .withDescripcion("Proyecto 1")
                .withContratista("OMG")
                .withtFechaInicio(LocalDate.now())
                .withPresupuesto(1000);

        // asignamos marces y p1
        marcesP1 = new DominioFactory().crearAsignacion()
                .withEmpleado(marces)
                .withProyecto(p1)
                .withPuesto("supervisor")
                .withPago(500);
        
        empleadoDao.create(marces); // persistimos a marces
        proyectoDao.create(p1);     // persistimos al proyecto p1
    }

    /**
     * Se establece una relación proyecto-empleado y se comprueba que se agregan
     * mutuamente las referencias de forma correcta. 
     * 
     * De las operaciones CRUD se prueban "CREATE" y "UPDATE"
     */
    @Test
    public void pruebaNuevaAsignacion() {
        
        // persistimos una asignación entre marces y p1
        asignacionDao.create(marcesP1); 
        
        // guardamos la asignación en ambas listas.
        marces.addAsignacion(marcesP1);
        p1.addAsignacion(marcesP1);
        
        // actualizamos
        empleadoDao.update(marces);    
        proyectoDao.update(p1);
        
        /*Lo anterior debería haber sido hecho en una transacción (atómicamente)*/
        
        // comprobamos que fueron ingresadas
        assertFalse("ESTA VACÍO!", marces.getAsignaciones().isEmpty());
        assertFalse("ESTA VACÍO!", p1.getAsignaciones().isEmpty());
    }
    
    /**
     * Se realiza una lectura a la base de datos y se vuelve a comprobar el 
     * estado de las referencias.
     * 
     * De las operaciones CRUD se prueba "READ"
     */
    @Test
    public void pruebaLeerAsignacion() {
        
        // obtenemos de la base de datos el empleado marces
        marces = empleadoDao.read(marces.getId());  
        // obtenemos de la base de datos el proyecto p1
        p1 = proyectoDao.read(p1.getId());
        
        // algunas comprobaciones
        assertFalse("p1 no existe!", p1 == null);
        assertFalse("marces no existe!", marces == null);
        assertFalse("asignaciones es null!", p1.getAsignaciones() == null);
        
        // volvemos a comprobar que estan presentes
        assertFalse("ESTA VACÍO!", marces.getAsignaciones().isEmpty());
        assertFalse("ESTA VACÍO!", p1.getAsignaciones().isEmpty());
        
       marcesP1 = asignacionDao.read(marcesP1.getId());
       assertTrue("La ref. del empleado en la asignacion NO concuerda!", 
                marcesP1.getEmpleado().equals(marces));
       
       assertTrue("La ref. del proyecto en la asignacion NO concuerda!", 
                marcesP1.getProyecto().equals(p1));
    }
    
    /**
     * Se realiza una lectura a la base de datos y se vuelve a comprobar el 
     * estado de las referencias.
     * 
     * De las operaciones CRUD se prueba "READ"
     */
    @Test
    public void pruebaBorrarAsignacion() {
        
    }
}
