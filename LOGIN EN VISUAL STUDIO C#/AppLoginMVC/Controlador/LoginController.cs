// Controlador/LoginController.cs 
using AppLoginMVC.Modelo;
using System.Windows.Forms;

namespace AppLoginMVC.Controlador
{
    public class LoginController
    {
        private readonly UsuarioDAO _dao = new UsuarioDAO();

        /// <summary> 
        /// Valida credenciales y redirige al formulario correcto. 
        /// </summary> 
        public void ProcesarLogin(string usuario, string contrasena, FrmLogin vista)
        {
            // 1. Campos vacíos 
            if (string.IsNullOrWhiteSpace(usuario) ||
                string.IsNullOrWhiteSpace(contrasena))
            {
                vista.SetEstado("Ingresa usuario y contraseña.");
                return;
            }

            // 2. Verificar si la cuenta está bloqueada 
            if (_dao.EstasBloqueado(usuario))
            {
                MessageBox.Show(
                    "Esta cuenta está bloqueada por múltiples intentos fallidos.\n" +
                    "Contacta al administrador del sistema.",
                    "Cuenta bloqueada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
                return;
            }

            // 3. Verificar credenciales en la base de datos 
            Usuario u = _dao.Login(usuario, contrasena);

            if (u != null)
            {
                // Login exitoso: abrir bienvenida y ocultar login 
                new FrmBienvenida(u).Show();
                vista.Hide();
            }
            else
            {
                vista.SetEstado("Usuario o contraseña incorrectos.");
            }
        }
    }
}