/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuracion;

public class configuracionBaseDatos {
    private String controlador = "com.mysql.jdbc.Driver";
    private String baseDatos = "jdbc:mysql://localhost/bdejemplo";
    private String usuario = "root";
    private String contrasena = "C7GCKpix";


    public configuracionBaseDatos(){

    }

    /**
     * @return the controlador
     */
    public String getControlador() {
        return controlador;
    }

    /**
     * @return the baseDatos
     */
    public String getBaseDatos() {
        return baseDatos;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param controlador the controlador to set
     */
    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    /**
     * @param baseDatos the baseDatos to set
     */
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
