package pl.polsl.lab.quadraticequations.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store quadratic quadratic roots and check count of roots
 * @author Seweryn Gładysz
 * @version 1.1
 */
public class QuadraticEquationResult {

    /**
     * Non-parameter constructor
     * Init when quadratic equation has no roots
     */
    public QuadraticEquationResult() {
        count = QuadraticEquationResultCount.NoRoots;
    }

    /**
     * Init when quadratic equation has only one root
     * @param result - x0, init when quadratic equation has only one root
     */
    public QuadraticEquationResult(double result) {
        firstResult = result;
        count = QuadraticEquationResultCount.OneRoot;
    }

    /**
     * Init when quadratic equation has two roots
     * @param firstResult - x1, first root of quadratic equation.
     * @param secondResult - x2, second root of quadratic equation.
     */
    public QuadraticEquationResult(double firstResult, double secondResult) {
        this.firstResult = firstResult;
        this.secondResult = secondResult;
        count = QuadraticEquationResultCount.TwoRoots;
    }

    /**
     * First or only one root getter
     * @return x1 - first root of quadratic equation or only one root
     */
    public double getFirstResult() {
        return firstResult;
    }

    /**
     * Second root getter
     * @return x2 - second root of quadratic equation
     */
    public double getSecondResult() {
        return secondResult;
    }

    /**
     * First result or only one result of quadratic equation
     */
    private double firstResult;

    /**
     * Second result of quadratic equation
     */
    private double secondResult;

    /**
     * Count of results
     */
    private final QuadraticEquationResultCount count;

    /**
     * Check if equation has only one result
     * @return return true if equation has only one root
     */
    public boolean hasOneResult() {
        return count == QuadraticEquationResultCount.OneRoot;
    }

    /**
     * Check if equation has to results
     * @return return true if equation has two roots
     */
    public boolean hasTwoResults() {
        return count == QuadraticEquationResultCount.TwoRoots;
    }

    /**
     * Check if equation has no results
     * @return return true if equation has no roots
     */
    public boolean hasNoResult() {
        return count == QuadraticEquationResultCount.NoRoots;
    }

    /**
     * Get roots as list
     *
     * @return list of roots
     */
    public List<Double> toList() {
        List<Double> results = new ArrayList<>();

        if (hasTwoResults()) {
            results.add(secondResult);
            results.add(firstResult);
        } else if (hasOneResult()) {
            results.add(firstResult);
        }

        return results;
    }
}
