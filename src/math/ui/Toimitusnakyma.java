package math.ui;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import math.domain.Loki;

public class Toimitusnakyma {
    private Loki loki;
    
    public Toimitusnakyma(Loki loki){
        this.loki=loki;
    }
        
    public Parent getNakyma() {
        TextArea asettelu = new TextArea("TOIMITUSRAPORTTI"+loki);

        return asettelu;
    }
}
