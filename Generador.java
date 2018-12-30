package redes;

import java.math.BigInteger;
import java.util.Random;


public class Generador {

 //objeto random que genera el numero
 private Random motor;

 //constante que contiene la certidumbre de que
 //sea número primo
 private final int PCT_CERTEZA_PRIMO = 1;

 //contiene si es primo o no
 private boolean es_primo;

 //p, q, n, e, d (componentes para crear claves)
 private BigInteger p;
 private BigInteger q;
 private BigInteger n;
 private BigInteger e;
 private BigInteger d;

 //variables auxiliares
 private BigInteger aux_1, aux_2, aux_3, aux_4, aux_5;

 //contiene la longitud de la clave a generar
 private int longitud_clave;

 //objeto que cronometra lo que se
 //tarda en generar la clave
 public Cronometrador cronometra;

 //constructor
 public Generador(int l_clave){

  //divido la longitud de la clave en dos
  //para definir el tamaño de cada parte
  //de la clave
  longitud_clave = l_clave / 2;
  motor = new Random();

  cronometra = new Cronometrador();

  cronometra.start ();
  //genero p
  p = new BigInteger (longitud_clave, PCT_CERTEZA_PRIMO, motor);
  //genero q
  q = new BigInteger (longitud_clave, PCT_CERTEZA_PRIMO, motor);
  //creo n
  n = new BigInteger (l_clave,motor);
  //creo e
  e = new BigInteger (l_clave,motor);
  //creo d
  d = new BigInteger (l_clave,motor);

  //creo variables auxiliares para los cálculos
  aux_1 = new BigInteger(BigInteger.ZERO.toString ());
  aux_2 = new BigInteger(BigInteger.ONE.toString ());
  aux_3 = new BigInteger("1000");
  aux_4 = new BigInteger("2");
  aux_5 = new BigInteger("5");
  boolean es_primo = false;

  //calculo n, es la primera
  //parte de la clave pública
  n = p.multiply (q);

  //(p-1)*(q-1)
  aux_1 = (p.subtract(aux_2)).multiply (q.subtract (aux_2));

      es_primo = aux_1.isProbablePrime (PCT_CERTEZA_PRIMO);


       if(!es_primo){
           System.out.println("antes :" + aux_1.toString ());
           //hallo el modulo2 de (p-1)*(q-1) para ver si es par
           aux_4 =  aux_1.mod(aux_4);
           System.out.println("modulo de 2 :" + aux_4.toString ());

           //Si es par resta uno para hacerlo impar
           if(aux_4.toString () == "0"){
             aux_1 = aux_1.subtract (aux_2);
           System.out.println("despues :" + aux_1.toString ());
           }//fin del if

           String mira = aux_1.toString ();
           /*aux_5 = aux_1.mod(aux_5);
           System.out.println("modulo de 5 :" + aux_5.toString ());
           if(aux_5.toString () == "0"){

           //le resto 2 para que siga siendo impar*/
           if(mira.endsWith ("5")){
           aux_1 = aux_1.subtract (aux_4);
           System.out.println("despues :" + aux_1.toString ());
           }//fin del if

         //el bucle se ejecuta mientras
         //(p-1)*(q-1) no sea primo
         do{
           aux_1 = aux_1.subtract (aux_3);
           es_primo = aux_1.isProbablePrime (PCT_CERTEZA_PRIMO);
           System.out.println("valor : " + aux_1.toString ());
         }while(!es_primo);

       }//fin de if

        System.out.println("Lo encontre");
        System.out.println("es : " + aux_1.toString ());
        System.out.println("es primo?: " +
                                     aux_1.isProbablePrime (PCT_CERTEZA_PRIMO));

        try{
        //cálculo del número e
        e =  e.modInverse(aux_1);
        }catch(ArithmeticException a){

       }//fin de try-catch
  //cálculo del número d
   try{
    d = e.modInverse (aux_1);
  }catch(ArithmeticException a){
  }

  cronometra.parar ();

  }//fin de constructor


  //Devuelve p generado
  public String dameP(){
   String valor_de_p;
   valor_de_p = p.toString();
   return valor_de_p;
 }//fin dameP

  //Devuelve q generado
  public String dameQ(){
    String valor_de_q;
   valor_de_q = q.toString();
   return valor_de_q;
  }//fin dameQ

  //Devuelve n generado
  public String dameN(){
   String valor_de_n;
   valor_de_n = n.toString();
   return valor_de_n;
  }//fin dameN

  //Devuelve e generado
  public String dameE(){
   String valor_de_e;
   valor_de_e = e.toString();
   return valor_de_e;
  }//fin dameE

  //Devuelve d generado
  public String dameD(){
   String valor_de_d;
   valor_de_d = d.toString();
   return valor_de_d;
  }//fin dameD

}//fin de la clase Generador
