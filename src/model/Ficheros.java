/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author javiyesares
 */
public class Ficheros {
    public String leeFichero(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = "";
        
        try{
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            String linea;
            while((linea=br.readLine())!=null)
                texto = texto + linea;
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!= fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        
        return texto;
    }
    
    public boolean escribeFichero(String ruta, String texto){
        File archivo = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try{
            archivo = new File(ruta);
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);
            
            fw.write(texto);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!= fw){
                    fw.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
                
        return true;
    }
    
    public String leeFicheroRetorno(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = "";
        
        try{
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            String linea;
            while((linea=br.readLine())!=null)
                texto = texto + linea + "\n";
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null!= fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        
        return texto;
    }
}
