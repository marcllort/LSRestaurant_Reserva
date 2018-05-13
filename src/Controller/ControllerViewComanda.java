package Controller;

import Model.Comanda;
import Model.Plat;
import NetworkManager.ServerConnect;
import View.PanelEditorComanda;
import View.VistaEditorComanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerViewComanda implements ActionListener {
    //vista
    private VistaEditorComanda viewComanda;
    //model
    private ServerConnect serverConnect;
    private Comanda comandaActual;

    //controller
    private ControllerMainWindow controllerMainWindow;


    public ControllerViewComanda(ServerConnect serverConnect, Comanda comandaActual, VistaEditorComanda viewComanda, ControllerMainWindow controllerMainWindow){

        this.serverConnect = serverConnect;
        this.comandaActual = comandaActual;
        this.viewComanda = viewComanda;
        this.controllerMainWindow = controllerMainWindow;

    }

    public void actionPerformed(ActionEvent event){


        if (event.getActionCommand().equals("ENVIA COMANDA")){
            //serverConnect.enviaComanda(comandaActual);
            //comandaActual.setUsuari("Alex");
            serverConnect.enviaComanda(comandaActual);
            comandaActual = new Comanda();
            controllerMainWindow.setComandaActual(comandaActual);
            viewComanda.setVisible(false);

        }else {
            for (int i = 0; i < viewComanda.getPanels().size(); i++){
                if (event.getActionCommand().equals("ELIMINA-" + viewComanda.getPanels().get(i).getNumPlat())){
                    eliminaPlatComanda(viewComanda.getPanels().get(i).getPlat());
                    break;
                }
                if(event.getActionCommand().equals("ACTUALITZA-" + viewComanda.getPanels().get(i).getNumPlat())){
                    int num = viewComanda.getActualitzacio(i);
                    if(num > viewComanda.getPanels().get(i).getUnitats()){
                        for(int j = 0; j < num - viewComanda.getPanels().get(i).getUnitats(); j++){
                            comandaActual.addPlat(viewComanda.getPanels().get(i).getPlat());
                        }
                        viewComanda.actualitzaComanda(comandaActual);
                        viewComanda.actualitzaPanells();
                        viewComanda.getPanels().get(i).setNumUnitats(num);
                        break;
                    }else{
                        for(int j = 0; j < viewComanda.getPanels().get(i).getUnitats() - num; j++){
                            comandaActual.getPlats().remove(viewComanda.getPanels().get(i).getPlat());
                        }
                        viewComanda.actualitzaComanda(comandaActual);
                        viewComanda.actualitzaPanells();
                        viewComanda.getPanels().get(i).setNumUnitats(num);
                        break;
                    }


                }
            }
            System.out.println("COMANDA MODIFICADA");
            for(int x = 0; x<comandaActual.getPlats().size(); x++){
                System.out.println(comandaActual.getPlat(x).getNomPlat());
            }
        }
        viewComanda.registerController(this);
    }

    private void eliminaPlatComanda(Plat plat){

        System.out.println(comandaActual.getPlats().size() + "mida comanda");
        for(Plat p: comandaActual.getPlats()){
            if(p.getNomPlat().equals(plat.getNomPlat())){
                comandaActual.getPlats().remove(p);
            }
        }
        System.out.println(comandaActual.getPlats().size() + "mida comanda nueva");
        JOptionPane.showMessageDialog(viewComanda, "Plat esborrat");
        viewComanda.actualitzaComanda(comandaActual);
        viewComanda.actualitzaVista(plat);
        viewComanda.setVisible(true);


        //viewComanda.actualitzaVista(comandaActual);

    }










}
