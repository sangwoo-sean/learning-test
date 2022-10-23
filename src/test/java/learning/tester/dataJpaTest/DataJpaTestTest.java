package learning.tester.dataJpaTest;

import learning.tester.jpa.CompanyEntity;
import learning.tester.jpa.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataJpaTestTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("DB 연결 및 Redis 연결 테스트")
    void contextLoad() {
    }

    @Test
    @Sql("classpath:static/tableInit.sql")
    void sqlScriptRunTest() {
        CompanyEntity companyEntity = companyRepository.findById(1L).get();

        assertThat(companyEntity.getCompanyCode()).isEqualTo("ready_made_company_code");
        assertThat(companyEntity.getCompanyName()).isEqualTo("ready_made_company_name");
    }
}
