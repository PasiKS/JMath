package math.domain;

import java.util.Random;
import math.logiikka.Apu;

/**
 * Ilmiön voisi ajatella yleisesti ja konkretisoituna: esimerkiksi myytäväksi
 * kelpuutetulla appelsiinilla on tietty massan vaihteluväli, joka voidaan
 * ilmoittaa tavallisesti noin kahdella järkevällä yksiköllä sekä tietyllä
 * tarkkuudella.
 *
 * Oppilaiden kykyyn hahmottaa appelsiini riippuu myös em. arvojen käyttäminen:
 * mitä tarkemmin massa ilmoitetaan, sitä vaikeampi lukua on käsitellä. Myös
 * mahdollinen tehtävässä tarvittava yksikkömuunnos tuo lisähaastetta.
 *
 * Kun Ilmio-olio luodaan, ilmiölle itselleen asetetaan vasta reunaehdot.
 * Konkreettiset arvot asetetaan myöhemmin, tehtävän laatimisen yhteydessä.
 *
 * @author pasikiema
 */
public class Ilmio implements Comparable<Ilmio> {

    private String kuvaus;
    private String tekija;
    private double alaraja;
    private double ylaraja;
    private String oletusyksikko;
    private String vaihtoehtoinenYksikko;
    private double muunnoskerroin;
    private double tarkkuus1;
    private double tarkkuus2;
    private double tarkkuus3;

    // näitä ei määritellä konstruktorissa vaan myöhemmin
    private double arvo;
    private boolean yksikkomuunnos;
    private int taso;

    public Ilmio(String kuvaus,
            String tekija,
            double alaraja,
            double ylaraja,
            String oletusyksikko,
            String vaihtoehtoinenYksikko,
            double muunnoskerroin,
            double tarkkuus1,
            double tarkkuus2,
            double tarkkuus3) {
        this.tekija = tekija;
        this.kuvaus = kuvaus;
        this.alaraja = alaraja;
        this.ylaraja = ylaraja;
        this.oletusyksikko = oletusyksikko;
        this.vaihtoehtoinenYksikko = vaihtoehtoinenYksikko;
        this.muunnoskerroin = muunnoskerroin;
        this.tarkkuus1 = tarkkuus1;
        this.tarkkuus2 = tarkkuus2;
        this.tarkkuus3 = tarkkuus3;

        // Kun ilmiöt tiedoston lukemisen yhteydessä luodaan,
        // asetetaan oletusarvoksi alaraja sekä
        // oletetaan, ettei yksikkömuunnosta tehdä vaan käytetään oletusyksikköä
        // Ilmiön arvo asetetaan uudelleen tehtävää laadittaessa.
        // Yksikkömuunnos voi tapahtua vaikeustasolla 3. 
        // Siinä tapauksessa myös arvo muuttuu ja
        // käytetään vaihtoehtoista yksikköä.
        // Ilmiöitä luettaessa taso on vielä tuntematon; 
        // asetetaan oletukseksi helpoin eli taso 1.
        this.arvo = alaraja;
        this.yksikkomuunnos = false;
        this.taso = 1;

    }

    public String annaIlmioMerkkijonona() {
        return this.tekija + " " + this.kuvaus + " " + annaArvoMerkkijonona() + " " + annaYksikko() + ".";
    }

    public String annaYksikko() {
        if (yksikkomuunnos) {
            return vaihtoehtoinenYksikko;
        } else {
            return oletusyksikko;
        }
    }

    @Override
    public int compareTo(Ilmio verrattava) {
        if (this.arvo < verrattava.arvo) {
            return -1;
        } else if (this.arvo == verrattava.arvo) {
            return 0;
        } else {
            return 1;
        }
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

    public double annaMuunnettuArvo() {
        return arvo * muunnoskerroin;
    }

    public void setTaso(int taso, boolean yksikkomuunnos) {
        this.taso = taso;
        if (taso == 3){
            this.yksikkomuunnos=yksikkomuunnos;
        }
    }
    
    public double getArvo() {
        return this.arvo;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }

    public String getTekija() {
        return this.tekija;
    }
    
    private String annaArvoMerkkijonona() {
        double tarkkuus = 0.0;
        double arvo = this.arvo;
        switch (taso) {
            case 1:
                tarkkuus = tarkkuus1;
                break;
            case 2:
                tarkkuus = tarkkuus2;
                break;
            case 3:
                tarkkuus = tarkkuus3;
                break;
        }
        if (yksikkomuunnos) {
            tarkkuus *= muunnoskerroin;
            arvo = annaMuunnettuArvo();
        }
        return Apu.desimaalilukuMerkkijonoksiTarkkuudella(arvo, tarkkuus);
    }
}
