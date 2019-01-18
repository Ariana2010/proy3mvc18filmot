/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Catica
 */
public class comparadorActorBirthday implements Comparator<Actor> {
    //LocalDate tien el método compareTo
    @Override
    public int compare(Actor o1, Actor o2){
        LocalDate d1 = o1.getFechaNac();
        LocalDate d2 = o2.getFechaNac();
        return d1.compareTo(d2);
    }
    
}
