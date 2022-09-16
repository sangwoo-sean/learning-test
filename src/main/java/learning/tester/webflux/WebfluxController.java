package learning.tester.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@Slf4j
public class WebfluxController {

    @GetMapping("mono")
    public Mono<String> mono() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Mono.just("hiiii");
    }

    @GetMapping("mono2")
    public Mono<String> mono2() {
        Mono<String> mono;
        try {
            mono = Mono.just("mono")
                    .doOnNext(log::info)
                    .log();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mono;
    }

    @GetMapping("flux")
    public Flux<Integer> flux() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1).limit(100);

        return Flux.interval(Duration.ofSeconds(3)).fromStream(stream);
//        return Flux.just("Hello", "World", "!");
    }

    @GetMapping("webclient")
    public Mono<String> webclient() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get()
                .uri("/mono")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("sub")
    public String sub() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        webClient.get()
                .uri("/mono")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);

        return "requesting...";
    }
}
