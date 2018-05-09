package View;

import Controller.ControllerMainWindow;
import Controller.ControllerViewComanda;
import Model.Comanda;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaEditorComanda extends JFrame {

    private Comanda comandaActual;
    private JScrollPane jspComanda;
    private JPanel jpComanda;
    private ArrayList<PanelEditorComanda> panels;
    private JButton jbEnvia;


    public VistaEditorComanda(Comanda comandaActual) {

        this.comandaActual = comandaActual;

        panels = new ArrayList<PanelEditorComanda>();

        this.setLayout(new BorderLayout());

        jpComanda = new JPanel();
        jpComanda.setLayout(new GridLayout(comandaActual.getPlats().size(), 1));

        PanelEditorComanda panelEditorComanda;


        for (int i = 0; i < comandaActual.getPlats().size(); i++) {
            panelEditorComanda = new PanelEditorComanda(comandaActual.getPlat(i), i);
            panels.add(panelEditorComanda);
            jpComanda.add(panelEditorComanda);
        }

        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);

        jbEnvia = new JButton("Envia");
        this.add(jbEnvia, BorderLayout.PAGE_END);

        this.setSize(400, 400);
        this.setTitle("Editor Comanda");

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    }

    public void registerController(ControllerViewComanda c) {

        for (PanelEditorComanda panelEditorComanda : panels) {
            panelEditorComanda.registerController(c);
        }

        jbEnvia.addActionListener(c);
        jbEnvia.setActionCommand("ENVIA COMANDA");

    }

    public ArrayList<PanelEditorComanda> getPanels() {
        return panels;
    }

    public void actualitzaVista(Plat platEsborrar){
        for(PanelEditorComanda p : panels){
            if(p.getPlat().getNomPlat().equals(platEsborrar.getNomPlat())){
                panels.remove(p);
                jpComanda.remove(p);
                break;
            }
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);


    }
    public void actualitzaComanda(Comanda comandaActual){
        this.comandaActual = comandaActual;
    }

}
