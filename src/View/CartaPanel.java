package View;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CartaPanel extends JPanel {


    private JButton jbAnterior;
    private JButton jbSeguent;
    private int numPagina;

    private JLabel jlPgina;
    private PaginaCarta pag;

    /**
     * Constructor sense parapetres que crea el panell de la carta
     */
    public CartaPanel() {

        pag = new PaginaCarta(1);


        this.setLayout(new BorderLayout());

        this.add(pag, BorderLayout.CENTER);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 206, 559, 46);


        jbAnterior = new JButton("Anterior");
        jbAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jbAnterior.setBounds(10, 6, 107, 35);
        panel_4.add(jbAnterior);

        jbSeguent = new JButton("Seg\u00FCent");
        jbSeguent.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jbSeguent.setBounds(442, 6, 107, 35);
        panel_4.add(jbSeguent);

        jlPgina = new JLabel("P\u00E0gina 1");
        jlPgina.setFont(new Font("Tahoma", Font.PLAIN, 17));
        jlPgina.setBounds(244, 12, 77, 23);
        panel_4.add(jlPgina);


        numPagina = 1;


        this.add(panel_4, BorderLayout.SOUTH);

    }


    /**
     * S'encarrega de registrar el controlador als botons i als panells
     *
     * @param c Controlador
     */
    public void registerController(ActionListener c) {
        jbAnterior.addActionListener(c);
        jbAnterior.setActionCommand("ANTERIOR");
        jbSeguent.addActionListener(c);
        jbSeguent.setActionCommand("SEGUENT");
        pag.registraControler(c);

    }


    /**
     * Crea les pagines de la carta
     *
     * @param plats  Un array que conte els diferents plats de cada pagina
     * @param pagina el numero de la pagina
     */
    public void paginaCarta(ArrayList<Plat> plats, int pagina) {
        numPagina = pagina;
        pag.canviaPagina(pagina);
        this.remove(((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER));
        this.add(pag, BorderLayout.CENTER);
        jlPgina.setText("P\u00E0gina " + numPagina);


        this.repaint();
        this.revalidate();
    }

    /**
     * Getter del numero de pagina
     *
     * @return numero de pagina
     */

    public int getPaginaCarta() {
        return numPagina;
    }

    /**
     * Getter del panell de la pagina
     *
     * @return el panell corresponent
     */
    public PaginaCarta getPag() {
        return pag;
    }


}
