package Controller;



import Model.Carta;
import Model.Comanda;
import Model.Plat;
import Model.Usuari;
import NetworkManager.ServerConnect;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller implements ActionListener {
    //VISTA
    private Vista view;
    private VistaEditorComanda viewComanda;
    //MODEL
    private Carta carta;
    private Comanda comanda;
    private Comanda comandaActual;


    /**
     * Constructor amb parametres
     * @param view relacio vista amb controlador
     */


    public Controller(Vista view){
        this.view = view;

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
                    handleAfageixPlat(0, view.getPanelCarta().getQuinaPagina() - 1);
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
            for(Plat p: comandaActual.getPlats()){
                comanda.addPlat(p);
            }
            //modifiquem comanda al panell
            view.modificaPanelEstatComanda(comanda);
            //comandaActual= new Comanda();
        }



    }

    private void handleAcces(){

        String usuari = view.getTypedUsuari();
        String contrasenya = view.getTypedContrasenya();
        //Comprovem dades

        //error = comprovaCredencials(usuari, contrasenya);
        ServerConnect serverConnect = new ServerConnect();
        serverConnect.enviaUser(new Usuari(usuari, contrasenya));
        String userConfirmation = serverConnect.repUserConfirmation();
        if (userConfirmation.equals("true")){

            //1.mostrem dialog de benvingut
            JOptionPane.showMessageDialog(view, "Benvingut!");
            //2.omplim dades
            carta.setPlats(serverConnect.repCarta());
            comanda = serverConnect.repComanda();
            //3.preparem els panells
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

    private void handleAfageixPlat(int numPlat, int numPagina){
        Plat p = view.getPanelCarta().getPaginaPlats(numPagina).getPlat(numPlat);
        //demanem plats disponibles al controlador d'aquest plat

    }



    //1.cuando se envie comanda actualizar hora
    //2.Dejarlo todo como una sola comanda
    //3.cuando envio comanda borrar comanda actual
    //4.mirar platos que fallan
}
