package bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionLogin {
    

    Connection cn;
    
    public Connection ConectarBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/importadorabd", "root", "");
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.println("Conexion Fallida" + e);
        }
        return cn;  
    }
}

