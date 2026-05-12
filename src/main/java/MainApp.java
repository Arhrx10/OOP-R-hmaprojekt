import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Simulaator simulaator = FailiLiides.loeFailist("graafik.dat");

//        Simulaator simulaator = new Simulaator();
//
//        simulaator.setValem("x+x");
//        simulaator.simuleeri(100000);

        new GraafikLava(simulaator).show(stage);
    }
}
