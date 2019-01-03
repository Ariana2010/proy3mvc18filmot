/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmoteca;

import vista.View;

/**
 *
 * @author pgina37
 */
public class Filmoteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View v=new View();
        v.runMenu("\nMENÚ PRINCIPAL:"
                + "\n1 -Archivos "
                + "\n2 -Películas "
                + "\n3 -Directores"
                + "\n4 -Actores"
                + "\n5 -Listados"
                + "\nq -Salir\n ");
    }
    
}
