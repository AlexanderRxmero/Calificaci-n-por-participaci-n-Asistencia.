package cotizador;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Alexander Romero Ramírez
 */
public class Cotizador extends JFrame implements ActionListener, ItemListener {

    private JLabel lblPlan, lblExtras, lblTotal;
    private JRadioButton rBasico, rProfesional, rEmpresarial;
    private ButtonGroup bgPlanes;
    private JCheckBox chkSoporte, chkBackup, chkSeguridad, chkAnual;
    private JButton btnCotizar, btnLimpiar; // 23. Agregado btnLimpiar

    public Cotizador() {
        setLayout(null);
        setTitle("Cotizador de Servicios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Planes ---
        lblPlan = new JLabel("Selecciona tu plan:");
        lblPlan.setBounds(20, 15, 200, 25);
        add(lblPlan);

        bgPlanes = new ButtonGroup();
        rBasico = new JRadioButton("Básico $199/mes");
        rProfesional = new JRadioButton("Profesional $399/mes");
        rEmpresarial = new JRadioButton("Empresarial $799/mes");
        
        rBasico.setBounds(20, 45, 250, 25);
        rProfesional.setBounds(20, 75, 250, 25);
        rEmpresarial.setBounds(20, 105, 250, 25);
        rBasico.setSelected(true);

        rBasico.addItemListener(this);
        rProfesional.addItemListener(this);
        rEmpresarial.addItemListener(this);

        bgPlanes.add(rBasico); bgPlanes.add(rProfesional); bgPlanes.add(rEmpresarial);
        add(rBasico); add(rProfesional); add(rEmpresarial);

        // --- Extras ---
        lblExtras = new JLabel("Servicios adicionales:");
        lblExtras.setBounds(20, 145, 220, 25);
        add(lblExtras);

        chkSoporte = new JCheckBox("Soporte 24/7 +$99");
        chkBackup = new JCheckBox("Backup diario +$49");
        chkSeguridad = new JCheckBox("Seguridad Plus +$79");
        
        chkSoporte.setBounds(20, 175, 250, 25);
        chkBackup.setBounds(20, 205, 250, 25);
        chkSeguridad.setBounds(20, 235, 250, 25);

        chkSoporte.addItemListener(this);
        chkBackup.addItemListener(this);
        chkSeguridad.addItemListener(this);

        add(chkSoporte); add(chkBackup); add(chkSeguridad);

        chkAnual = new JCheckBox("Pago Anual (15% Descuento)");
        chkAnual.setBounds(20, 270, 250, 25);
        chkAnual.addItemListener(this);
        add(chkAnual);

        // --- Botones ---
        btnCotizar = new JButton("Ver detalle");
        btnCotizar.setBounds(20, 310, 140, 35);
        btnCotizar.addActionListener(this);
        add(btnCotizar);

        // 23. Botón Limpiar Selección
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(170, 310, 100, 35);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        lblTotal = new JLabel("Total mensual: $199.00");
        lblTotal.setBounds(20, 360, 300, 30);
        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14));
        add(lblTotal);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        calcular();
    }

    private void calcular() {
        double total = 0;
        if (rBasico.isSelected()) total += 199;
        else if (rProfesional.isSelected()) total += 399;
        else if (rEmpresarial.isSelected()) total += 799;

        if (chkSoporte.isSelected()) total += 99;
        if (chkBackup.isSelected()) total += 49;
        if (chkSeguridad.isSelected()) total += 79;

        if (chkAnual.isSelected()) total *= 0.85;

        lblTotal.setText(String.format("Total: $%.2f MXN", total));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLimpiar) {
            // 23. Restablecer valores
            rBasico.setSelected(true);
            chkSoporte.setSelected(false);
            chkBackup.setSelected(false);
            chkSeguridad.setSelected(false);
            chkAnual.setSelected(false);
            calcular();
        }

        if (e.getSource() == btnCotizar) {
            // 24. Resumen detallado con conceptos y precios
            String resumen = "--- DETALLE DE COTIZACIÓN ---\n\n";
            double subtotal = 0;

            if (rBasico.isSelected()) { resumen += "Plan Básico: $199\n"; subtotal += 199; }
            else if (rProfesional.isSelected()) { resumen += "Plan Profesional: $399\n"; subtotal += 399; }
            else if (rEmpresarial.isSelected()) { resumen += "Plan Empresarial: $799\n"; subtotal += 799; }

            if (chkSoporte.isSelected()) { resumen += "Soporte 24/7: $99\n"; subtotal += 99; }
            if (chkBackup.isSelected()) { resumen += "Backup Diario: $49\n"; subtotal += 49; }
            if (chkSeguridad.isSelected()) { resumen += "Seguridad Plus: $79\n"; subtotal += 79; }

            resumen += "\nSubtotal: $" + subtotal;

            if (chkAnual.isSelected()) {
                double descuento = subtotal * 0.15;
                resumen += "\nDescuento Anual (15%): -$" + String.format("%.2f", descuento);
                resumen += "\nTOTAL FINAL: $" + String.format("%.2f", (subtotal - descuento));
            } else {
                resumen += "\nTOTAL FINAL: $" + subtotal;
            }

            JOptionPane.showMessageDialog(this, resumen, "Resumen de Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Cotizador cot = new Cotizador();
        cot.setBounds(200, 150, 340, 450);
        cot.setResizable(false);
        cot.setVisible(true);
    }
}