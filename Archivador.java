package redes;

import java.io.*;
import java.net.*;

public class Archivador {

  //estado devuelto al crear el fichero
  boolean estado;
  private String t_archivo;
  private URL archivoURL;
  private String prefijo = "file:"
                               + System.getProperty("user.dir")
                               + System.getProperty("file.separator");

  //nombre del fichero de claves a generar
  private final String fichero = "claves.htm";
  //cadenas de confirmación de la operación
  private final String ACCION_CORRECTA = "Fichero " + fichero + " creado y " +
                                          "guardado satisfactoriamente";
  private final String ACCION_FALLIDA = "No se ha podido realizar la operación";
  //carácter de retorno de carro
  private final String CAMBIO_LINEA = "\n";
  //cabeceras y etiquetas HTML
  private final String MARCA_INI = "<HTML>" + CAMBIO_LINEA;
  private final String MARCA_FIN = "</HTML>" + CAMBIO_LINEA;
  private final String CABECERA_INI = "<HEAD>" + CAMBIO_LINEA;
  private final String CABECERA_FIN = "</HEAD>" + CAMBIO_LINEA;
  private final String CONTENIDO ="<meta http-equiv=\"Content-Type\" " +
                            "content=\"text/html; charset=iso-8859-1\">";
  private String COLOR_BODY = "bgcolor=\"#33CCFF\"";
  private final String CUERPO_INI = "<BODY " + COLOR_BODY+ ">" + CAMBIO_LINEA;

  private final String CUERPO_FIN = "</BODY>" + CAMBIO_LINEA;
  private String TEXTO_INI = "<H3>";
  private String TEXTO_FIN = "</H3>";
  private final String PARRAFO_INI = "<P>";
  private final String PARRAFO_FIN = "</P>";
  private final String USUARIO = PARRAFO_INI + "<font color=\"#CC6600\">" +
                                 "<b><i><u>USUARIO:</u></i></b> </font>";
  private final String CLAVE = PARRAFO_INI + "<font color=\"#CC6600\">" +
                               "<b><i><u>CLAVE:</i></b> </font>";
  private final String PUBLICA = PARRAFO_INI + "<font color=\"#CC6600\">" +
                                 "<b><i><u>CLAVE PUBLICA:</u></i></b> </font>";
  private final String PRIVADA = PARRAFO_INI + "<font color=\"#CC6600\">" +
                                 "<b><i><u>CLAVE PRIVADA:</u></i></b> </font>";

  //fin de definición de variables y objetos

  //constructor vacio
  public Archivador() {
  }//fin de constructor

  //constructor que recibe el título del documento y la página a mostrar
  public Archivador(String titulo, String pagina) {
            t_archivo = pagina;
            try {
                archivoURL = new URL(prefijo + pagina);
            } catch (java.net.MalformedURLException exc) {
                System.out.println("No he podido abrir el archivo, "
                                   + "URL mal formada: " + archivoURL);
                archivoURL = null;
            }
  }//fin constructor

  //devuleve la página a mostrar
  public String toString() {
        return t_archivo;
  }//fin de toString

  //crea y guarda el fichero de claves
  public String guardarFichero(String usuario, String password,
                               String publica, String privada){
   estado = true;


   //creo las cadenas con formato HTML con los
   //parámetros recibidos
   String linea_usuario = USUARIO + TEXTO_INI + usuario + TEXTO_FIN +
                          PARRAFO_FIN + CAMBIO_LINEA;
   String linea_clave = CLAVE + TEXTO_INI +  password + TEXTO_FIN +
                        PARRAFO_FIN + CAMBIO_LINEA;
   String linea_publica = PUBLICA + TEXTO_INI + publica + TEXTO_FIN +
                          PARRAFO_FIN + CAMBIO_LINEA;
   String linea_privada = PRIVADA + TEXTO_INI + privada + TEXTO_FIN +
                          PARRAFO_FIN + CAMBIO_LINEA;

  //creo el fichero
  try{
      FileWriter escribe = new FileWriter(fichero);
      escribe.write (MARCA_INI);
      escribe.write (CABECERA_INI);
      escribe.write (CONTENIDO);
      escribe.write (CABECERA_FIN);
      escribe.write (CUERPO_INI);
      escribe.write (linea_usuario);
      escribe.write (linea_clave);
      escribe.write (linea_publica);
      escribe.write (linea_privada);
      escribe.write (CUERPO_FIN);
      escribe.write (MARCA_FIN);
      escribe.close ();
   } catch(java.io.IOException e) {
      estado = false;
   }
   //miro si se ha salvado bien el fichero
   if (estado){
      return ACCION_CORRECTA;
     }else{
      return ACCION_FALLIDA;
     }//fin del if

  }//fin de guardarFicheo

  //devueleve el fichero a abrir
  public URL abrirFichero(){

   return archivoURL;

  }//fin abrirFichero

}//fin de la clase Archivador
