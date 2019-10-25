/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Equipo;
import entidades.EquipoUsuario;
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
@WebService(serviceName = "EquipoUsuarioWS")
public class EquipoUsuarioWS {
 
    baseDatos bd = new baseDatos();

  @WebMethod(operationName = "AgregarEquipoUsuario")
    public int AgregarEquipoUsuario(@WebParam(name = "idEquipo") int idEquipo, 
                                    @WebParam(name = "idUsuario") int idUsuario,
                                    @WebParam(name = "rol") String rol) {
        
            int resultado = 0;
        String query = "INSERT INTO equipousuario (idEquipo,idUsuario,rol) "
                       +"VALUES ("
                            +"'"+idEquipo+"',"
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

    public int SeleccionarIdPorEquipoYUsuario(@WebParam(name = "idEquipo") int idEquipo,
                                    @WebParam(name = "idUsuario") int idUsuario){
    
       EquipoUsuario eu = new EquipoUsuario();
        String query = "SELECT * FROM equipousuario WHERE idEquipo = " + idEquipo + " AND idUsuario = " + idUsuario;
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
    
    public boolean EliminarEquipoUsuario(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM equipousuario WHERE id = " + id;
        
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
    
    public EquipoUsuario SeleccionarEquipoUsuario(@WebParam(name = "id") int id) {
        
       EquipoUsuario eu = new EquipoUsuario();
        String query = "SELECT * FROM equipousuario WHERE id = " + id;
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        eu.setId(rs.getInt("id"));
        eu.setIdEquipo(rs.getInt("idEquipo"));
        eu.setIdUsuario(rs.getInt("idUsuario"));
        eu.setRol(rs.getString("rol"));
       }
       
        }catch(Exception e){
            eu = null;
        }
        return eu;
    }
    
         public List<Equipo> ObtenerEquiposDeUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        
      List<Equipo> equipos = new ArrayList<>();
        
        String query = "SELECT equipo.id, \n" +
                              "equipo.nombre,\n" +
                              "equipo.descripcion\n" +
                        "FROM equipo \n" +
                        "JOIN equipousuario \n" +
                        "ON equipousuario.idEquipo = equipo.id\n" +
                        "JOIN usuario\n" +
                        "ON usuario.id = equipousuario.idUsuario\n" +
                        "WHERE usuario.id = " + idUsuario;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
           Equipo t = new Equipo();
           t.setId(rs.getInt("id"));
           t.setNombre(rs.getString("nombre"));
           t.setDescripcion(rs.getString("descripcion"));
           equipos.add(t);
        }}catch(Exception e){
            
        }
        return equipos;
    }
    
    public List<Usuario> ObtenerUsuariosDeEquipo(@WebParam(name = "idEquipo") int idEquipo) {
        
        List<Usuario> usuario = new ArrayList<>();
        String query = "SELECT usuario.id," +
                              "usuario.nombre," +
                              "usuario.email," +
                              "usuario.contrasena " +
                        "FROM usuario " +
                        "JOIN equipousuario " +
                        "ON equipousuario.idUsuario = usuario.id " +
                        "JOIN equipo " +
                        "ON equipo.id = equipousuario.idEquipo " +
                        "WHERE equipo.id = " + idEquipo;
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

    public List<EquipoUsuario> SeleccionarTodosEquipoUsuario(@WebParam(name = "idEquipo") int idEquipo) {
        
        List<EquipoUsuario> lista = new ArrayList<>();
        String query = "SELECT * FROM equipousuario WHERE idEquipo = " + idEquipo;
        ResultSet rs = bd.consultarDatos(query);

        try{
       while(rs.next()){
        EquipoUsuario eu = new EquipoUsuario();
        eu.setId(rs.getInt("id"));
        eu.setIdEquipo(rs.getInt("idEquipo"));
        eu.setIdUsuario(rs.getInt("idUsuario"));
        eu.setRol(rs.getString("rol"));
        lista.add(eu);
       }
       
        }catch(Exception e){
            lista = null;
        }
        return lista;
    }
}
