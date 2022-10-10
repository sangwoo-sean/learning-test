package learning.tester.java;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JavaTest {

    @Nested
    class StringUtilsTest {

        @Test
        void hasText() {
            assertFalse(StringUtils.hasText(null));
            assertFalse(StringUtils.hasText(""));
            assertFalse(StringUtils.hasText("   "));
            assertTrue(StringUtils.hasText("  text "));
        }

        @Test
        void containsWhitespace() {
            assertFalse(StringUtils.containsWhitespace(""));
            assertTrue(StringUtils.containsWhitespace("   "));
            assertTrue(StringUtils.containsWhitespace("   A "));
            assertFalse(StringUtils.containsWhitespace("A"));
            assertFalse(StringUtils.containsWhitespace(null));
        }

        @Test
        void trimWhitespaces() {
            assertEquals("A", StringUtils.trimAllWhitespace("   A   "));
            assertEquals("A   ", StringUtils.trimLeadingWhitespace("   A   "));
            assertEquals("   A", StringUtils.trimTrailingWhitespace("   A   "));
        }

        @Test
        void trimCharacters() {
            assertEquals("BBAABB", StringUtils.trimLeadingCharacter("AABBAABB", 'A'));
            assertEquals("AABBAA", StringUtils.trimTrailingCharacter("AABBAABB", 'B'));
        }
    }

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
   }
}
