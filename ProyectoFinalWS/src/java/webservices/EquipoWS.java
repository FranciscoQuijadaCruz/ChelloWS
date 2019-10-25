/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Equipo;
import java.sql.ResultSet;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Luis Bayardo
 */
@WebService(serviceName = "EquipoWS")
public class EquipoWS {

    baseDatos bd = new baseDatos();

  @WebMethod(operationName = "AgregarEquipo")
    public int AgregarEquipo(@WebParam(name = "nombre") String nombre, 
                             @WebParam(name = "descripcion") String descripcion) {
        
            int resultado = 0;
        String query = "INSERT INTO equipo (nombre,descripcion) "
                       +"VALUES ("
                            +"'"+nombre+"',"
                            +"'" +descripcion+ "'"
                       +")";
        
        String  query2 = "SELECT @@IDENTITY AS 'Identity'";
        
        try {
            bd.abrirConexion();
            bd.ejecutarComando(query);
           ResultSet rs = bd.consultarDatos(query2);

        while(rs.next()){
        resultado = (rs.getInt("Identity"));
       }
        
        } catch (Exception e) {
            resultado = 0;
        }finally{
            bd.cerrarConexion();
        }
        return resultado;
    }
    
    public boolean EliminarEquipo(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM equipo WHERE id = " + id;
        
        try {
            bd.abrirConexion();
            resultado = bd.ejecutarComando(query);
        } catch (Exception e) {
            resultado = false;
        }finally{
            bd.cerrarConexion();
        }
        return resultado;
    }
    
    public boolean EditarEquipo(@WebParam(name = "id") int id, 
                                    @WebParam(name = "nombre") String nombre, 
                                    @WebParam(name = "descripcion") String descripcion){
        
        boolean resultado = false;
        String query = "UPDATE equipo SET "
                            +"nombre = "+"'"+nombre+"',"
                            +"descripcion = "+"'" +descripcion+ "'"
                       +"WHERE id = " + id;
        
        try {
            bd.abrirConexion();
            resultado = bd.ejecutarComando(query);
        } catch (Exception e) {
            resultado = false;
        }finally{
            bd.cerrarConexion();
        }
        return resultado;
    }
    
    public Equipo SeleccionarEquipo(@WebParam(name = "id") int id) {
        
        Equipo eq = new Equipo();
        String query = "SELECT * FROM equipo WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        eq.setId(rs.getInt(("id")));
        eq.setNombre(rs.getString("nombre"));
        eq.setDescripcion(rs.getString("descripcion"));
       
       }
       
        }catch(Exception e){
            eq = null;
        }
        return eq;
    }
}
