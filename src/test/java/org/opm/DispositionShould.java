package org.opm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for {@link Disposition}
 */
class DispositionShould {

    @ParameterizedTest
    @MethodSource("enumStrings")
    void returnDispositionStringLowercase(Disposition disposition, String value) {
        // verify
        assertEquals(value, disposition.toString());
    }

    private static Stream<Arguments> enumStrings() {
        return Stream.of(
                Arguments.of(Disposition.INLINE, "inline"),
                Arguments.of(Disposition.ATTACHMENT, "attachment")
        );
    }
}