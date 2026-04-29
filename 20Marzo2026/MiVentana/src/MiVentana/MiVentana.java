package MiVentana;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Alexander Romero Ramírez
 */
public class MiVentana extends JFrame implements ActionListener
{
    // 1. Declarar los componentes como atributos de clase
    private JButton boton1;
    private JLabel label1;
    
    // 2. Constructor: crear, configurar y agregar componentes
    public MiVentana()
    {
        setLayout(null); // Posicionamiento absoluto
        setTitle("Mi aplicación"); // Titulo de la ventana
        
        label1 = new JLabel("Hola, Swing!");
        label1.setBounds(20, 20, 200, 30); // (x, y, ancho, alto)
        add(label1);
        
        boton1 = new JButton("Click aqui");
        boton1.setBounds(20, 70, 130, 35);
        boton1.addActionListener(this);
        add(boton1);
    }
    
    // 3. Implementar el método de la interfaz ActionListener
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == boton1)
        {
            label1.setText("Boton presionado!");
        }
    }
    
    // 4. Método main: punto de entrada
    public static void main(String[] args)
    {
        MiVentana ventana = new MiVentana();
        ventana.setBounds(100, 100, 320, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}