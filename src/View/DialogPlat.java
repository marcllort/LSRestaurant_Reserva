package View;

import Model.Plat;

import javax.swing.*;
import java.awt.*;

public class DialogPlat extends JDialog {

    private JLabel jlInfoPlat;
    private JTextField jtfQuants;
    private JButton jbAfageix;

    public DialogPlat(Plat plat){
        this.setLayout(new BorderLayout());
        JPanel jpAux = new JPanel();
        jpAux.setLayout(new BoxLayout(jpAux, BoxLayout.X_AXIS));
        jpAux.setBorder(BorderFactory.createTitledBorder("Afageix el numero de plats"));
        jlInfoPlat = new JLabel(plat.getNomPlat() + "\n" + plat.getPreu() +"€");
        jtfQuants = new JTextField();
        jtfQuants.setPreferredSize(new Dimension(40, 25));
        jpAux.add(jlInfoPlat);
        jpAux.add(jtfQuants);
        jbAfageix = new JButton("Afageix");
        this.add(jpAux, BorderLayout.CENTER);
        this.add(jbAfageix, BorderLayout.PAGE_END);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(200, 200);
        this.setResizable(false);
    }
    public int getNumUnitats(){
        String s = jtfQuants.getText();
        int quants = Integer.parseInt(s);
        return quants;
    }
}
