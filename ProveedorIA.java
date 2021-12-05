
package Modelo;

/**
 *
 * @author Adelma Lorenzo
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ProveedorIA {
      Conexion con = new Conexion();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarProveedor(String NombreP,String Apellidos,String Direccion, String Correo,String Telefono,String Producto) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO Proveedor(Nombre_Proveedor,Apellidos,Dirección,Correo,Telefono,Producto)"
                + " VALUES (?,?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,NombreP);
            psql.setString(2,Apellidos);
            psql.setString(3, Direccion);
            psql.setString(4, Correo);
            psql.setString(5, Telefono);
            psql.setString(6, Producto);
            
            
            
            
            resultado = psql.executeUpdate();
            if (resultado > 0) { //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el registro");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            }
            psql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar guardar un registro:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }
     public ArrayList<Proveeedor> listProveedores(){
        ArrayList listaProveedor= new ArrayList();
        Proveeedor proveedor;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM Proveedor";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                proveedor =new Proveeedor();
                proveedor.setNombreP(rs.getString(1));
                proveedor.setApellidos(rs.getString(2));
                proveedor.setDireccion(rs.getString(3));
                proveedor.setCorreo(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));
                proveedor.setProducto(rs.getString(6));
                
                
                
                listaProveedor.add(proveedor);                
            }            
        }catch(SQLException e){
            System.err.println(e);            
        }
        finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listaProveedor;        
    }
    public int eliminarProveedor(String  Nombre_Proveedor){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM Proveedor WHERE Nombre_Proveedor=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,Nombre_Proveedor);
            
            resultado = psql.executeUpdate();
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "El registro se eliminó correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
            }
            psql.close();
        } catch (SQLException e){
            System.err.println(e);
        } 
        return resultado;
    }
}
