package math.domain;

public class TilauksenRivi {

    String aihepiiri;
    int taso;
    int kappaletta;

    public TilauksenRivi(String aihepiiri, int taso, String kappaletta) {
        this.aihepiiri = aihepiiri;
        this.taso = taso;
        if (kappaletta.isEmpty()){
            this.kappaletta = 0;
        } else 
            this.kappaletta = Integer.parseInt(kappaletta);
    }

    public String getAihepiiri() {
        return aihepiiri;
    }

    public void setAihepiiri(String aihepiiri) {
        this.aihepiiri = aihepiiri;
    }

    public int getTaso() {
        return taso;
    }

    public void setTaso(int taso) {
        this.taso = taso;
    }

    public int getKappaletta() {
        return kappaletta;
    }

    public void setKappaletta(int kappaletta) {
        this.kappaletta = kappaletta;
    }

    @Override
    public String toString() {
        return "TilauksenRivi{" + "aihepiiri=" + aihepiiri + ", taso=" + taso + ", kappaletta=" + kappaletta + '}';
    }

}
