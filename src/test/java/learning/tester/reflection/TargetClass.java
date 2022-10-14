package learning.tester.reflection;

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
