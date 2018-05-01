/*
 * Lukee ongelmat tiedostosta
 */
package math.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pasikiema
 */
public class Ongelmat {

    private ArrayList<Ongelma> ongelmat;
    private String aihepiiri;
    private int taso;
    private Loki loki;

    public Ongelmat(String aihepiiri, int taso, Loki loki, Ilmiot ilmiot) {
        this.aihepiiri = aihepiiri;
        this.taso = taso;
        this.loki = loki;
        this.ongelmat = new ArrayList<>();
        luoOngelmat(ilmiot);

    }

    public ArrayList annaOngelmat() {
        return ongelmat;
    }

    private void luoOngelmat(Ilmiot ilmiot) {
        ArrayList<String> ongelmatRiveina = lueOngelmatTiedostosta();
        muutaRivitOngelmiksi(ongelmatRiveina, ilmiot);
    }

    private void muutaRivitOngelmiksi(ArrayList<String> ongelmatRiveina, Ilmiot ilmiot) {
        int indeksi = 0;
        for (String rivi : ongelmatRiveina) {
            if (indeksi != 0) {
                String[] palat = rivi.split(",");
                String ilmionKuvaus = palat[0];
                int ilmioidenMaara = Integer.parseInt(palat[1]);
                ArrayList<Ilmio> valitutVaihtoehdot = ilmiot.arvoIlmiot(ilmionKuvaus, ilmioidenMaara);
                switch (ilmioidenMaara) {
                    case 1:
                        this.ongelmat.add(luoYhdenIlmionOngelma(valitutVaihtoehdot, palat));
                        break;
                    case 2:
                        this.ongelmat.add(luoKahdenIlmionOngelma(valitutVaihtoehdot, palat));
                        break;
                    default:
                        System.out.println("Virhe: ilmiöitä tulisi olla yksi tai kaksi. Niitä oli " + ilmioidenMaara);
                }
            }
            indeksi++;
        }
    }

    private Ongelma1Ilmiosta luoYhdenIlmionOngelma(ArrayList<Ilmio> valitutVaihtoehdot, String[] palat) {
        return new Ongelma1Ilmiosta(valitutVaihtoehdot.get(0),
                palat[2],
                taso,
                Double.parseDouble(palat[3]),
                Double.parseDouble(palat[4]),
                palat[5], palat[6],
                Double.parseDouble(palat[7]),
                Double.parseDouble(palat[8]),
                Double.parseDouble(palat[9]),
                Double.parseDouble(palat[10]),
                palat[11],
                Integer.parseInt(palat[12]),
                Sisalto.valueOf(palat[13]));
    }

    private Ongelma2Ilmiosta luoKahdenIlmionOngelma(ArrayList<Ilmio> valitutVaihtoehdot, String[] palat) {
        return new Ongelma2Ilmiosta(valitutVaihtoehdot, palat[2], taso, palat[11], Integer.parseInt(palat[12]), Sisalto.valueOf(palat[13]));
    }

    /**
     * Luettuja tietoja ei jalosteta vaan ne annetaan merkkijonolistana
     * jatkokäsittelyyn.
     *
     * @param aihepiiri
     * @param taso
     * @return merkkijonolista tiedoston riveistä
     */
    private ArrayList<String> lueOngelmatTiedostosta() {
        ArrayList<String> tehtavatiedostonRivit = new ArrayList<>();
        String hakemistoJaTiedosto = "./" + "prosentti" + "/" + taso + "/" + aihepiiri + ".csv";
        try (Scanner tiedostonLukija = new Scanner(new File(hakemistoJaTiedosto))) {
            while (tiedostonLukija.hasNextLine()) {
                tehtavatiedostonRivit.add(tiedostonLukija.nextLine());
            }
        } catch (Exception e) {
            loki.lisaaLokiin("Virhe tehtäväsarjatiedoston lukemisessa:\n"
                    + "Tehtäväsarjan luominen aiheessa " + aihepiiri + " tasolla " + taso + "  ei onnistunut.");
        }
        return tehtavatiedostonRivit;
    }
}
