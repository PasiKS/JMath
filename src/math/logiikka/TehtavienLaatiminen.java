package math.logiikka;

import java.util.ArrayList;
import math.domain.Ongelma;
import math.domain.Ongelma1Ilmiosta;
import math.domain.Ongelma2Ilmiosta;
import math.domain.Ongelmat;
import math.domain.Tehtava;
import math.domain.Tehtavasarja;

public class TehtavienLaatiminen {

    private Tehtavasarja tehtavasarja;
    private Ongelmat ongelmat;
    private String aihepiiri;
    private int taso;

    public TehtavienLaatiminen(Ongelmat ongelmat, String aihepiiri, int taso) {
        this.tehtavasarja = new Tehtavasarja(aihepiiri, taso);
        this.ongelmat = ongelmat;
        this.aihepiiri = aihepiiri;
        this.taso = taso;
        laadiTehtavasarja();
    }

    public Tehtavasarja getTehtavasarja() {
        return tehtavasarja;
    }

    private void laadiTehtavasarja() {
        ArrayList<Ongelma> listaOngelmista = ongelmat.annaOngelmat();
        for (Ongelma ongelma : listaOngelmista) {
            Tehtava tehtava = new Tehtava();
            if (ongelma instanceof Ongelma1Ilmiosta) {
                Ongelma1Ilmiosta ongelma1Ilmiosta = (Ongelma1Ilmiosta) ongelma;
                tehtava = laadiTehtava1IlmionOngelmasta(ongelma1Ilmiosta);
            }
            if (ongelma instanceof Ongelma2Ilmiosta) {
                Ongelma2Ilmiosta ongelma2Ilmiosta = (Ongelma2Ilmiosta) ongelma;
                tehtava = laadiTehtava2IlmionOngelmasta(ongelma2Ilmiosta);
            }
            this.tehtavasarja.lisaaTehtava(tehtava);
        }
    }

    private Tehtava laadiTehtava1IlmionOngelmasta(Ongelma1Ilmiosta ongelma1Ilmiosta) {
        Tehtava tehtava = new Tehtava();

        // Kerrotaan ilmiölle ja ongelmalle, mitä tasoa ne ovat ja 
        // tuleeko arvo antaa muunnettuna toiseen yksikköön tasolla 3.
        ongelma1Ilmiosta.getIlmio().setTaso(taso, false);
        ongelma1Ilmiosta.setTaso(taso, true);

        // arvotaan ilmiön ja kysymyksen arvot
        ongelma1Ilmiosta.getIlmio().arvoArvo();
        ongelma1Ilmiosta.arvoArvo();

        // Laaditaan tehtävään kysymysteksti.
        tehtava.setKysymys(laadiKysymys1IlmionOngelmasta(ongelma1Ilmiosta));

        // Laaditaan tehtävään vastaus.
        tehtava.setVastaus(ongelma1Ilmiosta.getTulos());

        // palautetaan tehtävä
        return tehtava;
    }

    private String laadiKysymys1IlmionOngelmasta(Ongelma1Ilmiosta ongelma) {

        // Ilmiön osuus kysymyksestä
        String kysymysteksti = ongelma.getIlmio().annaIlmioMerkkijonona() + "\n";

        // Ongelmassa annettu osuus
        ongelma.korjaaKysymyksenasettelu();
        // ongelma.asetaKysymykseenMuuttujanArvo();
        // ongelma.asetaKysymyksenYksikko();

        kysymysteksti += ongelma.getKysymys();
        return kysymysteksti;
    }

    private Tehtava laadiTehtava2IlmionOngelmasta(Ongelma2Ilmiosta ongelma2Ilmiosta) {
        Tehtava tehtava = new Tehtava();

        // Kerrotaan ilmiölle, mitä tasoa ne ovat ja 
        // tuleeko arvo antaa muunnettuna toiseen yksikköön tasolla 3.
        ongelma2Ilmiosta.getIlmio1().setTaso(taso, false);
        ongelma2Ilmiosta.getIlmio2().setTaso(taso, true);

        try {
            arvoIlmioidenArvot(ongelma2Ilmiosta);
        } catch (Exception ex) {
            System.out.println("Virhe ilmiöiden arvojen arpomisessa: " + ex);;
        }

        // Laaditaan tehtävään kysymysteksti.
        tehtava.setKysymys(laadiKysymys2IlmionOngelmasta(ongelma2Ilmiosta));

        // Laaditaan tehtävään vastaus.
        tehtava.setVastaus(ongelma2Ilmiosta.getTulos());
        // palautetaan tehtävä
        return tehtava;
    }

    /**
     * Arvotaan niin kauan, kun ero ei ole vähintään 1 % eikä ensimmäisen ilmiön
     * arvo ole pienempi kuin toisen.
     *
     * @param ongelma2Ilmiosta
     * @throws Exception
     */
    private void arvoIlmioidenArvot(Ongelma2Ilmiosta ongelma) throws Exception {
        // Arvotaaan ainakin kerran:
        ongelma.getIlmio1().arvoArvo();
        ongelma.getIlmio2().arvoArvo();
        while (!((Math.abs(ongelma.getIlmio1().getArvo() - ongelma.getIlmio2().getArvo())
                / ongelma.getIlmio1().getArvo() > 0.01) && (ongelma.getIlmio1().getArvo() < ongelma.getIlmio2().getArvo()))) {
            ongelma.getIlmio1().arvoArvo();
            ongelma.getIlmio2().arvoArvo();
        }
    }

    private String laadiKysymys2IlmionOngelmasta(Ongelma2Ilmiosta ongelma) {
        ongelma.korvaaNimetKysymykseen();
        return ongelma.getIlmio1().annaIlmioMerkkijonona() + "\n"
                + ongelma.getIlmio2().annaIlmioMerkkijonona() + "\n"
                + ongelma.getKysymys();
    }
}
