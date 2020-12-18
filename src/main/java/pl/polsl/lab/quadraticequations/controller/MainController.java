package pl.polsl.lab.quadraticequations.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
     * @param url - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb - the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // init equation
        activeEquation = new QuadraticEquation();

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
     * set initial form values
     * @param arguments - application parameters for equation: ax^2 + bx + c,
     * where first parameter = a, second parameter = b, third parameter = c,
     * 4th parameter = star range, 5th parameter = end range, 6th parameter = step
     * Main application method. {@code main()} method manage application operations.
     */
    public void setInitialValues(List<String> arguments) {
        List<Double> initialValues = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        int index = 0;

        // parse arguments to double
        for (String argument : arguments) {
            try {
                initialValues.set(index, Double.parseDouble(argument));
            } catch (NumberFormatException exception) {
                initialValues.set(index, 0.0);
            }
            index++;
        }

        // init equation factors
        activeEquation.setA(initialValues.get(0));
        activeEquation.setB(initialValues.get(1));
        activeEquation.setC(initialValues.get(2));

        // init fields
        updateFactorFields();
        startRangeTextBox.setText(String.format("%.0f", initialValues.get(3)));
        endRangeTextBox.setText(String.format("%.0f", initialValues.get(4)));
        stepTextBox.setText(String.format("%.0f", initialValues.get(5)));

        // display results
        updateResultFields();
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
        this.updateEquationField();
        this.updateRootsFields();
        this.updateIntegralResultField();
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
