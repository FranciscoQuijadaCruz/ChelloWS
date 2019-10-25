/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Actividad;
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

@WebService(serviceName = "ActividadWS")
public class ActividadWS {

            baseDatos bd = new baseDatos();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "AgregarActividad")
    public int AgregarActividad(@WebParam(name = "titulo") String titulo, 
                                    @WebParam(name = "descripcion") String descripcion, 
                                    @WebParam(name = "vencimiento") String vencimiento, 
                                    @WebParam(name = "estado") String estado, 
                                    @WebParam(name = "idModulo") int idModulo) {
        
            int resultado = 0;
        String query = "INSERT INTO actividad (titulo,descripcion,vencimiento,estado,idModulo) "
                       +"VALUES ("
                            +"'"+titulo+"',"
                            +"'"+descripcion+"',"
                            +"'" +vencimiento+ "',"
                            +"'"+estado+"',"
                            +"'"+idModulo+"'"
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
    
     public boolean EliminarActividadVirtual(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
          String query = "UPDATE actividad SET "
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
    
     public boolean EliminarActividad(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
          String query = "DELETE FROM actividad WHERE id = "+id;
        
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
     
    public boolean EditarActividad(@WebParam(name = "id") int id,
                                   @WebParam(name = "titulo") String titulo,
                                   @WebParam(name = "descripcion") String descripcion,
                                   @WebParam(name = "vencimiento") String vencimiento,
                                   @WebParam(name = "estado") String estado
                                   ) {
        
        boolean resultado = false;
        String query = "UPDATE actividad SET "
                            +"titulo = "+"'"+titulo+"',"
                            +"descripcion = "+"'"+descripcion+"',"
                            +"vencimiento = "+"'" +vencimiento+ "',"
                            +"estado = "+"'"+estado+"'"
                       +" WHERE id = "+ id;
        
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
    
    public Actividad SeleccionarActividad(@WebParam(name = "id") int id){
        
        Actividad a = new Actividad();
        String query = "SELECT * FROM actividad WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        a.setId(rs.getInt("id"));
        a.setTitulo(rs.getString("titulo"));
        a.setDescripcion(rs.getString("descripcion"));
        a.setVencimiento(rs.getString("vencimiento"));
        a.setEstado(rs.getString("estado"));
        a.setIdModulo(rs.getInt("idModulo"));
        a.setActivo(rs.getInt("activo"));
       }
       
        }catch(Exception e){
        }
        return a;
    }
    
    public boolean EliminarActividades(@WebParam(name = "idModulo") int idModulo) {
        
        boolean resultado = false;
        String query = "DELETE FROM actividad WHERE idModulo = " + idModulo;
        
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
    
    public List<Actividad> ObtenerActividadesDeModulo(@WebParam(name = "idModulo") int idModulo) {
        
        List<Actividad> actividad = new ArrayList<>();
        
        String query = "SELECT * FROM actividad WHERE idModulo = " + idModulo;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
           Actividad a = new Actividad();
           a.setId(rs.getInt("id"));
           a.setTitulo(rs.getString("titulo"));
           a.setDescripcion(rs.getString("descripcion"));
           a.setVencimiento(rs.getString("vencimiento"));
           a.setEstado(rs.getString("estado"));
           a.setIdModulo(rs.getInt("idModulo"));
           a.setActivo(rs.getInt("activo"));
           actividad.add(a);
        }}catch(Exception e){
            
        }
        return actividad;
    
}
    
}

