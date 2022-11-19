package learning.tester.spring;


import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileCopyUtilsTest {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String BASE_PATH = USER_DIR + "\\src\\test\\java\\resources\\";

    @Test
    void copyTest() {
        //given
        String testFilePath = BASE_PATH + "test.txt";
        File testFile = new File(testFilePath);
        String targetPathName = BASE_PATH + "target" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";
        File targetFile = new File(targetPathName);

        try {
            //when
            FileCopyUtils.copy(testFile, targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertTestFileEquals(testFilePath, targetPathName);

        //post process
        FileSystemUtils.deleteRecursively(targetFile);
    }

    private void assertTestFileEquals(String testFilePath, String targetPathName) {
        try {
            List<String> from = Files.readAllLines(Paths.get(testFilePath));
            List<String> to = Files.readAllLines(Paths.get(targetPathName));

            assertThat(from).isSubsetOf(to);
            assertThat(to).isSubsetOf(from);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
