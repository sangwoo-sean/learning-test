package learning.tester.spring;

import org.junit.jupiter.api.Test;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;

class IdGeneratorTest {

    private IdGenerator idGenerator;

    @Test
    void SimpleIdGeneratorTest() {
        idGenerator = new SimpleIdGenerator();
        printNtimes();
    }

    @Test
    void JdkIdGeneratorTest() {
        idGenerator = new JdkIdGenerator();
        printNtimes();
    }

    @Test
    void AlternativeJdkIdGeneratorTest() {
        idGenerator = new AlternativeJdkIdGenerator();
        printNtimes();
    }

    private void printNtimes() {
        for (int i = 0; i < 10; i++) {
            System.out.println("uuid = " + idGenerator.generateId());
        }
    }
}
