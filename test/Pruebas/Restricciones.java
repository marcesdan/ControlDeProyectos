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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author marces
 */
public class Restricciones {

    //Factory de las clases de dominio
    private final DominioFactory dominioFactory;
    
    //Clases de dominio
    private Empleado marces, pepe;
    private Proyecto p1;
    private Asignacion marcesP1;
    
    //Clases DAO
    private final EmpleadoDao empleadoDao;
    private final ProyectoDao proyectoDao;
    private final AsignacionDao asignacionDao;

    public Restricciones() {
        
        DaoFactory daoFactory = new DaoFactory();
        
        empleadoDao = daoFactory.crearEmpleadoDao();
        proyectoDao = daoFactory.crearProyectoDao();
        asignacionDao = daoFactory.crearAsignacionDao();
        dominioFactory = new DominioFactory();
    }

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

        // asignamos marces y p1
        marcesP1 = new DominioFactory().crearAsignacion()
                .withEmpleado(marces)
                .withProyecto(p1)
                .withPuesto("supervisor")
                .withPago(500);
    }

    /**
     * Se realiza la prueba de restricción del documento: es una clave
     * candidata. Dos empleados no podran tener un mismo documento.
     */
    @Test(expected = IllegalStateException.class)
    public void testDeDocumentoUnico() {

        Empleado test = dominioFactory.crearEmpleado()
                .withNombre("test")
                .withApellido("test")
                .withNacimiento(LocalDate.now())
                .withDocumento(37533200) //documento de marces!
                .withCelular("test")
                .withEmail("test");

        // Persistimos test
        empleadoDao.create(test);

        // Intentamos persistir a marces (tiene el mismo documento que test)
        // (debería lanzar una excepción)
        empleadoDao.create(marces);

        fail("Dos empleados no pueden tener un mismo documento!");
    }

    /**
     * Se realiza la prueba de restricción de las asignaciones
     * empleado/proyecto: Un mismo empleado no podrá estar asignado más de una
     * vez al mismo proyeto.
     */
    @Test(expected = IllegalStateException.class)
    public void testDeAsignacion() {

        // persistimos a marces
        empleadoDao.create(marces);

        // persistimos al proyecto p1
        proyectoDao.create(p1);

        // persistimos una asignación entre marces y p1
        asignacionDao.create(marcesP1);

        // creamos otra asignación entre marces y p1
        Asignacion asignacion2 = dominioFactory.crearAsignacion()
                .withEmpleado(pepe)
                .withProyecto(p1)
                .withPuesto("empleado");

        // intentamos persistirla
        // (debería lanzar una excepción)
        asignacionDao.create(asignacion2);

        fail("Un mismo empleado no puede estar asignado más de una vez al mismo proyeto!");
    }

}
