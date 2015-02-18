/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JFileChooser;
import model.*;
import view.*;

/**
 *
 * @author javiyesares
 */
public class Controlador implements ActionListener{
    
    VistaPrincipal vp;
    RSA rsa;
    
    public Controlador (VistaPrincipal vp){
        this.vp = vp;
        this.vp.cal1.addActionListener(this);
        this.vp.exa2.addActionListener(this);
        this.vp.generaClaves.addActionListener(this);
        this.vp.cifrar2.addActionListener(this);
        this.vp.descifrar2.addActionListener(this);
        this.vp.exa3.addActionListener(this);
        this.vp.exa32.addActionListener(this);
        this.vp.exa33.addActionListener(this);
        this.vp.exa34.addActionListener(this);
        this.vp.generaClaves3.addActionListener(this);
        this.vp.verificaFirma3.addActionListener(this);
        this.vp.generaFirma3.addActionListener(this);
   
        rsa = new RSA();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        /////////////////////////////////////////////////
        //  Test Miller-Rabin   /////////////////////////
        /////////////////////////////////////////////////
        
        // Test Miller-Rabin
        if(e.getSource()==vp.cal1){
            String salida=null;
            BigDecimal cuatro = new BigDecimal("4");
            BigDecimal uno = new BigDecimal("1");
            BigInteger valor = new BigInteger(vp.p1.getText().toString());
            int iter = Integer.parseInt(vp.spinner1.getValue().toString());
            
            if(iter>=1){
                if(MillerRabin.esProbablePrimo(valor, iter))
                    salida = valor.toString() + " es probable primo con probabilidad mayor que "
                            + ((cuatro.pow(iter).subtract(uno)).divide(cuatro.pow(iter)));  // (4^iter - 1)/ 4^iter
                else
                    salida = valor.toString() + " no es primo";
            }
            vp.res.setText(salida);
        }
        
        /////////////////////////////////////////////////
        //   Cifrado asimétrico /////////////////////////
        /////////////////////////////////////////////////
        
        // examinar en cifrado asimétrico
        if(e.getSource()==vp.exa2){
            VistaFileChooser vfc = new VistaFileChooser(vp, true);
            vfc.jFileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //vfc.jFileChooser2.setAcceptAllFileFilterUsed(false);
            int returnVal = vfc.jFileChooser2.showOpenDialog(vp);
            if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.inputFile.setText(vfc.jFileChooser2.getSelectedFile().getAbsolutePath());
           }
        }
        
        // generación de claves pública y privada en los archivos de texto
        if(e.getSource()==vp.generaClaves){
            if(!vp.inputFile.getText().isEmpty()){
                rsa.generaClaves(vp.inputFile.getText());
            }
        }
        
        // cifrado
        if(e.getSource()==vp.cifrar2){
            if(!vp.mensaje2.getText().isEmpty()){
                vp.textCif.setText(rsa.cifrarMensaje(vp.mensaje2.getText(),vp.inputFile.getText()));
            }
        }
        
        // descrifrado
        if(e.getSource()==vp.descifrar2){
            if(!vp.mensaje2.getText().isEmpty()){
                vp.textDes.setText(rsa.descifrarMensaje(vp.mensaje2.getText(), vp.inputFile.getText()));
            }
        }
        
        /////////////////////////////////////////////////
        //   Firma digital      /////////////////////////
        /////////////////////////////////////////////////
     
        // examinar carpeta para generar claves publicas y privadas
        if(e.getSource()==vp.exa3){
            VistaFileChooser vfc = new VistaFileChooser(vp, true);
            vfc.jFileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //vfc.jFileChooser2.setAcceptAllFileFilterUsed(false);
            int returnVal = vfc.jFileChooser2.showOpenDialog(vp);
            if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.inputFile3.setText(vfc.jFileChooser2.getSelectedFile().getAbsolutePath());
           }
        }
        
        // generación de claves pública y privada en los archivos de texto
        if(e.getSource()==vp.generaClaves3){
            if(!vp.inputFile3.getText().isEmpty()){
                rsa.generaClaves(vp.inputFile3.getText());
            }
        }
        
        // fichero donde se encuentra el mensaje
        if(e.getSource()==vp.exa32){
            VistaFileChooser vfc = new VistaFileChooser(vp, true);
            //vfc.jFileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = vfc.jFileChooser2.showOpenDialog(vp);
            if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.inputFile32.setText(vfc.jFileChooser2.getSelectedFile().getAbsolutePath());
           }
        }
        
        // fichero donde se encuentran la clave
        if(e.getSource()==vp.exa33){
            VistaFileChooser vfc = new VistaFileChooser(vp, true);
            //vfc.jFileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = vfc.jFileChooser2.showOpenDialog(vp);
            if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.inputFile33.setText(vfc.jFileChooser2.getSelectedFile().getAbsolutePath());
           }
        }
        
        // fichero donde se encuentra la firma
        if(e.getSource()==vp.exa34){
            VistaFileChooser vfc = new VistaFileChooser(vp, true);
            vfc.jFileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = vfc.jFileChooser2.showOpenDialog(vp);
            if(returnVal == JFileChooser.APPROVE_OPTION){
               vp.inputFile34.setText(vfc.jFileChooser2.getSelectedFile().getAbsolutePath());
           }
        }
        
        // Generación de la firma
        if(e.getSource()==vp.generaFirma3){
            Ficheros f;
            f = new Ficheros();
            if(!vp.inputFile32.getText().isEmpty() && 
                    !vp.inputFile33.getText().isEmpty() && 
                    !vp.inputFile34.getText().isEmpty()){
                //vp.textCif.setText(rsa.cifrarMensaje(vp.mensaje2.getText(),vp.inputFile.getText()));
                
               
                f.escribeFichero(vp.inputFile34.getText()+"/RSA.txt", 
                        rsa.cifrarMensajeSHA1(vp.inputFile32.getText(), vp.inputFile33.getText()));
                
                //System.out.println(SHA1.convSHA1(vp.inputFile32.getText())); Resumen del texto SHA-1
            }
        }
        
        // Verificación de la firma
        if(e.getSource()==vp.verificaFirma3){
      
            if(!vp.inputFile32.getText().isEmpty() && 
                    !vp.inputFile33.getText().isEmpty() && 
                    !vp.inputFile34.getText().isEmpty()){
                
                if(rsa.verificaFirma(vp.inputFile32.getText(), vp.inputFile33.getText(), vp.inputFile34.getText())){
                    vp.respuesta3.setText("La firma es válida");
                }else
                    vp.respuesta3.setText("La firma no es válida");
            }
        }
    }
    
}
