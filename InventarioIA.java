
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
public class InventarioIA {
    Conexion con = new Conexion();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarInventario(int idInventario,String Fecha,String Nombre_Producto,int Existente) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO Inventario(idInventario,Fecha,Nombre_Producto,Existente)"
                + " VALUES (?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,idInventario);
            psql.setString(2,Fecha);
            psql.setString(3, Nombre_Producto);
            psql.setInt(4, Existente);
            
            
            
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
     public ArrayList<Inventario> listInvent(){
        ArrayList listaInventario= new ArrayList();
        Inventario inventario;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM Inventario";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                inventario =new Inventario();
                inventario.setidInventario(rs.getInt(1));
                inventario.setFecha(rs.getString(2));
                inventario.setNombre_Producto(rs.getString(3));
                inventario.setExistente(rs.getInt(4));
                               
                listaInventario.add(inventario);                
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
        return listaInventario;        
    }
    public int eliminarInventario(int idInventario){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM Inventario WHERE idInventario=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,idInventario);
            
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
