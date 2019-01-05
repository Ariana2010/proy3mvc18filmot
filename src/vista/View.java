/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controller;
import java.util.Scanner;
import static com.coti.tools.Esdia.*;
import static java.lang.System.out;
import static java.lang.System.err;
import static java.lang.System.exit;

/**
 *
 * @author pgina37
 */
public class View {

    
    Scanner sc = new Scanner(System.in,System.getProperty("os.name").contains("Windows") ? "iso-8859-1" : "UTF-8");
            
    Controller control = new Controller();

public void runMenu(String menu) {

    this.arranque();
    String opcion;
    String[] opciones = {"1", "2", "3", "4", "5", "q"};
    boolean salir = false;
    do {
        opcion = readString(menu, opciones);
        boolean volver = false;
        switch (opcion) {
            case "1":   //Opción Archivos
                String op;
                String[] opVal = {"1", "2","q"};
                op = readString("MENÚ ARCHIVOS:\n"
                        + "1 -Exportar directores a directores.col\n"
                        + "2 -Exportar películas a peliculas.html\n"
                        + "q -Volver al menú principal", opVal);
                do{
                    switch(op){
                        case "1":
                            control.exportarDirectores();
                            break;
                        case "2":
                            control.exportarPeliculas();
                            break;
                        case "q":
                            volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?"); 
                            break;
                        default:
                            err.println("\n\n NO DEBERIA ESTAR AQUI \n\n");
                            break;    
                    }
                }while(!volver);
                break;
            case "2":   //Opción Películas
                this.opcionPeliculas();
                break;
            case "3":   //Opción Directores
                this.opcionDirectores();
                break;
            case "4":   //Opción Actores
                this.opcionActores();
                break;
            case "5":   //Opción Listados
                String opc;
                String[] opcVal = {"1", "2","q"};
                opc = readString("MENÚ ARCHIVOS:\n"
                        + "1 -Listar películas\n"
                        + "2 -Listar directores\n"
                        + "3 -Listar actores\n"
                        + "q -Volver al menú principal", opcVal);
                do{
                    switch(opc){
                        case "1":
                            this.listados("peliculas");
                            break;
                        case "2":
                            this.listados("directores");
                            break;
                        case "3":
                            this.listados("actores");
                            break;
                        case "q":
                            volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?"); 
                            break;
                        default:
                            err.println("\n\n NO DEBERIA ESTAR AQUI \n\n");
                            break;    
                    }
                }while(!volver);
                break;
            case "q":
                salir = preguntarSiSalirOrContinuar("¿Esta seguro de que desea salir?");
                if(salir){
                    this.salida();
                }
                break;
            default:
                err.println("\n\n ERROR \n\n");
                break;
        }
    } while (!salir);   
    this.salida();
}

private boolean preguntarSiSalirOrContinuar(String q) {
    String op;
    String[] opValidas = {"s","S","n","N"};
    op = readString(q,opValidas);
    return (op.equalsIgnoreCase("s"));
}

//=====================================================================//
//**************************   PELICULAS  *****************************//
//=====================================================================//  
private void opcionPeliculas() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String menu = "MENÚ PELÍCULAS:"
                + "\n1 -Añadir película a la colección."
                + "\n2 -Borrar película de la colección."
                + "\n3 -Modificar película de la colección."
                + "\n4 -Consultar película."
                + "\nq -Volver a Menú Principal.\n";
    String[] _opciones = {"1", "2", "3", "4", "q"};
    String opSub;
    boolean volver = false;
    do {
        opSub = readString(menu, _opciones);
        switch(opSub){
            case "1": //Añadir película
                this.altaPelicula(control.getCAMPOS_PELICULA());
                break;
            case "2": //Borrar película
                this.borrarPelicula();
                break;
            case "3": //Modificar película
                this.modificarPelicula();
                break;
            case "4": //Consultar película
                this.consultar("pelicula");
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("\n\n ERROR \n\n");
                break;
        }

    } while(!volver); 
}

private void altaPelicula(String[] campos) {
    String [] nuevo;
    int index = 0;
    boolean done;
    //boolean follow = true;

    nuevo = new String[campos.length];
    for(int i=0;i<campos.length;i++){
        nuevo[i] = null;
    }

    System.out.println("Por favor rellene los siguientes campos:");
    for(String s : campos){
        if(s.startsWith("*")){  //este campo es una colección
            //System.out.printf("%s : ",s.substring(1));
            nuevo[index++]= this.leerColl(s.substring(1));
            /*follow= this.preguntarSiSalirOrContinuar("Continuar?");  //*/
        }else{ 
            System.out.printf("%s: ",s);
            nuevo[index++]= sc.nextLine();
            /*follow = this.preguntarSiSalirOrContinuar("Continuar?");  //*/
        }
       /*if (!follow)  break;  //*/
    }


    try {
        done = control.altaPelicula(nuevo);
    }catch (NumberFormatException e) {
        done = false;
        err.println("Por favor asegurese de introducir correctamente los"
                + "datos numéricos.\n Intentelo de nuevo.\n Excepcion : " + e.getMessage());
    }
    if (done) {
        System.out.println("Se ha añadido \""+nuevo[0]+"\" a la colección");
    }else{
        System.out.println("No se ha podido añadir \""+nuevo[0]+"\" a la colección");
    }
}

private String leerColl(String d) {
    StringBuilder coll = new StringBuilder();
    String c;
    System.out.println("Cuando termine de introducir los valores para "
            + "este campo pulse \"x\" y luego \"intro\", para continuar "
            + "con el siguiente campo.");
    do{
        System.out.printf("%s: ",d);
        c = sc.nextLine();
        if (!c.equals("x")) {
            coll.append(c);
            coll.append("\t");
        }
    }while (!c.equals("x"));
    return (coll.substring(0, coll.length()-1));
}

private void borrarPelicula() {
    out.print("Indique el nombre de la película que desea eliminar de la colección: ");
    String titulo = sc.nextLine();
    if (!control.verificarPeliculaEsta(titulo)){
        out.print("La película \""+titulo+"\" no está en la colección");
        exit(0);
    }
    control.eliminarPeliculaDeLaColección(titulo);/*implementar*/
}

private void modificarPelicula() {
    String titulo;
    String[] camposModif = control.getCamposModificar("pelicula"); /*implementar*/
    String[] nuevoValor = new String[camposModif.length];
    out.println("Instrucciones:\nPara cada campo modificable, introduzca su valor, si "
            + "no desea cambiarlo pulse \"x\" y después \"intro\"");
    out.println("Los campos modificables son los siguientes: "); 
    for(String c:camposModif){
        out.printf("-%s\n",c);
    }
    out.println("Por favor teclea el nombre de la película que desea modificar");
    titulo = sc.nextLine();
    if(!control.verificarPeliculaEsta(titulo))/*implemetar*/ {
        out.println("\""+titulo+"\" no esta en la colección, si desea puede añadir"
                + "está película a la colección.");
        exit(0);
    }
    out.println("Introduzca nuevos valores para:");
    int i=0;
    for(String c:camposModif){
        out.printf("-%s: ",c);
        nuevoValor[i] = sc.nextLine();
        if(nuevoValor[i].equalsIgnoreCase("x")){
            nuevoValor[i] = "";
        }
        i++;
    }
    control.modificarPelicula(titulo,camposModif,nuevoValor); /*implementar*/
}

private void consultar(String consulta) {
    String cad;
    switch (consulta){
        case "pelicula": //pedir por teclado el título de la película
            System.out.println("Por favor teclee el nombre de la "+consulta);
            cad = sc.nextLine();
            //recuperar sus datos
            String[] pelicula;
            pelicula = control.getDatosPelicula(cad);
            //Mostrar todos sus datos
            if (pelicula[0]!=null){
                this.showDatos(pelicula,control.getCAMPOS_PELICULA());
            }else{
                System.out.println("La película no está aún en la colección.");
            }
                break;
        case "actor": //pedir nombre por teclado
            //recuperar peliculas de ese actor
            //recupera de las películas : el titulo, año, duración, país y género
            //pasar esos 5 argumentos en un array a una función q lo muestre en
            //forma de tabla.
            System.out.println("pendiente de implementación");
            break;
        default:
            System.out.println("Por si acaso ;)");
    }
}
//=====================================================================//
//************************    DIRECTORES  *****************************//
//=====================================================================//
private void opcionDirectores() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String menu = "MENÚ DIRECTORES:"
                + "\n1 -Añadir director a la colección."
                + "\n2 -Borrar director de la colección."
                + "\n3 -Modificar datos."
                + "\nq -Volver a Menú Principal.\n";
    String[] _opciones = {"1", "2", "3", "q"};
    String opSub;
    boolean volver = false;
    do {
        opSub = readString(menu, _opciones);
        switch(opSub){
            case "1": //Añadir director
                System.out.println("No implementada.");
                break;
            case "2": //Borrar director
                System.out.println("No implementada.");
                break;
            case "3": //Modificar director
                System.out.println("No implementada.");
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("\n\n ERROR \n\n");
                break;
        }

    } while(!volver);    
}

//********************************************************************
//--------------------------    ACTORES   ----------------------------
private void opcionActores() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String menu = "MENÚ ACTORES:"
                + "\n1 -Añadir actor a la colección."
                + "\n2 -Borrar actor de la colección."
                + "\n3 -Modificar datos."
                + "\n4 -Consultar películas de un actor."
                + "\nq -Volver a Menú Principal.\n";
    String[] _opciones = {"1", "2", "3", "4", "q"};
    String opSub;
    boolean volver = false;
    do {
        opSub = readString(menu, _opciones);
        switch(opSub){
            case "1": //Añadir actor
                System.out.println("No implementada.");
                break;
            case "2": //Borrar actor
                System.out.println("No implementada.");
                break;
            case "3": //Modificar actor
                System.out.println("No implementada.");
                break;
            case "4": //Consultar película actor
                System.out.println("No implementada.");
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("\n\n ERROR \n\n");
                break;
        }

    } while(!volver);    
}    
    
//=====================================================================//
//************************    ARCHIVOS   ******************************//
//=====================================================================//

//=====================================================================//
//*************************    LISTADOS  ******************************//
//=====================================================================//

//=====================================================================//
//**********************  ARRANQUE Y SALIDA  **************************//
//=====================================================================//
    private void arranque (){
        control.loadFiles();
    }
    
    private void salida (){
        control.saveFiles();
    }

//=====================================================================//
//************************  MÁS MÉTODOS   *****************************//
//=====================================================================//
    
/**
 * Recibe dos arrays de string, uno con los datos de la película y otro
 * con la cabecera para los datos.
 * @param datos
 * @param title 
 */
private void showDatos(String[] datos,String[] title) {
    int index = 0;
    System.out.println("");
    for(String p:datos){
        if(title[index].startsWith("*")){
            System.out.println(title[index++].substring(1).toUpperCase()+":\n"+p);
        }else{
            System.out.println(title[index++].toUpperCase()+":\n"+p);                    
        }
    }
}
    
    private void showListadosEncolumnados(String[] datos, String[] cabecera){
        System.out.println("No implementada");
    }

    private void listados(String opcion) {
        System.out.println("No implementada.");
    }
       
    
    
    
}//End Class
