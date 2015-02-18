/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;

/**
 *
 * @author javiyesares
 */
public class MCD {
    
    public static BigInteger calculaMCD2(BigInteger a, BigInteger b){
        BigInteger temporal;
        
        while(a.compareTo(BigInteger.ZERO)>0){
            if(a.compareTo(b)<0){
                temporal = a;
                a = b;
                b = temporal;
            }
            
            a = a.subtract(b);
            
            
            
            
           
        }
        
        return b;
    }
    
     public static BigInteger calculaMCD(BigInteger a, BigInteger b){
        BigInteger temporal;
        
        while(b.compareTo(BigInteger.ZERO)>0){
            temporal = a.mod(b);
            a = b;
            b = temporal;
        }
            
        return a;
    }
    
    public static boolean sonPrimosRelativos(BigInteger a, BigInteger b){
        if(calculaMCD(a, b)==BigInteger.ONE){
            return true;
        }
           
        return false;
    }
}
