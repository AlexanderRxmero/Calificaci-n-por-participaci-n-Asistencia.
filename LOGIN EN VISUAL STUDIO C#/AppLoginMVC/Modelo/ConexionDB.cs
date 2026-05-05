using System;
using MySql.Data.MySqlClient;

namespace AppLoginMVC.Modelo
{
    public class ConexionDB
    {
        // ── Parámetros de conexión ──────────────────────────────
        private const string Servidor = "localhost";
        private const string Puerto = "3306";
        private const string BaseDatos = "app_login_csharp";
        private const string Usuario = "root";
        private const string Password = "Alexander.2006";

        // Instancia única (Singleton)
        private static ConexionDB _instancia;
        private MySqlConnection _conexion;

        private ConexionDB() { }

        public static ConexionDB Instancia
        {
            get
            {
                if (_instancia == null)
                {
                    _instancia = new ConexionDB();
                }
                return _instancia;
            }
        }

        /// <summary>
        /// Genera la cadena de conexión de forma segura usando el Builder oficial.
        /// Esto evita errores de formato (ArgumentException) con caracteres especiales.
        /// </summary>
        private static string GetConnectionString()
        {
            MySqlConnectionStringBuilder builder = new MySqlConnectionStringBuilder();
            builder.Server = Servidor;
            builder.Port = Convert.ToUInt32(Puerto);
            builder.Database = BaseDatos;
            builder.UserID = Usuario;
            builder.Password = Password;
            builder.SslMode = MySqlSslMode.Disabled;
            builder.AllowPublicKeyRetrieval = true;
            builder.CharacterSet = "utf8mb4";

            return builder.ConnectionString;
        }

        // Obtener (o abrir) la conexión
        public MySqlConnection GetConexion()
        {
            try
            {
                if (_conexion == null)
                {
                    _conexion = new MySqlConnection(GetConnectionString());
                }

                if (_conexion.State != System.Data.ConnectionState.Open)
                {
                    _conexion.Open();
                }
            }
            catch (MySqlException ex)
            {
                throw new Exception("Error al conectar a MySQL: " + ex.Message);
            }

            return _conexion;
        }

        // Cerrar conexión de forma segura
        public void Cerrar()
        {
            if (_conexion != null && _conexion.State == System.Data.ConnectionState.Open)
            {
                _conexion.Close();
            }
        }

        // Probar conexión (usado en Program.cs)
        public static bool ProbarConexion()
        {
            try
            {
                using (MySqlConnection conn = new MySqlConnection(GetConnectionString()))
                {
                    conn.Open();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}