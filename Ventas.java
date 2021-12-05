
package Modelo;



/**
 *
 * @author Adelma Lorenzo
 */
public class Ventas {
    private int idVentas;
    private String Nombre;
    private String Fecha;
    private String Tipo;
    private double Precio;
    private int Cantidad;
    private String Precio_total;
    private double Recibido;
    private double Cambio;

    public int getidVentas() {
        return idVentas;
    }

    public void setidVentas(int idVentas) {
        this.idVentas= idVentas;
    }
   
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre= Nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio= Precio;
    }
    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad= Cantidad;
    }
     public String getPrecio_total() {
        return Precio_total;
    }

    public void setPrecio_total(String Precio_total) {
        this.Precio_total= Precio_total;
    }
    public double getRecibido() {
        return Recibido;
    }

    public void setRecibido(double Recibido) {
        this.Recibido= Recibido;
    }
    public double getCambio() {
        return Cambio;
    }

    public void setCambio(double Cambio) {
        this.Cambio= Cambio;
    
    }
}
