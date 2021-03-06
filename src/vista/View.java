/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controller;
import java.util.Scanner;
import static com.coti.tools.Esdia.*;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import static java.lang.System.err;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 *
 * @author pgina37
 */
public class View {

    Scanner sc = new Scanner(System.in,System.getProperty("os.name").contains("Windows") ? "iso-8859-1" : "UTF-8");
    Controller control = new Controller();
    private final static String PELICULAS = "1";
    private final static String DIRECTORES = "2";
    private final static String ACTORES = "3";

public void runMenu(String _menu, String[] _opcionesMenu) {

    this.arranque(); 
    String opcion;
    boolean salir = false;
    do {
        opcion = readString(_menu, _opcionesMenu);
        boolean volver = false;
        switch (opcion) {
            case "1":   //Opción Archivos
                String[] opVal = {"1", "2","q"};
                String op;
                do{
                op = readString("\nMENÚ ARCHIVOS:\n"
                        + "1 -Exportar directores a directores.col\n"
                        + "2 -Exportar películas a peliculas.html\n"
                        + "q -Volver al menú principal", opVal);
                    switch(op){
                        case "1":
                            try{
                                control.exportarDirectores();
                                System.out.println("\n* Se ha creado \"directores.col\" en "
                                    + "la carpeta Filmot18 del escritorio.\n");
                            }catch (FileNotFoundException ex){
                                err.printf("No fue posible crear el archivo, exception: %s , "
                                        + "mensaje: %s.",ex.toString(),ex.getMessage());
                            }
                            break;
                        case "2":
                            try{
                                control.exportarPeliculas();
                                System.out.println("\n\tSe ha creado \"peliculas.html\" en "
                                        + "la carpeta Filmot18 del escritorio. ");
                            }catch (FileNotFoundException ex){
                                err.printf("No fue posible crear el archivo, exception: %s , "
                                        + "mensaje: %s.",ex.toString(),ex.getMessage());
                            }
                            break;
                        case "q":
                            volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?"); 
                            break;
                        default:
                            err.println("ERROR: VIEW: runMenu(): (case:Opción Archivos): NO DEBERIA ESTAR AQUI.");
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
                String[] opcVal = {"1","2","3","q"};
                String opc;
                do{
                opc = readString("\nMENÚ LISTADOS:\n"
                        + "1 -Listar películas\n"
                        + "2 -Listar directores\n"
                        + "3 -Listar actores\n"
                        + "q -Volver al menú principal", opcVal);
                    switch(opc){
                        case "1":
                            this.listados(View.PELICULAS);
                            break;
                        case "2":
                            this.listados(View.DIRECTORES);
                            break;
                        case "3":
                            this.listados(View.ACTORES);
                            break;
                        case "q":
                            volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?"); 
                            break;
                        default:
                            err.println("ERROR: VIEW: runMenu(): (case:Opción Listados): NO DEBERIA ESTAR AQUI.");
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
                err.println("(Por si acaso ;) )ERROR: VIEW: runMenu(): default: NO DEBERIA ESTAR AQUI.");
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
    String menu = "\nMENÚ PELÍCULAS:"
                + "\n1 -Añadir película a la colección."
                + "\n2 -Borrar película de la colección."
                + "\n3 -Modificar película de la colección."
                + "\n4 -Consultar película."
                + "\nq -Volver a Menú Principal.\n";
    String[] opciones = {"1", "2", "3", "4", "q"};
    String opMenuFilms;
    boolean volver = false;
    do {
        opMenuFilms = readString(menu, opciones);
        switch(opMenuFilms){
            case "1": //Añadir película
                this.peliculaAlta(control.getCAMPOS_PELICULA());
                break;
            case "2": //Borrar película
                this.peliculaBaja();
                break;
            case "3": //Modificar película
                this.peliculaModificar();
                break;
            case "4": //Consultar película
                this.consultar(View.PELICULAS);
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("ERROR: VIEW: peliculasOpcion(): No debería mostrar está línea.");
                break;
        }

    } while(!volver); 
}

private void peliculaAlta(String[] _campos) {
    String [] nuevo;
    int index = 0;
    boolean done;
  /*boolean follow = true;*/
    nuevo = new String[_campos.length];
  /*  for(int i=0;i<_campos.length;i++){
        nuevo[i] = null;
    }
    // nuevo[i] esta inicializado a null como resultado de usar el constructor.
  */
    Arrays.fill(nuevo,""); //Asi nuevo[i] contiene una cadena vacia, así me aseguro.
    System.out.println("\nComplete los siguientes campos:");
    for(String s : _campos){
        if(s.startsWith("*")){  //este campo es una colección
            nuevo[index++]= this.leerColeccion(s.substring(1));
            /*follow= this.preguntarSiSalirOrContinuar("Continuar?");  //*/
        }else{ //para campos simples.
            System.out.printf("%s: ",s.toUpperCase());
            /*   String input = sc.nextLine();
            if(!input.isEmpty()){nuevo[index++]= input;}
            else{ nuevo[index++]= ""; } */  //<-- No hay necesidad, porque:
            nuevo[index++]= sc.nextLine(); //si se pulsa intro => asigna un cadena vacía, talque 
                                           //nuevo[index].isEmpty retorna true. Y
                                           //nuevo[index].length retorna 0. 
                                           //Y sdemás Arrays.fill(nuevo,""); lo ha llenado con cadenas vacías.
            /*follow = this.preguntarSiSalirOrContinuar("Continuar?");  //*/
        }
       /*if (!follow)  break;  //*/
    }
    try {
        done = control.altaPelicula(nuevo);
    }catch (NumberFormatException e) {
        done = false;
        err.println("- Por favor asegurese de introducir correctamente los"
                + "datos numéricos. Intentelo de nuevo. Excepcion : " + e.getMessage());
    }
    if (done) {
        System.out.println("* Se ha añadido \""+nuevo[0]+"\" a la colección");
    }else{
        System.out.println("- No se ha podido añadir \""+nuevo[0]+"\" a la colección");
    }
}

private String leerColeccion(String d) {
    StringBuilder coll = new StringBuilder();
    String c;
    System.out.println("INSTRUCCIONES:"
                   + "\nIntroduzca un valor cada vez.Para pasar al siguiente campo pulse \"intro\".");
    do{
        System.out.printf("%s: ",d.toUpperCase());
        c = sc.nextLine();
        if (!c.isEmpty()) { //No agrega valores vacios en la colección.
            coll.append(c);
            coll.append("\t");
        }
    }while (!c.isEmpty());
    if(coll.length() == 0){ //Aunque la colección en sí, si puede estar vacia.
        return coll.substring(0);
    }
    return (coll.substring(0, coll.length()-1));
}

private void peliculaBaja() {  /*ok*/
    out.println("\nIndique el nombre de la película que desea eliminar de la colección: ");
    String titulo = sc.nextLine();
    if (control.verificarPeliculaEsta(titulo)){
        control.eliminarPeliculaDeLaColección(titulo);/*ok.*/
        System.out.println("* Se ha eliminado \""+titulo+"\" de la colección.");
    }else{
        out.println("- La película \""+titulo+"\" no se encuentra en la colección");
    }
}

private void peliculaModificar() {
    String titulo;
    String[] camposModif = control.getCamposModificar(View.PELICULAS);
    String[] nuevoValor = new String[camposModif.length];
    Arrays.fill(nuevoValor, ""); //asegurarme así de que hay cadenas vacias en el array.
    out.println("\nTeclee el nombre de la película que desea modificar: ");
    titulo = sc.nextLine();
    out.println("\nINSTRUCCIONES:"
              + "\nPara cada campo modificable, introduzca su valor."
              + "\nSi no desea modificarlo pulse \"intro\".");
  /*  out.println("Los campos modificables son los siguientes: "); 
    for(String c:camposModif){
        out.printf("-%s\n",c);
    }*/
    if(!control.verificarPeliculaEsta(titulo)) {
        out.println("* \""+titulo+"\" no esta en la colección, si desea puede "
                + "añadir está película a la colección.");
    }else{
        out.println("\nIntroduzca nuevos valores para:");
        int i=0;
        for(String c:camposModif){
            out.printf("-%s: ",c.toUpperCase());
            nuevoValor[i++] = sc.nextLine();
        }
        try{
            control.modificarPelicula(titulo,camposModif,nuevoValor); 
            System.out.println("* La pelicula \""+ titulo +"\" se ha actualizado");
        }catch(NumberFormatException e){
            err.println("- La película no se ha podido actualizar, Intentelo de nuevo y "
                    + "asegurese de introducir en el formato correcto los datos."
                    + " MENSAJE DE ERROR: "+e.getMessage());
        }
    }
}

private void consultar(String consulta) {
    String cad;
    switch (consulta){
        case View.PELICULAS: //pedir por teclado el título de la película
            System.out.println("\nTeclee el nombre de la pelicula a consultar: ");
            cad = sc.nextLine();
            //recuperar sus datos
            String[] pelicula;
            pelicula = control.getDatosPelicula(cad);
            //Mostrar todos sus datos
            if (pelicula[0]!=null){
                this.showDatos(pelicula,control.getCAMPOS_PELICULA());
            }else{
                System.out.println("- La película no está en la colección.");
            }
                break;
    /**/    case View.ACTORES: //pedir nombre por teclado   <-- HACER BIEN
            //recuperar peliculas de ese actor
            //recupera de las películas : el titulo, año, duración, país y género
            //pasar esos 5 argumentos en un array a una función q lo muestre en
            //forma de tabla.
            System.out.println("\nTeclee el nombre del actor a consultar:");
            cad = sc.nextLine();
            //recuperar sus datos
            String[][] listaPelis;
            listaPelis = control.getDatosPeliculaActor(cad);
            //Mostrar todos sus datos
            System.out.println("\nPeliculas de: "+ cad.toUpperCase());
            //System.out.println(listaPelis);
            this.showListadosEncolumnados(listaPelis);
            break;
        default:
            System.out.println("Por si acaso ;):ERROR: View: consultar();");
    }
}
//=====================================================================//
//************************    DIRECTORES  *****************************//
//=====================================================================//
private void opcionDirectores() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String menu = "\nMENÚ DIRECTORES:"
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
                this.directorDarDeAltaNuevo(control.getCAMPOS_DIRECTOR());
                break;
            case "2": //Borrar director
                System.out.println("\nNombre del Director que desea borrar de la colección:");
                String borrar = sc.nextLine();
                String res = control.eliminarDirectorDeLaColeccion(borrar);
                if (res.equals("true")) // se  ha borrado
                    System.out.println("* Se ha borrado \""+ borrar+"\" de la colección.");
                else{ 
                    if (res.equals("false")){
                        System.out.println("- El director no se encuentra en la colección");
                    }else{
                    System.out.println("- El director \""+borrar+"\" no se puede borrar de la coleccion."
                                   + "\n  Aún tiene las siguientes peliculas dadas de alta:"
                                   + "\n {"+res+"}.");
                    }
                }
                break;
            case "3": //Modificar director
                this.modificar(View.DIRECTORES);
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("ERROR: View: OpcionDirectores().");
                break;
        }

    } while(!volver);    
}

private void directorDarDeAltaNuevo(String[] _campos){
    String [] nuevo;
    int index = 0;
    boolean done;
    nuevo = new String[_campos.length];
    Arrays.fill(nuevo,""); //Asi nuevo[i] contiene una cadena vacia, así me aseguro.
    System.out.println("\nIntroduzca valores para los siguientes campos:");
    for(String s : _campos){
        if(s.startsWith("*")){  //este campo es una colección
            nuevo[index++]= this.leerColeccion(s.substring(1));
        }else{ //para campos simples.
            System.out.printf("%s: ",s.toUpperCase());
            if (s.equalsIgnoreCase("fecha de nacimiento")){
                System.out.println("");
                System.out.print("DIA (con dos digitos):");
                String dia = sc.nextLine();
                System.out.print("MES (con dos digitos):");
                String mes = sc.nextLine();
                System.out.print("AÑO (con cuatro digitos):");
                String year = sc.nextLine();
                nuevo[index++] = year.concat("-".concat(mes.concat("-".concat(dia))));
            }else{
         /*   String input = sc.nextLine();
            if(!input.isEmpty()){nuevo[index++]= input;}
            else{ nuevo[index++]= ""; } */  //<-- No hay necesidad, porque:
            nuevo[index++]= sc.nextLine(); //si se pulsa intro => asigna un cadena vacía, talque 
                                           //nuevo[index].isEmpty retorna true. Y
                                           //nuevo[index].length retorna 0. 
                                           //Y además Arrays.fill(nuevo,""); lo ha llenado con cadenas vacías.
            }
        }
    }
    try {
        done = false;
        if(!control.verificarDirectorEsta(nuevo[0])){
            done = control.altaDirector(nuevo);}
        else{    
            System.out.println("* Ya existe \""+nuevo[0]+"\" en la colección de directores."
                    + "\n* Utilice la opción Modificar datos.");
        }
    }catch (NumberFormatException e) {
        done = false;
        err.println("Por favor asegurese de introducir correctamente los"
                + "datos numéricos. Intentelo de nuevo. Excepcion : " + e.getMessage());
    }
    if (done) {
        System.out.println("* Se ha añadido \""+nuevo[0]+"\" a los directores.");
    }else{
        System.out.println("- No se ha podido añadir \""+nuevo[0]+"\" a los directores.");
    }
}
//=====================================================================//
//***************************   ACTORES   *****************************//
//=====================================================================//
private void opcionActores() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String menu = "\nMENÚ ACTORES:"
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
                this.actorDarDeAltaNuevo(control.getCAMPOS_ACTOR());
                break;
            case "2": //Borrar actor
                System.out.print("Nombre del Actor que desea borrar de la colección:");
                String borrar = sc.nextLine();
                String res = control.eliminarActorDeLaColeccion(borrar);
                if (res.equals("true")) // se  ha borrado
                    System.out.println("* Se ha borrado \""+ borrar+"\" de la colección.");
                else{ 
                    if (res.equals("false")){
                        System.out.println("- El actor no se encuentra en la colección.");
                    }else{
                        System.out.println("* El actor \""+borrar+"\" no se puede borrar de la coleccion."
                            + "\n  Aún tiene las siguientes peliculas dadas de alta:"
                            + "\n {"+res+"}.");
                    }
                }
                break;
            case "3": //Modificar actor
                this.modificar(View.ACTORES);
                break;
            case "4": //Consultar película actor
                this.consultar(View.ACTORES);
                break;
            case "q": //Volver a menú principal
                volver = this.preguntarSiSalirOrContinuar("¿Esta seguro de volver al Menú Principal?");
                break;
            default:
                err.println("ERROR: View: opcionActores().");
                break;
        }

    } while(!volver);    
}    
  
 private void actorDarDeAltaNuevo(String[] _campos) {
    String [] nuevo;
    int index = 0;
    boolean done;
    nuevo = new String[_campos.length];
    Arrays.fill(nuevo,""); //Asi nuevo[i] contiene una cadena vacia, así me aseguro.
    System.out.println("\nIntroduzca valores para los siguientes campos:");
    for(String s : _campos){
        if(s.startsWith("*")){  //este campo es una colección
            nuevo[index++]= this.leerColeccion(s.substring(1));
        }else{ //para campos simples.
            System.out.printf("%s: ",s.toUpperCase());
            if (s.equalsIgnoreCase("fecha de nacimiento")){
                System.out.println("");
                System.out.print("Dia (con dos digitos):");
                String dia = sc.nextLine();
                System.out.print("Mes (con dos digitos):");
                String mes = sc.nextLine();
                System.out.print("Año (con cuatro digitos):");
                String year = sc.nextLine();
                nuevo[index++] = year.concat("-".concat(mes.concat("-".concat(dia))));
            }else{
         /*   String input = sc.nextLine();
            if(!input.isEmpty()){nuevo[index++]= input;}
            else{ nuevo[index++]= ""; } */  //<-- No hay necesidad, porque:
            nuevo[index++]= sc.nextLine(); //si se pulsa intro => asigna un cadena vacía, talque 
                                           //nuevo[index].isEmpty retorna true. Y
                                           //nuevo[index].length retorna 0. 
                                           //Y además Arrays.fill(nuevo,""); lo ha llenado con cadenas vacías.
            }
        }
    }
    try {
        done = false;
        if(!control.verificarActorEsta(nuevo[0])){
            done = control.altaActor(nuevo);
        }else{    
            System.out.println("* Ya existe \""+nuevo[0]+"\" en la colección de actores."
                    + "\n* Utilice la opción Modificar datos.");
        }
    }catch (NumberFormatException | DateTimeParseException e) {
        done = false;
        err.println("Por favor asegurese de introducir correctamente los"
                + "datos numéricos.Intentelo de nuevo.Excepcion : " + e.getMessage());
    }
    if (done) {
        System.out.println("* Se ha añadido \""+nuevo[0]+"\" a los actores.");
    }else{
        System.out.println("- No se ha podido añadir \""+nuevo[0]+"\" a los actores.");
    }
 }
//=====================================================================//
//************************    ARCHIVOS   ******************************//
//=====================================================================//
//Opción realizada en el método runMenu();
//=====================================================================//
//*************************    LISTADOS  ******************************//
//=====================================================================//
    private void listados(String _opcion) {
        switch (_opcion){
            case View.ACTORES:
                control.OrdenarPor(View.ACTORES);
                String[][] tablaA = control.getActoresEnColumnas();
                System.out.printf("%nLISTADO ACTORES POR AÑO DEBUT Y NOMBRE%n");
                System.out.printf("====================================================="
                        + "==============================================================%n");
                this.showListadosEncolumnados(tablaA);
                break;
            case View.DIRECTORES:
                control.OrdenarPor(View.DIRECTORES);
                String[][] tablaD = control.getDirectoresEnColumnas();
                System.out.printf("%nLISTADO DIRECTORES POR NACIONALIDAD Y AÑO DE NACIMIENTO%n");
                System.out.printf("====================================================="
                        + "==============================================================%n");
                this.showListadosEncolumnados(tablaD);
                break;
            case View.PELICULAS:
                control.OrdenarPor(View.PELICULAS);
                String[][] tabla = control.getPeliculasEnColumnas();
                System.out.printf("%nLISTADO PELICULAS POR TITULO%n");
                System.out.printf("====================================================="
                        + "==============================================================%n");
                this.showListadosEncolumnados(tabla);
                break;
            default:
                err.println("\n\n ERROR \n\n: View: Listados(): ¿Por qué entro aquí?");
        }
        //Mostrar Listado()
        /*Despues de la accion de ordenar las listas estarán ordenadas, asi que es
         pedir los datos y deberian retornar en orden.*/
    }

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
 * con la cabecera para los datos.Igual puede usarse para cualquier otro dato.
 * @param datos
 * @param title 
 */
private void showDatos(String[] datos,String[] title) {
    int index = 0;
    System.out.println("");
    for(String p:datos){
        if(title[index].startsWith("*")){
            System.out.println(title[index++].substring(1).toUpperCase());
            if(!(p == null)){System.out.println(p);}
        }else{
            System.out.println(title[index++].toUpperCase());                    
            if(!(p == null)){System.out.println(p);}
        }
    }
}
       
private void showListadosEncolumnados(String[][] datos){
    StringBuilder separador = new StringBuilder();
    int nFilas = datos.length;
    int nCol = datos[0].length;

    for (int  col = 0; col < nCol; col++) {
        separador.append("+");
        int largo = datos[0][col].length();
        for(int i=0; i<=largo; i++){
            separador.append("-");
        }
    }
    separador.append("+");
    for(int fil=0; fil<nFilas; fil++){
        System.out.println(separador);
        for(int col=0; col<nCol; col++){
            //System.out.printf("| %33s ", (datos[i][j].length()>33)? datos[i][j].substring(0, 33):datos[i][j]);
            System.out.printf("| %s",datos[fil][col]);
        }
        System.out.println("|");
    }
    System.out.println(separador);
}

private void modificar(String _coleccion){
    
    String nombre;
    switch(_coleccion){
        case View.DIRECTORES:
            out.println("Introduzca el nombre del director que desea modificar: ");
            nombre = sc.nextLine();
            if(!control.verificarDirectorEsta(nombre)) {
                out.println("\""+nombre+"\" no esta en la colección, si desea puede añadir "
                                                           + "este director a la colección.");
            }else{
                out.println("\nINSTRUCCIONES:\nPara cada campo modificable, introduzca su valor."
                                         + " Si no desea modificarlo pulse \"intro\".");
                String[] camposModif = control.getCamposModificar(View.DIRECTORES);
                String[] nuevoValor = new String[camposModif.length];
                Arrays.fill(nuevoValor, ""); //asegurarme así de que hay cadenas vacias en el array.
                out.println("Introduzca nuevos valores para:");
                int i=0;
                for(String c:camposModif){
                    if(c.equalsIgnoreCase("fecha de nacimiento")){
                        System.out.println("formato para la fecha: año-mes-dia (con dos digitos para el mes y el dia)");
                    }
                    out.printf("-%s: ",c.toUpperCase());
                    nuevoValor[i++] = sc.nextLine();
                }
                try{
                    control.modificarDirector(nombre,camposModif,nuevoValor); 
                    System.out.println("* El director \""+ nombre +"\" se ha actualizado correctamente.");
                }catch(DateTimeParseException e){
                    err.println("- El director no se ha podido actualizar, Intentelo de nuevo y "
                            + "asegurese de introducir en el formato correcto los datos.\n"
                            + "error message: "+e.getMessage());
                }
            }
            break;
        case View.ACTORES:
            out.println("Por favor teclea el nombre del actor que desea modificar");
            nombre = sc.nextLine();
            if(!control.verificarActorEsta(nombre)) {
                out.println("\""+nombre+"\" no esta en la colección, si desea puede añadir "
                                                           + "este actor a la colección.");
            }else{
                out.println("\nINSTRUCCIONES:\nPara cada campo modificable, introduzca su valor."
                                          + "Si no desea modificarlo pulse \"intro\".");
                String[] camposModif = control.getCamposModificar(View.ACTORES);
                String[] nuevoValor = new String[camposModif.length];
                Arrays.fill(nuevoValor, ""); //asegurarme así de que hay cadenas vacias en el array.
                out.println("\nIntroduzca nuevos valores para:");
                int i=0;
                for(String c:camposModif){
                    if(c.equalsIgnoreCase("fecha de nacimiento")){
                        System.out.println("formato para la fecha: año-mes-dia (con dos digitos para el mes y el dia)");
                    }
                    out.printf("-%s: ",c.toUpperCase());
                    nuevoValor[i++] = sc.nextLine();
                }
                try{
                    control.modificarActor(nombre,camposModif,nuevoValor); 
                    System.out.println("* El actor \""+ nombre +"\" se ha actualizado correctamente.");
                }catch(DateTimeParseException | NumberFormatException e){
                    err.println("- El director no se ha podido actualizar, Intentelo de nuevo y "
                            + "asegurese de introducir en el formato correcto los datos."
                            + "MENSAJE DE ERROR: "+e.getMessage());
                }
            }
            break;
        default:
            System.out.println("ERROR:View:modificar().");
    }

    
}
    
    
}//End Class
