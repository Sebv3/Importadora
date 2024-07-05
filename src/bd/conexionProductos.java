package bd;

import packageBase.Productos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import packageBase.DetallePedidoClase;

public class conexionProductos {

    Connection cn;

    private static Statement Consulta;
    private static ResultSet Resultado;

    private String SQL_AGREGAR = "INSERT INTO productos (id_producto, nombre, precio, stock, imagen) VALUES (?, ?, ?, ?, ?)";
    private String SQL_CONSULTA = "SELECT * FROM productos";
    private String SQL_ELIMINAR = "DELETE FROM productos WHERE id_producto = ?";
    private String SQL_ACTUALIZAR = "UPDATE productos SET id_producto = ?, nombre = ?, precio = ?, stock = ?, imagen = ? WHERE id_producto = ?";
    private String SQL_PRODUCTO_PEDIDO = "INSERT INTO carrito (id_usuario, id_producto, nombre, precio, imagen, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String SQL_CONSULTA_CARRITO = "SELECT * FROM carrito WHERE  id_usuario = ?";
    private String SQL_ELIMINAR_CARRITO = "DELETE FROM carrito WHERE id_usuario = ?";
    private String SQL_AGREGAR_PEDIDO = "INSERT INTO pedido (id_usuario, direccion, estado, total) VALUES (?, ?, ?, ?)";
    private String SQL_AGREGAR_DETALLE = "INSERT INTO detalle_pedido (id_pedido, id_usuario, id_producto, nombre, cantidad, precio, subtotal, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                producto.setId_producto(Resultado.getInt("id_producto"));
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
        String SQL_ACTUALIZAR_STOCK = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";
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

    public void actualizarStockSuma(int id, int cantidad) {
        String SQL_ACTUALIZAR_STOCK = "UPDATE productos SET stock = stock + ? WHERE id_producto = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (!ConectarBD()) {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

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
            desconectarBD();
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }
    }

    public void AgregarProductoPedido(int usuario_id, int producto_id, String nombre, double precio, byte[] imagen, int cantidad, double subtotal) {
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            preparedStatement = cn.prepareStatement(SQL_CONSULTA_CARRITO);
            preparedStatement.setInt(1, usuario_id);
            resultado = preparedStatement.executeQuery();

            boolean encontrado = false;

            while (resultado.next()) {
                int id_producto = resultado.getInt("id_producto");
                if (id_producto == producto_id) {
                    int cantidadActual = resultado.getInt("cantidad");
                    double subtotalActual = resultado.getDouble("subtotal");
                    cantidad += cantidadActual;
                    subtotal += subtotalActual;

                    PreparedStatement updateStatement = cn.prepareStatement("UPDATE carrito SET cantidad = ?, subtotal = ? WHERE id_producto = ?");
                    updateStatement.setInt(1, cantidad);
                    updateStatement.setDouble(2, subtotal);
                    updateStatement.setInt(3, producto_id);
                    updateStatement.executeUpdate();

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                // Si no se encontró el producto en el carrito, agregarlo como nuevo
                preparedStatement = cn.prepareStatement(SQL_PRODUCTO_PEDIDO);
                preparedStatement.setInt(1, usuario_id);
                preparedStatement.setInt(2, producto_id);
                preparedStatement.setString(3, nombre);
                preparedStatement.setDouble(4, precio);
                preparedStatement.setBytes(5, imagen);
                preparedStatement.setInt(6, cantidad);
                preparedStatement.setDouble(7, subtotal);
                preparedStatement.executeUpdate();
            }

            System.out.println("Producto agregado al pedido correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al agregar el producto al pedido: " + ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar PreparedStatement o ResultSet: " + ex);
            }
        }
    }

    public boolean EliminarProductoCarrito(int id_producto, int id_usuario) {
        PreparedStatement preparedStatement = null;
        try {
            if (!ConectarBD()) {
                return false;
            }

            preparedStatement = cn.prepareStatement("DELETE FROM carrito WHERE id_producto = ? AND id_usuario = ?");
            preparedStatement.setInt(1, id_producto);
            preparedStatement.setInt(2, id_usuario);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto eliminado del carrito correctamente");
                return true;
            } else {
                System.out.println("No se encontró el producto en el carrito para eliminar");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar producto del carrito: " + ex);
            return false;
        } finally {
            desconectarBD();
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }
    }

    public int AgregarPedido(int id_usuario, double total, String direccion, String estado) {
        PreparedStatement preparedStatement = null;
        int idPedidoGenerado = -1;

        try {
            if (!ConectarBD()) {
                return idPedidoGenerado;
            }

            preparedStatement = cn.prepareStatement(SQL_AGREGAR_PEDIDO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id_usuario);
            preparedStatement.setString(2, direccion);
            preparedStatement.setString(3, estado);
            preparedStatement.setDouble(4, total);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPedidoGenerado = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al agregar el pedido: " + ex);
        } finally {
            desconectarBD();
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            }
        }

        return idPedidoGenerado;
    }

    public void AgregarDetallePedido(int id_pedido, int id_usuario, int id_producto, String nombre, int cantidad, double precio, double subtotal, byte[] imagen) {
        PreparedStatement preparedStatement = null;

        try {
            if (!ConectarBD()) {
                System.out.println("No se pudo conectar a la base de datos");
                return;
            }

            preparedStatement = cn.prepareStatement(SQL_AGREGAR_DETALLE);
            preparedStatement.setInt(1, id_pedido);
            preparedStatement.setInt(2, id_usuario);
            preparedStatement.setInt(3, id_producto);
            preparedStatement.setString(4, nombre);
            preparedStatement.setInt(5, cantidad);
            preparedStatement.setDouble(6, precio);
            preparedStatement.setDouble(7, subtotal);
            preparedStatement.setBytes(8, imagen);

            preparedStatement.executeUpdate();
            System.out.println("Detalle de pedido agregado correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al agregar el detalle de pedido: " + ex);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex);
            } finally {
                desconectarBD();
            }
        }
    }

    public ArrayList<DetallePedidoClase> CargarDetalle(int idUsuario) {
        ArrayList<DetallePedidoClase> ListaDetalle = new ArrayList<>();
        if (!ConectarBD()) {
            return null;
        }
        try {
            PreparedStatement consulta = cn.prepareStatement(SQL_CONSULTA_CARRITO);
            consulta.setInt(1, idUsuario);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                DetallePedidoClase detalle = new DetallePedidoClase(
                        resultado.getInt("id"),
                        resultado.getInt("id_producto"),
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getDouble("precio"),
                        resultado.getBytes("imagen"),
                        resultado.getInt("cantidad"),
                        resultado.getDouble("subtotal")
                );
                ListaDetalle.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los productos: " + e);
            return null;
        } finally {
            desconectarBD();
        }
        return ListaDetalle;
    }

    public boolean EliminarCarrito(int id_usuario) {
        PreparedStatement preparedStatement = null;
        try {
            if (!ConectarBD()) {
                return false;
            }

            preparedStatement = cn.prepareStatement(SQL_ELIMINAR_CARRITO);
            preparedStatement.setInt(1, id_usuario);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se eliminaron los productos del carrito correctamente");
                return true;
            } else {
                System.out.println("No se encontraron productos en el carrito para eliminar");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar productos del carrito: " + ex);
            return false;
        } finally {
            desconectarBD();
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
