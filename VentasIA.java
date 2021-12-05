
package Modelo;

/**
 *
 * @author Adelma Lorenzo
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;


import javax.swing.JOptionPane;
public class VentasIA {
    Conexion con = new Conexion();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarVenta(String Nombre,String Fecha,String Tipo,double Precio,int Cantidad,String Precio_total,
       double Recibido,double Cambio) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO venta(Nombre_Producto,Fecha,Tipo,Precio,Cantidad, Precio_total,Recibido,Cambio)"
                + " VALUES (?,?,?,?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,Nombre);
            psql.setString(2, Fecha);
            psql.setString(3, Tipo);
            psql.setDouble(4,Precio);
            psql.setInt(5,Cantidad);
            psql.setString(6, Precio_total);
            psql.setDouble(7, Recibido);
            psql.setDouble(8, Cambio);
            
            
            
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
     public ArrayList<Ventas> listVenta(){
        ArrayList listaVentas= new ArrayList();
        Ventas ventas;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM Venta";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                ventas =new Ventas();
                ventas.setidVentas(rs.getInt(1));
                ventas.setNombre(rs.getString(2));
                ventas.setFecha(rs.getString(3));
                ventas.setTipo(rs.getString(4));
                ventas.setPrecio(rs.getDouble(5));
                ventas.setCantidad(rs.getInt(6));
                ventas.setPrecio_total(rs.getString(7));
                ventas.setRecibido(rs.getDouble(8));
                ventas.setCambio(rs.getDouble(9));
                listaVentas.add(ventas);                
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
        return listaVentas;        
    }
    public int eliminarVentas(int idVentas){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM Venta WHERE idventas=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,idVentas);
            
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
    


