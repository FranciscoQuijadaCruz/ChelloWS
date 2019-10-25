/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Tablero;
import entidades.Usuario;
import entidades.tableroUsuario;
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
@WebService(serviceName = "tableroUsuarioWS")
public class tableroUsuarioWS {


    baseDatos bd = new baseDatos();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "CrearTableroUsuario")
    public int CrearTableroUsuario(@WebParam(name = "idTablero") int idTablero, 
                                   @WebParam(name = "idUsuario") int idUsuario,
                                   @WebParam(name = "rol") String rol) {
        
        int resultado = 0;
        String query = "INSERT INTO tablerousuario (idTablero,idUsuario, rol) "
                       +"VALUES ("
                            +"'"+idTablero+"',"
                            +"'" +idUsuario+ "',"
                            +"'" +rol+"'"
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
    
        @WebMethod(operationName = "EliminarTableroUsuario")
    public boolean EliminarTableroUsuario(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM tablerousuario WHERE id = " + id;
        
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
    
    public boolean EliminarTableroUsuarios(@WebParam(name = "idTablero") int idTablero) {
        
        boolean resultado = false;
        String query = "DELETE FROM tablerousuario WHERE idTablero = " + idTablero;
        
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
    
     public tableroUsuario SeleccionarTableroUsuario(@WebParam(name = "id") int id) {
        tableroUsuario m = new tableroUsuario();
        String query = "SELECT * FROM tablerousuario WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        m.setId(rs.getInt("id"));
        m.setIdTablero(rs.getInt("idTablero"));
        m.setIdUsuario(rs.getInt("idUsuario"));
        m.setRol(rs.getString("rol"));
       }
       
        }catch(Exception e){
            
        }
        return m;
    }
     
    public int SeleccionarIdPorTableroYUsuario(@WebParam(name = "idTablero") int idTablero,
                                    @WebParam(name = "idUsuario") int idUsuario){
    
       tableroUsuario eu = new tableroUsuario();
        String query = "SELECT * FROM tablerousuario WHERE idTablero = " + idTablero + " AND idUsuario = " + idUsuario;
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        eu.setId(rs.getInt("id"));
       }
       
        }catch(Exception e){
            eu = null;
        }
        return eu.getId();
    }
     
     public List<Tablero> ObtenerTablerosDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
      List<Tablero> tablero = new ArrayList<>();
        
        String query = "SELECT tablero.id, \n" +
                              "tablero.titulo, \n" +
                              "tablero.activo \n"+
                        "FROM tablero \n" +
                        "JOIN tablerousuario \n" +
                        "ON tablerousuario.idTablero = tablero.id \n" +
                        "JOIN usuario \n" +
                        "ON usuario.id = tablerousuario.idUsuario \n" +
                        "WHERE usuario.id = " + idUsuario;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
           Tablero t = new Tablero();
           t.setId(rs.getInt("id"));
           t.setTitulo(rs.getString("titulo"));
           t.setActivo(rs.getInt("activo"));
           tablero.add(t);
        }}catch(Exception e){
            
        }
        return tablero;
    }
    
    public List<Usuario> ObtenerUsuariosDeTablero(@WebParam(name = "idTablero") int idTablero) {
        
        List<Usuario> usuario = new ArrayList<>();
        String query = "SELECT usuario.id," +
                              "usuario.nombre," +
                              "usuario.email," +
                              "usuario.contrasena " +
                        "FROM usuario " +
                        "JOIN tablerousuario " +
                        "ON tablerousuario.idUsuario = usuario.id " +
                        "JOIN tablero " +
                        "ON tablero.id = tablerousuario.idTablero " +
                        "WHERE tablero.id = " + idTablero;
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
           
       }
        
        }catch(Exception e){
            
        }
        return usuario;
    }
    
     
    
}
