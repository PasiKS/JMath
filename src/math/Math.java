/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.Scanner;

/**
 *
 * @author pasikiema
 */
public class Math {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // On opetettu tekemään olit tiedoston ja IO:n käsittelyy:
        Scanner lukija = new Scanner(System.in);
        Tehtavasarja tehtavasarja = new Tehtavasarja();

        Kayttoliittyma kayttis = new Kayttoliittyma(lukija);
        kayttis.kaynnista();
        
    }
    
}
