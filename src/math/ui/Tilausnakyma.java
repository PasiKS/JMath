package math.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import math.domain.Loki;
import math.domain.TilauksenRivi;
import math.domain.Tilaus;
import math.logiikka.TilauksenToimittaminen;

public class Tilausnakyma {

    private Tilaus tilaus;
    private Loki loki;

    public Tilausnakyma(Tilaus tilaus, Loki loki) {
        this.tilaus = tilaus;
        this.loki = loki;
    }

    public Parent getNakyma() {
        GridPane asettelu = new GridPane();

        // Toistaiseksi kovakoodattu
        Label aiheUrheilu = new Label("Urheilu");
        Label aiheMopoilu = new Label("Mopoilu");
        Label aiheRaha = new Label("Raha");
        Label aiheYleista = new Label("Yleistä");
        Label taso1 = new Label("Taso 1");
        Label taso2 = new Label("Taso 2");
        Label taso3 = new Label("Taso 3");
        Label viesti = new Label("");

        TextField urheilu1 = luoUusiTekstikentta();
        TextField urheilu2 = luoUusiTekstikentta();
        TextField urheilu3 = luoUusiTekstikentta();
        TextField mopoilu1 = luoUusiTekstikentta();
        TextField mopoilu2 = luoUusiTekstikentta();
        TextField mopoilu3 = luoUusiTekstikentta();
        TextField raha1 = luoUusiTekstikentta();
        TextField raha2 = luoUusiTekstikentta();
        TextField raha3 = luoUusiTekstikentta();
        TextField yleistietous1 = luoUusiTekstikentta();
        TextField yleistietous2 = luoUusiTekstikentta();
        TextField yleistietous3 = luoUusiTekstikentta();
        
        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));

        Button tilaanappi = new Button("Tilaa");

        asettelu.add(taso1, 1, 0);
        asettelu.add(taso2, 2, 0);
        asettelu.add(taso3, 3, 0);
        asettelu.add(aiheUrheilu, 0, 1);
        asettelu.add(urheilu1, 1, 1);
        asettelu.add(urheilu2, 2, 1);
        asettelu.add(urheilu3, 3, 1);
        asettelu.add(aiheMopoilu, 0, 2);
        asettelu.add(mopoilu1, 1, 2);
        asettelu.add(mopoilu2, 2, 2);
        asettelu.add(mopoilu3, 3, 2);
        asettelu.add(aiheRaha, 0, 3);
        asettelu.add(raha1, 1, 3);
        asettelu.add(raha2, 2, 3);
        asettelu.add(raha3, 3, 3);
        asettelu.add(aiheYleista, 0, 4);
        asettelu.add(yleistietous1, 1, 4);
        asettelu.add(yleistietous2, 2, 4);
        asettelu.add(yleistietous3, 3, 4);
        asettelu.add(tilaanappi, 1, 5);
        asettelu.add(viesti, 1, 6);

        tilaanappi.setOnMouseClicked((event) -> {
            tilaus.tyhjenna();
            loki.tyhjenna();
            tilaus.lisaaRivi(new TilauksenRivi("urheilu", 1, urheilu1.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("urheilu", 2, urheilu2.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("urheilu", 3, urheilu3.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("mopoilu", 1, mopoilu1.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("mopoilu", 2, mopoilu2.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("mopoilu", 3, mopoilu3.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("raha", 1, raha1.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("raha", 2, raha2.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("raha", 3, raha3.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("yleistietous", 1, yleistietous1.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("yleistietous", 2, yleistietous2.getText()));
            tilaus.lisaaRivi(new TilauksenRivi("yleistietous", 3, yleistietous3.getText()));
            TilauksenToimittaminen toimittaminen = new TilauksenToimittaminen(loki);
            toimittaminen.kasitteleTilaus(tilaus);
            if (loki.onTyhja()) {
                loki.lisaaLokiin("Ei toimitusvaikeuksia.");
            }
            viesti.setText("Toimitettu!");
        });

        return asettelu;
    }

    private TextField luoUusiTekstikentta() {
        TextField tekstikentta = new TextField("");
        tekstikentta.setPrefWidth(90);
        salliVainNollastaYsiysiin(tekstikentta);
        return tekstikentta;
    }

    private void salliVainNollastaYsiysiin(TextField tekstikentta) {
        tekstikentta.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") || newValue.length() > 2) {
                tekstikentta.setText(oldValue);
            }
        });
    }
}
