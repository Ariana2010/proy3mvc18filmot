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
public class comparadorActorNacionalidad implements Comparator<Actor>{
    
    @Override
    public int compare(Actor o1, Actor o2) {
        
        return (o1.getNacionalidad().compareToIgnoreCase(o2.getNacionalidad()));
    }
    
}
