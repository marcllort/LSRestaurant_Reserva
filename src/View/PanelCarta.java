package View;


import Model.Carta;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PanelCarta extends JPanel {

    private Carta carta;
    private JButton jbTornar;
    private JButton jbSeguent;
    private int quinaPagina;
    private int numPagines;
    private JLabel jlPagina;
    private ArrayList<PaginaPlats> paginaPlats;


    /**
     * Constructor que crea el panell de la carta
     * @param carta conjunt de plats a mostrar
     */
    public PanelCarta(Carta carta){

        this.carta = carta;
        paginaPlats = new ArrayList<PaginaPlats>();
        paginaPlats = creaPaginaPlats(carta);
        this.setLayout(new BorderLayout());

        JPanel jpAux = new JPanel();
        //CAMBIAR A FLECHA
        jbTornar = new JButton("Tornar");
        //CAMBIAR A FLECHA
        jbSeguent = new JButton("Seguent");




    }

    private ArrayList<PaginaPlats> creaPaginaPlats(Carta carta){

        paginaPlats = new ArrayList<PaginaPlats>();
        numPagines = 0;
        PaginaPlats pp;
        int j = 0;
        LinkedList<Plat> quinsPlats = new LinkedList<Plat>();
        Plat p;
        int j = 0; //ens guardem el numero de pagina

        //MIRAR DISPONIBILIDAD ANTES DE AÑADIRLO

        for(int i = 0; i < carta.getNumPlats(); i++){
            p = new Plat();
            p = carta.getPlat(i);
            if(quinsPlats.size() == 6 && i != (carta.getNumPlats()- 1)){
                j++;
                pp = new PaginaPlats(quinsPlats, j);
                paginaPlats.add(pp);
                quinsPlats = new LinkedList<Plat>();
                numPagines++;
            }
            quinsPlats.add(p);
            if(i == (carta.getNumPlats()- 1)){
                j++;
                pp = new PaginaPlats(quinsPlats, j);
                paginaPlats.add(pp);
                numPagines++;
            }
        }

        return paginaPlats;
    }


}
