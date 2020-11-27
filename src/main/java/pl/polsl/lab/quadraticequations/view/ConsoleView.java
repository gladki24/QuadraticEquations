package pl.polsl.lab.quadraticequations.view;

import java.util.Scanner;

/**
 * The ConsoleView class provides methods for displaying and scanning information to the user.
 *
 * @author Seweryn Gładysz
 * @version 1.1
 */
public class ConsoleView {

    /**
     * Non-parameter constructor
     */
    public ConsoleView() {
    }

    /**
     * Print equation to console
     *
     * @param a - a factor in [a]x^2 + bx + c equation
     * @param b - b factor in ax^2 + [b]x + c equation
     * @param c - c factor in ax^2 + bx + [c] equation
     */
    public void printEquation(double a, double b, double c) {
        // init  message
        String message = "Równanie: ";

        // format pretty print for a factor
        if (a != 0) {
            if (a > 0) {
                message += String.format("%gx^2", a);
            } else {
                message += String.format("- %gx^2", a * -1);
            }
        }

        // format pretty print for b factor
        if (b != 0) {
            if (b > 0) {
                message += String.format(" + %gx", b);
            } else {
                message += String.format(" - %gx", b * -1);
            }
        }

        // format pretty print for c factor
        if (c != 0) {
            if (b > 0) {
                message += String.format(" + %g", c);
            } else {
                message += String.format(" - %g", c * -1);
            }
        }

        System.out.println(message);
    }

    /**
     * Prints result of calculations if there are no solutions for quadratic equation.
     */
    public void printResults() {
        System.out.println("Brak rozwiązań");
    }

    /**
     * Prints result of calculation
     *
     * @param result - the only root of the quadratic equation.
     */
    public void printResults(double result) {
        String message = String.format("x0 = %g", result);
        System.out.println(message);
    }

    /**
     * Prints result of calculation.
     *
     * @param result1 - first root of the quadratic equation.
     * @param result2 - second root of the quadratic equation.
     */
    public void printResults(double result1, double result2) {
        String message = String.format("x1 = %g, x2 = %g", result1, result2);
        System.out.println(message);
    }

    /**
     * Prints result of calculating integral
     *
     * @param startRange - start range of calculated integral
     * @param endRange - end range of calculated integral
     * @param step - step of calculated integral;
     * @param result - integral value
     */
    public void printIntegralCalculationResult(double startRange, double endRange, double step, double result) {
        String message = String.format("Dla podanej funkcji w przedziale %g - %g o kroku %g wartość " +
                "całki oznaczonej wynosi %g", startRange, endRange, step, result);
        System.out.println(message);
    }

    /**
     * Prints information about application to console.
     */
    public void printAppInfo() {
        System.out.println("*****************************************");
        System.out.println("Aplikacja oblicza pierwiastki równania kwadratowego w dziedzinie liczb rzeczywistych.");
        printMethodOfUse();
        System.out.println("*****************************************\n");
    }

    /**
     * Prints an error regarding the number of parameters.
     */
    public void printParamsCountError() {
        System.out.println("Niepoprawna liczba parametrów.");
        printMethodOfUse();
    }

    /**
     * Prints an error regarding the format of parameters.
     */
    public void printParamsFormatError() {
        System.out.println("Niepoprawny format parametrów.");
        printMethodOfUse();
    }

    /**
     * Prints an error regarding the step is not positive value
     */
    public void printStepIsNotPositiveError() {
        System.out.println("Krok powinien być większy od 0. Nie można obliczyć całki.");
    }

    /**
     * Prints information about equation is not quadratic
     */
    public void printEquationIsNotQuadratic() {
        System.out.println("Równanie nie jest kwadratowe");
    }

    /**
     * Scan quadratic equation factor from user input
     *
     * @param factorName - inform user which quadratic equation factor will be scanned
     * @return quadratic equation factor from user input
     */
    public String scanParameterFromUserInput(String factorName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Podaj wartość współczynnika " + factorName.toUpperCase() + " w równaniu ax^2 + bx + c = 0");
        return scanner.next();
    }

    /**
     * Scan start of range calculated integral
     * @return start range of integral which will be calculated
     */
    public String scanStartOfCalculatedIntegral() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj początek przedziału obliczanej całki oznaczonej");
        return scanner.next();
    }

    /**
     * Scan end of range calculated integral
     * @return end range of integral which will be calculated
     */
    public String scanEndRangeOfCalculatedIntegral() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj koniec przedziału obliczanej całki");
        return scanner.next();
    }

    /**
     * Scan step calculated integral
     * @return step of integral which will be calculated
     */
    public String scanIntegralCalculationStep() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj krok dla obliczanej całki");
        return scanner.next();
    }

    /**
     * Prints information about method of use application.
     */
    private void printMethodOfUse() {
        System.out.println("Dla równania ax^2 + bx + c podaj argumenty," +
                " gdzie: a = pierwszy argument, b = drugi argument, c = trzeci argument.");

        System.out.println("początek przedziału obliczanej całki oznaczonej = czwarty parametr, " +
                "koniec przedziału obliczanej całki oznaczonej = piąty parametr," +
                "krok z jakim będzie liczona całka = szósty parametr");
    }
}
