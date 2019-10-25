/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Modulo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "ModuloWS")
public class ModuloWS {

        baseDatos bd = new baseDatos();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "AgregarModulo")
    public int AgregarModulo(@WebParam(name = "nombre") String nombre, 
                                 @WebParam(name = "idTablero") int idTablero) {
        int resultado = 0;
        String query = "INSERT INTO modulo (nombre,idTablero) "
                       +"VALUES ("
                            +"'"+nombre+"',"
                            +"'"+idTablero+"'"
                           
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
    
    public boolean EliminarModulo(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM modulo WHERE id = " + id;
        
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
         
     public boolean EliminarModuloVirtual(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
          String query = "UPDATE modulo SET "
                       +"activo = "
                       +"'"+0+"'"          
                       +" WHERE id = " + id;
        
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
     

     
    public boolean EditarModulo(@WebParam(name = "nombre") String nombre, 
                                @WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "UPDATE modulo SET nombre = "
                            +"'"+nombre+"'"         
                       +" WHERE id = " + id;
        
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
    
    public Modulo SeleccionarModulo(@WebParam(name = "id") int id) {
        
        Modulo m = new Modulo();
        String query = "SELECT * FROM modulo WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setActivo(rs.getInt("activo"));
       }
       
        }catch(Exception e){
            
        }
        return m;
    }
    
    public List<Modulo> ObtenerModulosDeTablero(@WebParam(name = "idTablero") int idTablero) {
        
        List<Modulo> modulo = new ArrayList<>();
        
        String query = "SELECT * FROM modulo WHERE idTablero =" + idTablero;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
        Modulo m =  new Modulo();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setActivo(rs.getInt("activo"));
        modulo.add(m);
       }
       
        }catch(Exception e){
            
        }
        return modulo;
    }
    
}
