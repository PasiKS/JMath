package math.domain;

import java.util.Random;
import math.logiikka.Apu;

public class Ongelma1Ilmiosta extends Ongelma {

    private Ilmio ilmio;
    private double alaraja;
    private double ylaraja;
    private String oletusyksikko;
    private String vaihtoehtoinenYksikko;
    private double muunnoskerroin;
    private double tarkkuus1;
    private double tarkkuus2;
    private double tarkkuus3;
    private double arvo;
    private int taso;
    private boolean yksikkomuunnos;

    public Ongelma1Ilmiosta(Ilmio ilmio,
            String kysymys,
            int taso,
            double alaraja,
            double ylaraja,
            String oletusyksikko,
            String vaihtoehtoinenYksikko,
            double muunnoskerroin,
            double tarkkuus1,
            double tarkkuus2,
            double tarkkuus3,
            String vastaus,
            int pisteet,
            Sisalto sisalto) {
        super(kysymys, taso, vastaus, pisteet, sisalto);
        this.ilmio = ilmio;
        this.alaraja = alaraja;
        this.ylaraja = ylaraja;
        this.oletusyksikko = oletusyksikko;
        this.vaihtoehtoinenYksikko = vaihtoehtoinenYksikko;
        this.muunnoskerroin = muunnoskerroin;
        this.tarkkuus1 = tarkkuus1;
        this.tarkkuus2 = tarkkuus2;
        this.tarkkuus3 = tarkkuus3;

        // Oletusarvot, jotka tarvittaessa muutetaan myöhemmin. 
        this.arvo = alaraja;
        this.yksikkomuunnos = false;
        this.taso = 1;
    }

    public void setTaso(int taso, boolean yksikkomuunnos) {
        this.taso = taso;
        if (taso == 3) {
            this.yksikkomuunnos = yksikkomuunnos;
        }
    }

    public Ilmio getIlmio() {
        return ilmio;
    }

    public double getAlaraja() {
        return alaraja;
    }

    public double getYlaraja() {
        return ylaraja;
    }

    public void arvoArvo() {
        arvo = alaraja + (ylaraja - alaraja) * new Random().nextDouble();
        switch (taso) {
            case 1:
                arvo = Math.round(arvo / tarkkuus1) * tarkkuus1;
                break;
            case 2:
                arvo = Math.round(arvo / tarkkuus2) * tarkkuus2;
                break;
            case 3:
                arvo = Math.round(arvo / tarkkuus3) * tarkkuus3;
                break;
        }
    }

    public double getArvo() {
        return this.arvo;
    }

    public double annaMuunnettuArvo() {
        return arvo * muunnoskerroin;
    }

    public double getMuunnoskerroin() {
        return this.muunnoskerroin;
    }

    public void korjaaKysymyksenasettelu() {
        asetaKysymykseenMuuttujanArvo();
        asetaKysymyksenYksikko();
    }

    public String getYksikko() {
        if (taso == 3) {
            return vaihtoehtoinenYksikko;
        } else {
            return oletusyksikko;
        }
    }

    private void asetaKysymykseenMuuttujanArvo() {
        String kysymys = super.getKysymys();
        switch (taso) {
            case 1:
                kysymys = kysymys.replace("muuttuja", Apu.desimaalilukuMerkkijonoksiTarkkuudella(Math.round(arvo / tarkkuus1) * tarkkuus1, tarkkuus1));
                break;
            case 2:
                kysymys = kysymys.replace("muuttuja", Apu.desimaalilukuMerkkijonoksiTarkkuudella(Math.round(arvo / tarkkuus2) * tarkkuus2, tarkkuus2));
                break;
            case 3:
                kysymys = kysymys.replace("muuttuja", Apu.desimaalilukuMerkkijonoksiTarkkuudella(Math.round(arvo / tarkkuus3) * tarkkuus3, tarkkuus3));
                break;
        }
        super.setKysymys(kysymys);
    }

    private void asetaKysymyksenYksikko() {
        if (taso == 3) {
            super.setKysymys(super.getKysymys().replace("mittayksikko2", vaihtoehtoinenYksikko));
        } else {
            super.setKysymys(super.getKysymys().replace("mittayksikko2", oletusyksikko));
        }
    }

    @Override
    public String getTulos() {
        return Apu.tulos(super.getSisalto(), this);
    }

}
