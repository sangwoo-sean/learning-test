package learning.tester.java;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class JunitTest {

    @Test
    @EnabledOnOs(value = OS.MAC, disabledReason = "맥에서만 테스트")
    @Tag("test")
    void onlyOnMacOs() {}

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyOnWindowsOs() {}

    @Test
    @EnabledOnOs(value = {OS.MAC, OS.WINDOWS}, disabledReason = "맥과 윈도우에서만 테스트")
    void onlyOnMacAndWindowsOs() {}

    @Test
    @DisabledOnOs(value = OS.MAC, disabledReason = "맥에서만 테스트 제외")
    void notOnlyOnMacOs() {}

    @DisabledOnOs(OS.WINDOWS)
    @RepeatedTest(value = 5, name = "[{displayName}] {currentRepetition} / {totalRepetitions}")
    void notOnlyOnWindowsOs() {}

    @Test
    @DisabledOnOs(value = {OS.MAC, OS.WINDOWS}, disabledReason = "맥과 윈도우에서만 테스트 제외")
    void notOnlyOnMacAndWindowsOs() {}

}
