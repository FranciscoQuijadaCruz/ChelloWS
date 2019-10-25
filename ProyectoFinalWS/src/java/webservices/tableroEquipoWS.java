/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Equipo;
import entidades.Tablero;
import entidades.Usuario;
import entidades.tableroEquipo;
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
@WebService(serviceName = "tableroEquipoWS")
public class tableroEquipoWS {


    baseDatos bd = new baseDatos();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "CrearTableroEquipo")
    public int CrearTableroEquipo(@WebParam(name = "idTablero") int idTablero, 
                                      @WebParam(name = "idEquipo") int idEquipo) {
        
        int resultado = 0;
        String query = "INSERT INTO tableroequipo (idTablero,idEquipo) "
                       +"VALUES ("
                            +"'"+idTablero+"',"
                            +"'" +idEquipo+ "'"
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
     
    public boolean EliminarTableroEquipo(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "DELETE FROM tableroequipo WHERE id = " + id;
        
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

        public boolean EliminarTableroEquipos(@WebParam(name = "idTablero") int idTablero) {
        
        boolean resultado = false;
        String query = "DELETE FROM tableroequipo WHERE id = " + idTablero;
        
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
    
    
     public tableroEquipo SeleccionarTableroEquipo(@WebParam(name = "id") int id) {
        
        tableroEquipo te = new tableroEquipo();
        String query = "SELECT * FROM tableroequipo WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
        te.setId(rs.getInt("id"));
        te.setIdTablero(rs.getInt("idTablero"));
        te.setIdEquipo(rs.getInt("idEquipo"));
       }
       
        }catch(Exception e){
        te = null;    
        }
        return te;
    }
     
          public List<Tablero> ObtenerTablerosDeEquipo(@WebParam(name = "idEquipo") int idEquipo) {
        
      List<Tablero> tablero = new ArrayList<>();
        
        String query = "SELECT tablero.id, \n" +
                              "tablero.titulo,\n" +
                              "tablero.activo \n"+
                        "FROM tablero \n" +
                        "JOIN tableroequipo \n" +
                        "ON tableroequipo.idTablero = tablero.id \n" +
                        "JOIN equipo \n" +
                        "ON equipo.id = tableroequipo.idEquipo \n" +
                        "WHERE equipo.id = " + idEquipo;
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
    
    public List<Equipo> ObtenerEquiposDeTablero(@WebParam(name = "idTablero") int idTablero) {
        
        List<Equipo> equipos = new ArrayList<>();
        String query = "SELECT equipo.id,\n" +
                              "equipo.nombre,\n" +
                              "equipo.descripcion\n" +
                        "FROM equipo " +
                        "JOIN tableroequipo " +
                        "ON tableroequipo.idEquipo = equipo.id " +
                        "JOIN tablero " +
                        "ON tablero.id = tableroequipo.idTablero " +
                        "WHERE tablero.id = " + idTablero;
                                bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);
        
        try{
            
       while(rs.next()){
           
           Equipo u = new Equipo();
           u.setId(rs.getInt("id"));
           u.setNombre(rs.getString("nombre"));
           u.setDescripcion(rs.getString("descripcion"));
           equipos.add(u);
           
       }
        
        }catch(Exception e){
            
        }
        return equipos;
    }
     
     
}
