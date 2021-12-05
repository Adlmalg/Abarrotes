
package Modelo;

/**
 *
 * @author Adelma Lorenzo
 */
import static Modelo.Conector.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioIA {
     Conector con = new Conector();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarUsuario(String Nombre,String Apellidos,String Telefono,String Contraseña) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO Empleado(Nombre_Empleado,Apellidos,Telefono,contraseña)"
                + " VALUES (?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,Nombre);
            psql.setString(2, Apellidos);
            psql.setString(3, Telefono);
            psql.setString(4, Contraseña);
            
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
            
        }
        return resultado;
    }

    
     public ArrayList<Usuario> listUser(){
        ArrayList listaUsuario= new ArrayList();
        Usuario usuario;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM Empleado";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                usuario =new Usuario();
                usuario.setNombre(rs.getString(1));
                usuario.setApellidos(rs.getString(2));
                usuario.setTelefono(rs.getString(3));
                usuario.setContraseña(rs.getString(4));
                
                listaUsuario.add(usuario);                
            }            
        }catch(SQLException e){
            System.err.println(e);            
        }
        finally {
           
        }
        return listaUsuario;     
    }
     public boolean login(Usuario usr) throws SQLException{
         PreparedStatement ps= null;
         ResultSet rs=null;
         Connection con= Conectar();
         String sql ="SELECT Nombre_Empleado,Apellidos,Telefono,contraseña FROM Empleado WHERE Nombre_Empleado=? ";
         try {
             ps= con.prepareStatement(sql);
             ps.setString(1,usr.getNombre());
             rs = ps.executeQuery();
             if (rs.next())
             {
                 if(usr.getContraseña().equals(rs.getString(4))){
                     usr.setApellidos(rs.getString(2));
                     usr.setTelefono(rs.getString(3));
                     return true;
                 }else{
                     return false;   
                 }
                 
             }
             return false;
         }catch (SQLException ex){
             Logger.getLogger(UsuarioIA.class.getName()).log(Level.SEVERE,null, ex);
             return false;
         }
         
         
     }
     
    public int eliminarEmpleado(String Nombre_empleado){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM Empleado WHERE Nombre_Empleado=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1,Nombre_empleado);
            
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

    public boolean login(Vista.Usuario Us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
