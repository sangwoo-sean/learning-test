package learning.tester.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfo timerInfo = (TimerInfo) jobDataMap.get(MyJob.class.getSimpleName());
        log.info(timerInfo.getCallbackData());
    }
}
