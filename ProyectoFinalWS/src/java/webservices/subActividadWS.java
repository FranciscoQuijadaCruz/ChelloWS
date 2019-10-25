/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.subActividad;
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
@WebService(serviceName = "subActividadWS")
public class subActividadWS {

    baseDatos bd = new baseDatos();

    
    @WebMethod(operationName = "AgregarSubActividad")
    public int AgregarSubActividad(@WebParam(name = "descripcion") String descripcion, 
                                      @WebParam(name = "vencimiento") String vencimiento, 
                                      @WebParam(name = "estado") String estado, 
                                      @WebParam(name = "idActividad") int idActividad, 
                                      @WebParam(name = "idUsuario") int idUsuario) {
        
        int resultado = 0;
        String query = "INSERT INTO subactividad (descripcion,vencimiento,estado,idActividad,idUsuario) "
                       +"VALUES ("
                            +"'"+descripcion+"',"
                            +"'" +vencimiento+ "',"
                            +"'"+estado+"',"
                            +"'"+idActividad+"',"
                            +"'"+idUsuario+"'"
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
    
      public boolean EliminarSubActividad(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM subactividad WHERE id = " + id;
        
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
      
      public boolean EliminarSubActividades(@WebParam(name = "idActvidad") int idActividad) {
        
        boolean resultado = false;
        String query = "DELETE FROM subactividad WHERE idActividad = " + idActividad;
        
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
      
      
      public boolean EditarSubActividad(@WebParam(name = "descripcion") String descripcion, 
                                        @WebParam(name = "vencimiento") String vencimiento,
                                        @WebParam(name = "estado") String estado,
                                        @WebParam(name = "idActividad") int idActividad,
                                        @WebParam(name = "idUsuario") int idUsuario,
                                        @WebParam(name = "id") int id){
        boolean resultado = false;
        String query = "UPDATE subactividad SET "
                            +"descripcion = "+"'"+descripcion+"',"
                            +"vencimiento = "+"'"+vencimiento+"',"
                            +"estado = "+"'" +estado+ "',"
                            +"idActividad = "+"'"+idActividad+"',"
                            +"idUsuario = "+"'"+idUsuario+"'"
                       +"WHERE id = "+ id;
        
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
    
      
        public subActividad SeleccionarSubActividad(@WebParam(name = "id") int id){
        
        subActividad sa = new subActividad();
        String query = "SELECT * FROM subactividad WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        sa.setId(rs.getInt("id"));
        sa.setDescripcion(rs.getString("descripcion"));
        sa.setVencimiento(rs.getDate("vencimiento"));
        sa.setEstado(rs.getString("estado"));
        sa.setIdActividad(rs.getInt("idActividad"));
        sa.setIdUsuario(rs.getInt("idUsuario"));
       }
       
        }catch(Exception e){
            
        }
        return sa;
    
    }
        
        public List<subActividad> ObtenerSubActividadesDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
        List<subActividad> subactividad = new ArrayList<>();
        
        String query = "SELECT * FROM subactividad WHERE idUsuario = " + idUsuario;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
           subActividad sa = new subActividad();
            sa.setId(rs.getInt("id"));
            sa.setDescripcion(rs.getString("descripcion"));
            sa.setVencimiento(rs.getDate("vencimiento"));
            sa.setEstado(rs.getString("estado"));
            sa.setIdActividad(rs.getInt("idActividad"));
            sa.setIdUsuario(rs.getInt("idUsuario"));
           subactividad.add(sa);
        }}catch(Exception e){
            
        }
        return subactividad;
    
}
    
    public List<subActividad> ObtenerSubActividadesDeActividad(@WebParam(name = "idActividad") int idActividad) {
        
        List<subActividad> subactividad = new ArrayList<>();
        
        String query = "SELECT * FROM subactividad WHERE idActividad = " + idActividad;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
       try{
            
       while(rs.next()){
           subActividad s = new subActividad();
           s.setDescripcion(rs.getString("descripcion"));
           s.setVencimiento(rs.getDate("vencimiento"));
           s.setEstado(rs.getString("estado"));
           s.setIdActividad(rs.getInt("idActividad"));
           s.setIdUsuario(rs.getInt("idUsuario"));
           subactividad.add(s);
        }}catch(Exception e){
            
        }
        return subactividad;
    
}
}
