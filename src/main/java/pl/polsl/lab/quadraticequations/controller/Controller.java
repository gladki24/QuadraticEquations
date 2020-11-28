package pl.polsl.lab.quadraticequations.controller;

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
        // https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174
        GuiController.main(args);
    }
}
