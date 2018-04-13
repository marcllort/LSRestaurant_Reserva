package View;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogPlat extends JDialog {

    private JLabel jlInfoPlat;
    private JComboBox<String> jcbUnitatsDisponibles;
    private JButton jbAfageix;

    public DialogPlat(Plat plat, int unitatsDisponibles){
        this.setLayout(new BorderLayout());
        JPanel jpAux = new JPanel();
        jpAux.setLayout(new BoxLayout(jpAux, BoxLayout.X_AXIS));
        jpAux.setBorder(BorderFactory.createTitledBorder("Afageix el numero de plats"));
        jlInfoPlat = new JLabel(plat.getNomPlat() + "\n" + plat.getPreu() +"€");
        jcbUnitatsDisponibles = new JComboBox<>();
        ArrayList<int> s= new ArrayList<int>();
        for(int i = 0; i < unitatsDisponibles; i++){
            s.add(i);
        }
        for (int i = 0; i < unitatsDisponibles; i++){
            jcbUnitatsDisponibles.add(s.get(i).toString);
        }



        jpAux.add(jlInfoPlat);

        jbAfageix = new JButton("Afageix");
        this.add(jpAux, BorderLayout.CENTER);
        this.add(jbAfageix, BorderLayout.PAGE_END);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(200, 200);
        this.setResizable(false);
    }

    /**public int getNumUnitats(){
        String s = jtfQuants.getText();
        int quants = Integer.parseInt(s);
        return quants;
    }*/
}
