package bd;

import packageBase.Productos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class conexionProductos {

    Connection cn;

    private static Statement Consulta;
    private static ResultSet Resultado;

    private String SQL_AGREGAR = "INSERT INTO productos (id, nombre, precio, stock, imagen) VALUES (?, ?, ?, ?, ?)";
    private String SQL_CONSULTA = "SELECT * FROM productos";
    private String SQL_ELIMINAR = "DELETE FROM productos WHERE id = ?";
    private String SQL_ACTUALIZAR = "UPDATE productos SET id = ?, nombre = ?, precio = ?, stock = ?, imagen = ? WHERE id = ?";
    private String SQL_PRODUCTO_PEDIDO = "INSERT INTO detalle_pedido (producto_id, nombre, precio, imagen, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?)";

    public boolean ConectarBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/importadorabd", "root", "");
            System.out.println("Conexion Exitosa");
            return true;
        } catch (Exception e) {
            System.out.println("Conexion Fallida: " + e);
            return false;
        }
    }

    public void desconectarBD() {
        if (cn != null) {
            try {
                cn.close();
                System.out.println("Desconexion Exitosa");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
                e.printStackTrace();
            }
        }
    }

    public void AgregarProducto(Productos producto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_AGREGAR);
            preparedStatement.setInt(1, producto.getId_producto());
            preparedStatement.setString(2, producto.getNombre_producto());
            preparedStatement.setInt(3, producto.getPrecio_producto());
            preparedStatement.setInt(4, producto.getStock_producto());
            preparedStatement.setBytes(5, producto.getImagen_producto());
            preparedStatement.executeUpdate();
            System.out.println("Producto agregado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al agregar el producto: " + ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }
    }

    public ArrayList<Productos> CargarProductos() {
        ArrayList<Productos> ListaProductos = new ArrayList<>();
        try {
            Consulta = cn.createStatement();
            Resultado = Consulta.executeQuery(SQL_CONSULTA);
            while (Resultado.next()) {
                Productos producto = new Productos();
                producto.setId_producto(Resultado.getInt("id"));
                producto.setNombre_producto(Resultado.getString("nombre"));
                producto.setPrecio_producto(Resultado.getInt("precio"));
                producto.setStock_producto(Resultado.getInt("stock"));
                producto.setImagen_producto(Resultado.getBytes("imagen"));
                ListaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los productos: " + e);
            return null;
        }
        return ListaProductos;
    }

    public boolean EliminarProducto(int id_producto) {
        PreparedStatement preparedStatement = null;
        boolean resultado = false;

        try {
            preparedStatement = cn.prepareStatement(SQL_ELIMINAR);
            preparedStatement.setInt(1, id_producto);
            int filasAfectadas = preparedStatement.executeUpdate();
            resultado = filasAfectadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return resultado;
    }

    public boolean ActualizarProducto(Productos producto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_ACTUALIZAR);
            preparedStatement.setInt(1, producto.getId_producto());
            preparedStatement.setString(2, producto.getNombre_producto());
            preparedStatement.setInt(3, producto.getPrecio_producto());
            preparedStatement.setInt(4, producto.getStock_producto());
            preparedStatement.setBytes(5, producto.getImagen_producto());
            preparedStatement.setInt(6, producto.getId_producto());

            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Producto actualizado correctamente");
                return true;
            } else {
                System.out.println("No se encontró el producto con ID: " + producto.getId_producto());
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto: " + ex);
            return false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }

    }

    public void actualizarStock(int id, int cantidad) {
        String SQL_ACTUALIZAR_STOCK = "UPDATE productos SET stock = stock - ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_ACTUALIZAR_STOCK);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, id);
            int filasActualizadas = preparedStatement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Stock actualizado correctamente");
            } else {
                System.out.println("No se encontró el producto con ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el stock: " + ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }
    }
    
    
    public void AgregarProductoPedido(int id_producto, String nombre, double precio, byte[] imagen, int cantidad, double subtotal) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_PRODUCTO_PEDIDO);
            preparedStatement.setInt(1, id_producto);
            preparedStatement.setString(2, nombre);
            preparedStatement.setDouble(3, precio);
            preparedStatement.setBytes(4, imagen);
            preparedStatement.setInt(5, cantidad);
            preparedStatement.setDouble(6, subtotal);
            preparedStatement.executeUpdate();
            System.out.println("Pedido agregado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al agregar el pedido: " + ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }
    }
}
    

