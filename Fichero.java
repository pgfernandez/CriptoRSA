package redes;

import java.io.*;

public class Fichero {

  boolean estado;
  final String ACCION_CORRECTA = "Operación realizada satisfactoriamente";
  final String ACCION_FALLIDA = "No se ha podido realizar la operación";
  public Fichero() {


  }
  public String guardarFichero(String usuario, String password){
   estado = true;

   String clave = usuario + "\t" + password;

   byte b[] = clave.getBytes();
   String outputFileName = /*System.getProperty("user.home",
      File.separatorChar + "home" +
      File.separatorChar + "zelda") +
      File.separatorChar +*/ "claves.txt";
  try{
      FileOutputStream out = new FileOutputStream(outputFileName);
      out.write(b);
      out.close();
   } catch(java.io.IOException e) {
      //System.out.println("No he podido escribir text.txt");
      estado = false;
   }
     if (estado){
      return ACCION_CORRECTA;
     }else{
      return ACCION_FALLIDA;
     }

  }
  /*public abrirFichero(){

  }
  public crearFichero(){

  }*/




} 