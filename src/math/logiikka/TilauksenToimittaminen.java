package math.logiikka;

import math.ui.TulostaminenTekstitiedostoon;
import math.domain.Ilmiot;
import math.domain.Loki;
import math.domain.Ongelmat;
import math.domain.Tilaus;
import math.domain.TilauksenRivi;
import math.domain.Toimitus;

/**
 * Käsittelee tilauksen ja valmistelee dokumentin yksi tehtäväsarja kerrallaan.
 *
 * Ilmiöt luetaan muistiin tässä vaiheessa, koska kaikki tilatut asiat
 * perustuvat yhteen ilmiöjoukkoon.
 *
 * @author pasikiema
 */
public class TilauksenToimittaminen {

    Toimitus toimitus; // lista tehtäväsarjoja
    Ilmiot ilmiot;
    Loki loki;

    public TilauksenToimittaminen(Loki loki) {
        this.toimitus = new Toimitus();
        this.ilmiot = new Ilmiot(loki);
        this.loki = loki;
    }

    /**
     * Toimittaa tilauksen rivi riviltä Varautuu toimitusvaikeuksiin (puuttuvat
     * tehtävätiedostot tai virheet tiedostonkäsittelyssä) Kirjoittaa lokiin
     * puutteet.
     *
     * Koska samasta aihepiiristä ja tasosta voidaan tilata useampi eri
     * tehtäväsarja..
     *
     * @param tilaus
     */
    public void kasitteleTilaus(Tilaus tilaus) {
        for (TilauksenRivi rivi : tilaus.getRivit()) {
            String aihepiiri = rivi.getAihepiiri();
            int taso = rivi.getTaso();
            int lukumaara = rivi.getKappaletta();
            if (lukumaara > 0) {
                Ongelmat ongelmat = new Ongelmat(aihepiiri, taso, loki, ilmiot);
                for (int i = 0; i < lukumaara; i++) {
                    TehtavienLaatiminen tehtavienLaatiminen = new TehtavienLaatiminen(ongelmat, aihepiiri, taso);
                    this.toimitus.lisaaTehtavasarja(tehtavienLaatiminen.getTehtavasarja());
                }
            }
        }
    
    TulostaminenTekstitiedostoon tulostaminenTekstitiedostoon = new TulostaminenTekstitiedostoon(loki);
    tulostaminenTekstitiedostoon.tulosta (this.toimitus);
    }

}
