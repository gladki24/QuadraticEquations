package pl.polsl.lab.quadraticequations.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to store equation parameters,
 * calculate result of quadratic equation,
 * calculate function value and
 * calculate the definite integral
 *
 * @author Seweryn Gładysz
 * @version 1.2
 */
@Equation
public class QuadraticEquation {
    /**
     * Non-parameters constructor
     */
    public QuadraticEquation() {
    }

    /**
     * a factor in [a]x^2 + bx + c equation
     */
    private double a;

    /**
     * b factor in ax^2 + [b]x + c equation
     */
    private double b;

    /**
     * c factor in ax^2 + bx + [c] equation
     */
    private double c;

    /**
     * a factor in [a]x^2 + bx + c equation
     *
     * @return a getter
     */
    public double getA() {
        return a;
    }

    /**
     * set a factor in [a]x^2 + bx + c equation
     *
     * @param a - a setter
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * b factor in ax^2 + [b]x + c equation
     *
     * @return b getter
     */
    public double getB() {
        return b;
    }

    /**
     * set b factor in ax^2 + [b]x + c equation
     *
     * @param b - b setter
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * c factor in ax^2 + bx + [c] equation
     *
     * @return c getter
     */
    public double getC() {
        return c;
    }

    /**
     * set c factor in ax^2 + bx + [c] equation
     *
     * @param c - c setter
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * calculate results of equation and return model with roots
     * @return Calculation result {@link pl.polsl.lab.quadraticequations.model.QuadraticEquationResult}
     * @throws EquationIsNotQuadraticException - exception thrown when equation is not quadratic
     */
    public QuadraticEquationResult calculateResult() throws EquationIsNotQuadraticException {
        // check is quadratic equation
        if (!isQuadratic()) {
            throw new EquationIsNotQuadraticException();
        }

        // calculate delta
        double delta = calculateDelta();
        // calculate only one root for delta = 0
        if (delta == 0) {
            return new QuadraticEquationResult(calculateOnlyResult());
        }

        // return result object without roots
        if (delta < 0) {
            return new QuadraticEquationResult();
        }

        // return result object with two roots
        return new QuadraticEquationResult(calculateFirstResult(delta), calculateSecondResult(delta));
    }

    /**
     * calculate the definite integral in specified range
     *
     * @param start - beginning of the range of the calculated definite integral
     * @param end - end of range of the definite integral
     * @param step - determines the accuracy with which the integral will be calculated
     * @throws StepIsNotPositiveException - exception thrown when step is not positive
     * @return calculated integral determined by the trapezoidal method
     */
    public double calculateDefiniteIntegral(double start, double end, double step)throws StepIsNotPositiveException {
        if (step <= 0) {
            throw new StepIsNotPositiveException();
        }

        // if the end of the range is smaller than its start, swap start and end
        if (start > end) {
            double temp = end;
            end = start;
            start = temp;
        }

        double result = 0;
        double trapezoidRangeStart = start;

        // calculate the ranges values
        while (trapezoidRangeStart < end) {
            result += calculateIntervalDefiniteIntegral(trapezoidRangeStart, step);
            trapezoidRangeStart += step;
        }

        // calculate the last range which may be smaller than the step
        if (trapezoidRangeStart > end) {
            result += calculateIntervalDefiniteIntegral(trapezoidRangeStart -= step, end - trapezoidRangeStart);
        }

        return result;
    }

    /**
     * get factors as Map
     * @return factors as hashmap
     */
    public Map<String, Double> getFactors() {
        Map<String, Double> map = new HashMap<>();

        map.put("a", a);
        map.put("b", b);
        map.put("c", c);

        return map;
    }

    /**
     * transform equation to readable string
     * @return equation in readable format
     */
    @Override
    public String toString() {
        // init  message
        String message = "";

        // format pretty print for a factor
        if (a != 0) {
            if (a > 0) {
                message += String.format("%.3fx^2", a);
            } else {
                message += String.format("- %.3fx^2", a * -1);
            }
        }

        // format pretty print for b factor
        if (b != 0) {
            if (b > 0) {

                message += String.format(
                        a != 0 ? " + %.3fx" : "%.3fx",
                        b
                );
            } else {
                message += String.format(" - %.3fx", b * -1);
            }
        }

        // format pretty print for c factor
        if (c != 0) {
            if (c > 0) {
                message += String.format(
                        (b != 0 | a != 0) ? " + %.3f" : "%.3f",
                        c
                );
            } else {
                message += String.format(" - %.3f", c * -1);
            }
        }

        return message;
    }

    /**
     * calculate the definite integral in specified range by calculate trapezoid field
     *
     * @param x - function argument for which the base of the trapezoid will be calculated
     * @param step - determines height of the trapezoid
     * @return trapezoid field
     */
    private double calculateIntervalDefiniteIntegral(double x, double step) {
        return (calculateValue(x) + calculateValue(x + step)) * step / 2;
    }

    /**
     * calculate the value of the function at the given point
     * @param x point at which the function value will be calculated
     * @return value of the function at the given point
     */
    private double calculateValue(double x) {
        return (a * Math.pow(x, 2)) + (b * x) + c;
    }

    /**
     * calculate delta for quadratic equation
     *
     * @return calculated delta
     */
    private double calculateDelta() {
        return Math.pow(b, 2) - (4 * a * c);
    }

    /**
     * calculate result if delta = 0 and equation has only one result
     *
     * @return only one root of equation
     */
    private double calculateOnlyResult() {
        return (b * -1) / (2 * a);
    }

    /**
     * calculate first result of quadratic equation
     *
     * @param delta - delta of quadratic equation
     * @return first result of quadratic equation
     */
    private double calculateFirstResult(double delta) {
        return ((b * -1) + Math.sqrt(delta)) / (2 * a);
    }

    /**
     * calculate second result of quadratic equation
     *
     * @param delta - delta of quadratic equation
     * @return second result of quadratic equation
     */
    private double calculateSecondResult(double delta) {
        return ((b * -1) - Math.sqrt(delta)) / (2 * a);
    }

    /**
     * check equation is quadratic
     *
     * @return is quadratic equation
     */
    private boolean isQuadratic() {
        return a != 0;
    }
}
