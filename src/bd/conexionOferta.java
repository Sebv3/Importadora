package bd;

import almacenarImagen.Productos;
import almacenarImagen.ProductosOferta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class conexionOferta {




    Connection cn;

    private static Statement Consulta;
    private static ResultSet Resultado;

    private String SQL_AGREGAR = "INSERT INTO productos_oferta (id_prod, nombre_prod, descripcion_prod, precio_prod, imagen) VALUES (?, ?, ?, ?, ?)";
    private String SQL_CONSULTA = "SELECT * FROM productos_oferta";
    private String SQL_ELIMINAR = "DELETE FROM productos_oferta WHERE id_prod = ?";
    private String SQL_ACTUALIZAR = "UPDATE productos_oferta SET id_prod = ?, nombre_prod = ?, descripcion_prod = ?, precio_prod = ?, imagen = ? WHERE id_prod = ?";

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

    public void AgregarProducto(ProductosOferta producto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_AGREGAR);
            preparedStatement.setInt(1, producto.getId_producto());
            preparedStatement.setString(2, producto.getNombre_producto());
            preparedStatement.setString(3, producto.getDescripcion_producto());
            preparedStatement.setInt(4, producto.getPrecio_producto());
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

    public ArrayList<ProductosOferta> CargarProductos() {
        ArrayList<ProductosOferta> ListaProductos = new ArrayList<>();
        try {
            Consulta = cn.createStatement();
            Resultado = Consulta.executeQuery(SQL_CONSULTA);
            while (Resultado.next()) {
                ProductosOferta producto = new ProductosOferta();
                producto.setId_producto(Resultado.getInt("id_prod"));
                producto.setNombre_producto(Resultado.getString("nombre_prod"));
                producto.setDescripcion_producto(Resultado.getString("descripcion_prod"));
                producto.setPrecio_producto(Resultado.getInt("precio_prod"));
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

    public boolean ActualizarProducto(ProductosOferta producto) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cn.prepareStatement(SQL_ACTUALIZAR);
            preparedStatement.setInt(1, producto.getId_producto());
            preparedStatement.setString(2, producto.getNombre_producto());
            preparedStatement.setString(3, producto.getDescripcion_producto());
            preparedStatement.setInt(4, producto.getPrecio_producto());
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
}

