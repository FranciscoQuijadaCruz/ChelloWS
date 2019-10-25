/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import configuracion.baseDatos;
import entidades.Tablero;
import java.sql.ResultSet;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "TableroWS")
public class TableroWS {

        baseDatos bd = new baseDatos();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "AgregarTablero")
    public int AgregarTablero(@WebParam(name = "titulo") String titulo) {
        
            int resultado = 0;
        String query = "INSERT INTO tablero (titulo) VALUES ('"+titulo +"')";
        
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
    
     public boolean EliminarTablero(@WebParam(name = "id") int id) {
        
        boolean resultado = false;
        String query = "UPDATE tablero SET "
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
    
     public boolean EditarTablero(@WebParam(name = "id") int id,
                                  @WebParam(name = "titulo") String titulo
                                  ) {
        
        boolean resultado = false;
        String query = "UPDATE tablero SET "
                       +"titulo = "
                       +"'"+titulo+"'"          
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
    
     public Tablero SeleccionarTablero(@WebParam(name = "id") int id) {
        
        Tablero m = new Tablero();
        String query = "SELECT * FROM tablero WHERE id = " + id;
        bd.abrirConexion();
        ResultSet rs = bd.consultarDatos(query);

        try{
            
       while(rs.next()){
           
        m.setId(rs.getInt("id"));
        m.setTitulo(rs.getString("titulo"));
        m.setActivo(rs.getInt("activo"));
       }
       
        }catch(Exception e){
            
        }
        return m;
     }
}
