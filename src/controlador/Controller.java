/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileNotFoundException;
import modelo.Model;
import static java.lang.System.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import modelo.Pelicula;
import modelo.Director;
import modelo.Actor;
import modelo.Filmoteca;


/**
 *
 * @author pgina37
 */
public class Controller {

Model m = new Model();
    
    public void loadFiles() {
        m.loadFiles();
    }

    public void saveFiles() {
        m.saveFiles();
    }

    //***************************************************************
    public String[] getCAMPOS_PELICULA() {
        return m.getCAMPOS_PELICULA();
    }

    public String[] getCAMPOS_DIRECTOR() {
        return m.getCAMPOS_DIRECTOR();
    }

    public String[] getCAMPOS_ACTOR() {
        return m.getCAMPOS_ACTOR();
    }
    
    //***************************************************************
    public boolean altaPelicula(String[] nuevo) 
    {
        boolean res=false;
        if(nuevo[0].isEmpty()){
            err.println("ERROR: No se puede dar de alta una Película sin título.");
            return res; }
        try {
            m.guardarPeliculaEnModelo(nuevo);
            res = true;
        }catch (NumberFormatException  exc ){
            res = false;
            throw exc;
        }
        return res;
    }
    
    public boolean altaDirector(String[] nuevo) {
    boolean res=false;
        if(nuevo[0].isEmpty()){
            err.println("ERROR: No se puede dar de alta una Director sin nombre.");
            return res; }
        try {
            m.guardarDirectorEnModelo(nuevo);
            res = true;
        }catch (DateTimeParseException exc){
            res = false;
            throw exc;
        }
        return res;    
    }
    
    public boolean altaActor(String[] nuevo) {
    boolean res=false;
        if(nuevo[0].isEmpty()){
            err.println("ERROR: No se puede dar de alta una Actor sin nombre.");
            return res; }
        try {
            m.guardarActorEnModelo(nuevo);
            res = true;
        }catch (NumberFormatException | DateTimeParseException exc ){
            res = false;
            throw exc;
        }
        return res;    
    }
    //***************************************************************
    /**
     * 
     * @param cad
     * identifica el título de la película, de la cuál deben 
     * recuperarse los datos.
     * @return 
     * Los datos de la película consultada, ordenados en un array de String,
     * talque que cada posición del array se corresponde con un campo de datos.
     */
    public String[] getDatosPelicula(String cad) {
        return (m.getDatosPelicula(cad));
    }
   
    //***************************************************************
    public String[] getCamposModificar(String _queColeccion) {
       //debe retornar los campos susceptibles de ser modificados.
       String[] camposMof;
       int i = 0;
       switch(_queColeccion){
           case Filmoteca.PELICULA:
               //No modificables, el título y las colecciones.
               camposMof = new String[Pelicula.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_PELICULA()){
                   if(x.equals("Título") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Control. Pelicula campos modificables: "+Arrays.toString(camposMof));
               return camposMof;
           case Filmoteca.DIRECTOR:
               //No modificables, el nombre y las colecciones.
               camposMof = new String[Director.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_DIRECTOR()){
                   if(x.equals("Nombre") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Director campos modificables: "+Arrays.toString(camposMof));               
               return camposMof;
           case Filmoteca.ACTOR:
               //No modificables, el nombre y las colecciones.
               camposMof = new String[Actor.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_ACTOR()){
                   if(x.equals("Nombre") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Actor campos modificables: "+Arrays.toString(camposMof)); 
               return camposMof;
           default:
               System.err.println("ERROR: método getCamposModificar: No debería estar aquí.");
               exit(1);        
       }
    return null;
    }

    public void modificarPelicula(String _titulo,String[] _camposModif,String[] _nuevoValor)
        throws NumberFormatException {
        m.actualizarDatosPelicula(_titulo,_camposModif,_nuevoValor); 
    }
    
    public void modificarDirector(String _nombre,String[] _camposModif,String[] _nuevoValor)
        throws DateTimeParseException {
        m.actualizarDatosDirector(_nombre,_camposModif,_nuevoValor); 
    }
    
    public void modificarActor(String _nombre,String[] _camposModif,String[] _nuevoValor)
        throws NumberFormatException, DateTimeParseException {
        m.actualizarDatosActor(_nombre,_camposModif,_nuevoValor); 
    }

    public boolean verificarPeliculaEsta(String titulo) {
        return (m.estaEnColecciones(titulo, Filmoteca.PELICULA));
    }

    public boolean verificarDirectorEsta(String titulo) {
        return (m.estaEnColecciones(titulo, Filmoteca.DIRECTOR));
    }
    
    public boolean verificarActorEsta(String titulo) {
        return (m.estaEnColecciones(titulo, Filmoteca.ACTOR));
    }
    
    public void eliminarPeliculaDeLaColección(String titulo) {
        m.eliminarDelModelo(titulo,Filmoteca.PELICULA);
    }

    public String eliminarActorDeLaColeccion (String _nombre){
        String res = m.comprobarBorradoSeguro(_nombre, Filmoteca.ACTOR);
        if (res.equals("true")){
            m.eliminarDelModelo(_nombre, Filmoteca.ACTOR);
            return "true";
        }else{ return res; }
    }
    
    public String eliminarDirectorDeLaColeccion (String _nombre){
        String res = m.comprobarBorradoSeguro(_nombre, Filmoteca.DIRECTOR);
        if (res.equals("true")){
            m.eliminarDelModelo(_nombre, Filmoteca.DIRECTOR);
            return "true";
        }else{ return res; }
    }
    //***************************************************************
    public void exportarDirectores() throws FileNotFoundException {
        //a directores.col Con formato de columna. Guardar archivo en \Filmot18 del escritorio.
        m.exportarDatosDirectoresEnColumnas();
    }

    public void exportarPeliculas()  throws FileNotFoundException {
        //a peliculas.html con formato de tabla. Guardar archivo en \Filmot18 del escritorio.
        m.exportarDatosPeliculasTablaHtml();
    }
    
    //***************************************************************
    /*
    private void actualizarColeccionTitulosDirector(String _film,String _directores){
        String[] directores = _directores.split(Pelicula.SEPARADOR_COLL);
        for(String dir:directores){
            m.actualizarColeccionTitulosDirector(_film,dir,true);
        }
    }*/

    public String getDatosPeliculaActor(String cad) {
        if(m.estaEnColecciones(cad, Filmoteca.ACTOR)){
             return(m.getPeliculasActor(cad));  
        }else{
            return ("El actor no se encuentra en la colección.");
        }
    }

    public void OrdenarPor(String _opcion) {
        switch (_opcion){
            case Filmoteca.PELICULA:
                m.sortBy(Filmoteca.PELICULA);
                break;
            case Filmoteca.DIRECTOR:
                m.sortBy(Filmoteca.DIRECTOR);
                break;
            case Filmoteca.ACTOR:
                m.sortBy(Filmoteca.ACTOR);
                break;
            default:
                err.println("\n ERROR: Controller: OrdenarPor(): ¿Por qué entro aquí?");
        }
    }

    /**
     * 
     * @return String[][]
     * Los datos de las peliculas en un array bidimensional, donde la 
     * primera fila es la cabecera de los datos.
     */     
    public String[][] getPeliculasEnColumnas() {
        return m.getFilmsOnTableWithFormat();
    }
    
    public String[][] getActoresEnColumnas(){
        return m.getActorsOnTableWithFormat();
    }
    
    public String[][] getDirectoresEnColumnas(){
        return m.getDirectorsOnTableWithFormat();
    }

    
}//End Class
