/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Comparator;

/**
 * Orden: nacionalidad y año de nacimiento.
 * A igual nacionalidad, retorna el orden de comparar el año de nacimiento,
 * sino retorna el orden de comparar la nacionalidad.
 * 
 * @author Catica
 */
public class ComparadorDirectorNacionalidad implements Comparator<Director> {
    //Orden: nacionalidad y año nacimiento.
    @Override
    public int compare (Director d1,Director d2){
        String nac1 = d1.getNacionalidad();
        String nac2 = d2.getNacionalidad();
        
        if(nac1.compareToIgnoreCase(nac2) == 0){
            //A igual Nacionalidad retorna el orden comparando el año de nacimiento.
            int anno1 = d1.getFechaNac().getYear();
            int anno2 = d2.getFechaNac().getYear();
            return Integer.compare(anno1, anno2);
            //return (d1.getFechaNac().compareTo(d2.getFechaNac()));
        }
        //else retorna el resultado de comparar las nacionalidades.
        return (nac1.compareToIgnoreCase(nac2));
    }
    /* Para comparar por más de un criterio:  
     * Usa la interfaz Comparable, pero es lo mismo para el caso.
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
    
    /*
     * public class ComparadorDirectoBirthday implements Comparator<Director> {
     * 
     * @Override
     * public int compare(Director d, Director d1){
     *   int v = d.getFechaNac().getYear();
     *   int v1 = d1.getFechaNac().getYear();
     *   return Integer.compare(v, v1);
     * }
     *
     *}
     * 
     */
}
