package Controller;



import Model.Carta;
import Model.Comanda;
import Model.Plat;
import View.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {
    //VISTA
    private Vista view;
    //MODEL
    private Carta carta;
    private ArrayList<Comanda> comanda;

    /**
     * Constructor amb parametres
     * @param view relacio vista amb controlador
     */
    public Controller(Vista view){
        this.view = view;
        //podem omplir la carta
        this.carta = new Carta();
        this.comanda = new ArrayList<Comanda>();
    }

    /**
     * Gestiona els esdeveniments
     * @event Font de l'esdeveniment
     */

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("ENVIA CREDENCIALS")) {
            autentificaReserva(view.getTypedUsuari(), view.getTypedContrasenya());//Enviamos al servidor
            if (credencialsCorrectes()) {
                JOptionPane.showMessageDialog(view, "Benvingut!");
                handleAcces(view.getTypedUsuari());//crea la resta de panells
                //Si es tracta d'una accio sobre el menu, haurem de cambiar el panell
                if (event.getSource() instanceof JMenuItem) {
                    view.changePanel(event.getActionCommand());
                }
                // Si es tracta d'una opcio dels panells
                if (event.getActionCommand().equals("PAGINA ANTERIOR")) {
                    view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() - 1);
                }else if(event.getActionCommand().equals("PAGINA SEGUENT")){
                    view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() + 1);
                }else if(event.getActionCommand().equals("ACCES EDITOR COMANDA")){
                    //OBRIM LA VENTANA COMANDA
                }else if(event.getActionCommand().equals("SORTIR")){
                    //Mirem si encara queda alguna comanda per servir
                    for(Comanda c: comanda){
                       for(Plat p: c.getPlats()){
                           if(!p.isServit()){
                               view.getPanelSortida().mostraDialog(this);
                               break;
                           }
                       }
                    }

                }else if(event.getActionCommand().equals("SI")){
                    JOptionPane.showMessageDialog(view, "Fins aviat!");
                    view.cleanFields();//Por si acaso
                    view.changePanel("ACCES");//Tornem pagina principal

                }else if(event.getActionCommand().equals("NO")){

                }


            } else {

                JOptionPane.showMessageDialog(view, "Credencials incorrectes!");
                view.cleanFields();
            }
        }
    }

    private void handleAcces(String reserva){
        // Busquem la comanda d'aquesta reserva i l'omplim
        //comanda = ompleComanda(reserva);
        // Creem els altres panells i els inserim a la Vista
        view.activaPanells(comanda, carta, this);
    }
}
