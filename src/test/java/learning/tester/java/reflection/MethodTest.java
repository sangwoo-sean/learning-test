package learning.tester.java.reflection;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MethodTest {

    @Test
    void invoke_Method() {
        try {
            //given
            Class aClass = TargetClass.class;
            Method targetMethod = aClass.getMethod("targetMethod");
            TargetClass obj = new TargetClass();
            assertFalse(obj.isResult());

            //when
            targetMethod.invoke(obj);

            //then
            assertTrue(obj.isResult());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getConstructors() {
        //given
        Class aClass = TargetClass.class;
        Constructor[] constructors = aClass.getConstructors();

        try {
            assertEquals("public learning.tester.java.reflection.TargetClass()", constructors[0].toString());
            assertEquals("public learning.tester.java.reflection.TargetClass(boolean)", constructors[1].toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void newInstance() {
        //given
        Class aClass = TargetClass.class;
        Constructor[] constructors = aClass.getConstructors();

        try {
            //when
            TargetClass targetClass1 = (TargetClass) constructors[0].newInstance();
            TargetClass targetClass2 = (TargetClass) constructors[1].newInstance(true);
            TargetClass targetClass3 = (TargetClass) constructors[1].newInstance(false);

            //then
            assertFalse(targetClass1.isResult());
            assertTrue(targetClass2.isResult());
            assertFalse(targetClass3.isResult());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getMethods() {
        Method[] methods = TargetClass.class.getMethods();
        boolean result = Arrays.stream(methods)
                .anyMatch(method ->
                        method.toString().equals("public boolean java.lang.Object.equals(java.lang.Object)"));
        assertTrue(result);
        boolean result2 = Arrays.stream(methods)
                .anyMatch(method ->
                        method.toString().equals("public boolean learning.tester.java.reflection.TargetClass.isResult()"));
        assertTrue(result2);
    }

    @Test
    void declaredMethods() {
        Method[] methods = TargetClass.class.getDeclaredMethods();
        boolean result = Arrays.stream(methods)
                .anyMatch(method ->
                        method.toString().equals("public boolean java.lang.Object.equals(java.lang.Object)"));
        assertFalse(result);
        boolean result2 = Arrays.stream(methods)
                .anyMatch(method ->
                        method.toString().equals("public boolean learning.tester.java.reflection.TargetClass.isResult()"));
        assertTrue(result2);
    }

    @Test
    void getAnnotations() {
        Annotation[] annotations = TargetClass.class.getAnnotations();
        assertEquals("interface org.springframework.stereotype.Component", annotations[0].annotationType().toString());
    }

    @Test
    void getName() {
        String name = TargetClass.class.getName();
        assertEquals("learning.tester.java.reflection.TargetClass", name);
    }

    @Test
    void getSimpleName() {
        String name = TargetClass.class.getSimpleName();
        assertEquals("TargetClass", name);
    }

    @Test
    void getPackage() {
        String name = TargetClass.class.getPackage().getName();
        assertEquals("learning.tester.java.reflection", name);
    }
}
