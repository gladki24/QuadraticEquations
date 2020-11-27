package pl.polsl.lab.quadraticequations.model;

/**
 * Exception class for objects thrown when equations is not quadratic
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class EquationIsNotQuadraticException extends Exception {
    /**
     * Non-parameter constructor
     */
    public EquationIsNotQuadraticException() {
        super("Equation is not quadratic exception");
    }
}
