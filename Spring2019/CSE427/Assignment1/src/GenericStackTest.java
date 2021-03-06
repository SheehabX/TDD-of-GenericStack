import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {

    private GenericStack<String> testStack;

    @BeforeEach
    void setUp() {
        testStack = new GenericStack<String>();
    }

    @AfterEach
    void tearDown() {
        testStack = null;
    }

    /**
     *  Use of theory is not possible since Theory is dead for JUnit 5
     */


    /**
     * Parameterized Test for push method. It takes two
     * variable one is input and the second expected
     *
     * @param input
     * @param expected
     *
     */
    @ParameterizedTest
    @MethodSource("providePartitionedValueForPushTest")
    void pushTest(String input, String expected) {

        /**
         * push an object to the stack and then pop with for assertion to check
         *
         */
        testStack.push(input);
        assertEquals(expected, testStack.pop());
    }

    /**
     * Test cases using input space by partitioning the variables into block for push.
     * null values or valid values
     *
     */
    private static Stream<Arguments> providePartitionedValueForPushTest() {
        return Stream.of(
                Arguments.of("abc", "abc"),
                Arguments.of(null, null),
                Arguments.of("edf", "edf"),
                Arguments.of(null, null)
        );
    }

    /**
     * Parameterized Test for pop method. It takes two
     * variable one is input and the second expected
     *
     * @param input
     * @param expected
     *
     */
    @ParameterizedTest
    @MethodSource("providePartitionedValueForPopTest")
    void popTest(String input, String expected) {

        /**
         * pop an object to the stack for assertion to check
         * if returns the right object
         *
         */
        testStack.push(input);
        assertEquals(expected, testStack.pop());
    }

    /**
     * Test case using input space by partitioning the variables into block for pop.
     * null values or valid values
     *
     */
    private static Stream<Arguments> providePartitionedValueForPopTest() {
        return Stream.of(
                Arguments.of("abc", "abc"),
                Arguments.of(null, null),
                Arguments.of(null, null)
        );
    }



    /**
     * Test case for pop when no element is present in stack
     * should pass for exception
     * @Throws RuntimeException as the stack is empty.
     */
    @Test
    void popTestForExceptionWhenNoElementIsPresentInStack() {
        Exception exception = assertThrows(RuntimeException.class, () ->  testStack.pop());
        assertEquals("No object found in the stack to pop", exception.getMessage());
    }

    /**
     * Test cases for isEmpty method
     */
    @Test
    void isEmpty() {

        /**
         * Adding an object to check for un-empty stack.
         */
        testStack.push("Test String");
        assertFalse(testStack.isEmpty());
        
        /**
         * Cleaning up the stack check for empty stack.
         */
        testStack.pop();
        assertTrue(testStack.isEmpty());

    }
}
