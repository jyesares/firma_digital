/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.util.*;

/**
 *
 * @author javiyesares
 */
public class SHA1 {
    
    public static String convSHA1(String ruta){
        
        String m = "";
        
        try{
            MessageDigest messageDigest2 = MessageDigest.getInstance("SHA"); // Inicializa SHA-1
            
            try{
            InputStream archivo = new FileInputStream(ruta);
            byte[] buffer = new byte[1];
            int fin_archivo = -1;
            int caracter;
       
            caracter = archivo.read(buffer);
       
            while( caracter != fin_archivo ) {
         
               //messageDigest.update(buffer); // Pasa texto claro a la función resumen
               messageDigest2.update(buffer);
               caracter = archivo.read(buffer);
            }   
       
            archivo.close();
            //byte[] resumen = messageDigest.digest(); // Genera el resumen MD5
            byte[] resumen2 = messageDigest2.digest(); // Genera el resumen SHA-1
       
            //Pasar los resumenes a hexadecimal
       
            /*
            String s = "";
            for (int i = 0; i < resumen.length; i++)
            {
               s += Integer.toHexString((resumen[i] >> 4) & 0xf);
               s += Integer.toHexString(resumen[i] & 0xf);
            }
            System.out.println("Resumen MD5: " + s);
            */
         
            for (int i = 0; i < resumen2.length; i++)
            {
               m += Integer.toHexString((resumen2[i] >> 4) & 0xf);
               m += Integer.toHexString(resumen2[i] & 0xf);
            }
            //System.out.println("Resumen SHA-1: " + m);
         
         }
            //lectura de los datos del fichero
            catch(java.io.FileNotFoundException fnfe) {}
            catch(java.io.IOException ioe) {}
      
        }catch(java.security.NoSuchAlgorithmException nsae){}
        
        return m;
    }
    
}
