package learning.tester.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class WebfluxController {

    @GetMapping("flux")
    public String webflux() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello";
    }

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

}
