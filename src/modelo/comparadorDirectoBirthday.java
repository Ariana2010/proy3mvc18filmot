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
public class comparadorDirectoBirthday implements Comparator<Director> {
    
    @Override
    public int compare(Director d, Director d1){
        int v = d.getFechaNac().getYear();
        int v1 = d1.getFechaNac().getYear();
        return Integer.compare(v, v1);
    }
    
}
