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
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marces
 */
public class DeleteOpProyecto {
    
    //Factory de las clases de dominio
    private final DominioFactory dominioFactory;

    //Clases de dominio
    private Empleado marces1, marces2;
    private Proyecto p1;
    private Asignacion marces1P, marces2P;

    //Clases DAO
    private final EmpleadoDao empleadoDao;
    private final ProyectoDao proyectoDao;
    private final AsignacionDao asignacionDao;

    public DeleteOpProyecto() {

        DaoFactory daoFactory = new DaoFactory();

        empleadoDao = daoFactory.crearEmpleadoDao();
        proyectoDao = daoFactory.crearProyectoDao();
        asignacionDao = daoFactory.crearAsignacionDao();
        dominioFactory = new DominioFactory();
    }
    
    /**
     * Se establece una relación proyecto-empleado.
     */
    @Before
    public void setUp() {

        // creamos al empleado marces
        marces1 = dominioFactory.crearEmpleado()
                .withNombre("Marces")
                .withApellido("Dan")
                .withNacimiento(LocalDate.now())
                .withDocumento(37533200)
                .withCelular("test")
                .withEmail("test@gmail.com");
        empleadoDao.create(marces1);
        
        marces2 = dominioFactory.crearEmpleado()
                .withNombre("Marces")
                .withApellido("Dan")
                .withNacimiento(LocalDate.now())
                .withDocumento(44444458)
                .withCelular("test")
                .withEmail("test@gmail.com");
        empleadoDao.create(marces2); 

        // creamos al proyecto p1
        p1 = dominioFactory.crearProyecto()
                .withDescripcion("Proyecto 1")
                .withContratista("OMG")
                .withFechaInicio(LocalDate.now())
                .withPresupuesto(1000);
        proyectoDao.create(p1);

        // asignamos marces y p1
        marces1P = new DominioFactory().crearAsignacion()
                .withEmpleado(marces1)
                .withProyecto(p1)
                .withPuesto("supervisor")
                .withPago(500);
        
        // asignamos marces y p2
        marces2P = new DominioFactory().crearAsignacion()
                .withEmpleado(marces2)
                .withProyecto(p1)
                .withPuesto("supervisor")
                .withPago(500);
        // persistimos una asignación entre marces y p1
        asignacionDao.create(marces1P);
        asignacionDao.create(marces2P);

        // guardamos la asignación en ambas listas.
        marces1.addAsignacion(marces1P);
        marces2.addAsignacion(marces2P);
        
        p1.addAsignacion(marces1P);
        p1.addAsignacion(marces2P);

        // actualizamos
        empleadoDao.update(marces1);
        empleadoDao.update(marces2);
        proyectoDao.update(p1);
    }

    @After
    public void TearDown() {

        // De paso probamos la operacion CRUD "DELETE".
        empleadoDao.delete(marces1); // borramos a marces
        empleadoDao.delete(marces2); 
    }
    
     /**
     * Se comprueba que se elimina el proyecto, y con él todas sus asignaciones.
     * Con lo cual es necesario actualizar la lista de cada uno de los empleados
     * que participaban en ese proyecto.
     * 
     * Luego, se realiza una lectura a la base de datos y se vuelve a comprobar 
     * (post-delete).
     *
     * De las operaciones CRUD se prueba "READ" y "DELETE". (read post-delete)
     */
    @Test
    public void pruebaBorrarProyecto() {
        
        // Se elimina el proyecto de la BD
        proyectoDao.delete(p1);
        //Se necesita eliminar elementos de la lista
        Iterator<Asignacion> i = p1.getAsignaciones().iterator();
        while (i.hasNext()) {
            Asignacion asignacion = i.next();
            // Obtenemos el empleado
            Empleado empleado = asignacion.getEmpleado();
            // Eliminamos la asignación al proyecto a borrar
            empleado.removeAsignacion(asignacion);
            // Actualizamos
            empleadoDao.update(empleado);
            // Y se elimina el elemento de la lista
            i.remove();
        }
        
        assertTrue("1) La asignación aun se encuentra en la lista", 
                !marces1.getAsignaciones().contains(marces1P));
        assertTrue("2 )La asignación aun se encuentra en la lista", 
                !marces2.getAsignaciones().contains(marces2P));
        
        marces1 = empleadoDao.read(marces1.getId());
        assertTrue("3) La asignación aun se encuentra en la lista", 
                !marces1.getAsignaciones().contains(marces1P));
        
        marces2 = empleadoDao.read(marces2.getId());
        assertTrue("4) La asignación aun se encuentra en la lista", 
                !marces2.getAsignaciones().contains(marces2P));
    }
}
