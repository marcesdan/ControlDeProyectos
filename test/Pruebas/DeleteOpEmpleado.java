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
public class DeleteOpEmpleado {
    
    //Factory de las clases de dominio
    private final DominioFactory dominioFactory;

    //Clases de dominio
    private Empleado marces;
    private Proyecto p1, p2;
    private Asignacion marcesP1, marcesP2;

    //Clases DAO
    private final EmpleadoDao empleadoDao;
    private final ProyectoDao proyectoDao;
    private final AsignacionDao asignacionDao;

    public DeleteOpEmpleado() {

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
                .withFechaInicio(LocalDate.now())
                .withPresupuesto(1000);
        
        // creamos al proyecto p1
        p2 = dominioFactory.crearProyecto()
                .withDescripcion("Proyecto 2")
                .withContratista("OMG")
                .withFechaInicio(LocalDate.now())
                .withPresupuesto(1000);

        // asignamos marces y p1
        marcesP1 = new DominioFactory().crearAsignacion()
                .withEmpleado(marces)
                .withProyecto(p1)
                .withPuesto("supervisor")
                .withPago(500);
        
        // asignamos marces y p1
        marcesP2 = new DominioFactory().crearAsignacion()
                .withEmpleado(marces)
                .withProyecto(p2)
                .withPuesto("analista")
                .withPago(500);

        empleadoDao.create(marces); // persistimos a marces
        proyectoDao.create(p1);     // persistimos al proyecto p1
        proyectoDao.create(p2);     // persistimos al proyecto p2

        // persistimos una asignación entre marces y p1
        asignacionDao.create(marcesP1);
        // persistimos una asignación entre marces y p2
        asignacionDao.create(marcesP2);

        // guardamos la asignación en ambas listas.
        marces.addAsignacion(marcesP1);
        marces.addAsignacion(marcesP2);
        p1.addAsignacion(marcesP1);
        p1.addAsignacion(marcesP2);

        // actualizamos
        empleadoDao.update(marces);
        proyectoDao.update(p1);
        proyectoDao.update(p2);
    }

    @After
    public void TearDown() {
        proyectoDao.delete(p1);     // borramos el proyecto p1
        proyectoDao.delete(p2);     // borramos el proyecto p1
    }

    /**
     * Se comprueba que se elimina el empleado, y con él todas sus asignaciones.
     * Con lo cual es necesario actualizar la lista de cada uno de los proyectos
     * en los cuales participaba el empleado.
     * 
     * Luego, se realiza una lectura a la base de datos y se vuelve a comprobar 
     * (post-delete).
     *
     * De las operaciones CRUD se prueba "READ" y "DELETE". (read post-delete)
     */
    @Test
    public void pruebaBorrarEmpleado() {
        
        // Se elimina el empleado de la BD
        empleadoDao.delete(marces);
        //Se necesita eliminar elementos de la lista
        Iterator<Asignacion> i = marces.getAsignaciones().iterator();
        while (i.hasNext()) {
            Asignacion asignacion = i.next();
            // Obtenemos el proyecto
            Proyecto proyecto = asignacion.getProyecto();
            // Eliminamos la asignación del empleado a borrar
            proyecto.removeAsignacion(asignacion);
            // Actualizamos
            proyectoDao.update(proyecto);
            // Y se elimina el elemento de la lista
            i.remove();
        }
        
        assertTrue("1) La asignación aun se encuentra en la lista", 
                !p1.getAsignaciones().contains(marcesP1));
        assertTrue("2 )La asignación aun se encuentra en la lista", 
                !p2.getAsignaciones().contains(marcesP2));
        
        p1 = proyectoDao.read(p1.getId());
        assertTrue("3) La asignación aun se encuentra en la lista", 
                !p1.getAsignaciones().contains(marcesP1));
        
        p2 = proyectoDao.read(p2.getId());
        assertTrue("4) La asignación aun se encuentra en la lista", 
                !p2.getAsignaciones().contains(marcesP2));
    }
}