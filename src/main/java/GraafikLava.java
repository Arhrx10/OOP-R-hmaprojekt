import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GraafikLava {
    Simulaator simulaator;

    public GraafikLava(Simulaator simulaator) {
        this.simulaator = simulaator;
    }

    public void show(Stage stage) {
        VBox juur = new VBox();
        juur.setPadding(new Insets(10));

        // Pealkiri
        Font font = new Font(20);
        Label pealkiri = new Label("Simulatsioon tulemused:");
        pealkiri.setFont(font);
        // Pealkiri keskele
        pealkiri.setMaxWidth(Double.MAX_VALUE);
        pealkiri.setAlignment(Pos.CENTER);
        juur.getChildren().add(pealkiri);

        // Graafik
        CategoryAxis xTelg = new CategoryAxis();
        NumberAxis yTelg = new NumberAxis();
        xTelg.setLabel("Väärtus");
        yTelg.setLabel("Simulatsiooni tulemuste arv");

        BarChart<String, Number> graafik = new BarChart<>(xTelg, yTelg);
        graafik.setTitle(null);
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Lisame kõik tulemused
        int maxX = simulaator.väärtusePiirkonnaSuurus();
        for (int i = 0; i < maxX; i++) {
            series.getData().add(new XYChart.Data<>(
                    String.valueOf(i + simulaator.miinimumTäringuteVäärtus()),
                    simulaator.getViimaneSimulatsioon().get(i)
            ));
        }
        graafik.getData().add(series);

        graafik.setBarGap(-3);
        graafik.setLegendVisible(false);
        VBox.setVgrow(graafik, Priority.ALWAYS);
        graafik.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        juur.getChildren().add(graafik);

        // Nupurida alumises osas
        HBox nupurida = new HBox(10);
        nupurida.setPadding(new Insets(8, 0, 0, 0));
        nupurida.setAlignment(Pos.CENTER_LEFT);

        Button tagasiNupp = new Button("Tagasi menüüsse");
        tagasiNupp.setOnAction(e -> new Menüü().show(stage));

        Button salvestaFailiNupp = new Button("Salvesta faili");
        salvestaFailiNupp.setOnAction(e -> FailiLiides.salvestaFaili("graafik.dat", simulaator));

        // vahe
        Region vahe = new Region();
        HBox.setHgrow(vahe, Priority.ALWAYS);

        // statistika
        Font statsFont = Font.font("System", FontWeight.NORMAL, 13);
        Label exSilt  = new Label("E(X) = " + simulaator.getKeskväärtus());
        Label dxSilt  = new Label("D(X) = " + simulaator.getDispersioon());
        Label stdSilt = new Label("σ = "    + simulaator.getStandardhälve());

        exSilt.setFont(statsFont);
        dxSilt.setFont(statsFont);
        stdSilt.setFont(statsFont);

        HBox statistika = new HBox(20, exSilt, dxSilt, stdSilt);
        statistika.setAlignment(Pos.CENTER_RIGHT);

        nupurida.getChildren().addAll(tagasiNupp, salvestaFailiNupp,vahe,statistika);
        juur.getChildren().add(nupurida);

        // vahetab juure
        stage.getScene().setRoot(juur);
        stage.setTitle("Simulatsioon");
    }
}