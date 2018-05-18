package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelAcces extends JPanel {

    private JLabel jlUsuari;
    private JLabel jlContrasenya;
    private JTextField jtfUsuari;
    private JPasswordField jtfContrasenya;
    private JButton jbEnvia;

    /**
     * Constructor sense parametres.
     * Crea el panell per accedir a la reserva
     */
    public PanelAcces() {

        this.setLayout(null);

        JLabel lblLsrestaurant = new JLabel("LS_RESTAURANT");
        lblLsrestaurant.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblLsrestaurant.setBounds(162, 33, 242, 41);
        this.add(lblLsrestaurant);

        JPanel panel = new JPanel();
        panel.setBounds(75, 85, 426, 164);
        this.add(panel);
        panel.setLayout(null);

        jlUsuari = new JLabel("Usuari:");
        jlUsuari.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jlUsuari.setBounds(10, 14, 75, 20);
        panel.add(jlUsuari);

        jtfUsuari = new JTextField();
        jtfUsuari.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jtfUsuari.setBounds(134, 14, 270, 28);
        panel.add(jtfUsuari);
        jtfUsuari.setColumns(25);

        jlContrasenya = new JLabel("Contrasenya:");
        jlContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jlContrasenya.setBounds(10, 63, 127, 17);
        panel.add(jlContrasenya);

        jtfContrasenya = new JPasswordField();
        jtfContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jtfContrasenya.setBounds(134, 61, 270, 28);
        panel.add(jtfContrasenya);
        jtfContrasenya.setColumns(21);

        jbEnvia = new JButton("Entrar");
        jbEnvia.setFont(new Font("Tahoma", Font.BOLD, 20));
        jbEnvia.setBounds(155, 113, 116, 40);
        panel.add(jbEnvia);

    }

    /**
     * Registra el controlador
     *
     * @param controlador l'Action Listener
     */
    public void registerController(ActionListener controlador) {
        jbEnvia.addActionListener(controlador);
        jbEnvia.setActionCommand("ENVIA CREDENCIALS");
    }

    /**
     * Nateja els JTextFields del panell d'acces
     */
    public void cleanFields() {
        jtfUsuari.setText("");
        jtfContrasenya.setText("");
    }

    /**
     * Getter del usuari introduït al JTextField del usuari
     *
     * @return usuari escrit
     */
    public String getTypedUsuari() {
        return jtfUsuari.getText();
    }

    /**
     * Getter de la contrasenya introduïda al JTextField de la contrasenya
     *
     * @return contrasenya introduïda
     */
    public String getTypedContrasenya() {
        return String.valueOf(jtfContrasenya.getPassword());
    }


}
