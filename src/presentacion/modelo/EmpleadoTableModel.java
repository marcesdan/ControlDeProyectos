/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.modelo;

import dominio.Empleado;
import dao.DaoFactory;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author marces
 */
public class EmpleadoTableModel extends ATableModel{
    
    public EmpleadoTableModel(){
        
        super(new DaoFactory().crearEmpleadoDao(),
                
                new String[]{
                    "Documento","Nombre","Nacimiento","Contacto","Dirección"},
                
                new Class[]{
                    String.class, String.class, String.class, 
                    String.class, String.class,});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Empleado empleado = (Empleado) getData().get(rowIndex);
        switch(columnIndex) {
                    case 0 : return empleado.getDocumento();
                    
                    case 1 : return 
                            empleado.getApellido()+ ", " + empleado.getNombre();
                    
                    case 2 : {
                        // Devolvemos un string con la fecha formateada (sino sale feita)
                        DateTimeFormatter formatter 
                                = DateTimeFormatter.ofPattern("dd/MM/yy");
                        return empleado.getNacimientoLocalDate().format(formatter);
                    }
                    
                    case 3 : return empleado.getContacto().toString();
                    case 4 : return empleado.getDireccion().toString();
                    
                }
        
        return null;
    }

    @Override
    protected void updateId() {
        for (int i = 0; i < data.size(); i++) 
           id.add(((Empleado) data.get(i)).getId() );
    }
 
}
