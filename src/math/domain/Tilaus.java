package math.domain;

import java.util.List;
import java.util.ArrayList;

public class Tilaus {

    List<TilauksenRivi> rivit;

    public Tilaus() {
        this.rivit = new ArrayList();
    }

    public void lisaaRivi(TilauksenRivi rivi) {
        rivit.add(rivi);
    }

    public boolean onTilattu() {
        return !rivit.isEmpty();
    }

    public List<TilauksenRivi> getRivit() {
        return rivit;
    }

    public void tyhjenna() {
        rivit.clear();
    }

    @Override
    public String toString() {
        return "Tilaus{" + "rivit=" + rivit + '}';
    }

}
