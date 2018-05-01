package math.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pasikiema
 */
public class Ilmiot {

    private List<Ilmio> ilmiot;
    private Loki loki;

    public Ilmiot(Loki loki) {
        this.ilmiot = new ArrayList<>();
        this.loki = loki;

        this.lueIlmiotTiedostosta();

    }

    public List<Ilmio> getIlmiot() {
        return ilmiot;
    }

    public int annaLukumaara() {
        return this.ilmiot.size();
    }

    /**
     * 1) Valitaan satunnaiset, aiheen mukaiset ilmiöt, 2) jos ilmiöitä on
     * kaksi, tarkistetaan niiden arvot, tarvittaessa niitä pyydetään arpomaan
     * tilansa uudelleen, kunnes eroa on vähintään yksi prosentti ja 3)
     * palautetaan ne suuruusjärjestyksessä alkaen pienimmästä.
     *
     * @param etsittyIlmio
     * @param lukumaara
     * @return satunnaisesti valitut ilmiöt
     */
    public ArrayList<Ilmio> arvoIlmiot(String etsittyIlmio, int lukumaara) {
        ArrayList<Ilmio> tietynIlmionVaihtoehdot = poimiIlmionVaihtoehdot(etsittyIlmio);
        if (tietynIlmionVaihtoehdot.size() < lukumaara) {
            loki.lisaaLokiin("Virhe: ilmiöitä oli liian vähän tehtävää varten!");
            return null;
        }
        ArrayList<Ilmio> valitutVaihtoehdot = new ArrayList<>();
        ArrayList<Integer> indeksit = new ArrayList<>();
        int i = 0;
        while (i < lukumaara) {
            int indeksi = new Random().nextInt(tietynIlmionVaihtoehdot.size());
            if (indeksit.contains(indeksi)) {
                continue;
            }
            indeksit.add(indeksi);
            valitutVaihtoehdot.add(tietynIlmionVaihtoehdot.get(indeksi));
            i++;
        }

        // Järjestetään ilmiön arvon vaihteluvälin alarajan mukaan.
        Collections.sort(valitutVaihtoehdot);

        return valitutVaihtoehdot;
    }

    private ArrayList<Ilmio> poimiIlmionVaihtoehdot(String etsittyIlmio) {
        ArrayList<Ilmio> tietynIlmionVaihtoehdot = new ArrayList<>();
        for (Ilmio ilmio : ilmiot) {
            if (ilmio.getKuvaus().equals(etsittyIlmio)) {
                tietynIlmionVaihtoehdot.add(ilmio);
            }
        }
        return tietynIlmionVaihtoehdot;
    }

    private void lueIlmiotTiedostosta() {
        ArrayList<String> ilmiotiedostonRivit = new ArrayList<>();
        // lisättävä tiedoston nimen valinta ilmioAihealueen mukaan
        try (Scanner tiedostonLukija = new Scanner(new File("ilmiot.csv"))) {
            while (tiedostonLukija.hasNextLine()) {
                ilmiotiedostonRivit.add(tiedostonLukija.nextLine());
            }
        } catch (Exception e) {
            loki.lisaaLokiin("Virhe ilmiötiedoston lukemisessa.");
        }

        muutaRivitIlmioiksi(ilmiotiedostonRivit);
    }

    private void muutaRivitIlmioiksi(ArrayList<String> ilmiotiedostonRivit) {
        int indeksi = 0;
        for (String rivi : ilmiotiedostonRivit) {
            if (indeksi != 0) {
                String[] palat = rivi.split(",");
                this.ilmiot.add(new Ilmio(palat[0],
                        palat[1],
                        Double.parseDouble(palat[2]),
                        Double.parseDouble(palat[3]),
                        palat[4], palat[5],
                        Double.parseDouble(palat[6]),
                        Double.parseDouble(palat[7]),
                        Double.parseDouble(palat[8]),
                        Double.parseDouble(palat[9])));
            }
            indeksi++;
        }
    }
}
