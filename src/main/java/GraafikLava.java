import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraafikLava {
    Simulaator simulaator;

    public GraafikLava(Simulaator simulaator) {
        this.simulaator = simulaator;
    }

    public void show(Stage stage) {
        VBox juur = new VBox();

        // Pealkiri
        Font font = new Font(20);
        Label pealkiri = new Label("Simulatsioon tulemused:");
        pealkiri.setFont(font);
        pealkiri.setMaxWidth(Double.MAX_VALUE);
        pealkiri.setAlignment(Pos.CENTER);
        juur.getChildren().add(pealkiri);

        //Graafik
        CategoryAxis xTelg = new CategoryAxis();
        NumberAxis yTelg = new NumberAxis();

        xTelg.setLabel("Väärtus");
        yTelg.setLabel("Simulatsiooni tulemuste arv");

        BarChart<String, Number> graafik = new BarChart<>(xTelg, yTelg);
//        graafik.setTitle("Simulatsioon");
        graafik.setTitle(null);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        int maxX = simulaator.väärtusePiirkonnaSuurus();
//        int maxY = simulaator.maksimumTäringuteVäärtus();

        //Laeme sisse simulatsiooni tulemused
        for (int i = 0; i < maxX; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i+simulaator.miinimumTäringuteVäärtus()), simulaator.getViimaneSimulatsioon().get(i)));
        }
        graafik.getData().add(series);

        // Visuaalsed parandused graafikule
        graafik.setBarGap(-3);
        graafik.setLegendVisible(false);

        VBox.setVgrow(graafik, Priority.ALWAYS);
        graafik.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        juur.getChildren().add(graafik);

        // Nupp
        Button nupp = new Button();
        nupp.setText("Salvesta faili.");
        nupp.setOnMousePressed(e -> FailiLiides.salvestaFaili("graafik.dat", simulaator));
        nupp.setTranslateX(90);
        juur.getChildren().add(nupp);



        Scene scene = new Scene(juur, 800, 600);
        stage.setScene(scene);
        juur.minWidth(scene.getWidth());
        stage.setTitle("Simulatsioon");
        stage.show();
    }
}
