/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Comparator;

/**
 * Orden: año debut y nombre.
 * A igual año de debut, retornara el orden de comparar el nombre.
 * Sino retornara el orden de comparar el año de debut.
 * 
 * @author Catica
 */
public class ComparadorActorDebut implements Comparator<Actor>{
    //Orden: año debut y nombre.
    //A igual año de debut, retornara el orden de comparar el nombre.
    //Sino retornara el orden de comparar el año de debut.
    
    @Override
    public int compare(Actor o1, Actor o2) {
        
        if(Integer.compare(o1.getDebut(), o2.getDebut()) == 0)
            return o1.getNombre().compareToIgnoreCase(o2.getNombre());
        return Integer.compare(o1.getDebut(),o2.getDebut());
        
    }
         
    /* 
     * Para comparar por más de un criterio:  
     * // Usa la interfaz Comparable, pero es lo mismo para el caso.
     * Sobrescribir el método compareTo para hacer la comparación de 
     * dos atributos, teniendo en cuenta que Nombre será el que "pese" más.
     *
     * @Override
     * public int compareTo(Estudiante o) {
     *   if(getNombre().compareTo(o.getNombre())==0)
     *       return Integer.compare(getPuntos(), o.getPuntos());
     *   else 
     *       return getNombre().compareTo(o.getNombre());
     * }
     * 
     */
}
