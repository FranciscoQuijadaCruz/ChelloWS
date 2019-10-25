/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Modificacion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Luis Bayardo
 */
@WebService(serviceName = "ModificacionWS")
public class ModificacionWS {
    
    baseDatos bd = new baseDatos();

 public int CrearModificacion(@WebParam(name = "descripcion") String descripcion,
                                      @WebParam(name = "fecha") String fecha,
                                      @WebParam(name = "idTablero") int idTitulo,        
                                      @WebParam(name = "idUsuario") int idUsuario) {
        
        int resultado = 0;
        String query = "INSERT INTO modificacion (descripcion,fecha,idTablero,idUsuario) "
                       +"VALUES ("
                            +"'"+descripcion+"',"
                            +"'"+fecha+"',"
                            +"'"+idTitulo+"',"
                            +"'" +idUsuario+ "'"
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
 
    public boolean EliminarModificacion(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM modificacion WHERE id = " + id;
        
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
    
    public Modificacion SeleccionarModificacion(@WebParam(name = "id") int id) {
        
        Modificacion m = new Modificacion();
        String query = "SELECT * FROM modificacion WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
           
        m.setId(rs.getInt("id"));
        m.setDescripcion(rs.getString("descripcion"));
        m.setFecha(rs.getString("fecha"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setIdUsuario(rs.getInt("idUsuario"));
       
       }
       
        }catch(Exception e){
            
        }
        return m;
    }
    
    public List<Modificacion> ObtenerModificacionesDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
        List<Modificacion> modificacion = new ArrayList<>();
        
        String query = "SELECT * FROM modificacion WHERE idUsuario = " + idUsuario;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
        Modificacion m =  new Modificacion();
        m.setDescripcion(rs.getString("descripcion"));
        m.setFecha(rs.getString("fecha"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setIdUsuario(rs.getInt("idUsuario"));
        m.setId(rs.getInt("id"));
        modificacion.add(m);
       }
       
        }catch(Exception e){
            
        }
        return modificacion;
    }
    
    public List<Modificacion> ObtenerModificacionesDeTablero(@WebParam(name = "idTablero") int idTablero) {
        
        List<Modificacion> modificacion = new ArrayList<>();
        
        String query = "SELECT * FROM modificacion WHERE idTablero = " + idTablero;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
        Modificacion m =  new Modificacion();
        m.setId(rs.getInt("id"));
        m.setDescripcion(rs.getString("descripcion"));
        m.setFecha(rs.getString("fecha"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setIdUsuario(rs.getInt("idUsuario"));
        modificacion.add(m);
       }
       
        }catch(Exception e){
            
        }
        return modificacion;
    }
    
}
