/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Comentario;
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
@WebService(serviceName = "ComentarioWS")
public class ComentarioWS {
    
    baseDatos bd = new baseDatos();

 public int CrearComentario(@WebParam(name = "descripcion") String descripcion,
                                @WebParam(name = "fecha") String fecha,
                                @WebParam(name = "idUsuario") int idUsuario,
                                @WebParam(name = "idActividad") int idActividad) {
        
        int resultado = 0;
        String query = "INSERT INTO comentario (descripcion,fecha,idUsuario,idActividad) "
                       +"VALUES ("
                            +"'"+descripcion+"',"
                            +"'"+fecha+"',"
                            +"'"+idActividad+"',"
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
 
     public boolean EliminarComentario(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM comentario WHERE id = " + id;
        
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
    
       public Comentario SeleccionarComentario(@WebParam(name = "id") int id) {
        
        Comentario c = new Comentario();
        String query = "SELECT * FROM comentario WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        c.setId(rs.getInt("id"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setFecha(rs.getString("fecha"));
        c.setIdUsuario(rs.getInt("idUsuario"));
        c.setIdActividad(rs.getInt("idActividad"));
       }
       
        }catch(Exception e){
            c = null;
        }
        return c;
    }
       
    
    public List<Comentario> ObtenerComentariosDeActividad(@WebParam(name = "idActividad") int idActividad) {
        
        List<Comentario> comentario = new ArrayList<>();
        
        String query = "SELECT * FROM comentario WHERE idActividad =" + idActividad;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
        Comentario c =  new Comentario();
        c.setId(rs.getInt("id"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setFecha(rs.getString("fecha"));
        c.setIdUsuario(rs.getInt("idUsuario"));
        c.setIdActividad(rs.getInt("idActividad"));
        comentario.add(c);
       }
       
        }catch(Exception e){
            
        }
        return comentario;
    }
    
        public List<Comentario> ObtenerComentariosDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
        List<Comentario> comentario = new ArrayList<>();
        
        String query = "SELECT * FROM comentario WHERE idUsuario =" + idUsuario;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
        Comentario c =  new Comentario();
        c.setId(rs.getInt("id"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setFecha(rs.getString("fecha"));
        c.setIdUsuario(rs.getInt("idUsuario"));
        c.setIdActividad(rs.getInt("idActividad"));
        comentario.add(c);
       }
       
        }catch(Exception e){
            
        }
        return comentario;
    }
    
}
