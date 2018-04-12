package View;

import Controller.Controller;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogSortida extends JDialog {


    private JButton jbSi;
    private  JButton jbNo;
    private JLabel jlMissatge;

    public DialogSortida(ArrayList<Comanda> comanda){
        this.setLayout(new BorderLayout());
        //Per saber si encara queden plats per pagar
        boolean faltan = false;
        for(Comanda c : comanda){
            for(Plat p : c.getPlats()){
                if(!p.isServit()){
                    faltan = true;
                    break;
                }
            }
        }
        if(faltan){
            jlMissatge = new JLabel("Encara queden plats per servir. \n Segur que vol martxar?");
        }else{
            jlMissatge = new JLabel("Segur que vol martxar?");
        }
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(3,1));
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

    public void registerController(ActionListener c){
        jbSi.addActionListener(c);
        jbSi.setActionCommand("SORTIR PROGRAMA");
        jbNo.addActionListener(c);
        jbNo.setActionCommand("QUEDAR-SE");
    }

}
