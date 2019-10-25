/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
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
@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {
    
    baseDatos bd = new baseDatos();

  @WebMethod(operationName = "AgregarUsuario")
    public int AgregarUsuario(@WebParam(name = "nombre") String nombre, 
                                  @WebParam(name = "email") String email, 
                                  @WebParam(name = "contrasena") String contrasena){
        
            int resultado = 0;
        String query = "INSERT INTO usuario (nombre,email,contrasena) "
                       +"VALUES ("
                            +"'"+nombre+"',"
                            +"'" +email+ "',"
                            +"'"+contrasena+"'" 
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
    
    
    public boolean EditarUsuario(@WebParam(name = "id") int id, 
                                    @WebParam(name = "nombre") String nombre, 
                                    @WebParam(name = "email") String email, 
                                    @WebParam(name = "contraseña") String contraseña){ 

        
        boolean resultado = false;
        String query = "UPDATE usuario SET "
                       + "nombre = " + "'" + nombre + "'"
                       + ",email = " + "'" + email + "'"
                       + ",contrasena = " + "'" + contraseña + "'"
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
    
public Usuario Login(@WebParam(name = "email") String email, 
                         @WebParam(name = "contra") String contra) {

        Usuario m =  new Usuario();
        String query = "SELECT * FROM usuario WHERE email = " + "'" +email+"'" + " AND contrasena = " + "'"+ contra + "'" ;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setEmail(rs.getString("email"));
        m.setContraseña(rs.getString("contrasena"));
       }
       
        }catch(Exception e){
            
        }
        return m;
    }
    
    public Usuario SeleccionarUsuario(@WebParam(name = "id") int id){
        
        Usuario m = new Usuario();
        String query = "SELECT * FROM usuario WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setEmail(rs.getString("email"));
        m.setContraseña(rs.getString("contrasena"));
       }
       
        }catch(Exception e){
            
        }
        return m;
    }
    
    @WebMethod(operationName = "comprobarExistenciaEmail")
    public boolean ComprobarExistenciaEmail(@WebParam(name = "email") String email) {

        Usuario u =  new Usuario();
        u.setId(0);
        boolean existencia = false;
        String query = "SELECT id FROM usuario WHERE email = " + "'" +email+"'";
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        u.setId(rs.getInt("id"));
       }
       
        }catch(Exception e){
            
        }
        
        if(u.getId() > 0){
        existencia = true;
        }
        
        return existencia;
    }

    @WebMethod(operationName = "SeleccionUsuarioPorEmail")
    public int SeleccionUsuarioPorEmail(@WebParam(name = "email") String email) {

        int existencia = 0;
        String query = "SELECT id FROM usuario WHERE email = " + "'" + email + "'";
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try {

            while (rs.next()) {
                existencia = rs.getInt("id");
            }

        } catch (Exception e) {

        }

        return existencia;
    }

    @WebMethod(operationName = "SeleccionUsuarioPorEmailCoincidencia")
    public List<Usuario> SeleccionUsuarioPorEmailCoincidencia(@WebParam(name = "email") String email) {

        List<Usuario>usuarios = new ArrayList<>();
        
        String query = "SELECT * FROM usuario WHERE email LIKE '%"+email+"%'";
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setContraseña(rs.getString("contrasena"));
                usuarios.add(u);
            }

        } catch (Exception e) {

        }

        return usuarios;
    }
    

}
