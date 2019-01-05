/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Pelicula implements Serializable {

    
/*1*/     String titulo;
/*2*/     int year;
/*3*/     int duracion; //En minutos
/*4*/     String pais;
/*5*/     Collection<String> direccion; //Coleccion de nombres de los directores.
/*6*/     String guion;
/*7*/     String musica;
/*8*/     String fotografia;
/*9*/     Collection<String> reparto; //Coleccion de nombres de los actores.
/*10*/    String productora;
/*11*/    String genero;
/*12*/    String sinopsis;
//DELIMITADORES    
public final static String SEPARADOR_CAMPOS = "#";
public final static String SEPARADOR_COLL = "\t";
private final String SEPARADOR_COMA = ",";

static final int NUM_CAMPOS = 12;
public static final int NUMCAMPOSMODIF = 9;
//*******************************************************************/
/**
 * Método factoría
 * @param lineaP
 * String con formato delimitado por # y por '\t'.
 * @return 
 * Un objeto de la clase unaPeli.
 */
    public static Pelicula factoryMethodPeliculaFromString(String lineaP)
    {
        //Locale spanish = new Locale("es", "ES");
        Pelicula unaPeli = new Pelicula();
        //Los atributos 5 y 9 son colecciones.
        String[] separado = lineaP.split(Pelicula.SEPARADOR_CAMPOS);
        unaPeli.setTitulo(separado[0]);
        unaPeli.setYear(Integer.parseInt(separado[1]));
        unaPeli.setDuracion(Integer.parseInt(separado[2]));
        unaPeli.setPais(separado[3]);
        /* Atributo número 5 es una coleccion: */
        String[] t=separado[4].split(Pelicula.SEPARADOR_COLL);
        List<String> man = Arrays.asList(t);
        unaPeli.setDireccion(man);
        /**********/
        unaPeli.setGuion(separado[5]);
        unaPeli.setMusica(separado[6]);
        unaPeli.setFotografia(separado[7]);
        /* Atributo número 9 es una coleccion: */
        t = separado[8].split(Pelicula.SEPARADOR_COLL);
        unaPeli.setReparto(Arrays.asList(t));
        /**********/
        unaPeli.setProductora(separado[9]);
        unaPeli.setGenero(separado[10]);
        unaPeli.setSinopsis(separado[11]);
/*    
        unaPeli.getDireccion().forEach((g) -> {
            System.out.println("--" + g);
        });
        System.out.println("");
        for(String g:unaPeli.getRepartos()){
        System.out.println("**" + g);
        }
        System.out.println("");  
//*/
        return unaPeli;
    }
    
    
    public static Pelicula factoryMethodPeliculaXcampos(String[] nuevo) 
            throws NumberFormatException {
        Pelicula unaPeli = new Pelicula();
        //Los atributos 5 y 9 son colecciones.
        unaPeli.setTitulo(nuevo[0]);
        unaPeli.setYear(Integer.parseInt(nuevo[1]));
        unaPeli.setDuracion(Integer.parseInt(nuevo[2]));
        unaPeli.setPais(nuevo[3]);
        /* Atributo número 5 es una coleccion: */
        String[] t=nuevo[4].split("\t");
        List<String> man = Arrays.asList(t);
        //List<String> man = Arrays.asList(nuevo[4]);
        unaPeli.setDireccion(man);
        /**********/
        unaPeli.setGuion(nuevo[5]);
        unaPeli.setMusica(nuevo[6]);
        unaPeli.setFotografia(nuevo[7]);
        /* Atributo número 9 es una coleccion: */
        t = nuevo[8].split("\t");
        unaPeli.setReparto(Arrays.asList(t));
        //unaPeli.setReparto(Arrays.asList(nuevo[8]));
        /**********/
        unaPeli.setProductora(nuevo[9]);
        unaPeli.setGenero(nuevo[10]);
        unaPeli.setSinopsis(nuevo[11]);
        return unaPeli;
    }   
    
    public String[] getCampos(){
        String[] pxc = new String[this.NUM_CAMPOS];
        int i = 0;
        pxc[i++]=this.titulo;
        pxc[i++]=String.valueOf(this.year);
        pxc[i++]=String.valueOf(this.duracion);
        pxc[i++]=this.pais;
        pxc[i++]=this.getCamposCol(this.direccion,this.SEPARADOR_COMA);
        pxc[i++]=this.guion;
        pxc[i++]=this.musica;
        pxc[i++]=this.fotografia;
        pxc[i++]=this.getCamposCol(this.reparto,this.SEPARADOR_COMA);
        pxc[i++]=this.productora;
        pxc[i++]=this.genero;
        pxc[i++]=this.sinopsis;
        return pxc;
    }
//*******************************************************************/
// GETTERS AND SETTERS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Collection<String> getDireccion() {
        return direccion;
    }

    public void setDireccion(Collection<String> direccion) {
        this.direccion = direccion;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Collection<String> getReparto() {
        return reparto;
    }

    public void setReparto(Collection<String> reparto) {
        this.reparto = reparto;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    //*******************************************************************/

    private String getCamposCol(Collection<String> coll,String sep) {
        if(coll.size() == 1) {
            String n = null;
            for(String c:coll){
                n = c;
            }
        return n; 
        }else{
            StringBuilder nm = new StringBuilder();
            ///*
            coll.stream().map((c) -> {
                nm.append(c);
                return c;
            }).forEachOrdered((_item) -> {
                nm.append(sep);
            });
            //*/
            /*
             for(String c:coll){
                nm.append(c);
                nm.append(sep);
            }
             */
            return nm.substring(0, nm.length()-1);
        }
    }

    @Override
    public String toString() {
        return "Pelicula{" + "Titulo=" + titulo + ", Año=" + year + ", País=" + pais + ", Guión=" + guion + ", Directores=" + direccion + '}';
    }
      
}//End Class
