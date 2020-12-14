package pl.polsl.lab.quadraticequations.controller;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Main JavaFX graphic user interface controller
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class GuiController extends Application {

    /**
     * start main stage of GUI
     *
     * @param stage - main stage of application
     * @throws IOException - throws input/output exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadMainFXML());
        stage.setScene(scene);
        stage.setTitle("Równania kwadratowe");
        stage.show();
    }

    /**
     * return main FXML file to init graphic user interface
     *
     * @return main fxml file
     * @throws IOException - throws input/output exception
     */
    private static Parent loadMainFXML() throws IOException {
        URL name = MainController.class.getResource("/main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(name);
        return fxmlLoader.load();
    }

    /**
     * Graphic interface main method to start application
     *
     * @param args - run parameters
     */
    public static void main(String[] args) {
        launch();
    }
    
}
