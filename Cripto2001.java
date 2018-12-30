
//Título:       Cripto 2001
//Versión:      1.0.4.2001
//Copyright:    Copyright (c) 2001
//Autor:        Pedro García/Iván Embuena
//Empresa:      Newbies technologies
//Descripción:  Programa de encriptación RSA

package redes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;
import java.net.*;
import java.io.*;
import com.borland.jbcl.control.*;



public class Cripto2001 extends JFrame {

  //objeto de la clase Generador(crea las claves)
  Generador claves;
  JTextField text_usuario = new JTextField();
  JButton boton_Generar = new JButton();

  //Vector que guarda las longitudes de las claves
  Vector longitudes = new Vector(6);

  //objeto que enviará a cifrar y descifrar los mensajes
  Cifrador agente;

  //lista que contiene las longitudes de las claves
  //definidas en el objeto Vector longitudes
  JList lista_longitudes;
  JScrollPane panel_Arbol = new JScrollPane();

  //Árbol de directorios donde se sitúan
  //los archivos accesibles por el usuario
  JTree arbol;
  JScrollPane panel_consola = new JScrollPane();
  TitledBorder titledBorder1;

  //consola de salida de los mensajes
  //producidos por el programa
  JTextArea consola = new JTextArea();

  //nodos para el árbol de directorios
  DefaultMutableTreeNode directorio;

  //barra de botones
  JToolBar barra_Botones = new JToolBar();
  JButton barra_Generar = new JButton(new ImageIcon("iconos/satelite.jpg"));
  JButton barra_Guardar = new JButton(new ImageIcon("iconos/archivar.jpg"));
  JButton barra_Cifrar = new JButton(new ImageIcon("iconos/flechas3.jpg"));
  JButton barra_Descifrar = new JButton(new ImageIcon("iconos/flechas2.jpg"));
  JButton barra_Ayuda = new JButton(new ImageIcon("iconos/ayuda.jpg"));

  TitledBorder titledBorder2;
  JButton boton_Guardar = new JButton();
  JPasswordField text_pass = new JPasswordField();
  JButton boton_Descifrar = new JButton();

  //variables del programa
  final String CAMBIO_DE_LINEA = "\n";
  final String SEPARADOR = "/";
  String n, e, d;
  String clave_publica;
  String clave_privada;
  int longitud;

  //elementos del formulario
  JLabel label_usuario = new JLabel();
  JLabel label_password = new JLabel();
  JScrollPane panel_vista = new JScrollPane();
  JPanel panel_superior = new JPanel();
  JLabel label_datos = new JLabel();
  JLabel label_Claves = new JLabel();
  JButton boton_Cifrar = new JButton();
  JEditorPane text_Vista = new JEditorPane();

  //area de texto donde escribimos los mensajes
  JTextArea text_Cifrar = new JTextArea();
  JLabel label_Cifrar = new JLabel();
  ImageControl imagen_Superior = new ImageControl();

  //fin de definición de variables y objetos

  //constructor de Cripto2001
  public Cripto2001() {
    //añado los tamaños de clave al vector
    longitudes.addElement ("64");
    longitudes.addElement ("128");
    longitudes.addElement ("256");
    longitudes.addElement ("512");
    longitudes.addElement ("768");
    longitudes.addElement ("1024");
    longitudes.addElement ("2048");
    //creo la lista con lo que contiene el vector longitudes
    lista_longitudes = new JList(longitudes);

    //añado los elementos del directorio
    directorio = new DefaultMutableTreeNode("Cripto 2001");
    crearNodos(directorio);
    arbol = new JTree(directorio);

    //método init del frame
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    //cambio de tamaño y lo muestro
    this.setSize (1024,768);
    consola.append("Bienvenido a Cripto2001, Newbies Technologies 2001"
                   + CAMBIO_DE_LINEA);
    this.show();
  }//fin del constructor

  //método main
  public static void main(String[] args) {
    Cripto2001 cripto20011 = new Cripto2001();
  }//fin de main

  //método que inicializa, agrega y dispone los
  //elementos del Frame
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.addWindowListener(new java.awt.event.WindowAdapter() {

      //evento cierre de ventana
      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }
    });
    this.getContentPane().setLayout(null);
    text_usuario.setBackground(Color.lightGray);
    text_usuario.setFont(new java.awt.Font("Dialog", 1, 12));
    text_usuario.setBounds(new Rectangle(798, 129, 172, 24));
    text_usuario.addActionListener(new java.awt.event.ActionListener() {
      //evento si se ha escrito en el campo usuario
      public void actionPerformed(ActionEvent e) {
        text_usuario_actionPerformed(e);
      }
    });
    boton_Generar.setEnabled(false);
    boton_Generar.setText("Genera Clave");
    boton_Generar.setBounds(new Rectangle(825, 233, 143, 34));
    boton_Generar.addActionListener(new java.awt.event.ActionListener() {

      //evento botón Generar clave pulsado
      public void actionPerformed(ActionEvent e) {
        boton_Generar_actionPerformed(e);
      }
    });
    lista_longitudes.setBackground(new java.awt.Color(13, 118, 169));
    lista_longitudes.setFont(new java.awt.Font("Dialog", 1, 14));
    lista_longitudes.setForeground(Color.white);
    lista_longitudes.setSelectedIndex(0);
    lista_longitudes.setBounds(new Rectangle(702, 234, 50, 156));
    panel_Arbol.setBounds(new Rectangle(8, 83, 183, 521));
    panel_consola.setMaximumSize(new Dimension(327675, 327675));
    panel_consola.setBounds(new Rectangle(8, 608, 1004, 125));
    consola.setBackground(new java.awt.Color(13, 118, 169));
    consola.setForeground(Color.white);
    consola.setEditable(false);
    consola.setFont(new java.awt.Font("Dialog", 1, 12));
    barra_Botones.setBackground(new java.awt.Color(13, 118, 169));
    barra_Botones.setBorder(null);
    barra_Botones.setBounds(new Rectangle(13, 17, 173, 38));
    barra_Generar.setMaximumSize(new Dimension(34, 34));
    barra_Generar.setMinimumSize(new Dimension(34, 34));
    barra_Generar.setToolTipText ("Generar Claves");
    boton_Guardar.setEnabled(false);
    boton_Guardar.setText("Guardar Clave");
    boton_Guardar.setBounds(new Rectangle(825, 273, 143, 34));
    boton_Guardar.addActionListener(new java.awt.event.ActionListener() {

      //evento botón Guardar claves pulsado
      public void actionPerformed(ActionEvent e) {
        boton_Guardar_actionPerformed(e);
      }
    });
    text_pass.setEchoChar('@');
    text_pass.setBackground(Color.lightGray);
    text_pass.setFont(new java.awt.Font("Dialog", 1, 12));
    text_pass.setBounds(new Rectangle(799, 157, 172, 25));
    label_usuario.setFont(new java.awt.Font("Dialog", 1, 12));
    label_usuario.setText("USUARIO:");
    label_usuario.setBounds(new Rectangle(698, 124, 71, 28));
    label_password.setFont(new java.awt.Font("Dialog", 1, 12));
    label_password.setText("CONTRASEÑA:");
    label_password.setBounds(new Rectangle(700, 154, 90, 26));
    this.getContentPane().setBackground(Color.lightGray);
    this.setResizable(false);
    this.setTitle("NEWBIES TECHNOLOGIES---CRIPTO 2001. Release 1.0.5.2001");
    panel_vista.setBounds(new Rectangle(195, 84, 479, 520));
    panel_superior.setBackground(new java.awt.Color(13, 118, 169));
    panel_superior.setBounds(new Rectangle(-2, 1, 1023, 76));
    panel_superior.setLayout(null);
    label_datos.setFont(new java.awt.Font("Dialog", 1, 12));
    label_datos.setText("Introduzca usuario y contraseña deseados:");
    label_datos.setBounds(new Rectangle(685, 86, 275, 29));
    label_Claves.setFont(new java.awt.Font("Dialog", 1, 12));
    label_Claves.setText("Seleccione el tamaño de la clave a generar:");
    label_Claves.setBounds(new Rectangle(683, 198, 250, 27));
    boton_Cifrar.setEnabled(false);
    boton_Cifrar.setText("Cifrar Mensaje");
    boton_Cifrar.setBounds(new Rectangle(825, 312, 144, 35));
    boton_Cifrar.addActionListener(new java.awt.event.ActionListener() {
     //evento botón cifrar pulsado
      public void actionPerformed(ActionEvent e) {
        boton_Cifrar_actionPerformed(e);
      }
    });

   //escucha si se ha pulsado sobre algún nodo
   arbol.addTreeSelectionListener(new TreeSelectionListener() {
   //se pulsó sobre un nodo
   public void valueChanged(TreeSelectionEvent e) {
   DefaultMutableTreeNode nodo =
                   (DefaultMutableTreeNode)arbol.getLastSelectedPathComponent();

                if (nodo == null) return;

                Object nodoInfo = nodo.getUserObject();
                if (nodo.isLeaf()) {
                    Archivador archivo = (Archivador)nodoInfo;
                    mostrarArchivo(archivo.abrirFichero());
                }
                }
                });

    text_Vista.setEditable(false);
    arbol.setEditable(true);
    text_Cifrar.setBounds(new Rectangle(680, 447, 330, 155));
    label_Cifrar.setFont(new java.awt.Font("Dialog", 1, 12));
    label_Cifrar.setText("Escriba el texto a cifrar:");
    label_Cifrar.setBounds(new Rectangle(680, 413, 141, 29));
    boton_Descifrar.setEnabled(false);
    boton_Descifrar.setText("Descifrar Mensaje");
    boton_Descifrar.setBounds(new Rectangle(824, 352, 146, 37));
    boton_Descifrar.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        boton_Descifrar_actionPerformed(e);
      }
    });
    barra_Guardar.setMaximumSize(new Dimension(34, 34));
    barra_Guardar.setMinimumSize(new Dimension(34, 34));
    barra_Guardar.setToolTipText("Guardar");
    barra_Cifrar.setMaximumSize(new Dimension(34, 34));
    barra_Cifrar.setMinimumSize(new Dimension(34, 34));
    barra_Cifrar.setToolTipText("Cifrar Mensaje");
    barra_Descifrar.setMaximumSize(new Dimension(34, 34));
    barra_Descifrar.setMinimumSize(new Dimension(34, 34));
    barra_Descifrar.setToolTipText("Descifrar Mensaje");
    barra_Ayuda.setMaximumSize(new Dimension(34, 34));
    barra_Ayuda.setMinimumSize(new Dimension(34, 34));
    barra_Ayuda.setToolTipText("Ayuda");
    imagen_Superior.setBounds(new Rectangle(3, 0, 1017, 76));
    this.getContentPane().add(panel_consola, null);
    this.getContentPane().add(panel_Arbol, null);
    this.getContentPane().add(panel_vista, null);
    panel_vista.getViewport().add(text_Vista, null);
    this.getContentPane().add(text_Cifrar, null);
    this.getContentPane().add(label_Cifrar, null);
    this.getContentPane().add(label_Claves, null);
    this.getContentPane().add(label_datos, null);
    this.getContentPane().add(text_pass, null);
    this.getContentPane().add(text_usuario, null);
    this.getContentPane().add(label_password, null);
    this.getContentPane().add(lista_longitudes, null);
    this.getContentPane().add(label_usuario, null);
    this.getContentPane().add(panel_superior, null);
    imagen_Superior.setImageName ("iconos/genetica.jpg");
    panel_superior.add(barra_Botones, null);
    barra_Botones.add(barra_Generar, null);
    barra_Botones.add(barra_Guardar, null);
    barra_Botones.add(barra_Cifrar, null);
    barra_Botones.add(barra_Descifrar, null);
    barra_Botones.add(barra_Ayuda, null);
    panel_superior.add(imagen_Superior, null);
    this.getContentPane().add(boton_Generar, null);
    this.getContentPane().add(boton_Guardar, null);
    this.getContentPane().add(boton_Cifrar, null);
    this.getContentPane().add(boton_Descifrar, null);
    panel_Arbol.getViewport().add(arbol, null);
    panel_consola.getViewport().add(consola, null);
  }//fin de inicialización de Frame


//Eventos del Frame, cerrar, botones, arbol, etc
  void this_windowClosing(WindowEvent e) {
    System.exit(0);
  }

  void boton_Generar_actionPerformed(ActionEvent e) {

   generaClaves();
   boton_Guardar.setEnabled (true);

  }

  void boton_Guardar_actionPerformed(ActionEvent e) {

    guardarClaves();
    boton_Cifrar.setEnabled (true);

  }

  void boton_Cifrar_actionPerformed(ActionEvent e) {

    cifrarMensaje();
    boton_Descifrar.setEnabled (true);

  }

   void text_usuario_actionPerformed(ActionEvent e) {
    boton_Generar.setEnabled (true);
  }

   void boton_Descifrar_actionPerformed(ActionEvent e) {
     descifrarMensaje();
  }
  //Fin de eventos


  //método que añade los nodos al árbol
  private void crearNodos(DefaultMutableTreeNode nodos) {
        DefaultMutableTreeNode categoria = null;
        DefaultMutableTreeNode archivo = null;

        //nodo Usuario
        categoria = new DefaultMutableTreeNode("Usuario");
        nodos.add(categoria);

         archivo = new DefaultMutableTreeNode(new Archivador
                                             ("Claves", "claves.htm"));
         categoria.add(archivo);

         archivo = new DefaultMutableTreeNode(new Archivador
                                             ("Registro", "registro.htm"));
         categoria.add(archivo);

         archivo = new DefaultMutableTreeNode(new Archivador
                                             ("Datos", "datos.htm"));
         categoria.add(archivo);

        //nodo General
        categoria = new DefaultMutableTreeNode("General");
        nodos.add(categoria);

         archivo = new DefaultMutableTreeNode(new Archivador
                                          ("Ayuda de Cripto2001", "ayuda.htm"));
         categoria.add(archivo);

         archivo = new DefaultMutableTreeNode(new Archivador
                                             ("Acerca de...", "acerca.htm"));
         categoria.add(archivo);
    }//fin crearNodos


  //método que genera las claves por medio de un objeto
  //de la clase Generador
  private void generaClaves(){

   //obtendrá el índice del elemento pulsado de la lista
   int indice;
   //contendrá el tamaño de la clave
   Integer i;
   String seleccion;

   //miro el índice de la lista que está seleccionado
   indice = lista_longitudes.getSelectedIndex ();

   //del vector longitudes saco el elemento que
   //que corresponde al indice de la lista selccionado
   seleccion = (String)longitudes.elementAt (indice);

   //creo el Integer i con el valor
   //que tiene el elemento seleccionado
   i = new Integer(seleccion);

   //lo paso a tipo int para poder pasarlo al constructor de
   //Generador, así creará las claves del tamaño que yo
   //haya elegido en la lista
   longitud = i.intValue ();

   //escribo en la consola el tipo de clave
   //que voy a generar
   consola.append("Valores para la clave de " + longitud
                  + " bits" + CAMBIO_DE_LINEA);

    //creo el objeto claves y le paso a su constructor
   //el tamaño de la clave que ha sido elegido
   claves = new Generador(longitud);
   n = claves.dameN();
   e = claves.dameE();
   d = claves.dameD();

   //escribo en la consola los valores generados
   consola.append("Valor de P:(" + longitud/4 + " bits)"
                  + claves.dameP() + CAMBIO_DE_LINEA);
   consola.append("Valor de Q:(" + longitud/4 + " bits)"
                  + claves.dameQ() + CAMBIO_DE_LINEA);
   consola.append("Valor de N:(" + longitud/2 + " bits)"
                  + n + CAMBIO_DE_LINEA);
   consola.append("Valor de E:(" + longitud/2 + " bits)"
                  + e + CAMBIO_DE_LINEA);
   consola.append("Clave pública:(" + longitud + " bits)"
                   + n + "///" + e
                   + CAMBIO_DE_LINEA);
   consola.append("Valor de D:(" + longitud/2 + " bits)"
                  + d + CAMBIO_DE_LINEA);
   consola.append("Clave privada:(" + longitud + " bits)"
                   + n + "///" + d
                   + CAMBIO_DE_LINEA);
   consola.append("Si desea guardar la clave pulse sobre el" +
                  " botón 'Guardar Clave'" + CAMBIO_DE_LINEA);

   }//fin de generaClaves()

   //método que guarda las claves a través de un objeto
   //de la clase Archivador
   private void guardarClaves(){

    String estado;
    String usuario = text_usuario.getText ();
    String password = text_pass.getText ();
    String publica = n + SEPARADOR + e;
    String privada = n + SEPARADOR + d;

    //objeto de la clase Archivador
    Archivador fichero = new Archivador();

    //llamo al método guardarFichero, le paso el
    //nombre de usuario y su clave para
    //que los guarde. Me devuelve una cadena
    //con el estado producido al guardarlo
    estado = fichero.guardarFichero (usuario, password, publica, privada );

    //escribo en la consola el estado devuelto
    consola.append(estado + CAMBIO_DE_LINEA);
   }//fin guardarClaves

  //función que envia un mensaje y lo cifra
  private void cifrarMensaje(){

    //creo el objeto cifrador pasándole las partes de las
    //claves publica y privada
    agente = new Cifrador(longitud, n, e, d);
    String mensaje = text_Cifrar.getText ();
    consola.append ("Texto a cifrar: " + mensaje + CAMBIO_DE_LINEA);

    //llamo a cifrar y me devuelve el texto descifrado
    mensaje = agente.Cifrar (mensaje);
    text_Cifrar.setText (mensaje);
    consola.append ("Texto cifrado: " + mensaje + CAMBIO_DE_LINEA);
  }//fin de cifrarMensaje

  //función descifra un mensaje
  private void descifrarMensaje(){

     String descifrado;
    //llamo a descifrar, esta función
    //devuelve una cadena con el texto
    //descifrado
    descifrado = agente.Descifrar ();
    text_Cifrar.setText (descifrado);
    consola.append ("Texto descifrado: " + descifrado + CAMBIO_DE_LINEA);
  }//fin de descifrarMensaje

  //muestra el archivo seleccionado en el árbol
  private void mostrarArchivo(URL url) {
     try {
       text_Vista.setPage(url);
     } catch (IOException e) {
       System.err.println("No se pudo abrir la pagina solicitada " + url);
     }
  }///////////fin mostrarArchivo

}//fin de la clase Cripto2001

