/*
 * Alumno: Mariano César D'Angelo.
 * Título: Trabajo Práctico Integrador: Control De Proyectos.
 * Asignatura: Programación y Diseño Orientada a Objetos (2017).
 * Universidad Nacional de Tierra del Fuego (UNTDF).
 *
 */
package presentacion.modelo;

import dao.DaoFactory;
import dominio.Proyecto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import dao.ProyectoDao;

/**
 *
 * @author marces
 */
public class ProyectoComboBoxModel implements ComboBoxModel {
    
    private Proyecto item;
    private List<Proyecto> proyecto;
    private ProyectoDao dao;

    public ProyectoComboBoxModel() {
        dao = new DaoFactory().crearProyectoDao();
        proyecto = new ArrayList(dao.getAll());
    }
    
    @Override
    public Object getElementAt(int index) {
        return proyecto.get(index);
    }

    @Override
    public int getSize() {
        return proyecto.size();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        item = (Proyecto) anItem; // to select and register an
    } // item from the pull-down list

    // Methods implemented from the interface ComboBoxModel
    @Override
    public Proyecto getSelectedItem() {
        return item; // to add the selection to the combo box
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // bye
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // bye
    }
}