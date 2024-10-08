package at.ac.tuwien.sem2024w.ep1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static at.ac.tuwien.sem2024w.ep1.Main.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTest {
    private static final int[][] test1 = {{5}, {5, 7, 9}, {8, 5}, {}};
    private static final int[][] test2 = {{1, 2}, {1, 2, 3}, {1, 2, 3, 4}};
    private static final int[][] test3 = {{}, {1, 2, 3, 4}, {1}};
    private static final int[] seq = {1, 2, -5, 3, -1, 6, -3, 3};

    private static Stream<Arguments> getRectangularParams() {
        return Stream.of(
                Arguments.of(test1, new int[][] {{5, 5, 5}, {5, 7, 9}, {8, 5, 8}, {0, 0,0 }}),
                Arguments.of(test2, new int[][] {{1, 2, 1, 2}, {1, 2, 3, 1}, {1, 2, 3, 4}}),
                Arguments.of(test3, new int[][] {{0, 0, 0, 0}, {1, 2, 3, 4}, {1, 1, 1, 1}}),
                Arguments.of(new int[][] {{}}, new int[][] {{}})
        );
    }

    @ParameterizedTest
    @MethodSource("getRectangularParams")
    public void testGetRectangular(int[][] input, int[][] result) {
        assertArrayEquals(result, getRectangular(input));
    }

    private static Stream<Arguments> getRemoveEntryParams() {
        int[][] test2 = {{1, 2}, {1, 2, 3}, {1, 2, 3, 4}};
        int[][] test3 = {{}, {1, 2, 3, 4}, {1}};

        return Stream.of(
                Arguments.of(test2, 2, new int[][] {{1, 2}, {1, 2}, {1, 2, 4}}),
                Arguments.of(test3, 0, new int[][] {{}, {2, 3, 4}, {}})
        );
    }

    @ParameterizedTest
    @MethodSource("getRemoveEntryParams")
    public void testRemoveEntry(int[][] inputArray, int input, int[][] result) {
        removeEntry(inputArray, input);
        assertArrayEquals(result, inputArray);
    }

    private static Stream<Arguments> getIsAlternatingParameters() {
        Stream<Arguments> falseCases = IntStream.rangeClosed(0, 0)
                .mapToObj(index -> Arguments.of(seq, index, false));
        Stream<Arguments> trueCases = IntStream.range(1, seq.length)
                .mapToObj(index -> Arguments.of(seq, index, true));

        return Stream.concat(falseCases, trueCases);
    }

    @ParameterizedTest
    @MethodSource("getIsAlternatingParameters")
    public void testIsAlternating(int[] input, int index, boolean result) {
        assertEquals(result, isAlternating(input, index));
    }
}
