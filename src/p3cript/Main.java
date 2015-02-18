/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p3cript;

import view.*;
import controller.*;
import javax.swing.UIManager;
import model.*;

/**
 *
 * @author javiyesares
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        
        VistaPrincipal vp = new VistaPrincipal();
        Controlador c = new Controlador(vp);
        vp.setVisible(true);
        
    }
}
