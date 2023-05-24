import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
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
            assertThat(testDemo.addPositive(a, b), is(equalTo(expected)));
        } else {
            assertThatThrownBy(() -> testDemo.addPositive(a, b))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @NotNull
    private static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                arguments(2, 4, 6, false)
        );
    }

    @Test
    void assertThatNumberSquaredIsCorrect() {
        TestDemo testDemo = new TestDemo();
        TestDemo mockDemo = spy(testDemo);

        doReturn(5).when(mockDemo).getRandomInt();

        int fiveSquared = mockDemo.randomNumberSquared();

        assertThat(fiveSquared, is(equalTo(25)));
    }
}

