package pl.polsl.lab.quadraticequations.model.specs;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.lab.quadraticequations.model.QuadraticEquationResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link pl.polsl.lab.quadraticequations.model.QuadraticEquationResult} class
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class QuadraticEquationResultSpecs {

    /**
     * Should has one root, when was initialized by one value
     * @param root - root of quadratic equation
     */
    @ParameterizedTest
    @ValueSource(doubles = {-1, 5, 1.2, 1000})
    public void shouldHasOneResult(double root) {
        QuadraticEquationResult result = new QuadraticEquationResult(root);
        assertTrue(result.hasOneResult(), "Result should has only one root");
    }

    /**
     * Should has different count of roots than one
     */
    @Test
    void shouldNotHasOneResult() {
        QuadraticEquationResult resultTwoRoots = new QuadraticEquationResult(-2, 10);
        assertFalse(resultTwoRoots.hasOneResult(), "Result shouldn't has one root if init with two roots");

        QuadraticEquationResult resultNoRoots = new QuadraticEquationResult();
        assertFalse(resultNoRoots.hasOneResult(), "Result shouldn't has one root if init with no roots");
    }

    /**
     * Should has two roots, when was initialized by two values
     */
    @Test
    public void shouldHasTwoResults() {
        QuadraticEquationResult result = new QuadraticEquationResult(5, 10);
        assertTrue(result.hasTwoResults(), "Result should has two roots");
    }

    /**
     * Should has different count of roots than two
     */
    @Test
    public void shouldNotHasTwoResults() {
        QuadraticEquationResult resultNoRoots = new QuadraticEquationResult();
        assertFalse(resultNoRoots.hasTwoResults(), "Result shouldn't has two roots if init with no roots");

        QuadraticEquationResult resultOneRoot = new QuadraticEquationResult(7);
        assertFalse(resultOneRoot.hasTwoResults(), "Result shouldn't has two roots if init with one root");
    }

    /**
     * Should has no root, when non-parameter constructor was used
     */
    @Test
    public void shouldHasNoResult() {
        QuadraticEquationResult result = new QuadraticEquationResult();
        assertTrue(result.hasNoResult(), "Result should has no roots");
    }

    /**
     * Should has different count of roots than zero
     */
    @Test
    public void shouldNotHasNoResult() {
        QuadraticEquationResult resultOneRoot = new QuadraticEquationResult(6);
        assertFalse(resultOneRoot.hasNoResult(), "Result shouldn't has no roots, if init with one root");

        QuadraticEquationResult resultTwoRoot = new QuadraticEquationResult(6, -9);
        assertFalse(resultTwoRoot.hasNoResult(), "Result shouldn't has no roots, if init with two roots");
    }

    /**
     * Should return empty list of roots if equation has no roots
     */
    @Test
    public void shouldReturnEmptyListOfResults() {
        QuadraticEquationResult result = new QuadraticEquationResult();
        List<Double> roots = result.toList();

        assertEquals(0, roots.size(), "Should has empty list of roots");
    }

    /**
     * Should return filled list of roots if equation has root or roots
     */
    @Test
    public void shouldReturnFilledListOfResults() {
        QuadraticEquationResult resultOneRoot = new QuadraticEquationResult(1);
        List<Double> roots = resultOneRoot.toList();

        assertEquals(1, roots.size(), "Should has one root in list");

        QuadraticEquationResult resultTwoRoots = new QuadraticEquationResult(-8, 2);
        roots = resultTwoRoots.toList();

        assertEquals(2, roots.size(), "Should has two roots in list");
    }

    /**
     * Should return list of valid roots
     */
    @Test
    public void shouldReturnListWithValidRoots() {
        QuadraticEquationResult resultOneRoot = new QuadraticEquationResult(6);
        List<Double> roots = resultOneRoot.toList();

        assertTrue(roots.contains(6d), "Should has same roots in list if has one root");

        QuadraticEquationResult resultTwoRoots = new QuadraticEquationResult(-12, 100);
        roots = resultTwoRoots.toList();
        assertTrue(roots.contains(-12d), "Should has same roots in list if has two roots");
        assertTrue(roots.contains(100d), "Should has same roots in list if has two roots");
    }
}
