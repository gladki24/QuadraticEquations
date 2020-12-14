package pl.polsl.lab.quadraticequations.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.polsl.lab.quadraticequations.model.EquationIsNotQuadraticException;
import pl.polsl.lab.quadraticequations.model.QuadraticEquation;
import pl.polsl.lab.quadraticequations.model.QuadraticEquationResult;
import pl.polsl.lab.quadraticequations.model.StepIsNotPositiveException;

/**
 * Main application controller
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class MainController implements Initializable {

    /**
     * List of saved equations
     */
    @FXML
    private ListView<QuadraticEquation> equationsListView;

    /**
     * a factor text field
     */
    @FXML
    private TextField aFactorTextBox;

    /**
     * b factor text field
     */
    @FXML
    private TextField bFactorTextBox;

    /**
     * c factor text field
     */
    @FXML
    private TextField cFactorTextBox;

    /**
     * result equation text box
     */
    @FXML
    private TextField resultEquationTextBox;

    /**
     * first root or only root of equation text field
     */
    @FXML
    private TextField firstRootTextField;

    /**
     * second root of equation text field
     */
    @FXML
    private TextField secondRootTextField;

    /**
     * start range of integral text field
     */
    @FXML
    private TextField startRangeTextBox;

    /**
     * end rage of integral text field
     */
    @FXML
    private TextField endRangeTextBox;

    /**
     * step to calculate integral text field
     */
    @FXML
    private TextField stepTextBox;

    /**
     * result of integral calculation text field
     */
    @FXML
    private TextField integralResultTextBox;

    /**
     * active editable equation
     */
    private QuadraticEquation activeEquation;

    /**
     * Main Controller initialization
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // init equation
        activeEquation = new QuadraticEquation();

        // init equation factors
        activeEquation.setA(1);
        activeEquation.setB(1);
        activeEquation.setC(1);

        // init fields
        updateFactorFields();
        startRangeTextBox.setText(String.format("%.0f", -5.0));
        endRangeTextBox.setText(String.format("%.0f", 5.0));
        stepTextBox.setText(String.format("%.0f", 1.0));

        // display results
        updateResultFields();

        // listen selection change
        equationsListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, lastEquation, nextEquation) -> {
                    if (nextEquation != null) {
                        selectEquationFromList(nextEquation);
                    }
                });
    }

    /**
     * list view selection handler
     * @param equation - selected equation
     */
    private void selectEquationFromList(QuadraticEquation equation) {
        // update active equation
        activeEquation.setA(equation.getA());
        activeEquation.setB(equation.getB());
        activeEquation.setC(equation.getC());

        // update view
        updateFactorFields();
        updateResultFields();
    }

    /**
     * add new equation to list handler
     */
    @FXML
    private void addEquationToList() {
        // copy equation
        QuadraticEquation equation = new QuadraticEquation();
        equation.setA(activeEquation.getA());
        equation.setB(activeEquation.getB());
        equation.setC(activeEquation.getC());

        // add copy to list
        equationsListView.getItems().add(equation);
    }

    /**
     * remove selected equation from list handler
     */
    @FXML
    private void removeEquationFromList() {
        // remove selected equation
        QuadraticEquation selectedEquation = equationsListView.getSelectionModel().getSelectedItem();
        equationsListView.getItems().removeIf(quadraticEquation -> quadraticEquation == selectedEquation);
    }

    /**
     * display result of calculation
     * user input handler
     */
    @FXML
    private void updateResultFields() {
        this.updateIntegralResultField();
        this.updateRootsFields();
        this.updateEquationField();
    }

    /**
     * update factor fields
     * select equation from list handler
     */
    private void updateFactorFields() {
        aFactorTextBox.setText(String.format("%.0f", activeEquation.getA()));
        bFactorTextBox.setText(String.format("%.0f", activeEquation.getB()));
        cFactorTextBox.setText(String.format("%.0f", activeEquation.getC()));
    }

    /**
     * update equation field
     */
    private void updateEquationField() {
        try {
            // get factors from text fields and parse to double
            double aFactor = Double.parseDouble(aFactorTextBox.getText());
            double bFactor = Double.parseDouble(bFactorTextBox.getText());
            double cFactor = Double.parseDouble(cFactorTextBox.getText());

            // assign factors to equation
            activeEquation.setA(aFactor);
            activeEquation.setB(bFactor);
            activeEquation.setC(cFactor);

            // display result to text field
            resultEquationTextBox.setText(activeEquation.toString());

        } catch (NumberFormatException exception) {
            // handle invalid a, b, c field user input
            resultEquationTextBox.setText("Brak");
        }
    }

    /**
     * update roots fields
     * display roots of equation
     */
    private void updateRootsFields() {
        try {
            // get calculation result
            QuadraticEquationResult result = this.activeEquation.calculateResult();

            if (result.hasNoResult()) {
                // display result for no roots
                firstRootTextField.setText("Brak rozwiązań");
                secondRootTextField.setText("-");
            } else if (result.hasOneResult()) {
                // display result for one root
                firstRootTextField.setText(String.format("%g", result.getFirstResult()));
                secondRootTextField.setText("-");
            } else if (result.hasTwoResults()) {
                // display results for two roots
                firstRootTextField.setText(String.format("%g", result.getFirstResult()));
                secondRootTextField.setText(String.format("%g", result.getSecondResult()));
            }

        } catch (EquationIsNotQuadraticException exception) {
            // handle if equation is not quadratic
            firstRootTextField.setText("Równanie nie jest kwadratowe");
            secondRootTextField.setText("-");
        }
    }

    /**
     * update definite integral result field
     */
    private void updateIntegralResultField() {
        try {
            // get data from fields
            double startRange = Double.parseDouble(startRangeTextBox.getText());
            double endRange = Double.parseDouble(endRangeTextBox.getText());
            double step = Double.parseDouble(stepTextBox.getText());

            // limit range to keep acceptable performance
            if (Math.abs(startRange - endRange) > 200_000) {
                integralResultTextBox.setText("Został zdefiniowany zbyt szeroki przedział");
                return;
            }

            // limit step to keep acceptable performance
            if (step < 0.000_1) {
                integralResultTextBox.setText("Została zdefiniowana zbyt duża dokładność za pomocą kroku");
                return;
            }

            // display result in field
            integralResultTextBox.setText(
                    String.format("%g", activeEquation.calculateDefiniteIntegral(startRange, endRange, step))
            );

        } catch (NumberFormatException exception) {
            // handle invalid startRange and endRange fields value
            integralResultTextBox.setText("Niepoprawna wartość przedziałów");
        } catch (StepIsNotPositiveException exception) {
            // handle invalid step field value
            integralResultTextBox.setText("Niepoprawna wartość skoku");
        }
    }
}
