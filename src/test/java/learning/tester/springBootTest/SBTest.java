package learning.tester.springBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SBTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("classpath:static/tableInit.sql")
    void companyControllerTest() {
        ResponseEntity<String> exchange = restTemplate.exchange("/company", HttpMethod.GET, null, String.class);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isEqualTo("[{\"companyId\":1,\"companyCode\":\"ready_made_company_code\",\"companyName\":\"ready_made_company_name\"}]");
    }
}
