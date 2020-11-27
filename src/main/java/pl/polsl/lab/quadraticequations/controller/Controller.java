package pl.polsl.lab.quadraticequations.controller;

import pl.polsl.lab.quadraticequations.model.EquationIsNotQuadraticException;
import pl.polsl.lab.quadraticequations.model.QuadraticEquation;
import pl.polsl.lab.quadraticequations.model.QuadraticEquationResult;
import pl.polsl.lab.quadraticequations.model.StepIsNotPositiveException;
import pl.polsl.lab.quadraticequations.view.ConsoleView;

/**
 * Main class of application uses to manage application operations
 *
 * @author Seweryn Gładysz
 * @version 1.1
 */
public class Controller {

    /**
     * Main method of application
     *
     * @param args - application parameters for equation: ax^2 + bx + c,
     *             where first parameter = a, second parameter = b, third parameter = c
     *             Main application method. {@code main()} method manage application operations.
     */
    public static void main(String args[]) {
        // View initialization
        ConsoleView view = new ConsoleView();
        view.printAppInfo();
        String a, b, c, startRangeParameter, endRangeParameter, stepParameter;
        double startRange, endRange, step;

        // check params count
        if (args.length < 6) {
            view.printParamsCountError();

            // get parameters from user input instead of program arguments
            a = view.scanParameterFromUserInput("a");
            b = view.scanParameterFromUserInput("b");
            c = view.scanParameterFromUserInput("c");
            startRangeParameter = view.scanStartOfCalculatedIntegral();
            endRangeParameter = view.scanEndRangeOfCalculatedIntegral();
            stepParameter = view.scanIntegralCalculationStep();

        } else {
            // get values from program arguments
            a = args[0];
            b = args[1];
            c = args[2];
            startRangeParameter = args[3];
            endRangeParameter = args[4];
            stepParameter = args[5];
        }

        // model initialization
        QuadraticEquation quadraticEquation = new QuadraticEquation();

        try {
            // parse parameters to double type and set equation parameters
            quadraticEquation.setA(Double.parseDouble(a));
            quadraticEquation.setB(Double.parseDouble(b));
            quadraticEquation.setC(Double.parseDouble(c));

            // parse parameters to double type
            startRange = Double.parseDouble(startRangeParameter);
            endRange = Double.parseDouble(endRangeParameter);
            step = Double.parseDouble(stepParameter);
        } catch (NumberFormatException e) {
            // display error about parameters format
            view.printParamsFormatError();
            return;
        }

        // print equation
        view.printEquation(quadraticEquation.getA(), quadraticEquation.getB(), quadraticEquation.getC());

        try {
            // display roots calculation result
            QuadraticEquationResult results = quadraticEquation.calculateResult();

            // print result to console depend on results count
            if (results.hasNoResult()) {
                view.printResults();
            } else if (results.hasOneResult()) {
                view.printResults(results.getFirstResult());
            } else if (results.hasTwoResults()) {
                view.printResults(results.getFirstResult(), results.getSecondResult());
            }

            // display integral calculation result
            try {
                double result = quadraticEquation.calculateDefiniteIntegral(startRange, endRange, step);
                view.printIntegralCalculationResult(startRange, endRange, step, result);
            } catch (StepIsNotPositiveException exception) {
                view.printStepIsNotPositiveError();
            }

        } catch (EquationIsNotQuadraticException exception) {
            // display information about wrong equation
            view.printEquationIsNotQuadratic();
        }

    }
}
