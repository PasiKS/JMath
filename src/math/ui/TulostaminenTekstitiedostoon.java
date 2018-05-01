package math.ui;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import math.domain.Tehtava;
import math.domain.Loki;
import math.domain.Tehtavasarja;
import math.domain.Toimitus;

public class TulostaminenTekstitiedostoon {

    private Loki loki;

    public TulostaminenTekstitiedostoon(Loki loki) {
        this.loki = loki;
    }

    public void tulosta(Toimitus toimitus) {
        String filename = "tehtavasarjat.txt";
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            int sarjaIndeksi = 1;
            for (Tehtavasarja tehtavasarja : toimitus.getToimitus()) {
                outputStream.println("TEHTÄVÄSARJA " + sarjaIndeksi + ": " + tehtavasarja.getAihepiiri() + "  taso: " + tehtavasarja.getTaso() + "\n");
                if (tehtavasarja.onTyhja()) {
                    outputStream.println("Tehtaäväsarjaa ei saatu luotua.");
                } else {
                    int tehtavaIndeksi = 1;
                    List<Tehtava> sekoitettuTehtavasarja = tehtavasarja.annaTehtavatSekoitettuna();
                    for (Tehtava tehtava : sekoitettuTehtavasarja) {
                        outputStream.println("TEHTÄVÄ " + tehtavaIndeksi);
                        outputStream.println(tehtava.getKysymys());
                        outputStream.println("\n\n");
                        tehtavaIndeksi++;
                    }
                    outputStream.println("\n\n\n\n\n");
                    outputStream.println("VASTAUKSET tehtäväsarjaan " + sarjaIndeksi + ": " + tehtavasarja.getAihepiiri() + "  taso: " + tehtavasarja.getTaso() + "\n");
                     tehtavaIndeksi = 1;
                    for (Tehtava tehtava : sekoitettuTehtavasarja) {
                        outputStream.println("TEHTÄVÄ " + tehtavaIndeksi);
                        outputStream.println(tehtava.getVastaus());
                        outputStream.println("\n\n");
                        tehtavaIndeksi++;
                    }
 
                }
                outputStream.println("\n\n\n\n\n");
                sarjaIndeksi++;
            }
            outputStream.close();
        } catch (FileNotFoundException ex) {
            loki.lisaaLokiin("Tulostaminen tiedostoon ei onnistunut.");
        }
    }
}
