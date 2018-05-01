/*
 * Apuluokka:
 *
 * Laskutoimitukset vastausten ilmoittamiseksi
 *
 * TODO:    tarkkuudet, 
 *          refactorointia: laskutoimitusten läpikäynti tavoitteena yksinkertaistaa ja selkeyttää
 */
package math.logiikka;

import math.domain.Ongelma2Ilmiosta;
import math.domain.Ongelma;
import math.domain.Ongelma1Ilmiosta;
import math.domain.Sisalto;

public class Apu {

    public static String tulos(Sisalto sisalto, Ongelma ongelma) {
        Ongelma1Ilmiosta ongelma1 = null;
        Ongelma2Ilmiosta ongelma2 = null;
        if (ongelma instanceof Ongelma1Ilmiosta) {
            ongelma1 = (Ongelma1Ilmiosta) ongelma;
        } else if (ongelma instanceof Ongelma2Ilmiosta) {
            ongelma2 = (Ongelma2Ilmiosta) ongelma;
        }
        switch (sisalto) {
            case PROSENTTILUKU:
                return desimaalilukuMerkkijonoksiTarkkuudella(prosenttiluku(ongelma2), 0.1) + " %";
            case PROSENTTIARVO:
                return desimaalilukuMerkkijonoksiTarkkuudella(prosenttiarvo(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case LISAANTYNYTARVO:
                return desimaalilukuMerkkijonoksiTarkkuudella(lisaantynytArvo(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case VAHENTYNYTARVO:
                return desimaalilukuMerkkijonoksiTarkkuudella(vahentynytArvo(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case MUUTOSPROSENTTI:
                return desimaalilukuMerkkijonoksiTarkkuudella(muutosprosentti(ongelma1), tarkkuus1(ongelma1, 0.1)) + " %";
            case VERTAILUPROSENTTIVAHEMMAN:
                return desimaalilukuMerkkijonoksiTarkkuudella(vertailuprosenttiVahemman(ongelma2), 0.1) + " %";
            case VERTAILUPROSENTTIENEMMAN:
                return desimaalilukuMerkkijonoksiTarkkuudella(vertailuprosenttiEnemman(ongelma2), 0.1) + " %";
            case PERUSARVO:
                return desimaalilukuMerkkijonoksiTarkkuudella(perusarvo(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case KORKO:
                return desimaalilukuMerkkijonoksiTarkkuudella(korko(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case KORKOAKOROLLE:
                return desimaalilukuMerkkijonoksiTarkkuudella(korkoaKorolle(ongelma1), tarkkuus1(ongelma1, 0.01)) + " " + ongelma1.getYksikko();
            case SEOS:
                return desimaalilukuMerkkijonoksiTarkkuudella(seos(ongelma1), tarkkuus1(ongelma1, 0.1)) + " %";
            default:
                return "";
        }
    }

    // Yhden ilmiön ongelmat:
    //
    private static double prosenttiarvo(Ongelma1Ilmiosta ongelma) {
        if (ongelma.getTaso() == 3) {
            return ongelma.getIlmio().getArvo() * ongelma.getArvo() / 100.0 * ongelma.getMuunnoskerroin();
        }
        return ongelma.getIlmio().getArvo() * ongelma.getArvo() / 100.0;
    }

    private static double lisaantynytArvo(Ongelma1Ilmiosta ongelma) {
        if (ongelma.getTaso() == 3) {
            return ongelma.getIlmio().getArvo() * (1.0 + (ongelma.getArvo() / 100.0)) * ongelma.getMuunnoskerroin();
        }
        return ongelma.getIlmio().getArvo() * (1.0 + (ongelma.getArvo() / 100.0));
    }

    private static double vahentynytArvo(Ongelma1Ilmiosta ongelma) {
        if (ongelma.getTaso() == 3) {
            return ongelma.getIlmio().getArvo() * (1.0 - (ongelma.getArvo() / 100.0)) * ongelma.getMuunnoskerroin();
        }
        return ongelma.getIlmio().getArvo() * (1.0 - (ongelma.getArvo() / 100.0));
    }

    private static double muutosprosentti(Ongelma1Ilmiosta ongelma) {
        if (ongelma.getTaso() == 3) {
            // TODO: kysymyksen laatiminen eri mittayksiköille (siis myös tasolle 3)
            // toistaiseksi ilmiön yksikkö on metri ja kysymyksen senttimetri
        }
        return ongelma.getArvo() / 100 / ongelma.getIlmio().getArvo() * 100.0;
    }

    private static double perusarvo(Ongelma1Ilmiosta ongelma) {
        if (ongelma.getTaso() == 3) {
            return ongelma.getIlmio().getArvo() / ((ongelma.getArvo() / ongelma.getMuunnoskerroin()) / 100.0);
        }
        return ongelma.getIlmio().getArvo() / (ongelma.getArvo() / 100.0);
    }

    private static double korko(Ongelma1Ilmiosta ongelma) {
        return ongelma.getIlmio().getArvo() * (1.0 + (ongelma.getArvo() / 100.0));
    }

    private static double korkoaKorolle(Ongelma1Ilmiosta ongelma) {
        // TODO: korkokin pitäisi arpoa. Se on nyt 2%.
        return ongelma.getIlmio().getArvo() * Math.pow(1.02, ongelma.getArvo());
    }

    private static double seos(Ongelma1Ilmiosta ongelma) {
        return ongelma.getArvo() / 1000 / (ongelma.getArvo() / 1000 + ongelma.getIlmio().getArvo()) * 100.0;
    }

    private static double tarkkuus1(Ongelma1Ilmiosta ongelma, double tarkkuus) {
        if (ongelma.getTaso() == 3) {
            tarkkuus *= ongelma.getMuunnoskerroin();
        }
        return tarkkuus;
    }

    // Kahden ilmiön ongelmat:
    //
    private static double prosenttiluku(Ongelma2Ilmiosta ongelma) {
        return 100.0 * ongelma.getIlmio1().getArvo() / ongelma.getIlmio2().getArvo();
    }

    private static double vertailuprosenttiVahemman(Ongelma2Ilmiosta ongelma) {
        return 100.0 * (ongelma.getIlmio2().getArvo() - ongelma.getIlmio1().getArvo()) / ongelma.getIlmio2().getArvo();
    }

    private static double vertailuprosenttiEnemman(Ongelma2Ilmiosta ongelma) {
        return 100.0 * (ongelma.getIlmio2().getArvo() - ongelma.getIlmio1().getArvo()) / ongelma.getIlmio1().getArvo();
    }

    public static String desimaalilukuMerkkijonoksiTarkkuudella(double luku, double tarkkuus) {
        if (tarkkuus < 0.11 && tarkkuus > 0.09) {
            return String.format("%.1f", luku);
        } else if (tarkkuus < 0.011 && tarkkuus > 0.009) {
            return String.format("%.2f", luku);
        } else if (tarkkuus < 0.0011 && tarkkuus > 0.0009) {
            return String.format("%.3f", luku);
        } else if (tarkkuus < 0.00011 && tarkkuus > 0.00009) {
            return String.format("%.4f", luku);
        } else if (tarkkuus < 0.000011 && tarkkuus > 0.000009) {
            return String.format("%.5f", luku);
        } else if (tarkkuus < 0.0000011 && tarkkuus > 0.0000009) {
            return String.format("%.6f", luku);
        } else {
            return String.format("%.0f", luku);
        }
    }
}
