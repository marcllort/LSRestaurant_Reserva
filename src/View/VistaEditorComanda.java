package View;

import Controller.ControllerMainWindow;
import Controller.ControllerViewComanda;
import Model.Comanda;
import Model.Plat;
import Model.PlatComanda;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaEditorComanda extends JFrame {

    private Comanda comandaActual;
    private JScrollPane jspComanda;
    private JPanel jpComanda;
    private ArrayList<PanelEditorComanda> panels;
    private JButton jbEnvia;
    private ArrayList<PlatComanda> platsComanda;


    public VistaEditorComanda(Comanda comandaActual) {

        this.comandaActual = comandaActual;


        panels = new ArrayList<PanelEditorComanda>();

        this.setLayout(new BorderLayout());

        jpComanda = new JPanel();
        jpComanda.setLayout(new GridLayout(comandaActual.getPlats().size(), 1));

        PanelEditorComanda panelEditorComanda;

        platsComanda = new ArrayList<PlatComanda>();

        for(Plat p : comandaActual.getPlats()){
            if(platsComanda.size() == 0){
                PlatComanda pc = new PlatComanda(p);
                pc.augmentaUnitats();
                platsComanda.add(pc);
            }else {
                for (int i = 0; i < platsComanda.size(); i++) {

                    if (p.getNomPlat().equals(platsComanda.get(i).getNomPlat())) {
                        platsComanda.get(i).augmentaUnitats();
                        break;
                    }
                    if (!p.getNomPlat().equals(platsComanda.get(i).getNomPlat()) && i == platsComanda.size() - 1) {
                        PlatComanda pc = new PlatComanda(p);
                        //pc.augmentaUnitats();
                        platsComanda.add(pc);

                    }
                }
            }
        }




        for (int i = 0; i < platsComanda.size(); i++) {
            panelEditorComanda = new PanelEditorComanda(platsComanda.get(i), i);
            panels.add(panelEditorComanda);
            jpComanda.add(panelEditorComanda);
        }


        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);

        jbEnvia = new JButton("Envia");
        this.add(jbEnvia, BorderLayout.PAGE_END);

        this.setSize(600, 330);
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

    public void actualitzaVista(Plat platsEsborrar){
        for(PanelEditorComanda p : panels){
            if(p.getPlat().getNomPlat().equals(platsEsborrar.getNomPlat())){
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

    public int getActualitzacio(int i){return panels.get(i).getNovesUnitats(); }

    public void actualitzaPanells(){

        PanelEditorComanda panelEditorComanda;

        for (int i = 0; i < platsComanda.size(); i++) {
            if(platsComanda.get(i).getUnitats() == 0){
                platsComanda.remove(i);
            }else {
                panelEditorComanda = new PanelEditorComanda(platsComanda.get(i), i);
                panels.add(panelEditorComanda);
                jpComanda.add(panelEditorComanda);
            }
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);
    }


}
