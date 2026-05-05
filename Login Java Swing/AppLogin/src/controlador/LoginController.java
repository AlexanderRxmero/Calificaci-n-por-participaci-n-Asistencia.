package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.FrmLogin;
import vista.FrmBienvenida;
import javax.swing.JOptionPane;

/**
 * @author Alexander Romero Ramírez
 */
public class LoginController {

    private UsuarioDAO dao = new UsuarioDAO();

    /**
     * Valida las credenciales y redirige al formulario correcto.
     * 
     * @param usuario nombre de usuario ingresado
     * @param contrasena contraseña ingresada
     * @param vista referencia al FrmLogin para cerrarla si el login es exitoso
     */
    public void procesarLogin(String usuario, String contrasena, FrmLogin vista) {
        // 1. Validar que los campos no estén vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingresa usuario y contraseña.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Consultar la base de datos
        Usuario u = dao.login(usuario, contrasena);

        // 3. Evaluar resultado
        if (u != null) {
            // Login exitoso: abrir bienvenida y cerrar login
            new FrmBienvenida(u).setVisible(true);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.", "Error de acceso", JOptionPane.ERROR_MESSAGE);
        }
    }
}