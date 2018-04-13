package View;


import Model.Carta;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PanelCarta extends JPanel {

    private Carta carta; //ens la dona la BBDD
    private JButton jbTornar;
    private JButton jbSeguent;
    private int quinaPagina;
    private int numPagines;
    private JLabel jlPagina;
    private ArrayList<PanelPlats> paginaPlats;


    /**
     * Constructor que crea el panell de la carta
     * @param carta conjunt de plats a mostrar
     */
    public PanelCarta(Carta carta){
        this.carta = carta;
        paginaPlats = new ArrayList<PanelPlats>();
        paginaPlats = creaPaginaPlats(carta);
        this.setLayout(new BorderLayout());

        JPanel jpAux = new JPanel();
        jpAux.setLayout(new BoxLayout(jpAux, BoxLayout.X_AXIS));
        //CAMBIAR A FLECHA
        jbTornar = new JButton("Tornar");
        jbSeguent = new JButton("Seguent");
        this.add(paginaPlats.get(0), BorderLayout.CENTER); //Per defecte mostrem primer la primera pagina
        this.quinaPagina = 1;
        jlPagina = new JLabel("PÁGINA " + quinaPagina);
        jpAux.add(jbTornar);
        jpAux.add(jlPagina);
        jpAux.add(jbSeguent);
        activaDesactiva();
        this.add(jpAux, BorderLayout.PAGE_END);
    }

    private ArrayList<PanelPlats> creaPaginaPlats(Carta carta){
        paginaPlats = new ArrayList<PanelPlats>();
        numPagines = 1;
        PanelPlats pp;
        ArrayList<Plat> quinsPlats = new ArrayList<Plat>();
        Plat p;
        int j = 0; //ens guardem el numero de pagina
        for(int i = 0; i < carta.getNumPlats(); i++){
            p = carta.getPlat(i);
            if(quinsPlats.size() == 6 && i != (carta.getNumPlats()- 1)){
                j++;
                pp = new PanelPlats(quinsPlats, j);
                paginaPlats.add(pp);
                quinsPlats = new ArrayList<Plat>();
                numPagines++;
            }
            quinsPlats.add(p);
            if(i == (carta.getNumPlats()- 1)){
                j++;
                pp = new PanelPlats(quinsPlats, j);
                paginaPlats.add(pp);
                numPagines++;
            }
        }
        return paginaPlats;
    }

    public void registerController(ActionListener controlador){
        jbTornar.addActionListener(controlador);
        jbSeguent.addActionListener(controlador);
        jbTornar.setActionCommand("PAGINA ANTERIOR");
        jbSeguent.setActionCommand("PAGINA SEGUENT");
        for(PanelPlats p: paginaPlats){
            p.registerController(controlador);
        }
    }

    public int getNumPagines(){return numPagines;}

    public int getQuinaPagina(){return quinaPagina;}

    public void setQuinaPagina(int quinaPagina, String on){
        if (on.equals("anterior")){
            if(getQuinaPagina() != 1){
                this.quinaPagina = quinaPagina;
                this.add(paginaPlats.get(quinaPagina), BorderLayout.CENTER);
            }
        }else{
            if(getQuinaPagina() != numPagines ){
                this.quinaPagina = quinaPagina;
                this.add(paginaPlats.get(quinaPagina), BorderLayout.CENTER);
            }
        }

        jlPagina.setText("PÁGINA " + this.quinaPagina);
        activaDesactiva();

    }

    public void activaDesactiva(){
        if(numPagines > 1 && (numPagines - quinaPagina) > 0){
            jbSeguent.setEnabled(true);
        }
        if(quinaPagina == 1){
            jbTornar.setEnabled(false);
        }else{
            jbTornar.setEnabled(true);
        }
        if(numPagines - (quinaPagina) == 0){
            jbSeguent.setEnabled(false);
        }

    }

    public PanelPlats getPaginaPlats(int i){return paginaPlats.get(i);}






}
