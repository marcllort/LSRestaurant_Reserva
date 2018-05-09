package View;

import Model.Carta;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CartaPanel extends JPanel {

    /*private Carta carta;
    private JButton jbTornar;
    private JButton jbSeguent;
    private int paginaActual;
    private int numPagines = 0;
    private JLabel jlPagina;
    private ArrayList<PlatsPanel> arrayPanels;*/


    private JButton jbAnterior;
    private JButton jbSeguent;
    private int numPagina;

    private JLabel jlPgina;
    private PaginaCarta pag;


    /**
     * Constructor amb parametres del panell on es mostra la carta
     *
     */
    public CartaPanel(){

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


        /*this.carta = carta;
        arrayPanels = new ArrayList<PlatsPanel>();
        this.setLayout(new BorderLayout());
        jbTornar = new JButton("Tornar");
        jbSeguent = new JButton("Seguent");
        JPanel jpAux = new JPanel();
        jpAux.setLayout(new BoxLayout(jpAux, BoxLayout.X_AXIS));
        paginaActual = 1;
        arrayPanels = creaPanells();
        this.add(arrayPanels.get(paginaActual - 1), BorderLayout.CENTER);
        jlPagina = new JLabel("PAGINA  " + paginaActual);
        jpAux.add(jbTornar);
        jpAux.add(jlPagina);
        jpAux.add(jbSeguent);
        jbSeguent.setEnabled(false);
        jbTornar.setEnabled(false);
        activaBotons();
        this.add(jpAux, BorderLayout.PAGE_END);*/


    }

    /**
     * Funcio que crea els diferents panells on es troba la carta
     * @return retorna un array amb els diferents panells
     */

    /*private ArrayList<PlatsPanel> creaPanells(){

        ArrayList<PlatsPanel> array = new ArrayList<PlatsPanel>();
        ArrayList<Plat> plats = new ArrayList<Plat>();
        int i = 0;//quantitat plats

        for (Plat p: carta.getPlats()){
            i++;
            plats.add(p);
            if(i == carta.getPlats().size()){
                numPagines++;
                PlatsPanel pp = new PlatsPanel(plats, numPagines);
                array.add(pp);
            }else if(plats.size() == 6){
                numPagines++;
                PlatsPanel pp = new PlatsPanel(plats, numPagines);
                array.add(pp);
            }
        }

        return array;
    }

    /**
     *Registra el controlador als botons i als panells de la carta
     * @param c
     */

    public void registerController(ActionListener c){
        jbAnterior.addActionListener(c);
        jbAnterior.setActionCommand("ANTERIOR");
        jbSeguent.addActionListener(c);
        jbSeguent.setActionCommand("SEGUENT");
        pag.registraControler(c);

    }

    /**
     * S'encarrega d'activar i desactivar els botons
     */
    /*public void activaBotons(){
        if (paginaActual == numPagines){
            jbSeguent.setEnabled(false);
        }
        if(paginaActual == 1){
            jbTornar.setEnabled(false);
        }
        if(paginaActual != numPagines){
            jbSeguent.setEnabled(true);
        }
        if(paginaActual != 1){
            jbTornar.setEnabled(true);
        }
    }

    /**
     * S'encarrega de cambiar la pagina de la carta
     * @param on String que ens diu cap a on ens hem de moure
     */
    /*public void cambiaPagina(String on){

        if (on.equals("anterior")){
            paginaActual--;
            this.add(arrayPanels.get(paginaActual - 1), BorderLayout.CENTER);
            jlPagina.setText("PAGINA  " + paginaActual);
            activaBotons();
        }
        if(on.equals("seguent")){
            this.add(arrayPanels.get(paginaActual), BorderLayout.CENTER);
            paginaActual++;
            jlPagina.setText("PAGINA  " + paginaActual);
            activaBotons();
        }
    }

    /**
     * retorna un panell del arrayList de panells
     * @param i quin volem
     * @return el panell
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

    public int getPaginaCarta() {
        return numPagina;
    }

    public PaginaCarta getPag() {
        return pag;
    }

    /*public PlatsPanel getPagina(int i){return arrayPanels.get(i);}

    /**
     * Getter per saber la pagina en la que ens trobem
     * @return numero pagina actual
     */

    /*public int getPaginaActual(){return paginaActual;}

    /**
     * Getter que retorna l'array de panels de la carta
     * @return
     */

    /*public ArrayList<PlatsPanel> getArrayPanels() { return arrayPanels; }*/
}
