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
public class Potencia {
    
    public static BigInteger calculaPotencia(BigInteger a,BigInteger m,BigInteger n)
    {
            BigInteger b = new BigInteger("1"); 
            
            while(!m.equals(BigInteger.valueOf(1))){
                if((m.mod(BigInteger.valueOf(2))).equals(BigInteger.valueOf(1))){
                    b = b.multiply(a);
                    b = b.mod(n);
                }
                a = a.pow(2);
                a = a.mod(n);
                m = m.divide(BigInteger.valueOf(2)); 
                
            }
            b = a.multiply(b);
            b = b.mod(n);
            
            return b;
    }
    
}
