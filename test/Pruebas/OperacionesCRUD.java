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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marces
 */
public class OperacionesCRUD {

    //Factory de las clases de dominio
    private final DominioFactory dominioFactory;

    //Clases de dominio
    private Empleado marces;
    private Proyecto p1;
    private Asignacion marcesP1;

    //Clases DAO
    private final EmpleadoDao empleadoDao;
    private final ProyectoDao proyectoDao;
    private final AsignacionDao asignacionDao;

    public OperacionesCRUD() {

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

        // persistimos una asignación entre marces y p1
        asignacionDao.create(marcesP1);

        // guardamos la asignación en ambas listas.
        marces.addAsignacion(marcesP1);
        p1.addAsignacion(marcesP1);

        // actualizamos
        empleadoDao.update(marces);
        proyectoDao.update(p1);
    }

    @After
    public void TearDown() {

        // De paso probamos la operacion CRUD "DELETE".
        empleadoDao.delete(marces); // borramos a marces
        proyectoDao.delete(p1);     // borramos el proyecto p1
    }

    /**
     * Se comprueba que se agregan mutuamente las referencias de forma correcta.
     *
     * De las operaciones CRUD se prueban "CREATE" y "UPDATE"
     */
    @Test
    public void pruebaNuevaAsignacion() {

        // Se comprueban los objetos (no las referencias)
        assertTrue("La ref. del empleado en la asignacion NO concuerda!",
                marcesP1.getEmpleado().equals(marces));
        assertTrue("La ref. del proyecto en la asignacion NO concuerda!",
                marcesP1.getProyecto().equals(p1));

        // comprobamos que estan presentes en las listas
        assertTrue("La asignación no se encuentra la lista",
                marces.getAsignaciones().contains(marcesP1));
        assertTrue("La asignación no se encuentra la lista",
                p1.getAsignaciones().contains(marcesP1));
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
        assertTrue("La asignación no se encuentra la lista",
                marces.getAsignaciones().contains(marcesP1));
        assertTrue("La asignación no se encuentra la lista",
                p1.getAsignaciones().contains(marcesP1));

        // comprobamos que las referencias correspondan a los mismo objetos.
        marcesP1 = asignacionDao.read(marcesP1.getId());
        assertTrue("La ref. del empleado en la asignacion NO concuerda!",
                marcesP1.getEmpleado().equals(marces));
        assertTrue("La ref. del proyecto en la asignacion NO concuerda!",
                marcesP1.getProyecto().equals(p1));
    }

    /**
     * Se comprueba que se elimina la relación de forma correcta.
     * 
     * Luego, se realiza una lectura a la base de datos y se vuelve a comprobar 
     * el estado de las listas (post-delete).
     *
     * De las operaciones CRUD se prueba "READ" y "DELETE". (read post-delete)
     */
    @Test
    public void pruebaBorrarAsignacion() {

        // borramos la asignacion
        asignacionDao.delete(marcesP1);

        // actualizamos las listas
        marces.removeAsignacion(marcesP1);
        p1.removeAsignacion(marcesP1);

        // corroboramos que se haya removido la asignación de las listas.
        assertTrue("La asignación no se borró de la lista",
                !marces.getAsignaciones().contains(marcesP1));
        assertTrue("La asignación no se borró de la lista",
                !p1.getAsignaciones().contains(marcesP1));

        // actualizamos en la base de datos
        empleadoDao.update(marces);
        proyectoDao.update(p1);

        // leemos de la base de datos
        marces = empleadoDao.read(marces.getId());
        p1 = proyectoDao.read(p1.getId());

        // y volvemos a corroborar la consistencia de las listas.
        // si no se hubiesen hecho los "UPDATEs", estas dos pruebas fallarían...
        assertTrue("La asignación no se borró de la lista",
                !marces.getAsignaciones().contains(marcesP1));
        assertTrue("La asignación no se borró de la lista",
                !p1.getAsignaciones().contains(marcesP1));
    }
}