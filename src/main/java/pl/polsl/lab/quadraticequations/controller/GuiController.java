package pl.polsl.lab.quadraticequations.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
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

        // load template from fxml file
        URL name = MainController.class.getResource("/main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(name);

        // set scene
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        // pass application arguments
        MainController mainController = fxmlLoader.getController();
        List<String> initialValues = getParameters().getUnnamed();
        mainController.setInitialValues(initialValues != null ? initialValues : new ArrayList<String>());

        // configure stage
        stage.setTitle("Równania kwadratowe");
        stage.show();
    }

    /**
     * Graphic interface main method to start application
     *
     * @param args - run parameters
     */
    public static void main(String[] args) {
        launch(args);
    }
}
