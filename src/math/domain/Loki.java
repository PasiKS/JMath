/*
 * Tieto tilauksen käsittelyssä ilmenneistä epätoivotuista tapahtumista
 */
package math.domain;

public class Loki {

    String loki;

    public Loki() {
        this.loki = "";
    }

    public void lisaaLokiin(String raportti) {
        loki += "\n\n" + raportti;
    }

    public void tyhjenna() {
        this.loki = "";
    }

    public boolean onTyhja() {
        if (this.loki.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return loki;
    }
}
