/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Model;

/**
 *
 * @author pgina37
 */
public class Controller {

    Model m = new Model();
    
    public void loadFiles() {
        m.loadFiles();
    }

    public void salida() {
        m.saveFiles();
    }

    public boolean altaPelicula(String[] nuevo) 
    {
        boolean res;
        try {
            m.guardarPeliculaEnModelo(nuevo);
           // m.guardarDirectores(nuevo);
            res = true;
        }catch (NumberFormatException  exc ){
            res = false;
            throw exc;
        }
        return res;
    }
    
    /**
     * 
     * @param cad
     * @return 
     */
    public String[] getDatosPelicula(String cad) {
        return (m.getDatosPelicula(cad));
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

    public void exportarDirectores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void exportarPeliculas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
