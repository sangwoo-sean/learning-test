package learning.tester.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ConnectionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void test() {
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ConnectionPojo.class)
                .value(connectionPojo -> System.out.println("connectionPojo = " + connectionPojo));
    }

    @Test
    void test2() {
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ConnectionPojo.class)
                .value(connectionPojo -> System.out.println("connectionPojo = " + connectionPojo.getData()));
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ConnectionPojo.class)
                .value(connectionPojo -> System.out.println("connectionPojo = " + connectionPojo.getData()));
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ConnectionPojo.class)
                .value(connectionPojo -> System.out.println("connectionPojo = " + connectionPojo.getData()));
    }

}