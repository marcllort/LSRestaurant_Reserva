package View;

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

    /**
     * Constructor de la finestra que permet editar la comanda
     * Formada per un JScrollPane
     *
     * @param comandaActual per tal de poder modificar o enviar la comanda
     */
    public VistaEditorComanda(Comanda comandaActual) {
        this.comandaActual = comandaActual;
        this.setLayout(new BorderLayout());
        panels = new ArrayList<PanelEditorComanda>();

        PanelEditorComanda panelEditorComanda;

        platsComanda = new ArrayList<PlatComanda>();

        for (Plat p : comandaActual.getPlats()) {
            if (platsComanda.size() == 0) {
                PlatComanda pc = new PlatComanda(p);
                pc.augmentaUnitats();
                platsComanda.add(pc);
            } else {
                for (int i = 0; i < platsComanda.size(); i++) {

                    if (p.getNomPlat().equals(platsComanda.get(i).getNomPlat())) {
                        platsComanda.get(i).augmentaUnitats();
                        break;
                    }
                    if (!p.getNomPlat().equals(platsComanda.get(i).getNomPlat()) && i == platsComanda.size() - 1) {
                        PlatComanda pc = new PlatComanda(p);
                        platsComanda.add(pc);

                    }
                }
            }
        }
        jpComanda = new JPanel();
        jpComanda.setLayout(new GridLayout(comandaActual.getPlats().size(), 1));

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
        this.setTitle("Editor de la teva Comanda");

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    /**
     * Registra el panell a la finestra, i l'envia a els diferents panells que formen la finestra
     *
     * @param c controlador
     */

    public void registerController(ControllerViewComanda c) {

        for (PanelEditorComanda panelEditorComanda : panels) {
            panelEditorComanda.registerController(c);
        }

        jbEnvia.addActionListener(c);
        jbEnvia.setActionCommand("ENVIA COMANDA");

    }

    /**
     * Funcio per obtenir els panells que formen la finestra
     *
     * @return ArrayList dels panells
     */
    public ArrayList<PanelEditorComanda> getPanels() {
        return panels;
    }

    /**
     * Funcio que actualitza la vista a l'hora d'eliminar un plat.
     * Eliminem el panell que pertany al plat
     *
     * @param platsEsborrar El plat que volem eliminar
     */

    public void actualitzaVista(Plat platsEsborrar) {
        for (PanelEditorComanda p : panels) {
            if (p.getPlat().getNomPlat().equals(platsEsborrar.getNomPlat())) {
                panels.remove(p);
                jpComanda.remove(p);
                break;
            }
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);

    }

    /**
     * Acyualitza la comanda, ja per si s'ha eliminat alguns plats o s'ha actualitzat el nombre d'unitats
     *
     * @param comandaActual Comanda actualitzada
     */
    public void actualitzaComanda(Comanda comandaActual) {
        this.comandaActual = comandaActual;
    }

    /**
     * Rep el nou numero d'unitats que vol l'usuari
     *
     * @param i index per saber de quin panell(plat) es tracta
     * @return el nou nombre d'unitats
     */

    public int getActualitzacio(int i) {
        return panels.get(i).getNovesUnitats();
    }

    /**
     * Actualitza els panells quan s'ha esborrat un plat de la comanda
     */

    public void actualitzaPanells() {

        PanelEditorComanda panelEditorComanda;

        for (int i = 0; i < platsComanda.size(); i++) {
            if (platsComanda.get(i).getUnitats() == 0) {
                platsComanda.remove(i);
            } else {
                panelEditorComanda = new PanelEditorComanda(platsComanda.get(i), i);
                panels.add(panelEditorComanda);
                jpComanda.add(panelEditorComanda);
            }
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);
    }


    public void actualitzaPanells2() {

        platsComanda = new ArrayList<PlatComanda>();

        for (Plat p : comandaActual.getPlats()) {
            if (platsComanda.size() == 0) {
                PlatComanda pc = new PlatComanda(p);
                pc.augmentaUnitats();
                platsComanda.add(pc);
            } else {
                for (int i = 0; i < platsComanda.size(); i++) {

                    if (p.getNomPlat().equals(platsComanda.get(i).getNomPlat())) {
                        platsComanda.get(i).augmentaUnitats();
                        break;
                    }
                    if (!p.getNomPlat().equals(platsComanda.get(i).getNomPlat()) && i == platsComanda.size() - 1) {
                        PlatComanda pc = new PlatComanda(p);
                        platsComanda.add(pc);

                    }
                }
            }
        }
        jspComanda = new JScrollPane(jpComanda);
        this.add(jspComanda, BorderLayout.CENTER);
    }


    public JButton getJbElimina(int i) {
        return panels.get(i).getJbElimina();
    }

    public JButton getJbActualitza(int i) {
        return panels.get(i).getJbActualitza();
    }
}
