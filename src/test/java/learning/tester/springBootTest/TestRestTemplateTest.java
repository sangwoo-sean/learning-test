package learning.tester.springBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import learning.tester.jpa.CompanyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("classpath:static/tableInit.sql")
    void restTemplate_exchange() {
        ResponseEntity<String> exchange = restTemplate.exchange("/company", HttpMethod.GET, null, String.class);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(exchange.getBody()).isEqualTo("[{\"companyId\":1,\"companyCode\":\"ready_made_company_code\",\"companyName\":\"ready_made_company_name\"}]");
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void restTemplate_getForObjectTest() {
        String result = restTemplate.getForObject("/company", String.class);

        assertThat(result).isEqualTo("[{\"companyId\":1,\"companyCode\":\"ready_made_company_code\",\"companyName\":\"ready_made_company_name\"}]");
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void restTemplate_getForEntityTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/company", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("[{\"companyId\":1,\"companyCode\":\"ready_made_company_code\",\"companyName\":\"ready_made_company_name\"}]");
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void restTemplate_with_objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        String res = restTemplate.getForObject("/company", String.class);

        try {
            List<CompanyEntity> companyEntities = objectMapper.readValue(res, new TypeReference<List<CompanyEntity>>(){});

            assertThat(companyEntities.get(0).getCompanyName()).isEqualTo("ready_made_company_name");
            assertThat(companyEntities.get(0).getCompanyCode()).isEqualTo("ready_made_company_code");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void restTemplate_with_JsonPathRead() {
        String res = restTemplate.getForObject("/company", String.class);

        DocumentContext json = JsonPath.parse(res);

        assertThat((String) json.read("$[0].companyName")).isEqualTo("ready_made_company_name");
        assertThat((String) json.read("$[0].companyCode")).isEqualTo("ready_made_company_code");
    }
}
