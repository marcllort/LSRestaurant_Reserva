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
            dialogSortir("Al tancar pagaràs automàticament totes les comandes.\nEstas segur que vols sortir? ", controller);        }


    }


    public void dialogSortir(String missatge, ControllerMainWindow controller){
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                missatge, "Sortir",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons,ObjButtons[1]);

        if(PromptResult==0)
        {
            controller.enviaPagat();
            System.exit(0);
        }
    }




}

/*public void registerController(ActionListener c) {
        jbSi.addActionListener(c);
        jbSi.setActionCommand("SORTIR PROGRAMA");
        jbNo.addActionListener(c);
        jbNo.setActionCommand("QUEDAR-SE");
    }
*public class DialogSortida extends JDialog {

    private JButton jbSi;
    private JButton jbNo;
    private JLabel jlMissatge;


    public DialogSortida(Comanda comanda) {
        this.setLayout(new BorderLayout());
        //Per saber si encara queden plats per pagar
        boolean faltan = false;

        for (Plat p : comanda.getPlats()) {
            if (!p.isServit()) {
                faltan = true;
                break;
            }
        }

        if (faltan) {
            jlMissatge = new JLabel("Encara queden plats per servir.\nSegur que vol marxar?");
        } else {
            jlMissatge = new JLabel("Segur que vol marxar?");
        }

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(3, 1));
        JPanel jpAux = new JPanel();
        jbNo = new JButton("No, encara no");
        jbSi = new JButton("Si, estic segur");
        jp1.add(jbSi);
        jp1.add(jpAux);
        jp1.add(jbNo);
        this.add(jlMissatge, BorderLayout.CENTER);
        this.add(jp1, BorderLayout.PAGE_END);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(200, 200);

    }

    public void registerController(ActionListener c) {
        jbSi.addActionListener(c);
        jbSi.setActionCommand("SORTIR PROGRAMA");
        jbNo.addActionListener(c);
        jbNo.setActionCommand("QUEDAR-SE");
    }

}
* */