/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.modelo;

import dao.GenericDao;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marces
 */
public abstract class ATableModel extends AbstractTableModel {

    protected ArrayList data;
    protected ArrayList<Long> id;
    private GenericDao dao;
    private String[] columns;
    private Class[] tipos;
   
    protected String[] getColumns() {
        return this.columns;
    }

    protected Class[] getColumnClass() {
        return this.tipos;
    }

    public ATableModel(GenericDao dao, String[] columns, Class[] tipos) {
        this.dao = dao;
        this.columns = columns;
        this.tipos = tipos;
        this.update();
    }

    public Long getId(int index) {
        return id.get(index);
    }
    
    public void setId(int index, Long id) {
        this.id.add(id);
    }
    
    public ArrayList getData() {
        return data;
    }

    public void update() {
        data = new ArrayList(dao.getAllOrd());
        id = new ArrayList<>();
        updateId();
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
       
        return getData().size();
    }

    @Override
    public int getColumnCount() {
        return getColumns().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return getColumns()[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getColumnClass()[columnIndex];
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    /**
     *  Llenamos el array de IDs (no se los muestra en la tabla pero
     *  se los almacena).
     */
    protected abstract void updateId();
}
