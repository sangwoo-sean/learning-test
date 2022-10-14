package learning.tester.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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
            assertEquals("public learning.tester.reflection.TargetClass()", constructors[0].toString());
            assertEquals("public learning.tester.reflection.TargetClass(boolean)", constructors[1].toString());
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

}
