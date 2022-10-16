package learning.tester.java.reflection;

import org.springframework.stereotype.Component;

@Component
public class TargetClass {
    private boolean result;

    public TargetClass() {
        result = false;
    }

    public TargetClass(boolean result) {
        this.result = result;
    }

    public void targetMethod() {
        result = true;
    }

    public boolean isResult() {
        return result;
    }
}
