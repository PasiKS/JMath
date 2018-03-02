/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author pasikiema
 */
public class Kayttoliittyma {

    private Scanner lukija;

    public Kayttoliittyma(Scanner lukija) {
        this.lukija = lukija;

    }

    public void kaynnista() {
        while (true) {
            System.out.println("\nValitse:\n"
                    + "prosentti\n"
                    + "lopeta\n");
            String valinta = this.lukija.nextLine();
            if (valinta.equals("lopeta")) {
                break;
            }
            ArrayList<String> tehtavatiedostonRivit = new ArrayList<>();
            try (Scanner tiedostonLukija = new Scanner(new File(valinta + ".txt"))) {
                while (tiedostonLukija.hasNextLine()) {
                    tehtavatiedostonRivit.add(tiedostonLukija.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Virhe tiedoston lukemisessa: " + e);
            }
            
            for (String tehtavatiedostonRivi: tehtavatiedostonRivit){
                System.out.println(tehtavatiedostonRivi);
            }
            
            continue;
        }
    }
}
