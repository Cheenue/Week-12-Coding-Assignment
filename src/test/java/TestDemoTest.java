import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class TestDemoTest {
    private TestDemo testDemo;

    @BeforeEach
    public void setUp() {
        TestDemo testDemo = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource ("TestDemoTest#argumentsForAddPositive")
    public void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
        if (!expectException) {
            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> testDemo.addPositive(a, b))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    private static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                Arguments.of(2, 3, 5),
                Arguments.of(0, 5, 0),
                Arguments.of(10, -2, 0),
                Arguments.of(7, 8, 15)
        );
    }

    void assertThatNumberSquaredIsCorrect() {
        TestDemo testDemo = new TestDemo();
        TestDemo mockDemo = spy(testDemo);

        doReturn(5).when(mockDemo).getRandomInt();

        int fiveSquared = mockDemo.randomNumberSquared();

        assertThat(fiveSquared).isEqualTo(25);
    }
}

