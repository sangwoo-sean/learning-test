package learning.tester.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonObjectMapperTest {

    private final ObjectMapper mapper;

    public JacksonObjectMapperTest() {
        this.mapper = new ObjectMapper();
    }

    @Nested
    class Deserialize {
        @Test
        void LOWER_CAMEL_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"testField\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void UPPER_CAMEL_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"TestField\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void SNAKE_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"test_field\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void UPPER_SNAKE_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"TEST_FIELD\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void LOWER_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"testfield\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void KEBAB_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"test-field\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void LOWER_DOT_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_DOT_CASE);
            try {
                TestClass testClass = mapper.readValue("{\"test.field\": \"str\"}", TestClass.class);
                assertThat(testClass.getTestField()).isEqualTo("str");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Nested
    class Serialize {

        private TestClass testClass;

        private Serialize() {
            testClass = new TestClass();
            testClass.setTestField("str");
        }

        @Test
        void LOWER_CAMEL_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"testField\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void UPPER_CAMEL_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"TestField\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void SNAKE_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"test_field\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void UPPER_SNAKE_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"TEST_FIELD\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void LOWER_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"testfield\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void KEBAB_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"test-field\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        void LOWER_DOT_CASE() {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_DOT_CASE);
            try {
                String s = mapper.writeValueAsString(testClass);
                assertThat(s).isEqualTo("{\"test.field\":\"str\"}");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
