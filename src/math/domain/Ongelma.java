package math.domain;

public abstract class Ongelma {

    private String kysymys;
    private int taso;
    private String vastaus;
    private int pisteet;
    private Sisalto sisalto;

    Ongelma(String kysymys, int taso, String vastaus, int pisteet, Sisalto sisalto) {
        this.kysymys = kysymys;
        this.taso = taso;
        this.vastaus = vastaus;
        this.pisteet = pisteet;
        this.sisalto = sisalto;
    }

    public String getKysymys() {
        return kysymys;
    }

    public void setKysymys(String kysymys) {
        this.kysymys = kysymys;
    }

    public int getTaso() {
        return taso;
    }

    public String getVastaus() {
        return vastaus;
    }

    public int getPisteet() {
        return pisteet;
    }

    public Sisalto getSisalto() {
        return sisalto;
    }
    
    public abstract String getTulos();

}
