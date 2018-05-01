package math.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import math.domain.Loki;

public class Infonakyma {

    private String  info;
    private Loki loki;

    public Infonakyma(Loki loki) {
        this.loki = loki;
        ArrayList<String> rivit = new ArrayList<>();
        try (Scanner tiedostonLukija = new Scanner(new File("info.txt"))) {
            while (tiedostonLukija.hasNextLine()) {
                rivit.add(tiedostonLukija.nextLine());
            }
        } catch (Exception e) {
            loki.lisaaLokiin("Virhe info.txt -tiedoston lukemisessa: " + e);
        }
        this.info="";
        for (String rivi : rivit) {
            this.info += rivi + "\n";
        }
    }

    public Parent getNakyma() {
        TextArea asettelu = new TextArea(info);

        return asettelu;
    }
}
