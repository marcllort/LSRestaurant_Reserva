package View;

import Controller.ControllerMainWindow;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogPlat  {
    private String info;
    private boolean fet = false;

    public DialogPlat(Plat plat){


        info = JOptionPane.showInputDialog(null, "Quants plats de " + plat.getNomPlat() + " voldras?", JOptionPane.INFORMATION_MESSAGE);



    }

    public boolean comprova(){
        try{
            int i = Integer.parseInt(info);
            if (i < 0){
                return  false;
            }
        }catch (NumberFormatException nfe){
            return false;
        }


        return true;
    }

    public int returninfo(){
        return Integer.parseInt(info);
    }

   /* public int getTypedUnitats(){
        if(isNum(jtfUnitats.getText())){
            return Integer.parseInt(jtfUnitats.getText());
        }

        return -1;

    }

    public void registerController(ControllerMainWindow c){

        jbAfageix.addActionListener(c);
        jbAfageix.setActionCommand("AFEGEIX");
    }

    public void cleanFields(){

        jtfUnitats.setText("");
    }

    public String getFieldText(){ return jtfUnitats.getText(); }

*/
}
