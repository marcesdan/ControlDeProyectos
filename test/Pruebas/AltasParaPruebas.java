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
import org.junit.Test;

/**
 *
 * @author marces
 */
public class AltasParaPruebas {

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

    public AltasParaPruebas() {

        DaoFactory daoFactory = new DaoFactory();

        empleadoDao = daoFactory.crearEmpleadoDao();
        proyectoDao = daoFactory.crearProyectoDao();
        asignacionDao = daoFactory.crearAsignacionDao();
        dominioFactory = new DominioFactory();
    }

    @Test
    public void alta() {

        // En la tabla mostramos los empleados ordenados alfabeticamente
        String[][] nombreApellido = {
            {"Juan Francisco", "Pérez"},
            {"Esteban", "Domingez"},
            {"Florencia", "Gonzalez"},
            {"Emanuel", "Batista"},
            {"Fanis", "Mansilla"},
            {"Álvaro", "Agüero"},
            {"Franco", "Fernandez"},
            {"Luciana Antonella", "Di Stéfano"},
            {"Estefanía", "Díaz"},
            {"Adriana", "Gomez"},};

        for (int i = 0; i < 10; i++) {

            // creamos al empleado marces
            marces = dominioFactory.crearEmpleado()
                    .withNacimiento(LocalDate.now())
                    .withDocumento(11111111)
                    .withCelular("2901-150000")
                    .withTelefono("2901-440000")
                    .withEmail("testemail@gmail.com")
                    .withCalle("calle test")
                    .withAltura("28B")
                    .withBarrio("barrio test");

            // creamos al proyecto p1
            p1 = dominioFactory.crearProyecto()
                    .withDescripcion("test descripcion del proyecto")
                    .withContratista("test contratista")
                    .withFechaEstimada(LocalDate.now())
                    .withPresupuesto(1000);

            // asignamos marces y p1
            marcesP1 = new DominioFactory().crearAsignacion()
                    .withEmpleado(marces)
                    .withProyecto(p1)
                    .withPuesto("test puesto")
                    .withPago(10)
                    .withHoras(3);
            
            // cambiamos algunos datos
            marces.withNombre(nombreApellido[i][0])
                    .withApellido(nombreApellido[i][1])
                    .withDocumento(marces.getDocumento() + i);

            // en la tabla mostramos los proyectos ordenados por fecha de inicio
            p1.withtFechaInicio(LocalDate.now().minusDays(i));

            // en la tabla mostramos las asignaciones ordenados por fecha de ingreso
            marcesP1.withFechaIngreso(LocalDate.now().minusDays(i));

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
    }
}
