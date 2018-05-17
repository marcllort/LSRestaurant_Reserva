package View;

import Controller.ControllerMainWindow;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class PanelSortida extends JPanel {

    private JLabel jlPreu = new JLabel();
    private JButton jbMartxar = new JButton();
    private float preu;
    private Comanda comanda;
    private DialogSortida dialogSortida;

    /**
     * Constructor amb parametres per crear el panell de sortida
     *
     * @param comanda una llista de la comanda de la reserva per tal de calcular el preu final, donada per la BBDD
     */

    public PanelSortida(Comanda comanda) {

        this.setLayout(new BorderLayout(0, 0));

        this.comanda = comanda;

        for (Plat p : this.comanda.getPlats()) {
            preu += p.getPreu();
        }

        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2,1));

        jlPreu.setText("El preu a pagar es: " + preu);
        jlPreu.setFont(new Font("Tahoma", Font.PLAIN, 38));
        jlPreu.setBounds(30, 7, 400, 124);
        panel.add(jlPreu);

        jbMartxar.setText("Pagar");
        jbMartxar.setFont(new Font("Tahoma", Font.BOLD, 30));
        jbMartxar.setBounds(169, 128, 258, 82);
        panel.add(jbMartxar);
    }


    /**
     * Registra el controlador
     *
     * @param controlador
     */
    public void registerController(ActionListener controlador) {
        jbMartxar.addActionListener(controlador);
        jbMartxar.setActionCommand("SORTIR");
    }

    /**
     * Crea el Dialog que es mostra per pantalla a l'hora de sortir
     *
     * @param controlador per tar de registrar-lo
     */
    public void dialogSortida(ControllerMainWindow controlador, Comanda comanda) {
        this.comanda = comanda;
        dialogSortida = new DialogSortida(comanda, controlador);

    }



}
