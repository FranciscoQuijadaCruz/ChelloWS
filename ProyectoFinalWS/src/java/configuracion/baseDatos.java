package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class baseDatos {
    private Connection conexion;
    private String error;
    configuracionBaseDatos configuracionBaseDatos = new configuracionBaseDatos();
    
    
    public baseDatos(){
        
    }
    
    public baseDatos(String controlador, String baseDatos, String usuario, String contrasena){
        configuracionBaseDatos.setControlador(controlador);
        configuracionBaseDatos.setBaseDatos(baseDatos);
        configuracionBaseDatos.setUsuario(usuario);
        configuracionBaseDatos.setContrasena(contrasena);
    }
    
    public boolean abrirConexion(){
        try {
            /*Esta línea crea una asociación entre nuestra aplicación
             * y la clase Driver que está empaquetada en el  jar de conexión.*/ 
            Class.forName(configuracionBaseDatos.getControlador());
            /*La conexón se obtiene con una cadena que usa 
             *los parámetros que recibe el constructor*/
            setConexion(DriverManager.getConnection(configuracionBaseDatos.getBaseDatos(),configuracionBaseDatos.getUsuario(),configuracionBaseDatos.getContrasena()));
        }
        catch(ClassNotFoundException e){//Sucede si no se encuentra el driver
                error=e.getMessage();	
        }
        catch(SQLException e){//Sucede si la conexión falla
                error=e.getMessage();
        }
        return true;
    }
    
    public void abrir(){
        try {
            /*Esta línea crea una asociación entre nuestra aplicación
             * y la clase Driver que está empaquetada en el  jar de conexión.*/ 
            Class.forName(configuracionBaseDatos.getControlador());
            /*La conexón se obtiene con una cadena que usa 
             *los parámetros que recibe el constructor*/
            setConexion(DriverManager.getConnection(configuracionBaseDatos.getBaseDatos(),configuracionBaseDatos.getUsuario(),configuracionBaseDatos.getContrasena()));
        }
        catch(ClassNotFoundException e){//Sucede si no se encuentra el driver
                error=e.getMessage();	
        }
        catch(SQLException e){//Sucede si la conexión falla
                error=e.getMessage();
        }

    }
    
    public ResultSet consultarDatos(String sql) {
        
        ResultSet resultado =null;
        
        try {
        
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
        
        } catch (SQLException ex) {
            
            this.error = ex.getMessage();
            return null;
            
        }
        
        return resultado;
    }
    
    public boolean ejecutarComando(String sql) {
        
        try {
            Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (sentencia.executeUpdate(sql) > 0){
                
                sentencia.close();
                return true;
               
                
            }else{
                
                sentencia.close();
                return false;
            }
            
        } catch (SQLException ex) {
            this.error = ex.getMessage();
            return false;
        }
        
    }
    
    public void cerrarConexion(){
    	try {
            getConexion().close();
	}catch (Exception ex) {
            this.error = ex.getMessage();
	}
    }
    
    public String getMensajeError(){
    	return getError();
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * @param conexion the conexion to set
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}
