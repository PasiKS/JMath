package math;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import math.domain.Loki;
import math.domain.Tilaus;
import math.ui.Infonakyma;
import math.ui.Tilausnakyma;
import math.ui.Toimitusnakyma;

/**
 *
 * @author pasikiema
 * 
 * Latest update 30.4.2018
 */
public class Math extends Application {

    private Tilaus tilaus;
    private Loki loki;

    @Override
    public void init() throws Exception {
        this.tilaus = new Tilaus();
        this.loki = new Loki();
    }

    @Override
    public void start(Stage ikkuna) throws Exception {

        Tilausnakyma tilausnakyma = new Tilausnakyma(tilaus, loki);
        Toimitusnakyma toimitusnakyma = new Toimitusnakyma(loki);
        Infonakyma infonakyma = new Infonakyma(loki);

        BorderPane asettelu = new BorderPane();

        HBox valikko = new HBox();
        valikko.setPadding(new Insets(8, 8, 8, 8));
        valikko.setSpacing(10);

        Button infonappi = new Button("Info");
        Button tilausnappi = new Button("Tilaus");
        Button raporttinappi = new Button("Raportti");

        valikko.getChildren().addAll(infonappi, tilausnappi, raporttinappi);
        asettelu.setTop(valikko);

        infonappi.setOnAction((event) -> asettelu.setCenter(infonakyma.getNakyma()));
        tilausnappi.setOnAction((event) -> asettelu.setCenter(tilausnakyma.getNakyma()));
        raporttinappi.setOnAction((event) -> asettelu.setCenter(toimitusnakyma.getNakyma()));

        asettelu.setCenter(infonakyma.getNakyma());
        
        asettelu.setBottom(new Label("HUOM! Sisällöt on toteutettu toistaiseksi urheilun aihepiiristä."
                + " Muut seuraavat myöhemmin..."));

        Scene nakyma = new Scene(asettelu, 640, 396);

        ikkuna.setScene(nakyma);
        ikkuna.show();

    }

    public static void main(String[] args) {

        launch(Math.class);

    }

}
