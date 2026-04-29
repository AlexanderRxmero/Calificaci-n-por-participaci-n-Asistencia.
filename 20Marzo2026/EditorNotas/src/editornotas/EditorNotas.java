package editornotas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

/**
 * @author Alexander Romero Ramírez
 */
public class EditorNotas extends JFrame implements ActionListener {

    private JTextArea areaNota;
    private JScrollPane scroll;
    private JTextField tfBuscar;
    private JButton btnContar, btnBuscar, btnLimpiar, btnMayusculas;
    private JLabel lblEstado, lblCaracteres;

    public EditorNotas() {
        setLayout(null);
        setTitle("Editor de Notas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Área de texto y scroll
        areaNota = new JTextArea();
        areaNota.setLineWrap(true);
        areaNota.setWrapStyleWord(true);
        scroll = new JScrollPane(areaNota);
        scroll.setBounds(10, 10, 560, 280);
        add(scroll);

        // Campo de búsqueda
        tfBuscar = new JTextField();
        tfBuscar.setBounds(10, 310, 200, 30);
        add(tfBuscar);

        // Botones de acción
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(220, 310, 100, 30);
        btnBuscar.addActionListener(this);
        add(btnBuscar);

        btnContar = new JButton("Contar palabras");
        btnContar.setBounds(330, 310, 140, 30);
        btnContar.addActionListener(this);
        add(btnContar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(480, 310, 90, 30);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        // 11. Botón Mayúsculas
        btnMayusculas = new JButton("Mayúsculas");
        btnMayusculas.setBounds(330, 345, 140, 30);
        btnMayusculas.addActionListener(this);
        add(btnMayusculas);

        // Etiquetas de estado y contador
        lblEstado = new JLabel("Listo.");
        lblEstado.setBounds(10, 360, 300, 25);
        add(lblEstado);

        // 12. Etiqueta para contador de caracteres
        lblCaracteres = new JLabel("Caracteres: 0");
        lblCaracteres.setBounds(480, 360, 100, 25);
        add(lblCaracteres);

        // 12. DocumentListener para conteo en tiempo real
        areaNota.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { actualizarContador(); }
            @Override
            public void removeUpdate(DocumentEvent e) { actualizarContador(); }
            @Override
            public void changedUpdate(DocumentEvent e) { actualizarContador(); }
        });
    }

    // Método para actualizar el label de caracteres
    private void actualizarContador() {
        lblCaracteres.setText("Caracteres: " + areaNota.getText().length());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = areaNota.getText();

        // Lógica: Contar palabras
        if (e.getSource() == btnContar) {
            if (texto.trim().isEmpty()) {
                lblEstado.setText("El area esta vacia.");
            } else {
                String[] palabras = texto.trim().split("\\s+");
                lblEstado.setText("Total de palabras: " + palabras.length);
            }
        }

        // Lógica: Buscar término
        if (e.getSource() == btnBuscar) {
            String termino = tfBuscar.getText().trim();
            if (termino.isEmpty()) {
                lblEstado.setText("Escribe algo en el campo de busqueda.");
                return;
            }
            if (texto.toLowerCase().contains(termino.toLowerCase())) {
                lblEstado.setText("Encontrado: '" + termino + "'");
            } else {
                lblEstado.setText("No se encontro: '" + termino + "'");
            }
        }

        // Lógica: Limpiar campos
        if (e.getSource() == btnLimpiar) {
            areaNota.setText("");
            tfBuscar.setText("");
            lblEstado.setText("Listo.");
        }

        // 11. Lógica: Convertir a Mayúsculas
        if (e.getSource() == btnMayusculas) {
            areaNota.setText(texto.toUpperCase());
            lblEstado.setText("Texto convertido a mayúsculas.");
        }
    }

    public static void main(String[] args) {
        // Opcional: Look and Feel del sistema para que se vea moderno
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        EditorNotas editor = new EditorNotas();
        editor.setBounds(150, 100, 600, 440);
        editor.setResizable(false);
        editor.setVisible(true);
    }
}