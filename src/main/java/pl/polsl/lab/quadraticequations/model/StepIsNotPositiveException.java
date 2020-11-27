package pl.polsl.lab.quadraticequations.model;

/**
 * Exception class for objects thrown when step is not positive number during calculate integral
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class StepIsNotPositiveException extends Exception {
    /**
     * Non-parameter constructor
     */
    public StepIsNotPositiveException() {
        super("Step is negative");
    }
}
