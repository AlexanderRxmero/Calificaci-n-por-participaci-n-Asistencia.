package principal;
import vista.FrmLogin;
import javax.swing.SwingUtilities;

/**
 * @author Alexander Romero Ramírez
 */
public class Main
{
    public static void main(String[] args)
    {
    // Ejecutar en el hilo de la interfaz gráfica (EDT)
        SwingUtilities.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}