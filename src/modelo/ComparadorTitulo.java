/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Comparator;

/**
 *
 * @author Catica
 */
public class ComparadorTitulo implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula t, Pelicula t1) {
        String titulo1 = t.getTitulo();
        String titulo2 = t1.getTitulo();
        
        return titulo1.compareToIgnoreCase(titulo2);
    }

    
    
}
