package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alexander Romero Ramírez
 */
public class ConexionDB
{
    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/app_login"; // cambiar el número del localhost si es necesario
    private static final String USUARIO = "root"; // cambiar si es necesario
    private static final String PASSWORD = "Alexander.2006"; // agregar contraseña
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static Connection conexion = null;
    
    // Obtener conexión (Singleton)
    public static Connection getConexion()
    {
        try
        {
            if (conexion == null || conexion.isClosed())
            {
                Class.forName(DRIVER);
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión establecida correctamente.");
            }
        }
        
        catch (ClassNotFoundException e)
        {
            System.err.println("Driver JDBC no encontrado: "+e.getMessage());
        }
        
        catch (SQLException e)
        {
            System.err.println("Error al conectar con MySQL: "+e.getMessage());
        }
        
        return conexion;
    }
    
    // Cerrar conexión
    public static void cerrarConexion()
    {
        try
        {
            if (conexion != null && !conexion.isClosed())
            {
                conexion.close(); System.out.println("Conexión cerrada.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error al cerrar: "+e.getMessage());
        }
    }
}