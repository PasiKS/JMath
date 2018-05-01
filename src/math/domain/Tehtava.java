package math.domain;

/**
 *
 * @author pasikiema
 */
public class Tehtava {

    private String kysymys;
    private String vastaus;

    public Tehtava() {
        this.kysymys = "";
        this.vastaus = "";
    }

    public String getKysymys() {
        return kysymys;
    }

    public void setKysymys(String kysymys) {
        this.kysymys = kysymys;
    }

    public String getVastaus() {
        return vastaus;
    }

    public void setVastaus(String vastaus) {
        this.vastaus = vastaus;
    }

    @Override
    public String toString() {
        return kysymys;
    }
}
