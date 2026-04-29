package appconmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Alexander Romero Ramírez
 */
public class AppConMenu extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menuVista, menuTamano, menuAyuda, menuHerramientas;
    private JMenuItem miRojo, miAzul, miVerde, miBlancoFondo;
    private JMenuItem mi800, mi1024, miMaximizar, miSalir, miAcerca;
    private JMenuItem miHerramienta1;
    private JLabel lblInfo;

    public AppConMenu() {
        setLayout(null);
        setTitle("Aplicacion con Menu Avanzada");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Cambiado para controlar el cierre

        // Listener para la "X" de la ventana también pida confirmación
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { confirmarSalida(); }
        });

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // --- Menu Vista ---
        menuVista = new JMenu("Vista");
        menuBar.add(menuVista);

        miRojo = new JMenuItem("Fondo Rojo");
        // 18. Acelerador Ctrl + R
        miRojo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        miRojo.addActionListener(this);
        menuVista.add(miRojo);

        miAzul = new JMenuItem("Fondo Azul");
        miAzul.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        miAzul.addActionListener(this);
        menuVista.add(miAzul);

        // 19. Submenu Herramientas dentro de Vista
        menuHerramientas = new JMenu("Herramientas");
        miHerramienta1 = new JMenuItem("Opción Extra");
        menuHerramientas.add(miHerramienta1);
        menuVista.add(menuHerramientas);

        menuVista.addSeparator();
        miBlancoFondo = new JMenuItem("Restaurar fondo");
        miBlancoFondo.addActionListener(this);
        menuVista.add(miBlancoFondo);

        // --- Menu Ventana ---
        menuTamano = new JMenu("Ventana");
        menuBar.add(menuTamano);

        mi800 = new JMenuItem("800 x 600");
        mi800.addActionListener(this);
        menuTamano.add(mi800);

        // 17. JMenuItem Maximizar
        miMaximizar = new JMenuItem("Maximizar");
        miMaximizar.addActionListener(this);
        menuTamano.add(miMaximizar);

        menuTamano.addSeparator();
        miSalir = new JMenuItem("Salir");
        miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        miSalir.addActionListener(this);
        menuTamano.add(miSalir);

        // --- Menu Ayuda ---
        menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuAyuda);
        miAcerca = new JMenuItem("Acerca de...");
        miAcerca.addActionListener(this);
        menuAyuda.add(miAcerca);

        lblInfo = new JLabel("Prueba los atajos de teclado (Ctrl+R, Ctrl+A, Ctrl+Q)");
        lblInfo.setBounds(20, 20, 500, 30);
        add(lblInfo);
    }

    // 20. Método para confirmar salida
    private void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas salir?", "Confirmación", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        Container fondo = getContentPane();

        if (src == miRojo) fondo.setBackground(new Color(220, 80, 80));
        else if (src == miAzul) fondo.setBackground(new Color(70, 130, 200));
        else if (src == miBlancoFondo) fondo.setBackground(null);
        else if (src == mi800) setSize(800, 600);
        
        // 17. Lógica Maximizar
        else if (src == miMaximizar) setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 20. Lógica Salir con confirmación
        else if (src == miSalir) confirmarSalida();
        
        else if (src == miAcerca) {
            JOptionPane.showMessageDialog(this, "Editor con Menús V2.0\nAtajos activados.");
        }
    }

    public static void main(String[] args) {
        AppConMenu app = new AppConMenu();
        app.setBounds(150, 100, 600, 400);
        app.setVisible(true);
    }
}