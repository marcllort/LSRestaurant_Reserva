package Controller;



import Model.Carta;
import Model.Comanda;
import Model.Plat;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {
    //VISTA
    private Vista view;
    private VistaEditorComanda viewComanda;
    //MODEL
    private Carta carta;
    private ArrayList<Comanda> comanda;
    private Comanda comandaActual;


    /**
     * Constructor amb parametres
     * @param view relacio vista amb controlador
     */


    public Controller(Vista view){
        this.view = view;
        //podem omplir la carta
        this.carta = new Carta();
        this.comanda = new ArrayList<Comanda>();
        Plat p = new Plat("Pasta", 8);
        Plat plat = new Plat("pasta", 10);
        carta.afageixPlat(p);
        carta.afageixPlat(plat);
        comandaActual = new Comanda("Carlos");
        comandaActual.addPlat(p);
        comandaActual.addPlat(plat);
        comanda.add(comandaActual);

    }


    /**
     * Gestiona els esdeveniments
     * @event Font de l'esdeveniment
     */

    public void actionPerformed(ActionEvent event){
        //Si es tracta d'una accio sobre el menu
        if(event.getSource() instanceof JMenuItem){
            if(event.getActionCommand().equals("ACCES CARTA")){
                view.changePanel("CARTA");
                //comandaActual = new ArrayList<Plat>();
            }else if(event.getActionCommand().equals("ACCES ESTAT COMANDA")){
                view.changePanel("ESTAT COMANDA");
            }else if(event.getActionCommand().equals("ACCES SORTIDA")){
                view.changePanel("SORTIR");
            }else if(event.getActionCommand().equals("ACCES EDITOR COMANDA")){
                handleVistaComanda();
            }
        }else{
            //Si es tracta d'alguna opció sobre els panells
            //Si es tracta del panell d'acces
            if(event.getActionCommand().equals("ENVIA CREDENCIALS")){
                handleAcces();
            //Si es tracta d'una accio del panell sortida
            }else if(event.getActionCommand().equals("SORTIR")){
                view.getPanelSortida().dialogSortida(this);

            }else if(event.getActionCommand().equals("PAGINA ANTERIOR")){
                view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() - 1, "anterior");
            }else if(event.getActionCommand().equals("PAGINA SEGUENT")){
                view.getPanelCarta().setQuinaPagina(view.getPanelCarta().getQuinaPagina() + 1, "seguent");
            }else if(event.getSource() instanceof PanelPlats){
                if(event.getActionCommand().equals("PLAT1-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }else if(event.getActionCommand().equals("PLAT2-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }else if(event.getActionCommand().equals("PLAT3-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }else if(event.getActionCommand().equals("PLAT4-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }else if(event.getActionCommand().equals("PLAT5-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }else if(event.getActionCommand().equals("PLAT6-" + view.getPanelCarta().getQuinaPagina())){
                    //afegim el plat a la comanda + mostrar Jdialog
                }
            }
        }
        //Si es tracta d'una accio sobre el JDialog de sortida
        if(event.getActionCommand().equals("SORTIR PROGRAMA")){
            //Vista torna a ser la del principi
            view.getPanelSortida().desactivaDialogSortida();
            handleSortida();

        }else if(event.getActionCommand().equals("QUEDAR-SE")){
            view.changePanel("BUIT");
            view.getPanelSortida().desactivaDialogSortida();
        }
        //Si es tracta d'una opcio sobre la vista d'editar comanda
        if(event.getSource() instanceof PanelEditorComanda){
            for(int i = 0; i < viewComanda.getPanels().size(); i++){
                if(event.getActionCommand().equals("ELIMINA-" + viewComanda.getPanels().get(i).getNumPlat())){
                    handleEliminaPlatComandaActual(viewComanda.getPanels().get(i).getPlat());
                    break;
                }
            }

        }else if(event.getActionCommand().equals("ENVIA")){
            //Enviem comanda actual al servidor
            comanda.add(comandaActual);
            //modifiquem comanda al panell
            view.modificaPanelEstatComanda(comanda);
            //comandaActual= new Comanda();
        }



    }

    private void handleAcces(){

        String usuari = view.getTypedUsuari();
        String contrasenya = view.getTypedContrasenya();
        //Comprovem dades
        boolean error = false;
        //error = comprovaCredencials(usuari, contrasenya);
        if(!error){
            JOptionPane.showMessageDialog(view, "Benvingut!");
            //comanda = ompleComanda(usuari);
            //carta = ompleCarta();
            view.activaPanells(comanda, carta, this);

            view.changePanel("BUIT");
        }else{
            JOptionPane.showMessageDialog(view, "Credencials incorrectes!");
            view.cleanFields();
        }
    }

    private void handleSortida() {
        view.cleanFields();
        this.view = new Vista();
        view.setVisible(true);
        view.registerController(this);
    }

    private void handleVistaComanda(){

        viewComanda = new VistaEditorComanda(comandaActual);
        viewComanda.setLocationRelativeTo(view);
        viewComanda.setVisible(true);
        viewComanda.registerController(this);
    }

    private void handleEliminaPlatComandaActual(Plat platEsborrar){
        for(int i = 0; i < viewComanda.getPanels().size(); i++){
            if(platEsborrar.getNomPlat().equals(viewComanda.getPanels().get(i).getPlat().getNomPlat())){
                Plat p;
                for(int j = 0; j < comandaActual.getPlats().size(); j++){
                    p = comandaActual.getPlat(j);
                    if(p.getNomPlat().equals(platEsborrar.getNomPlat())){
                        comandaActual.getPlats().remove(j);
                        this.viewComanda = new VistaEditorComanda(comandaActual);
                        viewComanda.setVisible(true);
                        viewComanda.registerController(this);
                        break;
                    }
                }
                break;
            }
        }
    }

}
