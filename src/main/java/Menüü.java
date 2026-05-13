import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

public class Menüü {

    public void show(Stage stage) {
        BorderPane juur = new BorderPane();
        juur.setPadding(new Insets(30));

        // kirjeldav tekst
        VBox ülaosa = new VBox(10);
        ülaosa.setPadding(new Insets(0, 0, 20, 0));

        Label pealkiri = new Label("Simuleerija");
        pealkiri.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label kirjeldus = new Label(
                "Sisesta matemaatiline võrrand, kus x tähistab täringu väärtust (1–6). Iga x simuleerib eraldi täringuviset.\n" +
                        "Näide: \"(x+x)*2+7\", kusjuures x+x on erinev 2*x -st, kuna iga sümbol x-ist simuleerib eraldi täringu väärtust, 2*x aga ainult ühe.\n"+
                        "NB! Jagamine ei ole toetatud."
        );
        kirjeldus.setWrapText(true);
        kirjeldus.setFont(Font.font("System", 13));

        ülaosa.getChildren().addAll(pealkiri, kirjeldus);
        juur.setTop(ülaosa);

        // --- Keskel: sisestusväli ja nupud ---
        HBox keskel = new HBox(20);
        keskel.setAlignment(Pos.CENTER_LEFT);
        keskel.setPadding(new Insets(20, 0, 0, 0));

        Label silt = new Label("Sisesta Võrrand:");
        silt.setFont(Font.font("System", 13));
        silt.setMinWidth(130);

        TextField sisend = new TextField();
        sisend.setPromptText("nt. x+x");
        sisend.setPrefWidth(300);
        sisend.setPrefHeight(35);

        VBox nupud = new VBox(10);
        nupud.setAlignment(Pos.CENTER_LEFT);

        Button simuleeriNupp = new Button("Simuleeri");
        simuleeriNupp.setPrefWidth(130);
        simuleeriNupp.setPrefHeight(35);

        Button laeFailistNupp = new Button("Lae Failist");
        laeFailistNupp.setPrefWidth(130);
        laeFailistNupp.setPrefHeight(35);

        nupud.getChildren().addAll(simuleeriNupp, laeFailistNupp);
        keskel.getChildren().addAll(silt, sisend, nupud);
        juur.setCenter(keskel);

        // nupud
        simuleeriNupp.setOnAction(e -> {
            String valem = sisend.getText().trim();
            if (valem.isEmpty()) {
                näitaViga("Tühi sisend", "Palun sisesta võrrand enne simuleerimist.");
                return;
            }
            try {
                Simulaator simulaator = new Simulaator(valem);
                simulaator.simuleeri(100000);
                new GraafikLava(simulaator).show(stage);
            } catch (Exception ex) {
                näitaViga("Vigane valem", "Võrrandit ei õnnestunud tõlgendada:\n" + ex.getMessage());
            }
        });

        laeFailistNupp.setOnAction(e -> {
            try {
                Simulaator simulaator = FailiLiides.loeFailist("graafik.dat");
                new GraafikLava(simulaator).show(stage);
            } catch (Exception ex) {
                näitaViga("Faili laadimine ebaõnnestus", "Faili \"graafik.dat\" ei leitud või see on vigane:\n" + ex.getMessage());
            }
        });

        // lava
        if (stage.getScene() == null) {
            Scene scene = new Scene(juur, 800, 600);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(juur);
        }
        stage.setTitle("Simuleerija");
        stage.show();
    }

    private void näitaViga(String pealkiri, String sõnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Viga");
        alert.setHeaderText(pealkiri);
        alert.setContentText(sõnum);
        alert.showAndWait();
    }
}