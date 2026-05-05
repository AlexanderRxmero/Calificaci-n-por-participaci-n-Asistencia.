// Program.cs 
using AppLoginMVC.Modelo;
using System;
using System.Windows.Forms;

namespace AppLoginMVC
{
    internal static class Program
    {
        [STAThread]
        static void Main()
        {
            // Configuración estándar para WinForms en C# 7.3 / .NET Framework
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Verificar conexión a MySQL antes de iniciar 
            if (!ConexionDB.ProbarConexion())
            {
                MessageBox.Show(
                    "No se pudo conectar a la base de datos MySQL.\n" +
                    "Verifica que MySQL esté activo y que los datos\n" +
                    "de conexión en ConexionDB.cs sean correctos.",
                    "Error de conexión",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
                return;  // Salir si no hay conexión 
            }

            // Iniciar con el formulario de Login 
            Application.Run(new FrmLogin());
        }
    }
}