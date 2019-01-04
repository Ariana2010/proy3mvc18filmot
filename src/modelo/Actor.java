/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Actor implements Serializable{

    /*1*/ private String nombre;
    /*2*/ private LocalDate fechaNac;
    /*3*/ private String nacionalidad;
    /*4*/ private int debut; //año de debut.
    /*5*/ private Collection<String> _pelisAct;
    //DELIMITADORES
    private final static String SEPARADOR_CAMPOS = "#";
    private final static String SEPARADOR_COLL = "\t";
    private final String SEPARADOR_COMA = ",";
    
    private final static String DEFAULT_DATE = "2018-12-31";
    private final static int NUMERO_CAMPOS = 5;

       
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

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public Collection<String> getPelisAct() {
        return _pelisAct;
    }

    public void setPelisAct(Collection<String> _pelisAct) {
        this._pelisAct = _pelisAct;
    }

    public String getSEPARADOR_COMA() {
        return SEPARADOR_COMA;
    }
    
    public static String getSEPARADOR_CAMPOS() {
        return SEPARADOR_CAMPOS;
    }

    public static String getSEPARADOR_COLL() {
        return SEPARADOR_COLL;
    }

    /**
     * Contructor por defecto, asigna la fecha por defecto 31/12/2018 en formato ISO_LOCAL_DATE.
     * Por defecto: 2018-12-31
     */
    public Actor() {
        this.nombre = "";
        this.fechaNac = LocalDate.parse(Actor.DEFAULT_DATE);
        this.nacionalidad = "Desconocida";
        this.debut = 0;
        this._pelisAct = Arrays.asList("Sin títulos conocidos");
    }

    /**
     * Constructor con paramétros.
     * @param nombre
     * @param fechaNac
     * @param nacionalidad
     * @param debut
     * @param _pelisAct 
     */
    public Actor(String nombre, LocalDate fechaNac, String nacionalidad, int debut, Collection<String> _pelisAct) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.nacionalidad = nacionalidad;
        this.debut = debut;
        this._pelisAct = _pelisAct;
    }
    
    public Actor(String _name,String _tPeli) {
        this.nombre = _name;
        this.fechaNac = LocalDate.parse(Actor.DEFAULT_DATE);
        this.nacionalidad = "Desconocida";
        this.debut = 0;
        this._pelisAct = Arrays.asList(_tPeli);
    }
    /**
     * Método factoría para usar con el archivo de texto.
     * @param lineaA
     * @return 
     */
    public static Actor instanceActorFromString_facMet(String lineaA ){
        Actor unActor ;
        String[] separado = lineaA.split(Director.getSEPARADOR_CAMPOS(),5);
        
        if(separado.length < Actor.NUMERO_CAMPOS){
            //return null;
            System.err.println("CLASS ACTOR: ERROR: Hay menos campos de los esperados");
        }
        if(separado[0].isEmpty()){
            return null;
            //System.out.println("nombre actor :"+separado[0]);
        }else{
            unActor = new Actor();
            unActor.setNombre(separado[0]);
        }
        if(!separado[1].isEmpty()){
            unActor.setFechaNac(LocalDate.parse(separado[1]));
        } //de lo contrario conserva la fecha por defecto.
        if(!separado[2].isEmpty()){
            unActor.setNacionalidad(separado[2]);
        }
        if(!separado[3].isEmpty()){
            unActor.setDebut(Integer.parseInt(separado[3]));
        }
        /* Atributo número 5 es una coleccion de nombres de películas: */
        if(!separado[4].isEmpty()){
            String[] t=separado[4].split(Director.getSEPARADOR_COLL());
            List<String> man = Arrays.asList(t);
            unActor.setPelisAct(man);
        }
        System.out.println("factoriaActor "+unActor);
    return unActor;
    }
    
    /**
     * 
     * @return 
     */
    public String[] getCampos(){
        String[] dxc = new String[Actor.NUMERO_CAMPOS];
        int i = 0;
        dxc[i++]=this.nombre;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        dxc[i++]=this.fechaNac.format(formater);
        dxc[i++]=this.nacionalidad;
        dxc[i++]=String.valueOf(this.debut);
        dxc[i++]=this.getCamposCol(this._pelisAct,this.SEPARADOR_COMA);
        
        return dxc;
    }
    
    private String getCamposCol(Collection<String> coll,String sep) {
        if(coll.size() == 1) {
            String n = null;
            for(String c:coll){
                n = c;
            }
        return n; 
        }else{
            StringBuilder nm = new StringBuilder();
            coll.stream().map((c) -> {
                nm.append(c);
                return c;
            }).forEachOrdered((_item) -> {
                nm.append(sep);
            });
            return nm.substring(0, nm.length()-1);
        }
    }
    
        void addPelisAct(String _film) {
        //agregar a la coleccion de peliculas del director este título sino esta.
        boolean exist=false;
        for(String pd: this._pelisAct){
            if(pd.equalsIgnoreCase(_film)){
                exist=true;
            }
        }if(!exist){
            this._pelisAct.add(_film);
        }
    }

    @Override
    public String toString() {
        return "Actor{" + "nombre=" + nombre + ", fechaNac=" + fechaNac + '}';
    }
        
    
        
        
}//End Class
