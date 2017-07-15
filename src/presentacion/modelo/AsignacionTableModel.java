/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.modelo;

import dao.DaoFactory;
import dominio.Asignacion;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author marces
 */
public class AsignacionTableModel extends ATableModel{
    
    public AsignacionTableModel(){
        
        super(new DaoFactory().crearAsignacionDao(),
                
                new String[]{
                    "Empleado","Proyecto","Ingreso","Pago","Puesto","Horas"},
                
                new Class[]{
                    String.class, String.class, String.class, String.class,
                    String.class, String.class});
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Asignacion asignacion = (Asignacion) getData().get(rowIndex);
        switch(columnIndex){
            
                    case 0:return asignacion.getEmpleado().toString();
                    case 1:return asignacion.getProyecto().toString();
                    case 2: {
                        // Devolvemos un string con la fecha formateada (sino sale feita)
                        DateTimeFormatter formatter 
                                = DateTimeFormatter.ofPattern("dd/MM/yy");
                        return asignacion.getFechaIngresoLocalDate().format(formatter);
                    }
                    case 3:return asignacion.getPago();
                    case 4:return asignacion.getPuesto();
                    case 5:return asignacion.getHoras();
                }
        
        return null;
    }
    
    @Override
    protected void updateId() {
        for (int i = 0; i < data.size(); i++) 
           id.add( ((Asignacion) data.get(i)).getId() );
    }
}
