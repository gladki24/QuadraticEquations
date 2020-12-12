package pl.polsl.lab.quadraticequations.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import pl.polsl.lab.quadraticequations.model.QuadraticEquation;

/**
 * Main application controller
 *
 * @author Seweryn Gładysz
 */
public class MainController implements Initializable {

    public List<QuadraticEquation> getEquations() {
        return equations;
    }

    private List<QuadraticEquation> equations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
