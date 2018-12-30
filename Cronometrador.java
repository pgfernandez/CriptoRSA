package redes;

import java.util.Calendar;
import java.util.Date;


public class Cronometrador extends Thread{

  private Calendar tiempo;
  private int segundos;
  private int total;


  public Cronometrador(){
  }


  public void run(){
  int auxiliar = 0;

  segundos = 0;
  total = 0;

   for(;;){
    try{
     this.sleep(1000);
     segundos += 1;
    }catch(InterruptedException ie){
    }
    total += segundos;
   }

  }

  public void parar(){
   int min = 0;
   int seg = 0;
   int aux;
   this.stop ();

  /* for (aux = 0;aux<60;aux -=60){
     aux = seg -60;
     min += 1;
   }
   seg = aux;*/

   System.out.println("Tiempo total: " + total + " segundos");
   // + min + "minutos " + seg + " segundos");

  }


}