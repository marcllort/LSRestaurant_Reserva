package Controller;

import Model.Comanda;
import Model.Plat;
import NetworkManager.ServerConnect;
import View.PanelEditorComanda;
import View.VistaEditorComanda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControllerViewComanda implements ActionListener {

    //VISTA
    private VistaEditorComanda viewComanda;
    //MODEL
    private ServerConnect serverConnect;
    private Comanda comandaActual;
    //CONTROLLER
    private ControllerMainWindow controllerMainWindow;

    /**
     * Constructor amb parametres del Controlador la finestra que permet editar la comanda abans de ser enviada
     * @param serverConnect connexio amb servidor
     * @param comandaActual la comanda actual del client
     * @param viewComanda La finestra grafica del editor de la comanda
     * @param controllerMainWindow controlador de la finestra principal
     */
    public ControllerViewComanda(ServerConnect serverConnect, Comanda comandaActual, VistaEditorComanda viewComanda, ControllerMainWindow controllerMainWindow) {

        this.serverConnect = serverConnect;
        this.comandaActual = comandaActual;
        this.viewComanda = viewComanda;
        this.controllerMainWindow = controllerMainWindow;

    }



    /**
     * Gestiona els esdeveniments
     *
     * @param event font del esdeveniment
     */
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand().equals("ENVIA COMANDA")) {
            serverConnect.enviaComanda(comandaActual);
            comandaActual = new Comanda();
            controllerMainWindow.setComandaActual();
            viewComanda.setVisible(false);

        } else {
            for (int i = 0; i < viewComanda.getPanels().size(); i++) {
                if (event.getActionCommand().equals("ELIMINA-" + viewComanda.getPanels().get(i).getNumPlat())) {
                    eliminaPlatComanda(viewComanda.getPanels().get(i).getPlat());
                    break;
                }
                if (event.getActionCommand().equals("ACTUALITZA-" + viewComanda.getPanels().get(i).getNumPlat())) {
                    int num = viewComanda.getActualitzacio(i);
                    handleActualitzaComanda(i, num);
                    break;
                }

            }

            viewComanda.dispatchEvent(new WindowEvent(viewComanda, WindowEvent.WINDOW_CLOSING));
            controllerMainWindow.handleVistaComanda(viewComanda);

        }

    }



    /**
     * Elimina els plats desitjats de la comanda actual
     * @param plat els plats a esborrar
     */
    private void eliminaPlatComanda(Plat plat) {

        ArrayList<Plat> aBorrar = new ArrayList<>();
        for (Plat p : comandaActual.getPlats()) {
            if (p.getNomPlat().equals(plat.getNomPlat())) {
                aBorrar.add(p);
            }
        }

        comandaActual.getPlats().removeAll(aBorrar);

        JOptionPane.showMessageDialog(viewComanda, "Plat esborrat");
        viewComanda.actualitzaComanda(comandaActual);
        viewComanda.actualitzaVista(plat);
        viewComanda.setVisible(true);

    }

    /**
     * S'encarrega de actualitzar el numero de plats que es volen d'un plat
     * @param i ens diu quin plat es vol modificar
     * @param num nova quantitat de plats que es vol
     */
    private void handleActualitzaComanda(int i, int num) {

        if (num > viewComanda.getPanels().get(i).getUnitats()) {
            for (int j = 0; j < num - viewComanda.getPanels().get(i).getUnitats(); j++) {
                comandaActual.addPlat(viewComanda.getPanels().get(i).getPlat());
            }
            viewComanda.actualitzaComanda(comandaActual);
            viewComanda.getPanels().get(i).setNumUnitats(num);

        } else {
            for (int j = 0; j < viewComanda.getPanels().get(i).getUnitats() - num; j++) {
                comandaActual.getPlats().remove(viewComanda.getPanels().get(i).getPlat());
            }
            viewComanda.actualitzaComanda(comandaActual);
            viewComanda.getPanels().get(i).setNumUnitats(num);

        }
        viewComanda.actualitzaPanells();
        JOptionPane.showMessageDialog(viewComanda, "Plat actualitzat!");

    }

}
