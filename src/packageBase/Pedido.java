package packageBase;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private Date fecha;
    private String direccion;
    private String estado;
    private double total;
    private Date fechaEntrega;

    public Pedido(int idPedido, Date fecha, String direccion, String estado, double total, Date fechaEntrega) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.direccion = direccion;
        this.estado = estado;
        this.total = total;
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
    
}
