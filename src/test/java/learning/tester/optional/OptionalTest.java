package learning.tester.optional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalTest {

    @Test
    void optional_of() {
        Optional<String> optional = Optional.of("str");

        assertEquals("str", optional.get());
    }

    @Test
    void optional_ofNullable() {
        Optional<String> optional = Optional.ofNullable("str");
        Optional<String> optionalNull = Optional.ofNullable(null);

        assertEquals("str", optional.get());
        assertFalse(optionalNull.isPresent());
    }

    @Test
    void optional_empty() {
        Optional<String> optional = Optional.empty();

        assertFalse(optional.isPresent());
    }

    @Test
    void orElse() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        assertEquals("str", optional.orElse("else"));
        assertEquals("else", optionalEmpty.orElse("else"));
    }

    @Test
    void orElseThrow() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        assertEquals("str", optional.orElseThrow(RuntimeException::new));
        assertThrows(RuntimeException.class, () ->
                optionalEmpty.orElseThrow(RuntimeException::new)
        );
    }

    @Test
    void orElseGet() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        assertEquals("str", optional.orElseGet(() -> "else"));
        assertEquals("else", optionalEmpty.orElseGet(() -> "else"));
    }

    @Test
    void ifPresent() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        optional.ifPresent(s -> assertEquals("str", s));
        optionalEmpty.ifPresent(s -> fail());
    }

    @Test
    void filter() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        assertEquals("str", optional.filter(s -> s.equals("str")).get());
        assertFalse(optional.filter(s -> !s.equals("str")).isPresent());
        assertFalse(optionalEmpty.filter(s -> s.equals("str")).isPresent());
    }

    @Test
    void map() {
        Optional<String> optional = Optional.of("str");
        Optional<String> optionalEmpty = Optional.empty();

        assertEquals("str mapped", optional.map(s -> s + " mapped").get());
        assertFalse(optionalEmpty.map(s -> s + " mapped").isPresent());
    }

    @Test
    void mapList() {
        List<String> strList = new ArrayList<>();
        strList.add("foo");
        strList.add("bar");
        strList.add("baz");

        Optional<List<String>> optionalList = Optional.of(strList);

        assertEquals(3, optionalList.map(List::size).get());
    }
}
