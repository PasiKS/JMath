/*
 * Toimitus on lista erilaisia tehtäväsarjoja
 */
package math.domain;

import java.util.ArrayList;

/**
 *
 * @author pasikiema
 */
public class Toimitus {

    private ArrayList<Tehtavasarja> tehtavasarjat;

    public Toimitus() {
        this.tehtavasarjat = new ArrayList<>();
    }
    
    public void lisaaTehtavasarja(Tehtavasarja tehtavasarja){
        tehtavasarjat.add(tehtavasarja);
    }

    public ArrayList<Tehtavasarja> getToimitus() {
        return tehtavasarjat;
    }
}
