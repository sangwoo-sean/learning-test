package learning.tester.quartz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayGroundService {

    private final SchedulerService scheduler;

    public void runHelloWorldJob() {
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRepeatIntervalMs(1000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My Callback Data");

        scheduler.schedule(MyJob.class, info);
    }
}
