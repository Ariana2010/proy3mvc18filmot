/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Model {

    Filmoteca fmt = new Filmoteca();
    
    public void loadFiles() {
    
    List<Pelicula> tmpPelis = new ArrayList<>();
    List<Director> tmpDirectores = new ArrayList<>();;
    List<Actor> tmpActores = new ArrayList<>();;
    //comprobar si existen los ficheros .bin correspondientes
    Path rutaFileBinFilms = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileFilms(),fmt.getExtensiones()[0]);
    Path rutaFileBinDirectors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileDirector(),fmt.getExtensiones()[0]);
    Path rutaFileBinActors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileActors(),fmt.getExtensiones()[0]);
    //*************** PELICULAS ********************//
    //** Si existe leer fichero .bin
    if (Files.exists(rutaFileBinFilms)){  //Si existe leer fichero .bin
        //System.err.println("El archivo " + rutaFileBinFilms + "existe."); 
        //tmpPelis = new ArrayList<>();
        try {
        FileInputStream fis = new FileInputStream(rutaFileBinFilms.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        tmpPelis = (ArrayList<Pelicula>) ois.readObject();
        ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No fue posible leer el archivo: "+rutaFileBinFilms);
            System.err.println(ex.toString());
        }
        for(Pelicula p: tmpPelis){
            System.out.println(p);
        }
        //** Sino leer fichero .txt
    }else { 
        Path rutaFileTxt = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileFilms(),fmt.getExtensiones()[1]);
        //1A.Comprobar que el fichero existe.
        if (!Files.exists(rutaFileTxt)){  //Si no existe salir del programa.
            System.err.println("El archivo " + rutaFileTxt + "no existe."); 
            System.exit(1);
        }
        //2.Leer cada línea del fichero en la var archPelis, que es un List de String, (List es Collection)
        List<String> archPelis;
        try{
            archPelis = Files.readAllLines(rutaFileTxt,Charset.forName("UTF-8"));
            //System.out.printf("Se han leido %d líneas.",archPelis.size());
            //System.out.printf("archPelis = %s\n%s\n%s", archPelis.get(0),archPelis.get(1),archPelis.get(5));
        }catch(IOException e){
            System.err.printf("No se pudo leer el fichero: %s\n ERROR: %s", rutaFileTxt, e);
            archPelis = null;
        }
        if(archPelis != null && !archPelis.isEmpty()){ //sin archPelis no es null y no está vacío.
        //3B.inicializar la collección temporal ya esta hecho arriba
        //tmpPelis = new ArrayList<Pelicula>();
        Pelicula x;
        for (String lineaP: archPelis){  //Se lee una línea cada vez, así tratamos una línea a la vez.
            //Vamos a tratar cada línea a través de un método factoría de la clase correspondiente
            //que nos retorne el elemento apropiado y bien formado y ese lo añadimos a la colección.
            if(lineaP.isEmpty()==false){
                //si la línea no está vacía se llama al método factoría.
                //System.out.println(lineaP);
                x = Pelicula.factoryMethodPeliculaFromString(lineaP);
                if (x!=null)
                    tmpPelis.add(x);
            }
        }
        //fmt.setPeliculas(tmpPelis); //añadiendolo a la colección.
        }
    }
    fmt.setPeliculas(tmpPelis); 
    //*************** DIRECTORES ********************//
    if (Files.exists(rutaFileBinDirectors)){
               // System.err.println("El archivo " + rutaFileBinDirectors + " existe."); 
        try {
        FileInputStream fis = new FileInputStream(rutaFileBinDirectors.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        tmpDirectores = ((List<Director>) ois.readObject());
        ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No fue posible leer el archivo"+rutaFileBinDirectors);
            System.out.println(ex.toString());
        }
                
    }else{
        Path rutaFileTxt = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileDirector(),fmt.getExtensiones()[1]);
        //1A.Comprobar que el fichero existe.
        if (!Files.exists(rutaFileTxt)){  //Si no existe salir del programa.
            System.err.println("El archivo " + rutaFileTxt + "no existe."); 
            System.exit(1);
        }
        //2.Leer cada línea del fichero en la var archPelis, que es un List de String, (List es Collection)
        List<String> archDir;
        try{
            archDir = Files.readAllLines(rutaFileTxt,Charset.forName("UTF-8"));
            //System.out.printf("Se han leido %d líneas.",archPelis.size());
            //System.out.printf("archPelis = %s\n%s\n%s", archPelis.get(0),archPelis.get(1),archPelis.get(5));
        }catch(IOException e){
            System.err.printf("No se pudo leer el fichero: %s\n ERROR: %s", rutaFileTxt, e);
            archDir = null;
        }
        if(archDir != null && !archDir.isEmpty()){ //sin archDir no es null y no está vacío.
        //3B.inicializar la collección temporal
        //tmpDirectores = new ArrayList<>();
        Director x;
        for (String lineaD: archDir){ 
            if(lineaD.isEmpty()==false){
                //System.out.println(lineaD);
                x = Director.instanceDirectorFromString_facMet(lineaD);
                if (x!=null)
                    tmpDirectores.add(x);
            }
        }
        //fmt.setDirectores(tmpDirectores); //añadiendolo a la colección.
        }
    }
    fmt.setDirectores(tmpDirectores);
    //*************** ACTORES ********************//
    if (Files.exists(rutaFileBinActors)){
               //     System.err.println("El archivo " + rutaFileBinActors + " existe."); 
        try {
        FileInputStream fis = new FileInputStream(rutaFileBinActors.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        tmpActores = (List<Actor>) ois.readObject();
        ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No fue posible leer el archivo"+rutaFileBinActors);
            System.out.println(ex.toString());
        }
    }else{
        Path rutaFileTxt = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileActors(),fmt.getExtensiones()[1]);
        //1A.Comprobar que el fichero existe.
        if (!Files.exists(rutaFileTxt)){  //Si no existe salir del programa.
            System.err.println("El archivo " + rutaFileTxt + "no existe."); 
            System.exit(1);
        }
        //2.Leer cada línea del fichero en la var archPelis, que es un List de String, (List es Collection)
        List<String> archAct;
        try{
            archAct = Files.readAllLines(rutaFileTxt,Charset.forName("UTF-8"));
            //System.out.printf("Se han leido %d líneas.",archPelis.size());
            //System.out.printf("archPelis = %s\n%s\n%s", archPelis.get(0),archPelis.get(1),archPelis.get(5));
        }catch(IOException e){
            System.err.printf("No se pudo leer el fichero: %s\n ERROR: %s", rutaFileTxt, e);
            archAct = null;
        }
        if(archAct != null && !archAct.isEmpty()){ //sin archAct no es null y no está vacío.
        //3B.inicializar la collección temporal
        tmpActores = new ArrayList<>();
        Actor x;
        for (String lineA: archAct){  
            if(lineA.isEmpty()==false){
                //System.out.println(lineA);
                x = Actor.instanceActorFromString_facMet(lineA);
                if (x!=null)
                    tmpActores.add(x);
            }
        }
        //fmt.setActores(tmpActores); //añadiendolo a la colección.
        }
    }
    fmt.setActores(tmpActores);
    }//End loadFiles();

    public void saveFiles() {
        Path rutaFilms = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileFilms(),fmt.getExtensiones()[0]);
        Path rutaDirectors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileDirector(),fmt.getExtensiones()[0]);
        Path rutaActors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileActors(),fmt.getExtensiones()[0]);
        List<Pelicula> tmpP = (List)fmt.getPeliculas();
        List<Director> tmpD = (List)fmt.getDirectores();
        List<Actor> tmpA = (List)fmt.getActores();
       
        try{
            FileOutputStream fos = new FileOutputStream(rutaFilms.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(tmpP);
            oos.close();
        }catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo");
            System.out.println(ex.toString());
        }
        
        try{
            FileOutputStream fos = new FileOutputStream(rutaDirectors.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(tmpD);
            oos.close();
        }catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo");
            System.out.println(ex.toString());
        }
        
        try{
            FileOutputStream fos = new FileOutputStream(rutaActors.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(tmpA);
            oos.close();
        }catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo");
            System.out.println(ex.toString());
        }
    }//End saveFiles()
    
    private Path crearRuta(String nombreCarpeta, String nombreFichero, String ext){
        Path p;
        String file = nombreFichero.concat(ext);
        if (System.getProperty("os.name").startsWith("Windows")) {
            // includes: Windows 2000,  Windows 95, Windows 98, Windows NT, Windows Vista, Windows XP
            p = Rutas.pathToFileInFolderOnDesktop(nombreCarpeta, file);
        } else { /* return null; significa que no esta existe la clave "Windows",
                  más no significa que este en Linux, sin embargo, como esta app
                  solo correra en Windows o Linux-ubuntu nos vale.*/
            p = FileSystems.getDefault().getPath(System.getProperty("user.home"),
                                         File.separator + "Escritorio"
                                       + File.separator + nombreCarpeta
                                       + File.separator + file);
        } 
            return p;
    }

    public void guardarPeliculaEnModelo (String[] nuevo) 
            throws NumberFormatException  {
        List<Pelicula> tmpPelis;
        Pelicula x;
        x = Pelicula.factoryMethodPeliculaXcampos(nuevo);
        tmpPelis = (List) fmt.getPeliculas();
        tmpPelis.add(x);
        fmt.setPeliculas(tmpPelis);
        
        List<Director> dir = (List)fmt.getDirectores();
        String[] directores = nuevo[4].split(Director.getSEPARADOR_COLL());
        for(String name : directores){
            boolean exist = false;
            for (Director d : dir){
                if(d.getNombre().equalsIgnoreCase(name)){
                    exist = true;
                }
            }
            if(!exist){
                Director cnd = new Director(name);
                dir.add(cnd);
            }
        }
        fmt.setDirectores(dir);
        
        List<Actor> act = (List)fmt.getActores();
        String[] actores = nuevo[9].split(Director.getSEPARADOR_COLL());
        for(String name : actores){
            boolean exist = false;
            for (Actor d : act){
                if(d.getNombre().equalsIgnoreCase(name)){
                    exist = true;
                }
            }
            if(!exist){
                Actor cna = new Actor(name);
                act.add(cna);
            }
        }
        fmt.setActores(act);
        
    }

    public String[] getDatosPelicula(String cad) {
        String [] p = new String[this.getCAMPOS_PELICULA().length];
        List<Pelicula> tmpPelis;
        tmpPelis = (List) fmt.getPeliculas();
        for(Pelicula x : tmpPelis){
            if (x.titulo.equalsIgnoreCase(cad)){
                p = x.getCampos(); 
                break;
            }
        }
        return p;
    }

    public void guardarDirectores(String[] nuevo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    //**************************************************************************
    
    public String[] getCAMPOS_PELICULA() {
        return fmt.getCAMPOS_PELICULA();
    }

    public String[] getCAMPOS_DIRECTOR() {
        return fmt.getCAMPOS_DIRECTOR();
    }

    public String[] getCAMPOS_ACTOR() {
        return fmt.getCAMPOS_ACTOR();
    }
    
}//End Class.