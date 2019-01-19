/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
        List<String> man = new ArrayList<>(Arrays.asList(t));
        unaPeli.setDireccion(man);
        /**********/
        unaPeli.setGuion(separado[5]);
        unaPeli.setMusica(separado[6]);
        unaPeli.setFotografia(separado[7]);
        /* Atributo número 9 es una coleccion: */
        t = separado[8].split(Pelicula.SEPARADOR_COLL);
        man = new ArrayList<>(Arrays.asList(t));
        unaPeli.setReparto(man);
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
        if (nuevo[0].isEmpty()){
            System.err.println("ERROR: no puede guardarse una película sin nombre.Terminando Programa.");
            System.exit(12);
        }else{
            unaPeli.setTitulo(nuevo[0]);
        }
        if (nuevo[1].isEmpty()){
            unaPeli.setYear(0);
        }else{
            unaPeli.setYear(Integer.parseInt(nuevo[1]));
        }
        if (nuevo[2].isEmpty()){
            unaPeli.setDuracion(0);
        }else{
            unaPeli.setDuracion(Integer.parseInt(nuevo[2]));
        }
        if (nuevo[3].isEmpty()){
            unaPeli.setPais("Desconocido");
        }else{
            unaPeli.setPais(nuevo[3]);
        }
        /* Atributo número 5 es una coleccion: */
        if(nuevo[4].isEmpty()){
            unaPeli.setDireccion(new ArrayList(Arrays.asList("Sin directores conocidos")));
        }else{
        String[] t=nuevo[4].split("\t");
        List<String> man = new ArrayList(Arrays.asList(t));
        unaPeli.setDireccion(man);
        }
        /**********/
        if(nuevo[5].isEmpty()){
           unaPeli.setGuion("Anónimo");
        }else{
            unaPeli.setGuion(nuevo[5]);
        }
        if(nuevo[6].isEmpty()){
            unaPeli.setMusica("Anónimo");
        }else{
            unaPeli.setMusica(nuevo[6]);
        }
        if(nuevo[7].isEmpty()){
            unaPeli.setFotografia("Anónimo");
        }else{
            unaPeli.setFotografia(nuevo[7]);
        }
        /* Atributo número 9 es una coleccion: */
        if(nuevo[8].isEmpty()){
            unaPeli.setReparto(new ArrayList(Arrays.asList("Sin reparto conocido")));
        }else{
            String[] t = nuevo[8].split("\t");
            unaPeli.setReparto(new ArrayList(Arrays.asList(t)));
        }
        //unaPeli.setReparto(Arrays.asList(nuevo[8]));
        /**********/
        if(nuevo[9].isEmpty()){
            unaPeli.setProductora("Desconocida");
        }else{
            unaPeli.setProductora(nuevo[9]);
        }
        if(nuevo[10].isEmpty()){
            unaPeli.setGenero("Sin clasificar");
        }else{
            unaPeli.setGenero(nuevo[10]);
        }
        if(nuevo[11].isEmpty()){
            unaPeli.setSinopsis(" ...");
        }else{
            unaPeli.setSinopsis(nuevo[11]);
        }
        return unaPeli;
    }   
    
    public String[] getCampos(){
        String[] pxc = new String[Pelicula.NUM_CAMPOS];
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
    
    /**
     * Retorna un String separado por "sep" que contiene cada uno de los Strings
     * que conforman la colección "coll"
     * @param coll
     * @param sep
     * @return 
     */
    private String getCamposCol(Collection<String> coll,String sep) {
        if(coll.isEmpty()) {
            String n = "coleccion vacía";
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
    @Override
    public String toString() {
        return "Pelicula{" + "Titulo=" + titulo + ", Año=" + year + ", País=" + pais + ", Guion=" + guion + ", Directores=" + direccion + '}';
    }
    
    public String descripcionHtml (){
        String[] des = this.getCampos();
        int i = 0;
        String resultado;
        resultado = String.format("<TR>"    //<tr> crea fila </tr>
                + "<TD>%s</TD>" // 1        //<td> crea columna </td>
                + "<TD>%s</TD>" // 2
                + "<TD>%s</TD>" // 3
                + "<TD>%s</TD>" // 4
                + "<TD>%s</TD>" // 5
                + "<TD>%s</TD>" // 6
                + "<TD>%s</TD>" // 7
                + "<TD>%s</TD>" // 8
                + "<TD>%s</TD>" // 9
                + "<TD>%s</TD>" // 10
                + "<TD>%s</TD>" // 11
                + "<TD>%s</TD>" // 12
                + "</TR>",  //Cierra la fila
                des[i++], //0 
                des[i++], //1
                des[i++], //2
                des[i++], //3
                des[i++], //4
                des[i++], //5
                des[i++], //6
                des[i++], //7
                des[i++], //8
                des[i++], //9
                des[i++], //10
                des[i++]  //11
                );
        return resultado;
    }
      
}//End Class
