/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Filmoteca {
 
    /*
     * Nombres y extensiones de los ficheros de datos del proyecto.
     */
    private final String nameOfFolder = "Filmot18";
    private final String nameOfFileFilms = "peliculas";
    private final String nameOfFileActors = "actores";
    private final String nameOfFileDirector = "directores";
    private final String[] extensiones = {".bin",".txt"};
    private final String expDirectores = "directores.col";
    private final String expPeliculas = "peliculas.html";

    /*
     * Los campos precedidos por un asterisco son aquellos que son una colección.
     */
    private final String[] CAMPOS_PELICULA = {"Título", "Año", "Duración", "País", "*Dirección",
        "Guion", "Música", "Fotografía", "*Reparto", "Productora", "Género", "Sinopsis"};
    private final String[] CAMPOS_DIRECTOR = {"Nombre","Fecha de nacimiento","Nacionalidad",
        "Ocupación","*Películas"};
    private final String[] CAMPOS_ACTOR = {"Nombre","Fecha de nacimiento","Nacionalidad",
        "Año debut","*Películas"};
    /*
     * Aquí las colecciones.
     */
    private List<Pelicula> peliculas;
    private List<Director> directores;
    private List<Actor> actores;
    /*
     * Constantes Publicas
     */
    public static final String PELICULA = "1";
    public static final String DIRECTOR = "2";
    public static final String ACTOR = "3";
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public String getExpDirectores() {
        return expDirectores;
    }

    public String getExpPeliculas() {
        return expPeliculas;
    }
   
    public String getNameOfFolder() {
        return nameOfFolder;
    }

    public String getNameOfFileFilms() {
        return nameOfFileFilms;
    }

    public String getNameOfFileActors() {
        return nameOfFileActors;
    }

    public String getNameOfFileDirector() {
        return nameOfFileDirector;
    }

    public String[] getExtensiones() {
        return extensiones;
    }

    public String[] getCAMPOS_PELICULA() {
        return CAMPOS_PELICULA;
    }

    public String[] getCAMPOS_DIRECTOR() {
        return CAMPOS_DIRECTOR;
    }

    public String[] getCAMPOS_ACTOR() {
        return CAMPOS_ACTOR;
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public Collection<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Collection<Pelicula> peliculas) {
        this.peliculas = (List)peliculas;
    }

    public List<Director> getDirectores() {
        return (List)directores;
    }

    public void setDirectores(Collection<Director> directores) {
        this.directores = (List)directores;
    }

    public Collection<Actor> getActores() {
        return actores;
    }

    public void setActores(Collection<Actor> actores) {
        this.actores = (List) actores;
    }

    /**
     * 
     * @param _titulo
     * @return null sino se encuentra ese título de película en la colección.
     */
    Pelicula getUnaPeliculaPorTitulo(String _titulo) {
        Pelicula peli = null;
        for(Pelicula p: this.peliculas){
            if(p.getTitulo().equalsIgnoreCase(_titulo)){
                peli = p;
            }
        }
        return peli;
    }
    
    /**
     * 
     * @param _nombre
     * @return null sino se encuentra ese nombre de director en la colección.
     */
    Director getUnDirectorPorNombre(String _nombre){
        Director dir = null;
        for(Director d: this.directores){
            if(d.getNombre().equalsIgnoreCase(_nombre)){
                dir = d;
            }
        }
        return dir;
    }
    
    /**
     * 
     * @param _nombre
     * @return null sino se encuentra ese nombre de actor en la colección.
     */
    Actor getUnActorPorNombre(String _nombre){
        Actor act = null;
        for(Actor a: this.actores){
            if(a.getNombre().equalsIgnoreCase(_nombre)){
                act = a;
            }
        }
        return act;
    }
    
}//End Class
