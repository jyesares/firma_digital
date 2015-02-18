/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author javiyesares
 */
public class MillerRabin {
    
    private static final BigInteger CERO = BigInteger.ZERO;
    private static final BigInteger UNO = BigInteger.ONE;
    private static final BigInteger DOS = BigInteger.valueOf(2);
    //private BigInteger p;
    //private BigInteger s;
    //private BigInteger a;
    //private BigInteger u;
    
    public MillerRabin (){}
    
    public static boolean esProbablePrimo(BigInteger p, int iter){
        if(p.compareTo(CERO)<=0) return true;
        if(p.equals(DOS)) return true;
        if(!p.testBit(0) || p.equals(UNO)) return false;
        return testMillerRabin(p, iter);
    }
    
    public static boolean testMillerRabin(BigInteger p, int nite)
    {
        
        BigInteger np, s;
        int u;
        Random rnd;
        
        rnd = new Random();
        np = p.subtract(UNO);
        s = np;
        u = s.getLowestSetBit();
        s = s.shiftRight(u);
        
        for(int i=0; i<nite; i++){
            BigInteger a;
            do{
                a = new BigInteger(p.bitLength(), rnd);
            }while(a.compareTo(UNO)<=0 || a.compareTo(p)>=0);
            
            int j=0;
            BigInteger z = a.modPow(s, p);
            while(!((j==0 && z.equals(UNO)) || z.equals(np))){
                if(j>0 && z.equals(UNO) || ++j==u)
                    return false;
                z = z.modPow(DOS, p);
            }
        }
        
        return true;
        /*
        String salida = null;
        Random rnd = new Random();
        
        //if (p.intValue()==0 || p.intValue()==1 || p.intValue()%2==0){
        if(p.equals(BigInteger.ZERO) || p.equals(BigInteger.ONE) || (p.mod(BigInteger.valueOf(2))).equals(BigInteger.ZERO) ){
            salida = p.toString() + " no es primo";
            return salida;
        }
        if(p.equals(BigInteger.valueOf(2)) ){
            salida = p.toString() + " es primo";
        }
        
        for(int i=0; i<nite; i++){
            BigInteger s,x;
            int u=1;
            BigInteger a;
            do{
               a = new BigInteger(p.bitLength(), rnd);
            }while( a.compareTo(BigInteger.ONE)<=0 || a.compareTo(p.subtract(BigInteger.ONE))>=0);
            
            s = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
            while(s.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
                s = s.divide(BigInteger.valueOf(2));
                u++;
            }
            
            x = a.modPow(s, p);
            for(int j=0; j<u; j++){
                BigInteger xn = x.pow(x.intValue()).mod(p);
                if(xn.equals(BigInteger.ONE) && !x.equals(BigInteger.ONE) && !x.equals(p.subtract(BigInteger.ONE))){
                    salida = p.toString() + " no es primo";
                    return salida;
                }
                x = xn;
            }
            if(!x.equals(BigInteger.ONE)){
                salida = p.toString() + " no es primo";
            }else{
                salida = p.toString() + " es probable primo";
            }
            return salida;
            
        }
        
        salida = p.toString() + " es probable primo";
        return salida;
        
        */
        
        //if(p%2==1 && p>=5){
        //BigInteger aux, aux_cal;
        
        /*if(p.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1)) && p.compareTo(BigInteger.valueOf(5))>=0){
            this.p = p;
            //testMillerRabin();
            salida = p.toString()+" es probable primo.";
        }else{
            salida = p.toString()+" no es primo.";
        }
        */
        
        //aux = p.subtract(BigInteger.valueOf(1));
        /*
        int aux, aux2 = 0;
        int u = 1;
        int s, a;
        boolean salir1,salir2, encontrado;
        
        salir1 = false;
        salir2 = false;
        encontrado = false;
        //aux = p.intValue()-1;
        
        s = (p.intValue()-1)/2;
        while(s%2==0){
            s = s/2;
            u++;
        }
        
        Random rnd = new Random();
        
        a = rnd.nextInt(p.intValue()-2);
        if(a<2){
            a+=2;
        }
        
        a = (int)Math.pow((double)a,(double)s);
        
        if(a==1 || a==-1){
            salida = p.toString() + " es probable primo";
            return salida;
        }else{
            for(int i=1; i<=u-1; i++){
                a=(int)Math.pow((double)a, (double)2);
                if(a==-1){
                    salida = p.toString() + " p es probable primo";
                    return salida;
                }
                if(a==1){
                    salida = p.toString() + " p no es primo";
                    return salida;
                }
            }
            salida = p.toString() + " p no es primo";
            return salida;
        }
        */
        /*
        if(encontrado){
            salida = "encontrado" + p + " = " + "2^" + u + " * " + s;
        }else{
            salida = "no encontrado";
        }
        
        salida = "encontrado" + p + " = " + "2^" + u + " * " + s;
        */
        
        
    }  
    
}
