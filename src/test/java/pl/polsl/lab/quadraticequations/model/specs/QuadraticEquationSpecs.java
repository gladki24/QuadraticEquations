package pl.polsl.lab.quadraticequations.model.specs;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import pl.polsl.lab.quadraticequations.model.EquationIsNotQuadraticException;
import pl.polsl.lab.quadraticequations.model.QuadraticEquation;
import pl.polsl.lab.quadraticequations.model.QuadraticEquationResult;
import pl.polsl.lab.quadraticequations.model.StepIsNotPositiveException;

import java.util.List;
import java.util.Map;

/**
 * Unit tests for {@link pl.polsl.lab.quadraticequations.model.QuadraticEquation} class
 *
 * @author Seweryn Gładysz
 * @version 1.0
 */
public class QuadraticEquationSpecs {

    private QuadraticEquation equation;

    /**
     * Initialize equation object for each test
     */
    @BeforeEach
    public void setUp() {
        equation = new QuadraticEquation();
    }

    /**
     * Should throw exception if equation is not quadratic
     */
    @Test
    public void shouldThrowEquationIsNotQuadraticEquation() {
        equation.setA(0);

        EquationIsNotQuadraticException exception = assertThrows(EquationIsNotQuadraticException.class,
                () -> equation.calculateResult(), "Should throw exception if not quadratic");

        assertNotNull(exception, "Should throw valid exception");
    }

    /**
     * Should not throw exception if equation is quadratic
     *
     * @param factorA - factor a in quadratic equation
     */
    @ParameterizedTest
    @ValueSource(doubles = {.01, .1, 1, 10, 100, 2, -1, -2, -10, -100})
    public void shouldNotThrowEquationIsNotQuadraticEquation(double factorA) {
        equation.setA(factorA);

        try {
            equation.calculateResult();
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return result with two roots
     */
    @Test
    public void shouldReturnQuadraticEquationResultWithNoRoots() {
        equation.setA(-2);
        equation.setB(5);
        equation.setC(-2);

        try {
            QuadraticEquationResult result = equation.calculateResult();
            assertTrue(result.hasTwoResults(), "Should has two roots");
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return result with one root
     */
    @Test
    public void shouldReturnQuadraticEquationResultWithOneRoot() {
        equation.setA(-1);
        equation.setB(2);
        equation.setC(-1);

        try {
            QuadraticEquationResult result = equation.calculateResult();
            assertTrue(result.hasOneResult(), "Should has one root");
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return result with no roots
     */
    @Test
    public void shouldReturnQuadraticEquationResultWithTwoRoot() {
        equation.setA(10);
        equation.setB(5);
        equation.setC(10);

        try {
            QuadraticEquationResult result = equation.calculateResult();
            assertTrue(result.hasNoResult(), "Should has no roots");
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return valid roots
     */
    @Test
    public void shouldReturnQuadraticEquationResultWithTwoValidRoots() {
        equation.setA(-2);
        equation.setB(5);
        equation.setC(-2);

        try {
            QuadraticEquationResult result = equation.calculateResult();
            List<Double> roots = result.toList();

            assertTrue(roots.contains(2d), "Should has valid root");
            assertTrue(roots.contains(0.5d), "Should has valid root");
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return valid root
     */
    @Test
    public void shouldReturnQuadraticEquationResultWithOneValidRoot() {
        equation.setA(-1);
        equation.setB(2);
        equation.setC(-1);

        try {
            QuadraticEquationResult result = equation.calculateResult();
            List<Double> roots = result.toList();

            assertTrue(roots.contains(1d), "Should has valid root");
        } catch (EquationIsNotQuadraticException exception) {
            fail("Should not throw exception if equation is quadratic");
        }
    }

    /**
     * Should return valid Map of equation factors
     */
    @Test
    public void shouldReturnValidFactorsMap() {
        equation.setA(10);
        equation.setB(-5);
        equation.setC(.2);

        Map<String, Double> factors = equation.getFactors();

        // contains valid keys
        assertTrue(factors.containsKey("a"), "Should contains valid a factory");
        assertTrue(factors.containsKey("b"), "Should contains valid b factory");
        assertTrue(factors.containsKey("c"), "Should contains valid c factory");

        // contains valid values
        assertEquals(factors.get("a"), 10d, "should contains valid a factor value");
        assertEquals(factors.get("b"), -5d, "should contains valid b factor value");
        assertEquals(factors.get("c"), .2d, "should contains valid c factor value");
    }

    /**
     * Should throw exception if step is not positive
     */
    @Test
    public void shouldThrowStepIsNotPositiveExceptionIsStepIsNotPositive() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        StepIsNotPositiveException exception = assertThrows(StepIsNotPositiveException.class,
                () -> equation.calculateDefiniteIntegral(-10, 10, -2),
                "Should throw exception if step is not positive");

        assertNotNull(exception, "Should throw valid exception");
    }

    /**
     * Should throw exception if step is zero
     */
    @Test
    public void shouldThrowStepIsNotPositiveExceptionIsStepIsZero() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        StepIsNotPositiveException exception = assertThrows(StepIsNotPositiveException.class,
                () -> equation.calculateDefiniteIntegral(-10, 10, 0),
                "Should throw exception if step is zero");

        assertNotNull(exception, "Should throw valid exception");
    }

    /**
     * Should not throw exception if step is positive
     */
    @Test
    public void shouldNotThrowExceptionIfStepIsPositive() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        try {
            equation.calculateDefiniteIntegral(-10, 10, 0.1);
        } catch (StepIsNotPositiveException exception) {
            fail("Should calculate valid definite integral for positive step");
        }
    }

    /**
     * Should return valid definite integral value
     */
    @Test
    public void shouldCalculateValidDefiniteIntegral() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        try {
            double result = equation.calculateDefiniteIntegral(-10, 10, 1);
            assertEquals(result, 1533d, 10, "Should return valid definite integral value");
        } catch (StepIsNotPositiveException exception) {
            fail("Should calculate valid definite integral for positive step");
        }
    }

    /**
     * Should return valid definite integral value for flipped ends of the compartment
     */
    @Test
    public void shouldCalculateValidDefiniteIntegralForFlippedEndsOfTheCompartment() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        try {
            double result = equation.calculateDefiniteIntegral(10, -10, 1);
            assertEquals(result,
                    1533d,
                    10,
                    "Should return valid definite integral value for flipped ends of the compartment");
        } catch (StepIsNotPositiveException exception) {
            fail("Should calculate valid definite integral for positive step");
        }
    }

    /**
     * Should return valid definite integral value for low step value
     */
    @Test
    public void shouldCalculateValidDefiniteIntegralForLowStepValue() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        try {
            double result = equation.calculateDefiniteIntegral(-10, 10, 0.0001);
            assertEquals(result,
                    1533d,
                    1,
                    "Should return valid definite integral value for low step value");
        } catch (StepIsNotPositiveException exception) {
            fail("Should calculate valid definite integral for positive step");
        }
    }

    /**
     * Should return valid definite integral value for range length equals with step
     */
    @Test
    public void ShouldCalculateValidDefiniteIntegralForRangeLengthEqualsWithStep() {
        equation.setA(2);
        equation.setB(5);
        equation.setC(10);

        try {
            double result = equation.calculateDefiniteIntegral(0, 1, 1);
            assertEquals(result, 13d, 10);
        } catch (StepIsNotPositiveException exception) {
            fail("Should calculate valid definite integral for positive step");
        }
    }
}
