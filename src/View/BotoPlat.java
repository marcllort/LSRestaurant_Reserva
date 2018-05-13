package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotoPlat extends JButton {
    private JButton button;
    private String nomPlat;


    /**
     * Constructor dels botons de la carta
     * @param text String que ens diu de quin plat es tracta
     */
    public BotoPlat(String text) {
        nomPlat = text;

        button = new JButton(text);
        button.setBounds(10, 11, 125, 75);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }

    /**
     * Getter del boto
     * @return retorna el boto
     */
    public JButton getBoto() {
        return button;
    }

    /**
     * Registra el controlador al boto
     * @param controller Action Listener
     * @param text String per possar nom al command
     */
    public void registraController(ActionListener controller, String text){
        button.addActionListener(controller);
        button.setActionCommand(text);
    }

    /**
     * Getter del nom del plat en questio
     * @return el nom del plat
     */
    public String getNomPlat() {
        return nomPlat;
    }

}
