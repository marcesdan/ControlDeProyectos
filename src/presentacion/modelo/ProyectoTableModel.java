/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.modelo;

import dao.DaoFactory;

import dominio.Empleado;

import dominio.Proyecto;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

/**
 *
 *
 *
 * @author marces
 *
 */
public class ProyectoTableModel extends ATableModel {

    public ProyectoTableModel() {

        super(new DaoFactory().crearProyectoDao(),
                new String[]{
                    "Descripcion", "Contratista", "Presupuesto", "Disponible",
                    "Fecha de inicio", "Fecha estimada"},
                
                new Class[]{
                    String.class, String.class, String.class, String.class,
                    String.class, String.class,});

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Proyecto proyecto = (Proyecto) getData().get(rowIndex);

        switch (columnIndex) {

            case 0 : return proyecto.getDescripcion();
            case 1 : return proyecto.getContratista();
            case 2 : return proyecto.getPresupuesto().toString();
            case 3 : return proyecto.getPresupuestoDisponible().toString();
            
            case 4: {

                // Devolvemos un string con la fecha formateada (sino sale feita)
                DateTimeFormatter formatter
                        = DateTimeFormatter.ofPattern("dd/MM/yy");

                return proyecto.getFechaInicioLocalDate().format(formatter);
            }

            case 5: {

                // Devolvemos un string con la fecha formateada (sino sale feita)
                if (!isNull(proyecto.getFechaEstimadaLocalDate())) {

                    DateTimeFormatter formatter
                            = DateTimeFormatter.ofPattern("dd/MM/yy");

                    return proyecto.getFechaEstimadaLocalDate().format(formatter);
                }
                
                else return "";
            }
        }

        return null;
    }

    @Override
    protected void updateId() {

        for (int i = 0; i < data.size(); i++) {
            id.add(((Proyecto) data.get(i)).getId());
        }
    }
}
