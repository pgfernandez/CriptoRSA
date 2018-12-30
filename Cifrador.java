package redes;

import java.math.BigInteger;
import java.lang.Character;

public class Cifrador {

  //variables para cifrar y descifrar
  BigInteger n, m, c, d, e;
  int tamanyo;

  //cosntructor
  public Cifrador(int tam, String valor_n, String valor_e, String valor_d) {
   tamanyo = tam;
   n = new BigInteger(valor_n);
   e = new BigInteger(valor_e);
   d = new BigInteger(valor_d);
   c = new BigInteger(valor_n + valor_e);

  }//fin de constructor

  //cifro el texto
  public String Cifrar(String mensaje){

   //creo m con la cadena convertida en bytes
   m = new BigInteger(mensaje.getBytes ());
   System.out.println("valor m:" + m.toString ());

   //funcion de cifrar
   c = m.modPow (e, n);

   //muestro la cadena cifrada
   byte cifrada[] = new byte[]{};
   cifrada = c.toByteArray ();
   String tcifrada = new String(cifrada);
   System.out.println("cadena cifrada: " + tcifrada);


   return tcifrada;
  }//fin de Cifrar

  //descifro el texto
  public String Descifrar(){
  //descifro la cadena
   m = c.modPow (d, n);

   //muestro la cadena descifrada
   byte descifrado[] = new byte[]{};
   descifrado = m.toByteArray ();
   String texto_descifrado = new String(descifrado);
   System.out.println("valor descifrado" + texto_descifrado);

  return texto_descifrado;
  }//fin de Descifrar

}//fin de la clase Cifrador