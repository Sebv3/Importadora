package almacenarImagen;

public class Productos {
    private int id_producto;
    private String nombre_producto;
    private int precio_producto;
    private int stock_producto;
    private byte [] imagen_producto;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(int stock_producto) {
        this.stock_producto = stock_producto;
    }

    public byte[] getImagen_producto() {
        return imagen_producto;
    }

    public void setImagen_producto(byte[] imagen_producto) {
        this.imagen_producto = imagen_producto;
    }
    
    
     
}


