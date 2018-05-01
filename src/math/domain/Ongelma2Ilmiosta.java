package math.domain;

import java.util.ArrayList;
import math.logiikka.Apu;

public class Ongelma2Ilmiosta extends Ongelma {

    private Ilmio ilmio1;
    private Ilmio ilmio2;

    public Ongelma2Ilmiosta(ArrayList<Ilmio> ilmiot, String kysymys, int taso, String vastaus, int pisteet, Sisalto sisalto) {
        super(kysymys, taso, vastaus, pisteet, sisalto);
        this.ilmio1 = ilmiot.get(0);
        this.ilmio2 = ilmiot.get(1);
    }

    public Ilmio getIlmio1() {
        return ilmio1;
    }

    public Ilmio getIlmio2() {
        return ilmio2;
    }

    public void korvaaNimetKysymykseen() {
        String kysymys = super.getKysymys();
        String korjattuKysymys = kysymys.replace("subjekti1", ilmio1.getTekija());
        korjattuKysymys = korjattuKysymys.replace("subjekti2", ilmio2.getTekija());
        super.setKysymys(korjattuKysymys);
    }

    @Override
    public String getTulos() {
        return Apu.tulos(super.getSisalto(), this);
    }
}
