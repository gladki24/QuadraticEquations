package pl.polsl.lab.quadraticequations.controller;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Main JavaFX graphic user interface controller
 *
 * @author Seweryn Gładysz
 */
public class GuiController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadMainFXML());
        stage.setScene(scene);
        stage.setTitle("Równania kwadratowe");
        stage.show();
    }

    private static Parent loadMainFXML() throws IOException {
        URL name = MainController.class.getResource("/main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(name);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
}
