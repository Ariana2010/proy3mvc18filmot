/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Director implements Serializable{

        
    /*1*/ private String nombre;
    /*2*/ private LocalDate fechaNac;
    /*3*/ private String nacionalidad;
    /*4*/ private String ocupacion;
    /*5*/ private Collection<String> pelisDirector;
    //Valor por defecto para la fecha en formato ISO-LOCAL-DATE.
    private final static String DEFAULT_DATE = "2018-12-31";
    //DELIMITADORES
    private final static String SEPARADOR_CAMPOS = "#";
    private final static String SEPARADOR_COLL = "\t";
    private final String SEPARADOR_COMA = ",";
    //OTROS
    static final int NUMERO_CAMPOS = 5;
    public static final int NUMCAMPOSMODIF = 3;
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Collection<String> getPelisDirector() {
        return pelisDirector;
    }

    public void setPelisDirector(Collection<String> pelisDirector) {
        this.pelisDirector = (List)pelisDirector;
    }

    public static String getSEPARADOR_CAMPOS() {
        return SEPARADOR_CAMPOS;
    }

    public static String getSEPARADOR_COLL() {
        return SEPARADOR_COLL;
    }

    public String getSEPARADOR_COMA() {
        return SEPARADOR_COMA;
    }
    /**
     * Método factoría para usar con el archivo de texto.
     * @param lineaD
     * @return 
     */
    public static Director instanceDirectorFromString_facMet(String lineaD ){
        
        String[] separado = lineaD.split(Director.getSEPARADOR_CAMPOS());
        Director unDir;
        if(separado[0].isEmpty()){
            return null;
            //Retorna null si la cadena del nombre es vacía.
        }else{
            unDir = new Director();
            unDir.setNombre(separado[0]);
            if(separado.length < Director.NUMERO_CAMPOS){
                //return unDir; //con los valores por defecto, pero con nombre.
                System.err.println("CLASS DIRECTOR: ERROR: Hay menos campos de los esperados");
            }else{
                unDir.setNombre(separado[0]);
                if(!separado[1].isEmpty()){
                    unDir.setFechaNac(LocalDate.parse(separado[1]));
                } //de lo contrario conserva la fecha por defecto.
                if(!separado[2].isEmpty()) {
                    unDir.setNacionalidad(separado[2]); 
                }
                if(!separado[3].isEmpty()) {
                    unDir.setOcupacion(separado[3]); 
                }
                /* Atributo número 5 es una coleccion de nombres de películas: */
                if(!separado[4].isEmpty()) {
                    String[] t=separado[4].split(Director.getSEPARADOR_COLL());
                    List<String> man = new ArrayList(Arrays.asList(t));
                    unDir.setPelisDirector(man);
                }
            }
        }
        //System.out.println("FactoriaDirector "+unDir);
    return unDir;
    }
    
    /**
     * 
     * @param nuevo
     * @return 
     */
    static Director instanceDirectorFromString_facMet(String[] _nuevo) {
        Director unDir = null;
        if(_nuevo[0].isEmpty()){
            return null;
            //Retorna null si la cadena del nombre es vacía.
        }else{
            if(_nuevo.length < Director.NUMERO_CAMPOS){
                //return unDir; //con los valores por defecto, pero con nombre.
                System.err.println("CLASS DIRECTOR: ERROR: Hay menos campos de los esperados");
            }else{
                unDir = new Director();
                unDir.setNombre(_nuevo[0]);
                unDir.setNombre(_nuevo[0]);
                if(!_nuevo[1].isEmpty()){
                    unDir.setFechaNac(LocalDate.parse(_nuevo[1]));
                } //de lo contrario conserva la fecha por defecto.
                if(!_nuevo[2].isEmpty()) {
                    unDir.setNacionalidad(_nuevo[2]); 
                }
                if(!_nuevo[3].isEmpty()) {
                    unDir.setOcupacion(_nuevo[3]); 
                }
                /* Atributo número 5 es una coleccion de nombres de películas: */
                if(!_nuevo[4].isEmpty()) {
                    String[] t=_nuevo[4].split(Director.getSEPARADOR_COLL());
                    List<String> man = new ArrayList(Arrays.asList(t));
                    unDir.setPelisDirector(man);
                }
            }
        }
        //System.out.println("FactoriaDirector "+unDir);
    return unDir;
    }
    
    /**
     * Constructor con paramétros.
     * @param nombre
     * @param fechaNac
     * @param nacionalidad
     * @param ocupacion
     * @param _pelisDir 
     */
    public Director(String nombre, LocalDate fechaNac, String nacionalidad, String ocupacion, Collection<String> _pelisDir) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.pelisDirector = (List)_pelisDir;
    }
    /**
     * Contructor por defecto, asigna la fecha por defecto 31/12/2018 en formato ISO_LOCAL_DATE '2011-12-03'.
     * Por defecto: 2018-12-31
     */
    public Director() {
        this.nombre = "";
        this.fechaNac = LocalDate.parse(Director.DEFAULT_DATE);
        this.nacionalidad = "Desconocida";
        this.ocupacion = "Director de cine";
        this.pelisDirector = new ArrayList<>(Arrays.asList("Sin títulos conocidos"));
    }
    
    public Director(String name,String peli) {
        this.nombre = name;
        this.fechaNac = LocalDate.parse(Director.DEFAULT_DATE);
        this.nacionalidad = "Desconocida";
        this.ocupacion = "Director de cine";
        this.pelisDirector = new ArrayList<>(Arrays.asList(peli));
    }
    /**
     * 
     * @return 
     */
    public String[] getCampos(){
        String[] dxc = new String[Director.NUMERO_CAMPOS];
        int i = 0;
        dxc[i++]=this.nombre;
        dxc[i++]=this.fechaNac.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")); 
        dxc[i++]=this.nacionalidad;
        dxc[i++]=this.ocupacion;
        dxc[i++]=this.getCamposCol(this.pelisDirector,this.SEPARADOR_COMA);
        
        return dxc;
    }
    
    private String getCamposCol(Collection<String> coll,String sep) {
        if(coll.size() == 1) {
            String campoColl = null;
            for(String c:coll){
                campoColl = c;
            }
        return campoColl; 
        }else{
            StringBuilder camposColl = new StringBuilder();
            coll.stream().map((c) -> {
                camposColl.append(c);
                return c;
            }).forEachOrdered((_item) -> {
                camposColl.append(sep);
            });
            return camposColl.substring(0, camposColl.length()-1);
        }
    }

    void addPelisDir(String _film) {
        //agregar a la coleccion de peliculas del director este título sino esta.
        boolean exist=false;
        for(String pd: this.pelisDirector){
            if(pd.equalsIgnoreCase(_film)){
                exist=true;
            }
        }if(!exist){
            this.pelisDirector.add(_film);
        }
    }

    @Override
    public String toString() {
        return "Director{" + "Nombre=" + nombre + ", Fecha nacimiento=" + fechaNac + ", Nacionalidad=" + nacionalidad + ", Ocupacion=" + ocupacion + ", \n\tPeliculas que ha dirigido=" + pelisDirector + '}';
    }
    
    public String descripcionEncolumnada(){
        String[] des = this.getCampos();
        String retDes = String.format("| %-35s | %-19s | %-20s | %-70s | %-70s |%n",
                            des[0],
                            des[1],
                            (des[2].length() < 20? des[2]:des[2].substring(0, 20)),
                            (des[3].length() < 70? des[3]:des[3].substring(0, 70)),
                            (des[4].length() < 70? des[4]:des[4].substring(0, 70)));
        return retDes;
    }
    
    
}
