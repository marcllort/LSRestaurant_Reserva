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

    private VistaEditorComanda viewComanda;
    private ServerConnect serverConnect;
    private Comanda comandaActual;
    private boolean comandaEnviada = false;
    private boolean finestraActiva;

    public ControllerViewComanda(ServerConnect serverConnect, Comanda comandaActual){

        this.serverConnect = serverConnect;
        this.comandaActual = comandaActual;
        this.viewComanda = new VistaEditorComanda(comandaActual);
        viewComanda.registerController(this);
        viewComanda.setVisible(true);
        finestraActiva = true;
    }

    public void actionPerformed(ActionEvent event){

        if (event.getSource() instanceof PanelEditorComanda){
            for (int i = 0; i < viewComanda.getPanels().size(); i++){
                if (event.getActionCommand().equals("ELIMINA-" + viewComanda.getPanels().get(i).getNumPlat())){
                    viewComanda.setVisible(false);
                    eliminaPlatComanda(viewComanda.getPanels().get(i).getPlat());
                    viewComanda.setVisible(true);
                    break;
                }
            }
        }else if (event.getActionCommand().equals("ENVIA")){
           // serverConnect.enviaComanda(comandaActual);
            JOptionPane.showMessageDialog(viewComanda, "Comanda enviada!");
            comandaEnviada = true;
            finestraActiva = false;
            viewComanda.setVisible(false);

        }
    }

    private void eliminaPlatComanda(Plat plat){

        comandaActual.getPlats().remove(plat);
        JOptionPane.showMessageDialog(viewComanda, "Plat esborrat");
        viewComanda.actualitzaVista(comandaActual);
        viewComanda.registerController(this);
    }

    public boolean getIfComandaEnviada(){return comandaEnviada;}

    public boolean getIfFinestraActiva(){return finestraActiva;}


}
