package learning.tester.java;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorTest {

    @Nested
    class ErrorThrowTest {

        @Test
        void noMessage() {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        void withMessage() {
            try {
                throw new RuntimeException("message");
            } catch (RuntimeException e) {
                assertEquals("message", e.getMessage());
            }
        }

        @Test
        void getCause() {
            try {
                //given
                throw new RuntimeException("message");
            } catch (RuntimeException e) {
                //when
                e.initCause(new SQLException());

                //then
                assertTrue(e.getCause() instanceof SQLException);
                assertFalse(e.getCause() instanceof RuntimeException);
            }
        }
    }
}
