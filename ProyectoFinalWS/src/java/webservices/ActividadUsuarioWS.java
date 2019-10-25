/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Actividad;
import entidades.ActividadUsuario;
import entidades.Usuario;
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
@WebService(serviceName = "ActividadUsuarioWS")
public class ActividadUsuarioWS {

    baseDatos bd = new baseDatos();

  @WebMethod(operationName = "AgregarActividadUsuario")
    public int AgregarActividadUsuario(@WebParam(name = "idActividad") int idActividad, 
                                    @WebParam(name = "idUsuario") int idUsuario) {
        
            int resultado = 0;
        String query = "INSERT INTO actividadusuario (idActividad,idUsuario) "
                       +"VALUES ("
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
    
    public boolean EliminarActividadUsuario(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM actividadusuario WHERE id = " + id;
        
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
    
      public boolean EliminarActividadUsuarios(@WebParam(name = "idActvidad") int idActividad) {
        
        boolean resultado = false;
        String query = "DELETE FROM actividadusuario WHERE idActividad = " + idActividad;
        
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
    
    public ActividadUsuario SeleccionarActividadUsuario(@WebParam(name = "id") int id) {
        
        ActividadUsuario a = new ActividadUsuario();
        String query = "SELECT * FROM actividadusuario WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
       while(rs.next()){
        a.setId(rs.getInt("id"));
        a.setIdActividad(rs.getInt("idActividad"));
        a.setIdUsuario(rs.getInt("idUsuario"));
       }
       
        }catch(Exception e){
            
        }
        return a; 
    }
    
    public List<Actividad> ObtenerActividadesDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
        List<Actividad> actividad = new ArrayList<>();
        
        String query = "SELECT actividad.id, \n" +
                              "actividad.titulo, \n" +
                              "actividad.descripcion, \n" +
                              "actividad.vencimiento, \n" +
                              "actividad.estado, \n" +
                              "actividad.idModulo, \n " +
                              "actividad.activo \n" +
                        "FROM actividad \n" +
                        "JOIN actividadusuario \n" +
                        "ON actividadusuario.idActividad = actividad.id \n" +
                        "JOIN usuario \n" +
                        "ON usuario.id = actividadusuario.idUsuario \n" +
                        "WHERE usuario.id = " + idUsuario;
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
    
    public List<Usuario> ObtenerUsuariosDeActividad(@WebParam(name = "idActividad") int idActividad) {
        
        List<Usuario> usuario = new ArrayList<>();
        
        String query = "SELECT usuario.id, \n" +
                              "usuario.nombre, \n" +
                              "usuario.email, \n" +
                              "usuario.contrasena \n" +
                        "FROM usuario \n" +
                        "JOIN actividadusuario \n" +
                        "ON actividadusuario.idUsuario = usuario.id\n" +
                        "JOIN actividad\n" +
                        "ON actividad.id = actividadusuario.idActividad\n" +
                        "WHERE actividad.id = " + idActividad;
        
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
       try{
            
       while(rs.next()){
           Usuario u = new Usuario();
           u.setId(rs.getInt("id"));
           u.setNombre(rs.getString("nombre"));
           u.setEmail(rs.getString("email"));
           u.setContraseña(rs.getString("contrasena"));
           usuario.add(u);
        }}catch(Exception e){
            
        }
        return usuario;
    
}
    
    
}
