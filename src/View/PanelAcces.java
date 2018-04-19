package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelAcces extends JPanel {

    private JLabel jlUsuari;
    private JLabel jlContrasenya;
    private JTextField jtfUsuari;
    private JTextField jtfContrasenya;
    private JButton jbEnvia;

    /**
     * Constructor sense parametres.
     * Crea el panell per accedir a la reserva
     */
    public PanelAcces() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("LSRestaurant"));

        JPanel jpUsuari = new JPanel();
        JPanel jpContrasenya = new JPanel();

        ((FlowLayout) jpUsuari.getLayout()).setAlignment(FlowLayout.LEFT);

        jlUsuari = new JLabel("Usuari");
        jtfUsuari = new JTextField();
        jtfUsuari.setPreferredSize(new Dimension(280, 25));
        jpUsuari.add(jlUsuari);
        jpUsuari.add(jtfUsuari);

        ((FlowLayout) jpContrasenya.getLayout()).setAlignment(FlowLayout.LEFT);

        jlContrasenya = new JLabel("Contrasenya");
        jtfContrasenya = new JTextField();
        jtfContrasenya.setPreferredSize(new Dimension(280, 25));
        jpContrasenya.add(jlContrasenya);
        jpContrasenya.add(jtfContrasenya);

        jbEnvia = new JButton("Envia");
        jbEnvia.setPreferredSize(new Dimension(60, 40));

        JPanel jpAux = new JPanel();

        ((FlowLayout) jpAux.getLayout()).setAlignment(FlowLayout.LEFT);

        jpAux.add(jpUsuari);
        jpAux.add(jpContrasenya);

        this.add(jpAux, BorderLayout.CENTER);
        this.add(jbEnvia, BorderLayout.PAGE_END);

    }

    public void registerController(ActionListener controlador) {
        jbEnvia.addActionListener(controlador);
        jbEnvia.setActionCommand("ENVIA CREDENCIALS");
    }

    public String getTypedUsuari() {
        return jtfUsuari.getText();
    }

    public String getTypedContrasenya() {
        return jtfContrasenya.getText();
    }

    public void cleanFields() {
        jtfUsuari.setText("");
        jtfContrasenya.setText("");
    }

}
