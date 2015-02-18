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
public class Inverso {

    public static BigInteger calculaInverso(BigInteger a, BigInteger n){
        
        BigInteger y, yaux, v;
        BigInteger r, c;
        
        y = new BigInteger("0");
        v = new BigInteger("1");
        r = n.mod(a);

        while(r.compareTo(BigInteger.ZERO)!=0){
            c = n.divide(a);
            yaux = y;
            y = v;
            v = yaux.subtract(v.multiply(c));
            n = a;
            a = r;
            r = n.mod(a);
            
        }
        
        if(a.compareTo(BigInteger.ONE)!=0){
            return BigInteger.valueOf(-1);
        }
        
        return v;
    }
}
