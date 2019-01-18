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
public class comparadorDirectorNacionalidad implements Comparator<Director> {
    
    @Override
    public int compare (Director d1,Director d2){
        String nac1 = d1.getNacionalidad();
        String nac2 = d2.getNacionalidad();
        return (nac1.compareToIgnoreCase(nac2));
    }
    
}
