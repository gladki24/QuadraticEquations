package pl.polsl.lab.quadraticequations.controller;

/**
 * Main class of application uses to manage application operations
 *
 * @author Seweryn Gładysz
 * @version 1.2
 */
public class Controller {

    /**
     * Main method of application
     *
     * @param args - application parameters for equation: ax^2 + bx + c,
     *             where first parameter = a, second parameter = b, third parameter = c,
     *             4th parameter = star range, 5th parameter = end range, 6th parameter = step
     *             Main application method. {@code main()} method manage application operations.
     */
    public static void main(String[] args) {
        // call javaFX gui controller
        GuiController.main(args);
    }
}
