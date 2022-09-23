package learning.tester.quartz;

import lombok.Data;

@Data
public class TimerInfo {
    private int totalFireCount;
    private boolean runForever;
    private long repeatIntervalMs;
    private long InitialOffsetMs;
    private String callbackData;
}
