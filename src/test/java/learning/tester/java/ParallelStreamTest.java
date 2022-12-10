package learning.tester.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelStreamTest {

    long startTime;

    @BeforeEach
    void beforeEach() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void afterEach() {
        System.out.println("Method took " + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Test
    void normalStream() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.stream().forEach(number -> {
            slowTask();
            System.out.println(number + " " + Thread.currentThread().getName());
        });
    }

    @Test
    void parallelStream() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.parallelStream().forEach(number -> {
            slowTask();
            System.out.println(number + " " + Thread.currentThread().getName());
        });
    }

    @Test
    void parallelStreamSplitSources() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(5, (a, b) -> {
            System.out.println(a + " + " + b + ": " + Thread.currentThread().getName());
            return Integer.sum(a, b);
        });
        assertThat(sum).isNotEqualTo(15);
    }

    @Test
    void parallelStreamSplitSources2() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(0, (a, b) -> {
            System.out.println(a + " + " + b + ": " + Thread.currentThread().getName());
            return Integer.sum(a, b);
        }) + 5;
        assertThat(sum).isEqualTo(15);
    }

    @Test
    void forkJoinPool() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool customThreadPool = new ForkJoinPool();
        int sum;
        try {
            sum = customThreadPool
                    .submit(() -> listOfNumbers.parallelStream()
                            .reduce(0, (a, b) -> {
                                System.out.println(a + " + " + b + ": " + Thread.currentThread().getName());
                                return Integer.sum(a, b);
                            }))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        customThreadPool.shutdown();
        assertThat(sum).isEqualTo(10);
    }

    @Test
    void forkJoinPoolWithSlowTask() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        customThreadPool.execute(() -> listOfNumbers.parallelStream().forEach(integer -> slowTask()));
        customThreadPool.shutdown();

        try { //끝나고 나서 결과를 계속 출력하기 위한 sleep
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void slowTask() {
        try {
            System.out.println("Task Start " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Task Finished " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
