package learning.tester.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WebfluxTest {

    @Test
    void Mono_빈값() {
        Mono.empty()
                .subscribe(o -> assertEquals("", o.toString()));
    }
    @Test
    void Flux_빈값() {
        Flux.empty()
                .subscribe(o -> assertEquals("", o.toString()));
    }
    @Test
    void Mono_1개의값() {
        Mono.just("foo")
                .subscribe(o -> assertEquals("foo", o));
    }
    @Test
    void Flux_바로_subscribe하면_여러개의_결과가_나온다() {
        List<String> result = new ArrayList<>();

        Flux.just("foo", "bar", "baz")
                .subscribe(result::add);

        multipleStringEquals(result, "foo", "bar", "baz");
    }
    @Test
    void Flux_딜레이하면_결과가_비동기처리된다() {
        List<String> result = new ArrayList<>();

        Flux.just("foo", "bar", "baz")
                .delaySubscription(Duration.ofSeconds(1))
                .subscribe(result::add);

        assertEquals(0, result.size());

        try {
            Thread.sleep(500);
            assertEquals(0, result.size());

            Thread.sleep(1000);
            multipleStringEquals(result, "foo", "bar", "baz");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void Flux_Array에서_값_받아오기() {
        List<String> result = new ArrayList<>();

        Flux.fromArray(new String[]{"foo", "bar", "baz"})
                .subscribe(result::add);

        multipleStringEquals(result, "foo", "bar", "baz");
    }
    @Test
    void Flux_ArrayList에서_값_받아오기() {
        List<String> result = new ArrayList<>();
        List<String> values = Arrays.asList("foo", "bar", "baz");

        Flux.fromIterable(values)
                .subscribe(result::add);

        multipleStringEquals(result, "foo", "bar", "baz");
    }
    @Test
    void Flux_ArrayList에_값_추가해서_받아오기() {
        List<String> result = new ArrayList<>();
        List<String> values = Arrays.asList("foo", "bar", "baz");

        Flux.fromIterable(values)
                .concatWith(Flux.just("qux", "quux"))
                .subscribe(result::add);

        multipleStringEquals(result, "foo", "bar", "baz", "qux", "quux");
    }

    // 테스트 편의 메소드
    private void multipleStringEquals(List<String> result, String... values) {
        assertEquals(result.size(), values.length);
        for (int i = 0; i < result.size(); i++) {
            assertEquals(values[i], result.get(i));
        }
    }

}