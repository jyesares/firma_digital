/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.util.Random;
import model.Ficheros;

/**
 *
 * @author javiyesares
 */
public class RSA {
    
    BigInteger p;
    BigInteger q;
    BigInteger n;
    BigInteger ninv;
    BigInteger e;
    BigInteger d;
    
    public int generaClaves(String ruta){
        Ficheros fpu, fpr;
        Random rnd;
        
        fpu = new Ficheros();
        fpr = new Ficheros();
        
        rnd = new Random();
        //p = new BigInteger(32, rnd);
        //do{
        
            p = new BigInteger(512, rnd);
            p = (p.add(BigInteger.ONE)).subtract(p.mod(new BigInteger("2")));
        //}//while(!MillerRabin.esProbablePrimo(p, 30));
        while(!MillerRabin.esProbablePrimo(p,30)){
            p = p.add(new BigInteger("2"));
            System.out.println(p.toString());
        }
        
        //do{
            q = new BigInteger(512, rnd);
            q = (q.add(BigInteger.ONE)).subtract(q.mod(new BigInteger("2")));

        //}while(!MillerRabin.esProbablePrimo(q, 30));
        while(!MillerRabin.esProbablePrimo(q,30)){
            q = q.add(new BigInteger("2"));
            System.out.println(q.toString());
        }
            
            
       // p = new BigInteger("1081298104698286063813737967304568031406522676857739555339880517562925221530558524296599584286163751908713364829390795648074146197550782524900963175263757603219");
       // q = new BigInteger("204616454475328391399619135615615385636808455963116802820729927402260635621645177248364272093977747839601125961863785073671961509749189348777945177811");
        
        n = p.multiply(q);
        ninv = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("65537");
        System.out.println(e.toString());
        
        while((MCD.calculaMCD(e, ninv)).compareTo(BigInteger.ONE)!=0){
            e = e.add(new BigInteger("2"));
            System.out.println(e.toString());
        }
        
        fpu.escribeFichero(ruta+"/public.txt", n.toString()+"\n"+e.toString());

        d = Inverso.calculaInverso(e, ninv);
        d = d.mod(ninv); ///////////////////////////// Ya no se si es el inverso o n
        
        fpr.escribeFichero(ruta+"/private.txt", n.toString()+"\n"+d.toString()); 
        
        return 1;
    }
    
    
    // Para el cifrado asimétrico
    public String cifrarMensaje (String m, String ruta){
        Ficheros f;
        String salida = null;
        String claves, vecClaves[];
        BigInteger men, c;
        
        
        f = new Ficheros();
        claves = f.leeFicheroRetorno(ruta+"/public.txt");
        vecClaves = claves.split("\n");
        n = new BigInteger(vecClaves[0]);
        e = new BigInteger(vecClaves[1]);
        men = new BigInteger(m);
        
        //System.out.println(MCD.sonPrimosRelativos(men, n));
 //       if(MCD.sonPrimosRelativos(men, n)){
            //System.out.println("Son primos relativos");
            
            //Si el mensaje es menor que n
        //    if(men.compareTo(n)<0){
               c = men.modPow(e, n);
               salida = c.toString();
               System.out.println(c.toString());
        //   }else{ // Si el mensaje es mayor que n
               
        //   }
 //       }
        
        
        return salida;
    }
    
     public String descifrarMensaje (String c, String ruta){
        Ficheros f;
        String salida = null;
        String claves, vecClaves[];
        BigInteger men, m;
        
        
        f = new Ficheros();
        claves = f.leeFicheroRetorno(ruta+"/private.txt");
        vecClaves = claves.split("\n");
        n = new BigInteger(vecClaves[0]);
        d = new BigInteger(vecClaves[1]);
        men = new BigInteger(c);
        
        //System.out.println(MCD.sonPrimosRelativos(men, n));
//        if(MCD.sonPrimosRelativos(men, n)){
            //System.out.println("Son primos relativos");
            
            //Si el mensaje es menor que n
        //    if(men.compareTo(n)<0){
               m = men.modPow(d, n);
               salida = m.toString();
        //   }else{ // Si el mensaje es mayor que n
               
        //   }
/*        }else{
            if(men.compareTo(BigInteger.ZERO)==0){
                salida = "0";
            }
            if(MCD.calculaMCD(men, n).compareTo(p)==0){
                m = men.modPow(e.multiply(d), q);
                salida = m.toString();
            }
            if(MCD.calculaMCD(men, n).compareTo(q)==0){
                m = men.modPow(e.multiply(d), p);
                salida = m.toString();
            }
        }
*/      
        
        return salida;
    }
     
    public String cifrarMensajeSHA1 (String rutaMensaje, String rutaClavePrivada){
        Ficheros f;
        String salida = null;
        String claves, vecClaves[];
        BigInteger men, c;
        
        
        f = new Ficheros();
        claves = f.leeFicheroRetorno(rutaClavePrivada);
        vecClaves = claves.split("\n");
        n = new BigInteger(vecClaves[0]);
        d = new BigInteger(vecClaves[1]);
        //men = new BigInteger(m);
        
        //System.out.println(MCD.sonPrimosRelativos(men, n));
 //       if(MCD.sonPrimosRelativos(men, n)){
            //System.out.println("Son primos relativos");
            
            //Si el mensaje es menor que n
        //    if(men.compareTo(n)<0){
               men = new BigInteger(SHA1.convSHA1(rutaMensaje),16);
               c = men.modPow(d, n);
               salida = c.toString(16); // toHex
        //   }else{ // Si el mensaje es mayor que n
               
        //   }
 //       }
        
        
        return salida;
    }
     
    public boolean verificaFirma(String rutaMensaje, String rutaClavePublica, String rutaFirma){
        Ficheros f, ff;
        String claves, vecClaves[];
        String resumen, h, aux;
        String firma;
        BigInteger firmaBI, aux2;
        
        f = new Ficheros();
        ff = new Ficheros();
        claves = f.leeFicheroRetorno(rutaClavePublica);
        vecClaves = claves.split("\n");
        n = new BigInteger(vecClaves[0]);
        e = new BigInteger(vecClaves[1]);
        
        resumen = SHA1.convSHA1(rutaMensaje);
        System.out.println("Resumen 1: "+resumen);
        firma = f.leeFichero(rutaFirma);
        //System.out.println(firma);
        firmaBI = new BigInteger(firma,16); // firma biginteger
        //System.out.println(firmaBI.toString());
        h = firmaBI.modPow(e, n).toString();
        //System.out.println("Resumen 2: "+h);
        //aux = new String (resumen, 0x10);
        aux2 = new BigInteger(resumen,16);
        resumen=aux2.toString();
        System.out.println("Resumen 1: "+resumen);
        System.out.println("Resumen 2: "+h);
        
        if(h.compareTo(resumen)==0)
            return true;
        else
            return false;
    }
}
