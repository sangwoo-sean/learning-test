package learning.tester.java;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class SpringUtilTest {

    @Test
    void hasLength() {
        assertFalse(StringUtils.hasLength(null));
        assertFalse(StringUtils.hasLength(""));
        assertTrue(StringUtils.hasLength("   "));
        assertTrue(StringUtils.hasLength("  text "));
    }

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
