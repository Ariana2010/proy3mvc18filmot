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
import static java.lang.System.err;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Catica
 */
public class Model {

    Filmoteca fmt = new Filmoteca();
    
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
    
    //**************************************************************************
    public void loadFiles() {
    
    //comprobar si existen los ficheros .bin correspondientes
    Path rutaFileBinFilms = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileFilms(),fmt.getExtensiones()[0]);
    Path rutaFileBinDirectors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileDirector(),fmt.getExtensiones()[0]);
    Path rutaFileBinActors = crearRuta(fmt.getNameOfFolder(),fmt.getNameOfFileActors(),fmt.getExtensiones()[0]);
    //===============================================//    
    //**************** PELICULAS ********************//
    //===============================================//
    List<Pelicula> tmpPelis = new ArrayList<>();
    //** Si existe leer fichero .bin
    if (Files.exists(rutaFileBinFilms)){  //Si existe leer fichero .bin
        //System.err.println("El archivo " + rutaFileBinFilms + "existe."); 
        //tmpPelis = new ArrayList<>();
        try {
        FileInputStream fis = new FileInputStream(rutaFileBinFilms.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
            try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                tmpPelis = (ArrayList<Pelicula>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No fue posible leer el archivo: "+rutaFileBinFilms);
            System.err.println(ex.toString());
        }
        System.out.println("Peliculas.bin : ");
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
    //===============================================//
    //*************** DIRECTORES ********************//
    //===============================================//
    List<Director> tmpDirectores = new ArrayList<>();
    if (Files.exists(rutaFileBinDirectors)){
               // System.err.println("El archivo " + rutaFileBinDirectors + " existe."); 
        try {
        FileInputStream fis = new FileInputStream(rutaFileBinDirectors.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
            try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                tmpDirectores = (List<Director>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No fue posible leer el archivo"+rutaFileBinDirectors);
            System.out.println(ex.toString());
        }
        System.out.println("Directores.bin :");        
        for(Director x:tmpDirectores){
            System.out.println(x);
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
  /**///                  System.out.println(lineaD);
                    x = Director.instanceDirectorFromString_facMet(lineaD);
                    if (x!=null) 
                        tmpDirectores.add(x);
/**///                    System.out.println("director x :" +x);
                }
            }
        //fmt.setDirectores(tmpDirectores); //añadiendolo a la colección.
        }
    }
    fmt.setDirectores(tmpDirectores);
    //===============================================//
    //***************** ACTORES *********************//
    //===============================================//
    List<Actor> tmpActores = new ArrayList<>();
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
        System.out.println("Actores.bin :");        
        for (Actor x : tmpActores) {
            System.out.println(x);
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
            //tmpActores = new ArrayList<>();
            Actor x;
            for (String lineA: archAct){  
                if(lineA.isEmpty()==false){
/**///                System.out.println(lineA);
                    x = Actor.instanceActorFromString_facMet(lineA);
                    if (x!=null)
                        tmpActores.add(x);
/**///                   System.out.println("actor x :" +x);
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
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(tmpA);
            }
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

    //**************************************************************************
    public void guardarPeliculaEnModelo (String[] nuevo) 
            throws NumberFormatException  {
        List<Pelicula> tmpPelis;
        Pelicula x;
        x = Pelicula.factoryMethodPeliculaXcampos(nuevo);
        tmpPelis = (List) fmt.getPeliculas();
        tmpPelis.add(x);
        fmt.setPeliculas(tmpPelis);
        //hasta ahi la pelicula se ha agregado al modelo; queda:
        //1. actualizar la lista de peliculas del director.
        //2. actualizar la lista de peliculas del actor.
        String tPeli = nuevo[0];    //Campo 1 de la clase Pelicula.
        String listaDir = nuevo[4];  //Campo 5 de la clase Pelicula.
        String listaAct = nuevo[8];  //Campo 9 de la clase Pelicula.
        
        List<Director> dir = (List)fmt.getDirectores();
        if(!listaDir.isEmpty()){
            String[] directores = listaDir.split(Director.getSEPARADOR_COLL());
            for(String name : directores){
                if(!name.isEmpty()){
                    boolean exist = false;
                    for (Director d : dir){
                        if(d.getNombre().equalsIgnoreCase(name)){
                            d.addPelisDir(tPeli);
                            exist = true;
                            System.out.println(d);
                        }
                    }
                    if(!exist){ //el director no existe a crearlo.
                        Director cnd = new Director(name,tPeli);
                        dir.add(cnd);
                        System.out.println(cnd);
                    }
                }
            }
        }
        fmt.setDirectores(dir);
        //Se ha realizado 1.actualizar lista peliculas director.
        //List<Actor> act = (List)fmt.getActores();
        
        if(!listaAct.isEmpty()){
            String[] actores = listaAct.split(Director.getSEPARADOR_COLL());
            for(String name : actores){
                if(!name.isEmpty()){
                    boolean exist = false;
                    //for (Actor d : act){
                    for (Actor d : fmt.getActores()){
                        if(d.getNombre().equalsIgnoreCase(name)){
                            d.addPelisAct(tPeli);
                            exist = true;
                            System.out.println(d);
                        }
                    }
                    if(!exist){
                        Actor cna = new Actor(name,tPeli);
                        try{
                        fmt.getActores().add(cna);}catch(UnsupportedOperationException e){
                            System.err.println("ERROR: "+e);
                        }
                        System.out.println(cna);
                    }
                }    
            }
        }
        //Se ha realizado 2.actualizar lista peliculas actor.
        //fmt.setActores(act);
        
    }

    /**
     * Falta comprobar que efectivamente realiza los cambios.
     * @param _titulo
     * @param _modificar
     * @param _nuevoValor 
     */
    public void actualizarDatosPelicula(String _titulo,String[] _modificar,String[] _nuevoValor){
        Pelicula peli = fmt.getUnaPeliculaPorTitulo(_titulo);
 /**/       System.out.println(peli);
        int i=0;
        for(String modif:_modificar){
            if(modif.equalsIgnoreCase("año")){
                if(!_nuevoValor[i].isEmpty()){
                    peli.setYear(Integer.parseInt(_nuevoValor[i]));
                    i++;
                }
                else
                    i++;
            }
            if(modif.equalsIgnoreCase("duración")){
                if(!_nuevoValor[i].isEmpty()){
                    peli.setDuracion(Integer.parseInt(_nuevoValor[i]));
                    i++;
                }
                else
                    i++;
            }
            if(modif.equalsIgnoreCase("país")){
                if(!_nuevoValor[i].isEmpty()){
                    peli.setPais(_nuevoValor[i]);
                    i++;
                }
                else
                    i++;
            }
            if(modif.equalsIgnoreCase("guion")){
                if(!_nuevoValor[i].isEmpty()){
                    peli.setGuion(_nuevoValor[i]);
                    i++;
                }
                else
                    i++;
            }
            if(modif.equalsIgnoreCase("música")) {
                if(_nuevoValor[i].isEmpty()){i++;}
                else{
                    peli.setMusica(_nuevoValor[i]);
                    i++;
                }
            }
            if(modif.equalsIgnoreCase("fotografía")) {
                if(_nuevoValor[i].isEmpty()){i++;}
                else{
                    peli.setFotografia(_nuevoValor[i]);
                    i++;
                }
            }
            if(modif.equalsIgnoreCase("productora")) {
                if(_nuevoValor[i].isEmpty()){i++;}
                else{
                    peli.setProductora(_nuevoValor[i]);
                    i++;
                }
            }
            if(modif.equalsIgnoreCase("género")) {
                if(_nuevoValor[i].isEmpty()){i++;}
                else{
                    peli.setGenero(_nuevoValor[i]);
                    i++;
                }
            }
            if(modif.equalsIgnoreCase("sinopsis")) {
                if(_nuevoValor[i].isEmpty()){i++;}
                else{
                    peli.setSinopsis(_nuevoValor[i]);
                    i++;
                }
            }
        }
        System.out.println(peli);
    }
    
    /**
     * 
     * @param cad
     * @return 
     */
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
   
   /* public void actualizarColeccionTitulosDirector(String _film, String dir, boolean b) {
        Collection<Director> tmpDir = fmt.getDirectores();
        boolean set=false;
        for( Director d : tmpDir){
            if (d.getNombre().equals(dir)){
                d.addPelisDir(_film); //agregar ese titulo a la coleccion de pelis del director
                set = true;
            }else{//el director no esta en la coleccion de directores,crear un objeto Director y agregarlo
                Director newDir = new Director(dir,_film);
                tmpDir.add(newDir);
                set = true;
            }
        }
    }*/

    public boolean buscarEnColecciones(String _busca, String _donde) 
    {   
    switch (_donde){
        case Filmoteca.PELICULA:
            if (fmt.getPeliculas().stream().anyMatch((x) -> (x.getTitulo().equalsIgnoreCase(_busca)))) {
                return true;
            }
            return false;
            //break;
        case Filmoteca.DIRECTOR:
            if (fmt.getDirectores().stream().anyMatch((x) -> (x.getNombre().equalsIgnoreCase(_busca)))) {
                return true;
            }
            return false;
            //break;
        case Filmoteca.ACTOR:
            boolean ret = false;
            for(Actor x : fmt.getActores()){
                if(x.getNombre().equalsIgnoreCase(_busca)){
                    System.out.println(x.getNombre());
                    ret =true;
                }
            }
        //    fmt.getActores().forEach((x) -> {
        //        System.out.println("\t"+x.getNombre());    });
        //    System.out.println(ret);
            return ret;
            //break;
        default:
            System.err.println("ERROR: MÉTODO del modelo: buscarEnColecciones: no debería estar aquí.");
            return false;
        }
    }

    public void eliminarDelModelo(String _nombre, String _eliminarDe) {
    switch (_eliminarDe){
        case Filmoteca.PELICULA:
            String nombrePeli = new String();
            List<String> directoresPeli = new ArrayList<>();
            List<String> actoresPeli = new ArrayList<>();
            for (Pelicula x:fmt.getPeliculas()){
                if (x.getTitulo().equalsIgnoreCase(_nombre)){
                    nombrePeli = x.getTitulo();
                    directoresPeli = (List)x.getDireccion();
                    actoresPeli = (List)x.getReparto();
                }
            }
            fmt.getPeliculas().removeIf(p -> p.getTitulo().equalsIgnoreCase(_nombre));
            //Eliminando de la lista de peliculas de los directores
            List<Director> listDir =  fmt.getDirectores();
            Iterator<Director> ind = listDir.listIterator();
            while(ind.hasNext()){
                Director dir = ind.next();
                String nombreDir = dir.getNombre();
                Iterator<String> li = dir.getPelisDir().iterator();
                for(String x : directoresPeli){
                    if(nombreDir.equalsIgnoreCase(x)) {
                        while(li.hasNext()){
                            if (li.next().equals(nombrePeli)){
                                li.remove();
                            }
                        }
                    }//else se pasa al siguiente director  
                }
            }
            //Eliminando de la lista de peliculas de los actores
            List<Actor> listAct = (List)fmt.getActores();
            for(String xp : actoresPeli){
                listAct.stream().filter((act) -> (act.getNombre().equals(xp))).forEachOrdered((act) -> {
                    act.getPelisAct().removeIf(xn -> xn.equalsIgnoreCase(_nombre));
        });
            }
            /*
            for(String xp:actoresPeli){
                for(Actor act : listAct){
                    if (act.getNombre().equals(xp)){
                        act.getPelisAct().removeIf(xn -> xn.equalsIgnoreCase(_nombre));
                    }
                }
            }
             */
            break;
        case Filmoteca.DIRECTOR:
            fmt.getDirectores().removeIf(d -> d.getNombre().equalsIgnoreCase(_nombre));
            break;
        case Filmoteca.ACTOR:
            fmt.getActores().removeIf(a -> a.getNombre().equalsIgnoreCase(_nombre));
            break;
        default:
            System.err.println("ERROR: MODEL.JAVA: eliminarDelModelo(): no debería estar aquí.");
            System.exit(1);
        }    
    }

    public String getPeliculasActor(String cad) {
        String datos = "Sin peliculas conocidas";
        for(Actor act: fmt.getActores()){
            if(act.getNombre().equalsIgnoreCase(cad)){
                if(act.getPelisAct().isEmpty()){
                    return datos;
                }else{
                    StringBuilder pelis = new StringBuilder();
                    act.getPelisAct().stream().map((p) -> {
                        pelis.append(p);
                        return p;
                    }).forEachOrdered((_item) -> {
                        pelis.append(Actor.getSEPARADOR_COMA());
                    });
                    /*
                    StringBuilder pelis = new StringBuilder();
                    for(String p:act.getPelisAct()){
                        pelis.append(p);
                        pelis.append(Actor.getSEPARADOR_COMA());
                    }
                     */
                    datos = pelis.substring(0, pelis.length()-1);
                } 
            }
        }
        return datos;
    }

    public void sortBy(String _opcion) {
        Comparator<Pelicula> p;
        Comparator<Actor> a;
        Comparator<Director> d;
        switch (_opcion){
            case Filmoteca.PELICULA:
                p = new ComparadorTitulo();
                Collections.sort((List)fmt.getPeliculas(), p);
                break;
            case Filmoteca.DIRECTOR:

                break;
            case Filmoteca.ACTOR:

                break;
            default:
                err.println("\n\n ERROR \n\n: Model: sortBy(): ¿Por qué entro aquí?");
        }
    }

    public String[][] getFilmsOnTableWithFormat() {
        String[][] tablaP ; String cadAux;
        List <Pelicula> tmp = (ArrayList) fmt.getPeliculas();
        tablaP = new String[1+tmp.size()][5];
        
        tablaP[0][0] = String.format("%-35s","TITULO");
        tablaP[0][1] = String.format("%6s","AÑO");
        tablaP[0][2] = String.format("%10s","DURACION");
        tablaP[0][3] = String.format("%-30s","PAIS");
        tablaP[0][4] = String.format("%-20s","GENERO");
        
        for(int fila = 1; fila < tmp.size()+1; fila++){
            int aux = fila - 1;
            int col = 0;
            cadAux = tmp.get(aux).getTitulo();
            tablaP[fila][col++] = String.format("%-35s",((cadAux.length() > 35 )? cadAux.substring(0,35): cadAux));
            tablaP[fila][col++] = String.format("%6s",String.valueOf(tmp.get(aux).getYear()));
            tablaP[fila][col++] = String.format("%6s min",String.valueOf(tmp.get(aux).getDuracion()));
            cadAux = tmp.get(aux).getPais();
            tablaP[fila][col++] = String.format("%30s",((cadAux.length() > 30 )? cadAux.substring(0,30): cadAux));
            cadAux = tmp.get(aux).getGenero();
            tablaP[fila][col++] = String.format("%20s",((cadAux.length() > 20 )? cadAux.substring(0,20): cadAux));
        }
        
        return tablaP;
    }
   
    
}//End Class.