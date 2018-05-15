package View;

import Controller.ControllerMainWindow;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogSortida {


    public DialogSortida(Comanda comanda, ControllerMainWindow controller) {
        //Per saber si encara queden plats per pagar
        boolean faltan = false;

        for (Plat p : comanda.getPlats()) {
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

