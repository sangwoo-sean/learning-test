package learning.tester.springBootTest;

import learning.tester.jpa.CompanyController;
import learning.tester.jpa.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebTestClient {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Sql("classpath:static/tableInit.sql")
    void webTestClient_expectBody_jsonPath() {
        webTestClient.get().uri("/company")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].companyName").isNotEmpty()
                .jsonPath("$[0].companyName").isEqualTo("ready_made_company_name");
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void webTestClient_expectBody_class() {
        webTestClient.get().uri("/company/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CompanyEntity.class);
    }


    @Test
    void webTestClient_expectBodyList() {
        webTestClient.get().uri("/company")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CompanyEntity.class);
    }

    @Test
    void bindToController() {
        WebTestClient.bindToController(companyController).build()
                .get().uri("/company")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CompanyEntity.class);
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void webTestClient_method() {
        webTestClient.method(HttpMethod.GET)
                .uri("/company/1")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(CompanyEntity.class);
    }
}
