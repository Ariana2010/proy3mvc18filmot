/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Model;
import static java.lang.System.*;
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
       String[] camposMof = null;
       int i = 0;
       switch(_queColeccion){
           case "pelicula":
               //No modificables, el título y las colecciones.
               camposMof = new String[Pelicula.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_PELICULA()){
                   if(x.equals("Título") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Control. Pelicula campos modificables: "+Arrays.toString(camposMof));
               break;
           case "director":
               camposMof = new String[Director.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_DIRECTOR()){
                   if(x.equals("Nombre") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Director campos modificables: "+Arrays.toString(camposMof));               
               break;
           case "actor":
               camposMof = new String[Actor.NUMCAMPOSMODIF];
               for (String x : this.getCAMPOS_ACTOR()){
                   if(x.equals("Nombre") || x.startsWith("*")){ /*do nothing*/ }
                   else{camposMof[i++] = x;}
               }
/**/            System.out.println("Actor campos modificables: "+Arrays.toString(camposMof)); 
               break;
           default:
               System.err.println("ERROR: método getCamposModificar: No debería estar aquí.");
               exit(1);        
       }
       return camposMof;
    }

    public void modificarPelicula(String _titulo,String[] _camposModif,String[] _nuevoValor)
    {
        m.actualizarDatosPelicula(_titulo,_camposModif,_nuevoValor); 
    }

    public boolean verificarPeliculaEsta(String titulo) {
        return (m.buscarEnColecciones(titulo, Filmoteca.PELICULA));
    }

    public void eliminarPeliculaDeLaColección(String titulo) {
        m.eliminarDelModelo(titulo,Filmoteca.PELICULA);
    }

    
    //***************************************************************
    public void exportarDirectores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void exportarPeliculas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        String datos = new String();
        if(m.buscarEnColecciones(cad, Filmoteca.ACTOR)){
          return(m.getPeliculasActor(cad));  
        }else{
         datos = "El actor no se encuentra en la colección.";
         return datos;   
        }
    }
    
}//End Class
