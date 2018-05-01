package math.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tehtavasarja {

    private List<Tehtava> tehtavat;
    private String aihepiiri;
    private int taso;

    public Tehtavasarja(String aihepiiri, int taso) {
        this.tehtavat = new ArrayList<>();
        this.aihepiiri = aihepiiri;
        this.taso = taso;
    }
    
    public void lisaaTehtava(Tehtava  tehtava ){
        tehtavat.add(tehtava);
    }

    public List<Tehtava> getTehtavasarja() {
        return tehtavat;
    }

    public List<Tehtava> annaTehtavatSekoitettuna() {
        List<Tehtava> sekoitetutTehtavat = new ArrayList<>();
        for (Tehtava kysymys : this.tehtavat) {
            sekoitetutTehtavat.add(kysymys);
        }
        Collections.shuffle(sekoitetutTehtavat);
        return sekoitetutTehtavat;
    }

    public String getAihepiiri() {
        return aihepiiri;
    }

    public int getTaso() {
        return taso;
    }

    public boolean onTyhja() {
        return this.tehtavat.isEmpty();
    }

    @Override
    public String toString() {
        String kysymykset = "";
        for (Tehtava kysymys : this.tehtavat) {
            kysymykset += "\n" + kysymys.toString();
        }
        return kysymykset;
    }
}
