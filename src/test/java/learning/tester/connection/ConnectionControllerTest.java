package learning.tester.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ConnectionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void connectionTest() {
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class);
    }

    @Test
    void test2() {
        final String[] result = {"a", "b"};

        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .value(str -> result[0] = str);
        webTestClient.method(HttpMethod.GET)
                .uri("/connection")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .value(str -> result[1] = str);

        assertThat(result[0]).isNotEqualTo(result[1]); // session scope 이면 두 요청에 다른 uuid 가 생성된다
    }

}