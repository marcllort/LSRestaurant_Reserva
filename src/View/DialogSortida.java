package View;

import Controller.ControllerMainWindow;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogSortida {
    private Comanda comanda;

    /**
     * Consrtuctor amb parametres
     *
     * @param comanda    la comanda de l'usuari
     * @param controller controlador finestra principal
     */
    public DialogSortida(Comanda comanda, ControllerMainWindow controller) {
        //Per saber si encara queden plats per pagar
        this.comanda = comanda;
        boolean faltan = false;

        for (Plat p : this.comanda.getPlats()) {
            if (!p.isServit()) {
                faltan = true;
                break;
            }
        }
        if (faltan) {
            dialogSortir("Al tancar pagaràs automàticament totes les comandes, incloses les que encara no s'han servit.\nEstas segur que vols sortir? ", controller);
        } else {
            dialogSortir("Al tancar pagaràs automàticament totes les comandes.\nEstas segur que vols sortir? ", controller);
        }

    }


    /**
     * Mostra el missatge abans de la sortida
     *
     * @param missatge   quin missatge volem mostrar
     * @param controller el controlador de la vista principal
     */
    public void dialogSortir(String missatge, ControllerMainWindow controller) {
        String ObjButtons[] = {"Si", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                missatge, "Sortir",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);

        if (PromptResult == 0) {
            controller.enviaPagat();
            System.exit(0);
        }
    }

}

